package com.example.hotelBooking.service.impl;

import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;
import com.example.hotelBooking.entity.RoomEntity;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.service.BookingService;
import com.example.hotelBooking.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final RoomService roomService;
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(RoomService roomService, BookingRepository bookingRepository){
        this.roomService = roomService;
        this.bookingRepository = bookingRepository;
    }
    @Override
    public BookingResponse createBooking(String userEmail, LocalDate start, LocalDate end) {
        RoomEntity room = roomService.findAvailableRoom(start, end)
                .orElseThrow(() -> new RuntimeException("No rooms available for selected dates"));

        BookingEntity booking = new BookingEntity();
        booking.setRoomId(room.getId());
        booking.setUserEmail(userEmail);
        booking.setStartDate(start);
        booking.setEndDate(end);
        bookingRepository.save(booking);
        return new BookingResponse();
    }

    @Override
    public void cancelBooking(int id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }
}

package com.example.hotelBooking.service.impl;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;
import com.example.hotelBooking.entity.RoomEntity;
import com.example.hotelBooking.exceptions.RoomNotAvailableException;
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
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        RoomEntity room = roomService.findAvailableRoom(bookingRequest.getStartDate(), bookingRequest.getEndDate())
                .orElseThrow(() -> new RoomNotAvailableException("No rooms available for selected dates"));

        BookingEntity booking = new BookingEntity();
        booking.setRoomId(room.getId());
        booking.setName(bookingRequest.getName());
        booking.setPhoneNumber(bookingRequest.getPhoneNumber());
        booking.setUserEmail(bookingRequest.getUserEmail());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        bookingRepository.save(booking);
        return new BookingResponse(booking.getBookingId(), booking.getRoomId(), booking.getName());
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

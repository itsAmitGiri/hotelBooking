package com.example.hotelBooking.service.impl;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;
import com.example.hotelBooking.entity.RoomEntity;
import com.example.hotelBooking.exceptions.BookingNotFoundException;
import com.example.hotelBooking.exceptions.RoomNotAvailableException;
import com.example.hotelBooking.mapper.BookingMapper;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.service.BookingService;
import com.example.hotelBooking.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final RoomService roomService;
    private final BookingRepository bookingRepository;
    private final BookingMapper mapper;

    public BookingServiceImpl(RoomService roomService, BookingRepository bookingRepository, BookingMapper mapper){
        this.roomService = roomService;
        this.bookingRepository = bookingRepository;
        this.mapper = mapper;
    }
    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        RoomEntity room = roomService.findAvailableRoom(bookingRequest.getStartDate(), bookingRequest.getEndDate())
                .orElseThrow(() -> new RoomNotAvailableException("No rooms available for selected dates"));

        BookingEntity booking = mapper.toEntity(bookingRequest);
        bookingRepository.save(booking);
        return mapper.toResponse(booking);
    }

    @Override
    public void cancelBooking(int id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        List<BookingEntity> bookings = bookingRepository.findAll();
        return bookings.stream()
            .map(booking -> new BookingResponse(booking.getBookingId(), booking.getRoomId(), booking.getName()))
            .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingByBookingId(int id)
    {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        if (booking.isEmpty()){
            throw new BookingNotFoundException("Booking does not exist with booking Id : " +id);
        }

        return mapper.toResponse(booking.get());
    }

    @Override
    public List<BookingResponse> getBookingsByName(String name)
    {
        List<BookingEntity> bookings = bookingRepository.findByName(name);
        if (bookings.isEmpty()){
            throw new BookingNotFoundException("Booking does not exist with name : " +name);
        }
        return bookings.stream()
            .map(mapper::toResponse)
            .collect(Collectors.toList());
    }

    @Override
    public BookingResponse updateBooking(int id, BookingRequest request)
    {
        Optional<BookingEntity> booking = bookingRepository.findById(id);
        if (booking.isEmpty()){
            throw new BookingNotFoundException("Booking does not exist with booking Id : " +id);
        }

        booking.get().setName(request.getName());
        booking.get().setPhoneNumber(request.getPhoneNumber());
        booking.get().setUserEmail(request.getUserEmail());
        booking.get().setStartDate(request.getStartDate());
        booking.get().setEndDate(request.getEndDate());
        bookingRepository.save(booking.get());
        return mapper.toResponse(booking.get());

    }
}

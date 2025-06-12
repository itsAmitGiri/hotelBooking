package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
    void cancelBooking(int id);
    List<BookingEntity> getAllBookings();
}

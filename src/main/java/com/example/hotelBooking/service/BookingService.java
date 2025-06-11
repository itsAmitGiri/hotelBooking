package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingResponse createBooking(String userEmail, LocalDate start, LocalDate end);
    void cancelBooking(int id);
    List<BookingEntity> getAllBookings();
}

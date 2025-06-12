package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);
    void cancelBooking(int id);
    List<BookingResponse> getAllBookings();
    BookingResponse getBookingByBookingId(int id);
    List<BookingResponse> getBookingsByName(String name);
    BookingResponse updateBooking(int id, BookingRequest request);

}

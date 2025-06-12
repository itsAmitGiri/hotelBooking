package com.example.hotelBooking.controller;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;
import com.example.hotelBooking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel-booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookRoom(@RequestBody BookingRequest request) {
        BookingResponse booking = bookingService.createBooking(request);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable int id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<BookingEntity> getAll() {
        return bookingService.getAllBookings();
    }
}

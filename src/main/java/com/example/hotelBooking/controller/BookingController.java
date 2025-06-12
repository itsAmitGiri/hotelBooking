package com.example.hotelBooking.controller;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/booking/create")
    public ResponseEntity<BookingResponse> bookRoom(@Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable int id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bookings")
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable int id) {
        BookingResponse booking = bookingService.getBookingByBookingId(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookingResponse>> searchBookings(@RequestParam String name) {
        List<BookingResponse> bookings = bookingService.getBookingsByName(name);
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/booking/update/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable int id, @Valid @RequestBody BookingRequest request) {
        BookingResponse response = bookingService.updateBooking(id, request);
        return ResponseEntity.ok(response);
    }
}

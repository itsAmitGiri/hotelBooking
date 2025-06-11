package com.example.hotelBooking.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    private String name;
    private String phoneNumber;
    private String userEmail;
    private LocalDate startDate;
    private LocalDate endDate;
}

package com.example.hotelBooking.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {
    @NotNull
    private String name;
    @NotNull
    @Size(min = 10, max = 11)
    private String phoneNumber;
    @Email
    private String userEmail;
    @FutureOrPresent
    private LocalDate startDate;
    @Future
    private LocalDate endDate;
}

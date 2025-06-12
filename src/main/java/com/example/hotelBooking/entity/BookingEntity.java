package com.example.hotelBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "booking_table")
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @NotNull
    private int room;

    @NotBlank
    private String name;

    @NotNull
    @Pattern(regexp = "^[0-9]{10}$")
    private String PhoneNumber;

    @Email
    @NotNull
    private String userEmail;

    @NotNull
    @FutureOrPresent
    private LocalDate startDate;

    @NotNull
    @Future
    private LocalDate endDate;
}

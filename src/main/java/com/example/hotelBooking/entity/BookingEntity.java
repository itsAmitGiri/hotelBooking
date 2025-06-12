package com.example.hotelBooking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    private String name;
    @NotNull
    @Size(min = 10, max = 11)
    private String PhoneNumber;
    @Email
    private String userEmail;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}

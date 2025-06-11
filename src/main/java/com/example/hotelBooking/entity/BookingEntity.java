package com.example.hotelBooking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "booking_table")
@Data
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    private int roomId;
    private String name;
    private String PhoneNumber;
    private String userEmail;
    private LocalDate startDate;
    private LocalDate endDate;
}

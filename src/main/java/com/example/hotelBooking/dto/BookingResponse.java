package com.example.hotelBooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponse {
    private int bookingId;
    private int roomId;
    private String name;
}

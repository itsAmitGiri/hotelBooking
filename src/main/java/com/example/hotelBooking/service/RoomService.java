package com.example.hotelBooking.service;

import com.example.hotelBooking.entity.RoomEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface RoomService {
    Optional<RoomEntity> findAvailableRoom(LocalDate start, LocalDate end);
}

package com.example.hotelBooking.service.impl;

import com.example.hotelBooking.entity.BookingEntity;
import com.example.hotelBooking.entity.RoomEntity;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.repository.RoomRepository;
import com.example.hotelBooking.service.RoomService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository){
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }
    @Override
    public Optional<RoomEntity> findAvailableRoom(LocalDate start, LocalDate end) {
        List<RoomEntity> rooms = roomRepository.findAll();
        for (RoomEntity room : rooms) {
            List<BookingEntity> bookings = bookingRepository.findByRoomId(room.getId());
            boolean isAvailable = bookings.stream().noneMatch(b ->
                    !(b.getEndDate().isBefore(start) || b.getStartDate().isAfter(end))
            );
            if (isAvailable) return Optional.of(room);
        }
        return Optional.empty();
    }
}

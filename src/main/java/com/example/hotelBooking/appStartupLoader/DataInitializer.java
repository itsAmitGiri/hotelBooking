package com.example.hotelBooking.appStartupLoader;

import com.example.hotelBooking.entity.RoomEntity;
import com.example.hotelBooking.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoomRepository roomRepository;

    public DataInitializer(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    @Override
    public void run(String... args) {
        for (int i = 1; i <= 10; i++) {
            int capacity = (i <= 5) ? 2 : 3;
            roomRepository.save(new RoomEntity(i, capacity));
        }
    }
}

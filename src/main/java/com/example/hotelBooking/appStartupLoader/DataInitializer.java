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
    public void run(String... args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            roomRepository.save(new RoomEntity(i, 2));
        }
        for (int i = 1; i <= 5; i++) {
            roomRepository.save(new RoomEntity(i, 3));
        }
    }
}

package com.example.hotelBooking.repository;

import com.example.hotelBooking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    List<BookingEntity> findByRoomId(int roomId);
    List<BookingEntity> findByName(String name);
}

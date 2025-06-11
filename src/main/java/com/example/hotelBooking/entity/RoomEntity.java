package com.example.hotelBooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room_table")
@Data
@NoArgsConstructor
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roomNumber;

    private int capacity;

    public RoomEntity(int roomNumber, int capacity){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

}

package com.example.hotelBooking.mapper;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingEntity toEntity(BookingRequest request) {
        BookingEntity entity = new BookingEntity();
        entity.setName(request.getName());
        entity.setPhoneNumber(request.getPhoneNumber());
        entity.setUserEmail(request.getUserEmail());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        return entity;
    }

    public BookingResponse toResponse(BookingEntity entity) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(entity.getBookingId());
        response.setRoomId(entity.getRoomId());
        response.setName(entity.getName());
        return response;
    }
}

package com.example.hotelBooking.exceptions.exceptionHandler;

import com.example.hotelBooking.exceptions.BookingNotFoundException;
import com.example.hotelBooking.exceptions.RoomNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler
{
  @ExceptionHandler(RoomNotAvailableException.class)
  public ResponseEntity<Map<Object, Object>> handleRoomNotAvailableException(Exception e, WebRequest request){
    Map<Object, Object> response = new HashMap<>();
    response.put("timestamp", Instant.now());
    response.put("message", e.getMessage());
    response.put("description", request.getDescription(false));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(BookingNotFoundException.class)
  public ResponseEntity<Map<Object, Object>> handleBookingNotFoundException(Exception e, WebRequest request){
    Map<Object, Object> response = new HashMap<>();
    response.put("timestamp", Instant.now());
    response.put("message", e.getMessage());
    response.put("description", request.getDescription(false));

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}

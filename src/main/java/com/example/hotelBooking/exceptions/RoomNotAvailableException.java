package com.example.hotelBooking.exceptions;

public class RoomNotAvailableException extends RuntimeException
{
  public RoomNotAvailableException(String message){
    super(message);
  }
}

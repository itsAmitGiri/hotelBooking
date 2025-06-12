package com.example.hotelBooking.exceptions;

public class BookingNotFoundException extends RuntimeException
{
  public BookingNotFoundException(String message){
    super(message);
  }
}

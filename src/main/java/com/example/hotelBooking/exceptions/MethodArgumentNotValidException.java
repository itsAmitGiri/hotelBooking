package com.example.hotelBooking.exceptions;

public class MethodArgumentNotValidException extends RuntimeException
{
  public MethodArgumentNotValidException(String message){
    super(message);
  }
}

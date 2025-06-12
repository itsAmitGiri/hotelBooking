package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.BookingRequest;
import com.example.hotelBooking.dto.BookingResponse;
import com.example.hotelBooking.entity.BookingEntity;
import com.example.hotelBooking.entity.RoomEntity;
import com.example.hotelBooking.exceptions.BookingNotFoundException;
import com.example.hotelBooking.exceptions.RoomNotAvailableException;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

  @Mock
  private BookingRepository bookingRepository;

  @Mock
  private RoomService roomService;

  @InjectMocks
  private BookingServiceImpl bookingService;

  private RoomEntity mockRoom;
  private BookingEntity mockBooking;

  @BeforeEach
  public void setup() {
    mockRoom = new RoomEntity();
    mockRoom.setId(1);
    mockRoom.setRoomNumber(101);
    mockRoom.setCapacity(2);

    mockBooking = new BookingEntity();
    mockBooking.setBookingId(1);
    mockBooking.setRoom(101);
    mockBooking.setName("amit");
    mockBooking.setPhoneNumber("1234567890");
    mockBooking.setUserEmail("user@example.com");
    mockBooking.setStartDate(LocalDate.now().plusDays(1));
    mockBooking.setEndDate(LocalDate.now().plusDays(3));
  }

  @Test
  public void testCreateBooking_Success() {
    when(roomService.findAvailableRoom(any(), any())).thenReturn(Optional.of(mockRoom));
    when(bookingRepository.save(any())).thenReturn(mockBooking);

    BookingRequest request = new BookingRequest();
    request.setName("amit");
    request.setPhoneNumber("1234567890");
    request.setUserEmail("user@example.com");
    request.setStartDate(LocalDate.now().plusDays(1));
    request.setEndDate(LocalDate.now().plusDays(3));

    BookingResponse response = bookingService.createBooking(request);

    assertNotNull(response);
    assertEquals("amit", response.getName());
    verify(bookingRepository, times(1)).save(any());
  }

  @Test
  public void testCreateBooking_NoRoomAvailable() {
    when(roomService.findAvailableRoom(any(), any())).thenReturn(Optional.empty());

    BookingRequest request = new BookingRequest();
    request.setUserEmail("extra@mail.com");
    request.setStartDate(LocalDate.now().plusDays(5));
    request.setEndDate(LocalDate.now().plusDays(6));

    assertThrows(RoomNotAvailableException.class, () -> bookingService.createBooking(request));
    verify(bookingRepository, never()).save(any());
  }

  @Test
  public void testCancelBooking() {
    doNothing().when(bookingRepository).deleteById(any());

    bookingService.cancelBooking(1);

    verify(bookingRepository, times(1)).deleteById(1);
  }

  @Test
  public void testGetAllBookings() {
    List<BookingEntity> mockList = new ArrayList<>();
    mockList.add(mockBooking);
    when(bookingRepository.findAll()).thenReturn(mockList);

    List<BookingResponse> bookings = bookingService.getAllBookings();

    assertEquals(1, bookings.size());
    assertEquals("amit", bookings.get(0).getName());
    verify(bookingRepository, times(1)).findAll();
  }

  @Test
  public void testGetBookingById_Success() {
    when(bookingRepository.findById(1)).thenReturn(Optional.of(mockBooking));
    BookingResponse response = bookingService.getBookingByBookingId(1);

    assertEquals(1, response.getBookingId());
    assertEquals(101, response.getRoomId());
    assertEquals("amit", response.getName());
  }

  @Test
  public void testGetBookingById_NotFound() {
    when(bookingRepository.findById(2)).thenReturn(Optional.empty());
    assertThrows(BookingNotFoundException.class, () -> bookingService.getBookingByBookingId(2));
  }

  @Test
  public void testGetBookingsByName_Success() {
    when(bookingRepository.findByName("amit")).thenReturn(List.of(mockBooking));
    List<BookingResponse> responses = bookingService.getBookingsByName("amit");

    assertEquals(1, responses.size());
    assertEquals("amit", responses.get(0).getName());
  }

  @Test
  public void testGetBookingsByName_NotFound() {
    when(bookingRepository.findByName("NonExistent"))
        .thenReturn(List.of());

    assertThrows(BookingNotFoundException.class,
        () -> bookingService.getBookingsByName("NonExistent"));
  }

  @Test
  public void testUpdateBooking_Success() {
    BookingRequest request = new BookingRequest();
    request.setName("Updated Name");
    request.setPhoneNumber("9876543210");
    request.setUserEmail("updated@example.com");
    request.setStartDate(LocalDate.now().plusDays(2));
    request.setEndDate(LocalDate.now().plusDays(4));

    when(bookingRepository.findById(1)).thenReturn(Optional.of(mockBooking));
    when(bookingRepository.save(any())).thenReturn(mockBooking);

    BookingResponse response = bookingService.updateBooking(1, request);

    assertEquals("Updated Name", response.getName());
  }

  @Test
  public void testUpdateBooking_NotFound() {
    BookingRequest request = new BookingRequest();
    when(bookingRepository.findById(999)).thenReturn(Optional.empty());

    assertThrows(BookingNotFoundException.class, () -> bookingService.updateBooking(999, request));
  }
}


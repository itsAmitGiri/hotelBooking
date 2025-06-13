# Hotel Room Booking API

A Spring Boot-based REST API for hotel room booking, allowing users to search, create, update, and cancel bookings.


## ✨ Features

Book a room between a start and end date

Search bookings by name or booking ID

Update existing bookings

Cancel bookings

Input validation with informative error messages

Global exception handling


## 📁 Tech Stack
 
Java 17 (Note: Although Java 11 was specified, this project uses Java 17 as required by Spring Boot 3.x. However, no Java 17-specific features are used, so the code remains fully compatible with Java 11 in terms of language constructs.)

Spring Boot 3.5.0

Spring Data JPA

H2 Database (in-memory)

Spring Validation (Jakarta Bean Validation)

JUnit & Mockito


## ⚖️ API Endpoints
### default URI will looks like http://localhost:8080/hotel-booking

| Method | Endpoint                | Description             |
|--------|-------------------------|-------------------------|
| POST   | `/booking/create`       | Create a new booking    |
| GET    | `/booking/{id}`         | Get booking by ID       |
| GET    | `/search?name=xyz`      | Search bookings by name |
| GET    | `/bookings`             | Get all bookings        |
| PUT    | `/booking/update/{id}`  | Update a booking        |
| DELETE | `/booking/cancel/{id}`  | Cancel a booking        |


🔍 Sample JSON: Create Booking/ Update Booking
```json
{
  "name": "Amit Giri",
  "phoneNumber": "9876543210",
  "userEmail": "amit@example.com",
  "startDate": "2025-06-15",
  "endDate": "2025-06-17"
}


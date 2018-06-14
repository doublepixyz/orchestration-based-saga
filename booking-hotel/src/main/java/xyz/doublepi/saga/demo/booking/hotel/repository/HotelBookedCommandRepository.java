package xyz.doublepi.saga.demo.booking.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.doublepi.saga.demo.booking.hotel.model.BookingHotel;

public interface HotelBookedCommandRepository extends JpaRepository<BookingHotel, Long> {
}

package xyz.doublepi.saga.demo.booking.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.doublepi.saga.demo.booking.car.model.BookingCar;

public interface CarBookedCommandRepository extends JpaRepository<BookingCar, Long> {
}

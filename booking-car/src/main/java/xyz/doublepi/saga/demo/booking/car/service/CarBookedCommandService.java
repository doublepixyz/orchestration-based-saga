/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.doublepi.saga.demo.booking.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import xyz.doublepi.saga.demo.booking.car.model.BookingCar;
import xyz.doublepi.saga.demo.booking.car.model.CarBookedEvent;
import xyz.doublepi.saga.demo.booking.car.model.HotelBookedCompensationEvent;
import xyz.doublepi.saga.demo.booking.car.repository.CarBookedCommandRepository;

/**
 *
 * @author pi314
 */
@Service
@Slf4j
public class CarBookedCommandService {

    @Autowired
    private CarBookedCommandRepository carBookedCommandRepository;

    public BookingCar save(CarBookedEvent carBookedEvent) {
        BookingCar bookingCar = BookingCar.builder()
                .userName(carBookedEvent.getUserName())
                .carCount(carBookedEvent.getCarCount())
                .status(BookingCar.STATUS_BOOKED)
                .build();

        log.info("Saved as  BookingCar: {}", bookingCar);
        // save
        return carBookedCommandRepository.save(bookingCar);
    }

    public BookingCar process(HotelBookedCompensationEvent hotelBookedCompensationEvent) {
        BookingCar bookingCar = BookingCar.builder()
                .userName(hotelBookedCompensationEvent.getUserName())
                .carCount(hotelBookedCompensationEvent.getRoomCount())
                .status(BookingCar.STATUS_CANCELED)
                .cancelReason(hotelBookedCompensationEvent.getReason())
                .build();

        log.info("updated  BookingHotel: {}", bookingCar);
        // save
        return carBookedCommandRepository.save(bookingCar);
    }
}

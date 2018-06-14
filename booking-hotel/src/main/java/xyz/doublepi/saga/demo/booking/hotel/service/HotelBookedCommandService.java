/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.doublepi.saga.demo.booking.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import xyz.doublepi.saga.demo.booking.hotel.model.BookingHotel;
import xyz.doublepi.saga.demo.booking.hotel.model.CarBookedCompensationEvent;
import xyz.doublepi.saga.demo.booking.hotel.model.HotelBookedEvent;
import xyz.doublepi.saga.demo.booking.hotel.repository.HotelBookedCommandRepository;

/**
 *
 * @author pi314
 */
@Service
@Slf4j
public class HotelBookedCommandService {

    @Autowired
    private HotelBookedCommandRepository hotelBookedCommandRepository;

    public BookingHotel save(HotelBookedEvent hotelBookedEvent) {
        BookingHotel bookingHotel = BookingHotel.builder()
                .userName(hotelBookedEvent.getUserName())
                .roomCount(hotelBookedEvent.getRoomCount())
                .status(BookingHotel.STATUS_BOOKED)
                .build();

        log.info("Saved as  BookingHotel: {}", bookingHotel);
        // save
        return hotelBookedCommandRepository.save(bookingHotel);
    }
    
    public BookingHotel process(CarBookedCompensationEvent carBookedCompensationEvent) {
        BookingHotel bookingHotel = BookingHotel.builder()
                .userName(carBookedCompensationEvent.getUserName())
                .roomCount(carBookedCompensationEvent.getCarCount())
                .status(BookingHotel.STATUS_CANCELED)
                .cancelReason(carBookedCompensationEvent.getReason())
                .build();

        log.info("updated  BookingHotel: {}", bookingHotel);
        // save
        return hotelBookedCommandRepository.save(bookingHotel);
    }

}

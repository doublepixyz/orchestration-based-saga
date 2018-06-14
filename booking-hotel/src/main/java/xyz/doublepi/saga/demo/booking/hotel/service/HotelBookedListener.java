package xyz.doublepi.saga.demo.booking.hotel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.doublepi.saga.demo.booking.hotel.model.CarBookedCompensationEvent;
import xyz.doublepi.saga.demo.booking.hotel.model.HotelBookedEvent;
import xyz.doublepi.saga.demo.booking.hotel.stream.BookedStreams;

@Component
@Slf4j
public class HotelBookedListener {

    @Autowired
    HotelBookedCommandService hotelBookedCommandService;

    @StreamListener(BookedStreams.INPUT)
    public void handle(@Payload HotelBookedEvent hotelBookedEvent) {
        log.info("Received HotelBookedEvent: {}", hotelBookedEvent);

        hotelBookedCommandService.save(hotelBookedEvent);
    }
    
    @StreamListener(BookedStreams.INPUT_COMPENSATION)
    public void handle(@Payload CarBookedCompensationEvent carBookedCompensationEvent) {
        log.info("Received CarBookedCompensationEvent: {}", carBookedCompensationEvent);

        hotelBookedCommandService.process(carBookedCompensationEvent);
    }
}

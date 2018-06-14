package xyz.doublepi.saga.demo.booking.car.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.doublepi.saga.demo.booking.car.model.CarBookedEvent;
import xyz.doublepi.saga.demo.booking.car.model.HotelBookedCompensationEvent;
import xyz.doublepi.saga.demo.booking.car.stream.BookedStreams;
import static xyz.doublepi.saga.demo.booking.car.stream.BookedStreams.INPUT_COMPENSATION;

@Component
@Slf4j
public class CarBookedListener {

    @Autowired
    CarBookedCommandService carBookedCommandService;

    @StreamListener(BookedStreams.INPUT)
    public void handle(@Payload CarBookedEvent carBookedEvent) {
        log.info("Received CarBookedEvent: {}", carBookedEvent);

        carBookedCommandService.save(carBookedEvent);
    }

    @StreamListener(BookedStreams.INPUT_COMPENSATION)
    public void handle(@Payload HotelBookedCompensationEvent hotelBookedCompensationEvent) {
        log.info("Received HotelBookedCompensationEvent: {}", hotelBookedCompensationEvent);

        carBookedCommandService.process(hotelBookedCompensationEvent);
    }
}

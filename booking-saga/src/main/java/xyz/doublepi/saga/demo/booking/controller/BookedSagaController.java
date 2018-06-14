package xyz.doublepi.saga.demo.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.doublepi.saga.demo.booking.model.BookingDto;
import xyz.doublepi.saga.demo.booking.service.BookingSagaService;

@RestController
public class BookedSagaController {

    @Autowired
    private BookingSagaService bookingSagaService;

    @PostMapping("/api/v1/booking/{userName}/{carCount}/{roomCount}")
    public BookingDto order(@PathVariable String userName,  
            @PathVariable Integer carCount, 
            @PathVariable Integer roomCount) {

        BookingDto bookingDto = BookingDto.builder()
                .userName(userName)
                .carCount(carCount)
                .roomCount(roomCount)
                .build();
        
        bookingSagaService.sendBookedEvent(bookingDto);
        
        return bookingDto;
    }
}

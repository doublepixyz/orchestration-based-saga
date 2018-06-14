package xyz.doublepi.saga.demo.booking.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BookedStreams {

    String OUTPUT_CAR = "booked-car-out";
    String OUTPUT_HOTEL = "booked-hotel-out";
    String OUTPUT_CAR_COMPENSATION = "compensation-car-out";
    String OUTPUT_HOTEL_COMPENSATION = "compensation-hotel-out";
   
    
    @Output(OUTPUT_CAR)
    MessageChannel outboundCarBooked();
    
    @Output(OUTPUT_HOTEL)
    MessageChannel outboundHotelBooked();
    
    @Output(OUTPUT_CAR_COMPENSATION)
    MessageChannel outboundCarCOMPENSATED();
    
    @Output(OUTPUT_HOTEL_COMPENSATION)
    MessageChannel outboundHotelCOMPENSATED();
}

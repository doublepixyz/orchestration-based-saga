package xyz.doublepi.saga.demo.booking.hotel.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BookedStreams {

    String INPUT = "booked-hotel-in";
    String INPUT_COMPENSATION = "compensation-hotel-in";
    
    @Input(INPUT)
    SubscribableChannel inboundHotelBooked();


    @Input(INPUT_COMPENSATION)
    SubscribableChannel inboundHotelCOMPENSATED();

}

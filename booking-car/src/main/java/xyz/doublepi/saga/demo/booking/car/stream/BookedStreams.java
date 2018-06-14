package xyz.doublepi.saga.demo.booking.car.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface BookedStreams {

    String INPUT = "booked-car-in";
    String INPUT_COMPENSATION = "compensation-car-in";
    
    @Input(INPUT)
    SubscribableChannel inboundCarBooked();

    @Input(INPUT_COMPENSATION)
    SubscribableChannel inboundCarCOMPENSATED();
}

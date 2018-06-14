package xyz.doublepi.saga.demo.booking.car.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import xyz.doublepi.saga.demo.booking.car.stream.BookedStreams;

@EnableBinding(BookedStreams.class)
public class StreamsConfig {
}

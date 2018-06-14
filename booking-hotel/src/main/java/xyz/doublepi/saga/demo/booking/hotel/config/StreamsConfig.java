package xyz.doublepi.saga.demo.booking.hotel.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import xyz.doublepi.saga.demo.booking.hotel.stream.BookedStreams;

@EnableBinding(BookedStreams.class)
public class StreamsConfig {
}

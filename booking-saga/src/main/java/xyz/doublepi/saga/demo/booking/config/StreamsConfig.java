package xyz.doublepi.saga.demo.booking.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import xyz.doublepi.saga.demo.booking.stream.BookedStreams;

@EnableBinding(BookedStreams.class)
public class StreamsConfig {
}

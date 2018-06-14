package xyz.doublepi.saga.demo.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingDto {

    private String userName;

    private int carCount;

    private int roomCount;

}

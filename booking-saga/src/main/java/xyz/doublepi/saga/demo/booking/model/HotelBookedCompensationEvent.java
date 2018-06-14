package xyz.doublepi.saga.demo.booking.model;

// lombok autogenerates getters, setters, toString() and a builder (see https://projectlombok.org/):
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class HotelBookedCompensationEvent {

    private String userName;
    private int roomCount;
    private String reason;
}

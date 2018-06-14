package xyz.doublepi.saga.demo.booking.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "BookingHotel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingHotel {

    public static final String STATUS_BOOKED = "BOOKED";
    public static final String STATUS_CANCELED = "CANCELED";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private int roomCount;

    private String status;   // {'BOOKED', 'CAlCELED'}

    private String cancelReason;

}

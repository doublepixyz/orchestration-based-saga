package xyz.doublepi.saga.demo.booking.car.model;

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
@Table(name = "BookingCar")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookingCar {
    public static final String STATUS_BOOKED = "BOOKED";
    public static final String STATUS_CANCELED = "CANCELED";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    private String userName;

    private int carCount;

    private String status;   // {'BOOKED', 'CAlCELED'}

    private String cancelReason;

}

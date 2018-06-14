package xyz.doublepi.saga.demo.booking.service;

import com.sun.applet2.preloader.event.DownloadEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import xyz.doublepi.saga.demo.booking.model.BookingDto;
import xyz.doublepi.saga.demo.booking.model.CarBookedCompensationEvent;
import xyz.doublepi.saga.demo.booking.model.CarBookedEvent;
import xyz.doublepi.saga.demo.booking.model.HotelBookedCompensationEvent;
import xyz.doublepi.saga.demo.booking.model.HotelBookedEvent;
import xyz.doublepi.saga.demo.booking.stream.BookedStreams;

/**
 * This service is the {@code Command} creator, producer of {@link DownloadEvent}. It uses the <em>Message Queue</em> to
 * publish the <i>Event</i>.
 *
 * @author pi314
 */
@Service
@Slf4j
public class BookingSagaService {

    @Autowired
    private BookedStreams bookedStreams;

    public void sendBookedEvent(BookingDto bookingDto) {
        CarBookedEvent bce = CarBookedEvent.builder()
                .userName(bookingDto.getUserName())
                .carCount(bookingDto.getCarCount())
                .build();
        sendCarBookedEvent(bce);

        HotelBookedEvent bhe = HotelBookedEvent.builder()
                .userName(bookingDto.getUserName())
                .roomCount(bookingDto.getRoomCount())
                .build();

        sendHotelBookedEvent(bhe);
    }

    private void sendCarBookedEvent(CarBookedEvent carBookedEvent) {
        log.info("Sending CarBookedEvent {}", carBookedEvent);

        MessageChannel messageChannel = bookedStreams.outboundCarBooked();
        boolean success = messageChannel.send(MessageBuilder
                .withPayload(carBookedEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        if (!success) {
            CarBookedCompensationEvent cbce = CarBookedCompensationEvent.builder()
                    .userName(carBookedEvent.getUserName())
                    .carCount(carBookedEvent.getCarCount())
                    .reason("booking Car failure")
                    .build();

            processCarBookedCompensationEvent(cbce);
        }
    }

    private void sendHotelBookedEvent(final HotelBookedEvent bookingHotelEvent) {
        log.info("Sending BookingHotelEvent {}", bookingHotelEvent);

        MessageChannel messageChannel = bookedStreams.outboundHotelBooked();
        boolean success = messageChannel.send(MessageBuilder
                .withPayload(bookingHotelEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        if (!success) {
            HotelBookedCompensationEvent hbce = HotelBookedCompensationEvent.builder()
                    .userName(bookingHotelEvent.getUserName())
                    .roomCount(bookingHotelEvent.getRoomCount())
                    .reason("booking Hotel failure")
                    .build();
            
            processHotelBookedCompensationEvent(hbce);
        }
    }

    public void processCarBookedCompensationEvent(CarBookedCompensationEvent carBookedCompensationEvent) {
        log.info("Sending CarBookedCompensationEvent {} to {}", carBookedCompensationEvent, BookedStreams.OUTPUT_HOTEL_COMPENSATION);

        MessageChannel messageChannel = bookedStreams.outboundHotelCOMPENSATED();
        messageChannel.send(MessageBuilder
                .withPayload(carBookedCompensationEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

    public void processHotelBookedCompensationEvent(HotelBookedCompensationEvent hotelBookedCompensationEvent) {
        log.info("Sending HotelBookedCompensationEvent {} to {}", hotelBookedCompensationEvent, BookedStreams.OUTPUT_CAR_COMPENSATION);

        MessageChannel messageChannel = bookedStreams.outboundCarCOMPENSATED();
        messageChannel.send(MessageBuilder
                .withPayload(hotelBookedCompensationEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}

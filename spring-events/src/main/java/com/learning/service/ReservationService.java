package com.learning.service;

import com.learning.event.BusEvent;
import com.learning.event.FlyEvent;
import com.learning.event.HotelEvent;
import com.learning.request.ReservationRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ApplicationEventPublisher eventPublisher;

    public ReservationService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Async
    public void create(ReservationRequest reservationRequest) {
        switch (reservationRequest.getReservationType()) {
            case Bus:
                eventPublisher.publishEvent(new BusEvent(reservationRequest));
                break;
            case Fly:
                eventPublisher.publishEvent(new FlyEvent(reservationRequest));
                break;
            case Hotel:
                eventPublisher.publishEvent(new HotelEvent(reservationRequest));
                break;
            default:
                break;
        }
    }
}

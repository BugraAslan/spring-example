package com.learning.event;

import com.learning.request.ReservationRequest;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class BusEvent extends ApplicationEvent {
    public BusEvent(ReservationRequest reservationRequest) {
        super(reservationRequest);
    }
}

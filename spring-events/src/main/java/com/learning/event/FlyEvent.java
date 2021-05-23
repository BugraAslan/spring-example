package com.learning.event;

import com.learning.request.ReservationRequest;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class FlyEvent extends ApplicationEvent {
    public FlyEvent(ReservationRequest reservationRequest) {
        super(reservationRequest);
    }
}

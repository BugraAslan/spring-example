package com.learning.event;

import com.learning.request.ReservationRequest;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class HotelEvent extends ApplicationEvent {
    public HotelEvent(ReservationRequest reservationRequest) {
        super(reservationRequest);
    }
}

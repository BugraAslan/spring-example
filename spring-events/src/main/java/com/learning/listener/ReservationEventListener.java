package com.learning.listener;

import com.learning.event.BusEvent;
import com.learning.event.FlyEvent;
import com.learning.event.HotelEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventListener {

    @EventListener(classes = {com.learning.event.HotelEvent.class})
    public void hotelEventHandler(HotelEvent hotelEvent) {
        System.out.println("Hotel event handler" + hotelEvent.getSource().toString());
    }

    @EventListener(classes = {com.learning.event.BusEvent.class})
    public void busEventHandler(BusEvent busEvent) {
        System.out.println("Bus event handled" + busEvent.getSource().toString());
    }

    @EventListener(classes = {com.learning.event.FlyEvent.class})
    public void flyEventHandler(FlyEvent flyEvent) {
        System.out.println("Fly event handled" + flyEvent.getSource().toString());
    }
}

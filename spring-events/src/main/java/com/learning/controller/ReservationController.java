package com.learning.controller;

import com.learning.request.ReservationRequest;
import com.learning.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    public String createReservation(@RequestBody ReservationRequest reservationRequest) {
        reservationService.create(reservationRequest);
        return "Accept";
    }
}

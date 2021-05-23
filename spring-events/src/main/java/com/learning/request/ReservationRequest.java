package com.learning.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.type.ReservationEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Component
public class ReservationRequest {

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate checkIn;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate checkOut;

    private ReservationEnum reservationType;

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", reservationType=" + reservationType +
                '}';
    }
}

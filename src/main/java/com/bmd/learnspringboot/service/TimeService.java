package com.bmd.learnspringboot.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TimeService {

    public final ZoneId IndianTime = ZoneId.of("Asia/Kolkata");

    public LocalDateTime convertUtcToIst(LocalDateTime utcDateTime) {
        if (utcDateTime == null) {
            throw new IllegalArgumentException("utcDateTime cannot be null");
        }

        ZoneId utcZone = ZoneId.of("UTC");
        ZonedDateTime utcZonedDateTime = utcDateTime.atZone(utcZone);
        ZonedDateTime istZonedDateTime = utcZonedDateTime.withZoneSameInstant(IndianTime);
        return istZonedDateTime.toLocalDateTime();
    }
}
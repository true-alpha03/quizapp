package com.bmd.learnspringboot.service;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

@Service
public class TimeService {
    public final ZoneId IndianTime = ZoneId.of("Asia/Kolkata");

}

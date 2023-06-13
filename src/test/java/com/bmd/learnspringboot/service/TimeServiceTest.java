package com.bmd.learnspringboot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeServiceTest {

    private TimeService timeService;

    @BeforeEach
    public void setUp() {
        timeService = new TimeService();
    }

    @Test
    public void testConvertUtcToIst_edgeCases() {
        // Leap year: February 29
        testUtcToIstConversion(LocalDateTime.of(2020, 2, 29, 23, 0),
                LocalDateTime.of(2020, 3, 1, 4, 30));

        // Non-leap year: February 28 to March 1
        testUtcToIstConversion(LocalDateTime.of(2021, 2, 28, 23, 0),
                LocalDateTime.of(2021, 3, 1, 4, 30));

        // Transition between two different years
        testUtcToIstConversion(LocalDateTime.of(2022, 12, 31, 23, 0),
                LocalDateTime.of(2023, 1, 1, 4, 30));

        // Transition between two different months
        testUtcToIstConversion(LocalDateTime.of(2023, 4, 30, 23, 0),
                LocalDateTime.of(2023, 5, 1, 4, 30));

        // Daylight saving time change (not applicable to IST but could affect other time zones)
        testUtcToIstConversion(LocalDateTime.of(2023, 3, 10, 1, 59),
                LocalDateTime.of(2023, 3, 10, 7, 29));
    }

    @Test
    public void testConvertUtcToIst_additionalCases() {
        // Transition between two different days
        testUtcToIstConversion(LocalDateTime.of(2023, 5, 15, 18, 30),
                LocalDateTime.of(2023, 5, 16, 0, 0));

        // Midnight (start of the day) in UTC
        testUtcToIstConversion(LocalDateTime.of(2023, 9, 1, 0, 0),
                LocalDateTime.of(2023, 9, 1, 5, 30));

        // Midnight (start of the day) in IST
        testUtcToIstConversion(LocalDateTime.of(2023, 11, 6, 19, 0),
                LocalDateTime.of(2023, 11, 7, 0, 30));

        // Last minute of the day in UTC
        testUtcToIstConversion(LocalDateTime.of(2023, 6, 30, 23, 59),
                LocalDateTime.of(2023, 7, 1, 5, 29));

        // Last minute of the day in IST
        testUtcToIstConversion(LocalDateTime.of(2023, 8, 31, 18, 29),
                LocalDateTime.of(2023, 8, 31, 23, 59));

        // Random date and time
        testUtcToIstConversion(LocalDateTime.of(2023, 10, 22, 13, 45),
                LocalDateTime.of(2023, 10, 22, 19, 15));
    }

    private void testUtcToIstConversion(LocalDateTime utcDateTime, LocalDateTime expectedIstDateTime) {
        LocalDateTime actualIstDateTime = timeService.convertUtcToIst(utcDateTime);

        assertEquals(expectedIstDateTime, actualIstDateTime,
                "The converted IST time is incorrect for UTC time: " + utcDateTime);
    }

    @Test
    public void testConvertUtcToIst_nullArgument() {
        assertThrows(IllegalArgumentException.class, () -> timeService.convertUtcToIst(null),
                "Expected IllegalArgumentException when utcDateTime is null");
    }
}
package com.inkhyang.service;

import com.inkhyang.dto.Flight;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.inkhyang.utils.TicketsParser.jsonParser;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightServiceTest {
    public static final String VVO = "VVO";
    public static final String TLV = "TLV";
    private static final List<Flight> flights = jsonParser(new File("src/test/resources/tickets.json"));
    private final FlightService flightService = new FlightService();
    private final List<Flight> flightsByOriginAndDestination = flightService
            .getFlightsByOriginAndDestination(flights, VVO, TLV);


    @Test
    void should_return_avg_price() {
        int expected = 5000;
        int actual = flightService.avgPrice(flightsByOriginAndDestination);
        assertEquals(expected, actual);
    }

    @Test
    void should_return_median_price() {
        int expected = 5000;
        int actual = flightService.medianPrice(flightsByOriginAndDestination);
        assertEquals(expected, actual);
    }

    @Test
    void should_return_diff_between_avg_and_median_price() {
        int expected = 0;
        int actual = Math.abs(flightService.avgPrice(flightsByOriginAndDestination) -
                flightService.medianPrice(flightsByOriginAndDestination));
        assertEquals(expected, actual);
    }

    @Test
    void should_return_carrier_and_flight_duration() {
        String carrier = "S7";
        String expected = "0 day(s) 6 hour(s) 0 minutes";
        String actual = flightService
                .carrierWithMinFlightDuration(flightsByOriginAndDestination).get(carrier);
        assertEquals(expected, actual);
    }
}
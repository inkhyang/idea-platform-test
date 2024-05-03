package com.inkhyang;

import com.inkhyang.dto.Flight;
import com.inkhyang.service.FlightService;

import java.io.File;
import java.util.List;

import static com.inkhyang.utils.TicketsParser.jsonParser;


public class Application {

    public static final String VVO = "VVO";
    public static final String TLV = "TLV";
    public static final String TICKETS_JSON = "src/main/resources/tickets.json";

    public static void main(String[] args) {
        List<Flight> flights = jsonParser(new File(TICKETS_JSON));
        FlightService flightService = new FlightService();
        List<Flight> requiredFlights = flightService
                .getFlightsByOriginAndDestination(flights, VVO, TLV);
        flightService.carrierWithMinFlightDuration(requiredFlights)
                .forEach((key, value) -> System.out.println(key + ": " + value));

        int avgPrice = flightService.avgPrice(requiredFlights);
        int medianPrice = flightService.medianPrice(requiredFlights);
        System.out.println("Difference between average and median price: " + Math.abs(avgPrice - medianPrice));
    }
}
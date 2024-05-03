package com.inkhyang.service;

import com.inkhyang.dto.Flight;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightService {

    public Map<String, String> carrierWithMinFlightDuration(List<Flight> list) {
        Map<String, String> result = new HashMap<>();
        Map<String, List<Flight>> flights = mapFlightsToCarrier(list);
        for (String s : flights.keySet()) {
            String duration = flightDurationToString(flightDuration(minFlightTime(flights.get(s))));
            result.put(s, duration);
        }
        return result;
    }


    public List<Flight> getFlightsByOriginAndDestination(List<Flight> list,
                                                         String origin,
                                                         String destination) {
        return list.stream()
                .filter(x -> origin.equals(x.getOrigin()) &&
                        destination.equals(x.getDestination()))
                .collect(Collectors.toList());
    }

    private Map<String, List<Flight>> mapFlightsToCarrier(List<Flight> list) {
        Map<String, List<Flight>> map = new HashMap<>();
        for (Flight flight : list) {
            String carrier = flight.getCarrier();
            if (!map.containsKey(carrier)) {
                map.put(carrier, new ArrayList<>());
            }
            map.get(carrier).add(flight);
        }
        return map;
    }

    public int avgPrice(List<Flight> list) {
        return (int) list.stream()
                .map(Flight::getPrice)
                .mapToDouble(x -> x)
                .average()
                .orElse(0);
    }

    public int medianPrice(List<Flight> list) {
        list.sort(Comparator.comparing(Flight::getPrice));
        int size = list.size();
        if (size % 2 != 0) {
            return list.get(size / 2).getPrice();
        }
        return (list.get(size / 2).getPrice() + list.get(size / 2 + 1).getPrice()) / 2;
    }

    private Flight minFlightTime(List<Flight> list) {
        return list.stream()
                .min(this::compareFlightsByDuration)
                .orElse(null);
    }

    private int compareFlightsByDuration(Flight f1, Flight f2) {
        return flightDuration(f1).compareTo(flightDuration(f2));
    }

    private String flightDurationToString(Duration duration) {
        return String.format("%d" + " day(s) " + "%d" + " hour(s) " + "%d" + " minutes",
                duration.toDaysPart(),
                duration.toHoursPart(),
                duration.toMinutesPart());
    }

    private Duration flightDuration(Flight flight) {
        return Duration.between(LocalDateTime.of(flight.getDepartureDate(), flight.getDepartureTime()),
                (LocalDateTime.of(flight.getArrivalDate(), flight.getArrivalTime())));
    }
}

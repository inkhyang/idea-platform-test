package com.inkhyang.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String origin;
    @JsonProperty(value = "origin_name")
    private String originName;
    private String destination;
    @JsonProperty(value = "destination_name")
    private String destinationName;
    @JsonProperty(value = "departure_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate departureDate;
    @JsonProperty(value = "departure_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H[H]:mm")
    private LocalTime departureTime;
    @JsonProperty(value = "arrival_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yy")
    private LocalDate arrivalDate;
    @JsonProperty(value = "arrival_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H[H]:mm")
    private LocalTime arrivalTime;
    private String carrier;
    private int stops;
    private int price;
}

package com.inkhyang.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inkhyang.dto.Flight;
import com.inkhyang.dto.Tickets;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class TicketsParser {
    private static final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

    @SneakyThrows
    public static List<Flight> jsonParser(File file) {
        return objectMapper.readValue(file, Tickets.class).getTickets();
    }
}

package com.project.ticket_manager.dto;

import java.time.LocalDateTime;

public record TicketDto(
        Long ticketId,
        String cinemaName,
        LocalDateTime ticketDateTime,
        String hall,
        Integer rowNumber,
        Integer placeNumber) {
}

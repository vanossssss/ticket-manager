package com.project.ticket_manager.dto;

public record CinemaDto(
        Long cinemaId,
        String name,
        String posterUrl,
        String premiere,
        String genre,
        String duration,
        String rating,
        String director,
        String description
) {
}

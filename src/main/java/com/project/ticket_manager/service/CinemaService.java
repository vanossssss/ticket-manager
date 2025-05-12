package com.project.ticket_manager.service;

import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    public Cinema getCinemaById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId).get();
    }
}

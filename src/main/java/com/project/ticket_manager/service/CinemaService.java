package com.project.ticket_manager.service;

import com.project.ticket_manager.Mapper.CinemaMapper;
import com.project.ticket_manager.dto.CinemaDto;
import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.repository.CinemaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    public List<CinemaDto> getAllCinemaDto() {
        return cinemaMapper.toCinemaDtoList(getAll());
    }

    public Cinema getCinemaById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId).get();
    }

    public CinemaDto getCinemaDtoFromCinema(Cinema cinema) {
        return cinemaMapper.toCinemaDto(cinema);
    }

    public CinemaDto getCinemaDtoById(Long cinemaId) {
        return cinemaMapper.toCinemaDto(getCinemaById(cinemaId));
    }
}

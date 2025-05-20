package com.project.ticket_manager.service;

import com.project.ticket_manager.Mapper.CinemaMapper;
import com.project.ticket_manager.Mapper.TicketMapper;
import com.project.ticket_manager.dto.CinemaDto;
import com.project.ticket_manager.dto.TicketDto;
import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.repository.CinemaRepository;
import com.project.ticket_manager.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CinemaService {

    private final CinemaRepository cinemaRepository;
    private final TicketRepository ticketRepository;
    private final CinemaMapper cinemaMapper;
    private final TicketMapper ticketMapper;

    public List<CinemaDto> getAllCinemaDto() {
        return cinemaMapper.toCinemaDtoList(cinemaRepository.findAll());
    }

    public CinemaDto getCinemaDtoById(Long cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).get();
        return cinemaMapper.toCinemaDto(cinema);
    }

    public void getViewCinemas(Model model) {
        List<CinemaDto> cinemaDtoList = getAllCinemaDto();

        model.addAttribute("cinemas", cinemaDtoList);
    }

    public void getViewCinemaDetails(Long id, Model model) {
        CinemaDto cinemaDto = getCinemaDtoById(id);
        List<TicketDto> ticketDtoList = ticketMapper.toTicketDtoList(ticketRepository.findAvailableTicketsByCinemaId(id));

        model.addAttribute("cinema", cinemaDto);
        model.addAttribute("tickets", ticketDtoList);
    }

}

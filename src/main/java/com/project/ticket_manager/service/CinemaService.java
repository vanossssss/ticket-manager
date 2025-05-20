package com.project.ticket_manager.service;

import com.project.ticket_manager.Mapper.CinemaMapper;
import com.project.ticket_manager.Mapper.TicketMapper;
import com.project.ticket_manager.dto.CinemaDto;
import com.project.ticket_manager.dto.TicketDto;
import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.entity.Ticket;
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

    public void getViewCinemaDetails(Long id, Model model) {
        CinemaDto cinemaDto = getCinemaDtoById(id);
        List<TicketDto> ticketDtoList = getTicketList(id);

        model.addAttribute("cinema", cinemaDto);
        model.addAttribute("tickets", ticketDtoList);
     }

     public void getViewCinema(Model model) {
         List<CinemaDto> cinemaDtoList = getAllCinemaDto();

         model.addAttribute("cinemas", cinemaDtoList);
     }

    public List<Cinema> getAll() {
        return cinemaRepository.findAll();
    }

    public List<CinemaDto> getAllCinemaDto() {
        return cinemaMapper.toCinemaDtoList(getAll());
    }

    public Cinema getCinemaById(Long cinemaId) {
        return cinemaRepository.findById(cinemaId).get();
    }

    public CinemaDto getCinemaDtoById(Long cinemaId) {
        return cinemaMapper.toCinemaDto(getCinemaById(cinemaId));
    }

    private List<TicketDto> getTicketList(Long id) {
        List<Ticket> tickets = ticketRepository.findNotBoughtTicketsByCinemaId(id);
        return ticketMapper.toTicketDtoList(tickets);
    }
}

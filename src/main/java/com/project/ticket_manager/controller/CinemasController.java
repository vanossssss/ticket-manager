package com.project.ticket_manager.controller;

import com.project.ticket_manager.dto.CinemaDto;
import com.project.ticket_manager.dto.TicketDto;
import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.service.CinemaService;
import com.project.ticket_manager.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CinemasController {

    private final CinemaService cinemaService;
    private final TicketService ticketService;

    @GetMapping("/cinemas")
    public String viewCinemas(Model model) {
        List<CinemaDto> cinemaDtoList = cinemaService.getAllCinemaDto();
        model.addAttribute("cinemas", cinemaDtoList);
        return "cinemas";
    }

    @GetMapping("/cinemas/{id}")
    public String viewCinemaDetails(@PathVariable Long id, Model model) {
        Cinema cinema = cinemaService.getCinemaById(id);
        CinemaDto cinemaDto = cinemaService.getCinemaDtoFromCinema(cinema);
        List<TicketDto> ticketsDto = ticketService.getNotBoughtTicketDtoByCinema(cinema);

        model.addAttribute("cinema", cinemaDto);
        model.addAttribute("tickets", ticketsDto);

        return "cinema-details";
    }

    @PostMapping("/cinemas/{cinemaId}/buy/{ticketId}")
    public String buyTicket(@PathVariable Long cinemaId,
                      @PathVariable Long ticketId) {
        ticketService.buyTicket(ticketId);

        return "redirect:/cinemas/" + cinemaId;
    }

}

package com.project.ticket_manager.controller;

import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.entity.Ticket;
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
    public String cinemas(Model model) {
        List<Cinema> cinemas = cinemaService.getAll();
        model.addAttribute("cinemas", cinemas);
        return "cinemas";
    }

    @GetMapping("/cinemas/{id}")
    public String cinemas(@PathVariable Long id, Model model) {
        Cinema cinema = cinemaService.getCinemaById(id);
        List<Ticket> tickets = ticketService.getNotBoughtTicketsByCinema(cinema);

        model.addAttribute("cinema", cinema);
        model.addAttribute("tickets", tickets);

        return "cinema-details";
    }

    @PostMapping("/cinemas/{cinemaId}/buy/{ticketId}")
    public String buy(@PathVariable Long cinemaId,
                      @PathVariable Long ticketId) {
        ticketService.buyTicket(ticketId);

        return "redirect:/cinemas/" + cinemaId;
    }

}

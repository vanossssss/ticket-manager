package com.project.ticket_manager.controller;

import com.project.ticket_manager.service.CinemaService;
import com.project.ticket_manager.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cinemas")
@RequiredArgsConstructor
public class CinemasController {

    private final CinemaService cinemaService;
    private final TicketService ticketService;

    @GetMapping
    public String viewCinemas(Model model) {
        cinemaService.getViewCinema(model);

        return "cinemas";
    }

    @GetMapping("/{id}")
    public String viewCinemaDetails(@PathVariable Long id, Model model) {
        cinemaService.getViewCinemaDetails(id, model);

        return "cinema-details";
    }

    @PostMapping("/{cinemaId}/buy/{ticketId}")
    public String buyTicket(@PathVariable Long cinemaId,
                            @PathVariable Long ticketId) {
        ticketService.buyTicket(ticketId);

        return "redirect:/cinemas/" + cinemaId;
    }

}

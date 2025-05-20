package com.project.ticket_manager.controller;

import com.project.ticket_manager.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketsController {

    private final TicketService ticketService;

    @GetMapping
    public String viewTicketsBoughtByUser(Model model) {
       ticketService.getViewTicketsBoughtByUser(model);

        return "tickets";
    }

    @PostMapping("/cancel/{id}")
    public String cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicketById(id);

        return "redirect:/tickets";
    }
}

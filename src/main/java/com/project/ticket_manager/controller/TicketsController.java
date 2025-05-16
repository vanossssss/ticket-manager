package com.project.ticket_manager.controller;

import com.project.ticket_manager.dto.TicketDto;
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
public class TicketsController {

    private final TicketService ticketService;

    @GetMapping("/tickets")
    public String viewTicketsBoughtByUser(Model model) {
        List<TicketDto> ticketDtoList = ticketService.getTicketDtoListByUser();
        model.addAttribute("tickets", ticketDtoList);

        return "tickets";
    }

    @PostMapping("/tickets/cancel/{id}")
    public String cancelTicket(@PathVariable Long id) {
        ticketService.cancelTicketById(id);
        return "redirect:/tickets";
    }
}

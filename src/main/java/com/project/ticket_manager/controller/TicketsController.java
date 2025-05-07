package com.project.ticket_manager.controller;

import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import com.project.ticket_manager.repository.UserRepository;
import com.project.ticket_manager.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TicketsController {

    private final UserRepository userRepository;
    private final TicketService ticketService;

    private final String EMAIL_ATTRIBUTE = "email";

    @GetMapping("/tickets")
    public String tickets(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute(EMAIL_ATTRIBUTE);

        User user = userRepository.findByEmail(email).get();

        List<Ticket> tickets = ticketService.getTicketsByUser(user);
        model.addAttribute("tickets", tickets);

        return "tickets";

    }
}

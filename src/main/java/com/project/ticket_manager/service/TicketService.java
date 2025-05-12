package com.project.ticket_manager.service;

import com.project.ticket_manager.entity.Cinema;
import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import com.project.ticket_manager.repository.TicketRepository;
import com.project.ticket_manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private static final String EMAIL_ATTRIBUTE= "email";

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    public List<Ticket> getNotBoughtTicketsByCinema(Cinema cinema) {
        return ticketRepository.findNotBoughtTicketsByCinemaId(cinema.getCinemaId());
    }

    public void buyTicket(Long ticketId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttributes().get(EMAIL_ATTRIBUTE).toString();
        User user = userRepository.findByEmail(email).get();

        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    public void cancelTicketById(Long id) {
        Optional<Ticket> boughtTicket = ticketRepository.findById(id);

        boughtTicket.ifPresent(ticket -> {
            ticket.setUser(null);
            ticketRepository.save(ticket);
        });
    }
}

package com.project.ticket_manager.service;

import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import com.project.ticket_manager.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    public void cancelTicketById(Long id) {
        Optional<Ticket> boughtTicket = ticketRepository.findById(id);

        boughtTicket.ifPresent(ticket -> {
            ticket.setUser(null);
            ticketRepository.save(ticket);
        });
    }
}

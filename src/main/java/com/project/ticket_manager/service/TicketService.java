package com.project.ticket_manager.service;

import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import com.project.ticket_manager.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }
}

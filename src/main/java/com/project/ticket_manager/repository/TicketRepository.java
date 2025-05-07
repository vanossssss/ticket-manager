package com.project.ticket_manager.repository;

import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser(User user);
}

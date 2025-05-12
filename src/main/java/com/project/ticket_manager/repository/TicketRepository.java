package com.project.ticket_manager.repository;

import com.project.ticket_manager.entity.Ticket;
import com.project.ticket_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser(User user);

    @Modifying
    @Query(
            value = "SELECT * FROM tickets " +
                    "WHERE cinema_id = :cinema_id AND user_id is NULL",
            nativeQuery = true
    )
    List<Ticket> findNotBoughtTicketsByCinemaId(@Param("cinema_id") Long cinemaId);
}

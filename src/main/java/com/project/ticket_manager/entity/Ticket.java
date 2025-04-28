package com.project.ticket_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    private Long ticketId;
    private Long userId;
    private Long cinemaId;
    private LocalDateTime ticketDate;
    private Integer row;
    private Integer place;

}

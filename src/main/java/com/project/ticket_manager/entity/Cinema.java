package com.project.ticket_manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "cinemas")
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    @Id
    private Long cinemaId;
    private String name;
}

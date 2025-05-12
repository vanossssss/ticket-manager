package com.project.ticket_manager.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@Entity
@Table(name = "cinemas")
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    @Column(nullable = false)
    private String name;

    @Column
    private String posterUrl;

    @Column(nullable = false)
    private String premiere;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private String director;

    @Column(nullable = false)
    private String description;
}

package com.project.ticket_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cinemas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

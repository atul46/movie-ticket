package com.superops.tickets.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(MovieTicketId.class)
public class Theater {

    @Id
    @Column(name = "movie_id")
    private String movieId;

    @Id
    @Column(name="movie_name")
    private String movieName;

    @Id
    @Column(name="movie_hall")
    private String hall;

    @Column(name="showtime")
    private String showtime;

    @Column(name="avlble_ticks")
    private String availableTickets;
}

package com.superops.tickets.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "movies_user")
@Data
@EqualsAndHashCode
@IdClass(MovieTicketId.class)
public class MovieTicket {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Id
    @Column(name="movie_id")
    private String movieId;

    @Id
    @Column(name="movie_name")
    private String movieName;

    @Id
    @Column(name="hall")
    private String hall;

    @Column(name="showtime")
    private String showtime;

    @Column(name="tickets_booked")
    private String ticketsBooked;
}

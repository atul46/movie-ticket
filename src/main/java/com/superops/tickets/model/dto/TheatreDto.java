package com.superops.tickets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TheatreDto {


    private String movieId;


    private String movieName;


    private String hall;


    private String showtime;


    private String availableTickets;
}

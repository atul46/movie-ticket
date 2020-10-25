package com.superops.tickets.service;

import com.superops.tickets.model.dto.BookTicketsDto;
import com.superops.tickets.model.dto.TheatreDetailDto;
import com.superops.tickets.model.dto.TheatreDto;
import com.superops.tickets.model.entity.MovieTicket;
import org.springframework.stereotype.Service;


public interface TicketBookingService {

    public TheatreDetailDto getAllMovies();

    public MovieTicket getTicketDetails();

    public MovieTicket bookTicket(BookTicketsDto bookTicketsDto);
}

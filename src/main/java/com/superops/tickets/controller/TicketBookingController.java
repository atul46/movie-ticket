package com.superops.tickets.controller;

import com.superops.tickets.model.dto.BookTicketsDto;
import com.superops.tickets.model.dto.TheatreDetailDto;
import com.superops.tickets.model.dto.TheatreDto;
import com.superops.tickets.service.TicketBookingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/movieticket")
public class TicketBookingController {


    @Autowired
    private TicketBookingService ticketBookingService;

    @GetMapping("/list")
    @ApiOperation(value="to get movie list from database" )
    public ResponseEntity<TheatreDetailDto> getMovieList(){
           return new ResponseEntity(ticketBookingService.getAllMovies(),HttpStatus.OK);
    }


   @PostMapping("/ticket")
   @ApiOperation(value="book movie tickets")
   public ResponseEntity<BookTicketsDto> reserveMovieTickets(@Valid @RequestBody BookTicketsDto bookTicketsDto){

        return new ResponseEntity(ticketBookingService.bookTicket(bookTicketsDto),HttpStatus.CREATED);


    }
}

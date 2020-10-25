package com.superops.tickets.service.impl;

import com.superops.tickets.model.dto.BookTicketsDto;
import com.superops.tickets.model.dto.TheatreDetailDto;
import com.superops.tickets.model.dto.TheatreDto;
import com.superops.tickets.model.entity.MovieTicket;
import com.superops.tickets.model.entity.Theater;
import com.superops.tickets.payments.PaymentService;
import com.superops.tickets.repo.MovieTicketRepo;
import com.superops.tickets.repo.TheatreRepo;
import com.superops.tickets.service.TicketBookingService;
import net.bytebuddy.pool.TypePool;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import springfox.documentation.spi.service.contexts.SecurityContext;


import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class TicketBookingServiceImpl implements TicketBookingService {

    @Autowired
    private  MovieTicketRepo movieTicketRepo;

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PaymentService paymentService;

    @Override
    public TheatreDetailDto getAllMovies() {
        TheatreDetailDto theatreDetailDto=null;
        try{
            List<Theater> theatres=theatreRepo.findAll();
            if(theatres.isEmpty()){
                throw new RuntimeException("Data not found");
            }
            List<TheatreDto> theatreDtos=theatres.stream()
                    .map(theater -> modelMapper.map(theater,TheatreDto.class))
                    .collect(Collectors.toList());

            theatreDetailDto=new TheatreDetailDto();
            theatreDetailDto.setTheatreDetails(theatreDtos);


        }catch(DataAccessException ex){
            throw new RuntimeException("Error occured while fetching data "+ ex.getMessage());
        }
        return theatreDetailDto;
    }

    @Override
    public MovieTicket getTicketDetails() {
        return null;
    }

    @Transactional
    @Cacheable(value = "bookTicket")
    @Override
    public MovieTicket bookTicket(BookTicketsDto bookTicketsDto) {

        MovieTicket movieTicket=null;
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        bookTicketsDto.setUserId(auth.getName());
        Cache cache= RedisCacheManager.builder().build().getCache("bookTicket");
        BookTicketsDto bookTicketsDtoCache=cache.get(new BookTicketsDto(),BookTicketsDto.class);
        if(Optional.of(bookTicketsDto).equals(bookTicketsDtoCache)){
            List<String> listCommon = bookTicketsDtoCache.getSeatsSelected().stream()
                    .filter(obj -> bookTicketsDto.getSeatsSelected().contains(obj))
                    .collect(Collectors.toList());
            if(!listCommon.isEmpty()){
                throw new RuntimeException("Seat already has been booked by another user");
            }

        }

        try{
            String result=paymentService.makePayment();
            if(result.equals("SUCCESS")){

                movieTicket=modelMapper.map(bookTicketsDto,MovieTicket.class);
                movieTicket.setTicketsBooked(String.join(",",bookTicketsDto.getSeatsSelected()));
                movieTicketRepo.save(movieTicket);
            }


        }catch(DataAccessException ex){
              throw new RuntimeException("Exception while saving data");
        }

        return movieTicket;
    }
}

package com.superops.tickets.repo;

import com.superops.tickets.model.entity.MovieTicket;
import com.superops.tickets.model.entity.MovieTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTicketRepo extends JpaRepository<MovieTicket, MovieTicketId> {

}


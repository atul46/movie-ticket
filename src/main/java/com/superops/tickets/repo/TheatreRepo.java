package com.superops.tickets.repo;

import com.superops.tickets.model.entity.MovieTicketId;
import com.superops.tickets.model.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepo extends JpaRepository<Theater, MovieTicketId> {
}

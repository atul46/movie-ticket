package com.superops.tickets.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.util.List;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BookTicketsDto {

    @NotNull
    @NotBlank
    private String userId;

    @NotNull
    @NotBlank
    private String movieId;

    @NotNull
    @NotBlank
    private String movieName;

    @NotNull
    @NotBlank
    private String hall;


    private String showtime;


    private List<String> seatsSelected;

    @JsonIgnore
    private String ticketsBooked;


}

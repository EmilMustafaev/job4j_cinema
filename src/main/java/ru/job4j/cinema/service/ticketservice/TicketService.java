package ru.job4j.cinema.service.ticketservice;

import ru.job4j.cinema.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    Optional<Ticket> buyTicket(int sessionId, int rowNumber, int placeNumber, int userId);
    List<Ticket> findTicketsByUser(int userId);

}

package ru.job4j.cinema.repository.ticketrepository;

import ru.job4j.cinema.model.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> save(Ticket ticket);
    List<Ticket> findByUserId(int userId);
    boolean isPlaceTaken(int sessionId, int rowNumber, int placeNumber);
}

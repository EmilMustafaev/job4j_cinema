package ru.job4j.cinema.service.ticketservice;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.ticket.TicketRepository;
import ru.job4j.cinema.service.filmsessionservice.FilmSessionService;

import java.util.List;
import java.util.Optional;

/*
Этот сервис отвечает за покупку билетов. Он проверяет доступность места перед покупкой.
 */
@Service
public class SimpleTicketService implements TicketService {
    private final TicketRepository ticketRepository;
    private final FilmSessionService sessionService;

    public SimpleTicketService(TicketRepository sql2TicketRepository, FilmSessionService sql2oFilmSessionRepository) {
        this.ticketRepository = sql2TicketRepository;
        this.sessionService = sql2oFilmSessionRepository;
    }


    /*
    Покупка билета
     */
    @Override
    public Optional<Ticket> buyTicket(int sessionId, int rowNumber, int placeNumber, int userId) {
        if (sessionService.isPlaceTaken(sessionId, rowNumber, placeNumber)) {
            return Optional.empty();
        }
        var ticket = new Ticket(0, sessionId, rowNumber, placeNumber, userId);
        return ticketRepository.save(ticket);
    }

    /*
    Найти билеты пользователя
     */
    @Override
    public List<Ticket> findTicketsByUser(int userId) {
        return ticketRepository.findByUserId(userId);
    }
}

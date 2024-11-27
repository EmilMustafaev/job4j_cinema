package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.repository.FilmRepository;
import ru.job4j.cinema.repository.FilmSessionRepository;
import ru.job4j.cinema.repository.HallRepository;
import ru.job4j.cinema.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

/*
Этот сервис работает с киносеансами.
Он отвечает за вывод расписания и деталей конкретного сеанса.
 */
@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository sessionRepository;
    private final FilmRepository filmRepository;
    private final HallRepository hallRepository;
    private final TicketRepository ticketRepository;

    public SimpleFilmSessionService(FilmSessionRepository sql2oFilmSessionRepositoryRepository,
                                    FilmRepository sql2oFilmRepository,
                                    HallRepository sql2oHallRepository,
                                    TicketRepository sql2oTicketRepository) {
        this.sessionRepository = sql2oFilmSessionRepositoryRepository;
        this.filmRepository = sql2oFilmRepository;
        this.hallRepository = sql2oHallRepository;
        this.ticketRepository = sql2oTicketRepository;
    }


/*
Все сеансы с фильмами и залами
 */
    @Override
    public List<FilmSessionDto> findAllSessions() {
        var sessions = sessionRepository.findAll();
        return sessions.stream()
                .map(session -> {
                    var film = filmRepository.findById(session.getFilmId()).orElseThrow();
                    var hall = hallRepository.findById(session.getHallId()).orElseThrow();
                    return new FilmSessionDto(
                            session.getId(),
                            film.getName(),
                            hall.getName(),
                            session.getStartTime(),
                            session.getEndTime(),
                            session.getPrice()
                    );
                })
                .toList();
    }

    /*
    Найти сеанс по ID
     */
    @Override
    public Optional<FilmSessionDto> findSessionById(int id) {
        return sessionRepository.findById(id).map(session -> {
            var film = filmRepository.findById(session.getFilmId()).orElseThrow();
            var hall = hallRepository.findById(session.getHallId()).orElseThrow();
            return new FilmSessionDto(
                    session.getId(),
                    film.getName(),
                    hall.getName(),
                    session.getStartTime(),
                    session.getEndTime(),
                    session.getPrice()
            );
        });
    }

    @Override
    public boolean isPlaceTaken(int sessionId, int rowNumber, int placeNumber) {
        return ticketRepository.isPlaceTaken(sessionId, rowNumber, placeNumber);
    }
}

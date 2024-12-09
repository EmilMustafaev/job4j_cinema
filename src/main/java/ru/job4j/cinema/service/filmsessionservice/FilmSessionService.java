package ru.job4j.cinema.service.filmsessionservice;

import ru.job4j.cinema.dto.FilmSessionDto;
import ru.job4j.cinema.model.FilmSession;

import java.util.List;
import java.util.Optional;

public interface FilmSessionService {
    List<FilmSessionDto> findAllSessions();
    Optional<FilmSessionDto> findSessionById(int id);
    boolean isPlaceTaken(int sessionId, int rowNumber, int placeNumber);

}

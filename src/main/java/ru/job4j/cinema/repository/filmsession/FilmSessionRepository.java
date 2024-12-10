package ru.job4j.cinema.repository.filmsession;

import ru.job4j.cinema.model.FilmSession;

import java.util.List;
import java.util.Optional;

public interface FilmSessionRepository {

    List<FilmSession> findAll();
    Optional<FilmSession> findById(int id);
    List<FilmSession> findByFilmId(int filmId);
    List<FilmSession> findByHallId(int hallId);
}

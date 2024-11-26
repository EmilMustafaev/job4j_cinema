package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FilmRepository {

    List<Film> findAll();
    Optional<Film> findById(int id);
    List<Film> findByGenreId(int genreId);
}

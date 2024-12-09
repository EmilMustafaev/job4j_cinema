package ru.job4j.cinema.service.filmservice;

import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {

    List<FilmDto> findAllFilms();
    Optional<FilmDto> findFilmById(int id);

}

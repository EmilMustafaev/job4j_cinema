package ru.job4j.cinema.service.filmservice;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.repository.filmrepository.FilmRepository;
import ru.job4j.cinema.repository.genrerepository.GenreRepository;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.service.filmservice.FilmService;


import java.util.List;
import java.util.Optional;

/*
Этот сервис отвечает за отображение фильмов.
Он собирает данные о фильмах, включая жанры, для вывода на экран.
 */
@Service
public class SimpleFilmService implements FilmService {
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreRepository sql2oGenreRepository) {
        this.filmRepository = sql2oFilmRepository;
        this.genreRepository = sql2oGenreRepository;
    }

/*
Получить список фильмов с жанрами
 */
    @Override
    public List<FilmDto> findAllFilms() {
        var films = filmRepository.findAll();
        return films.stream()
                .map(film -> {
                    var genre = genreRepository.findById(film.getGenreId())
                            .map(Genre::getName)
                            .orElse("Unknown");
                    return new FilmDto(
                            film.getId(),
                            film.getName(),
                            film.getDescription(),
                            film.getYear(),
                            film.getMinimalAge(),
                            film.getDurationInMinutes(),
                            genre
                    );
                })
                .toList();
    }

    @Override
    public Optional<FilmDto> findFilmById(int id) {
        return filmRepository.findById(id).map(film -> {
            var genre = genreRepository.findById(film.getGenreId())
                    .map(Genre::getName)
                    .orElse("Unknown");
            return new FilmDto(
                    film.getId(),
                    film.getName(),
                    film.getDescription(),
                    film.getYear(),
                    film.getMinimalAge(),
                    film.getDurationInMinutes(),
                    genre
            );
        });
    }
}

package ru.job4j.cinema.service.filmservice;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmDto;
import ru.job4j.cinema.repository.file.FileRepository;
import ru.job4j.cinema.repository.film.FilmRepository;
import ru.job4j.cinema.repository.genre.GenreRepository;
import ru.job4j.cinema.model.Genre;
import ru.job4j.cinema.model.File;


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

    private final FileRepository fileRepository;

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreRepository sql2oGenreRepository,
                             FileRepository sql2oFileRepository) {
        this.filmRepository = sql2oFilmRepository;
        this.genreRepository = sql2oGenreRepository;
        this.fileRepository = sql2oFileRepository;
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
                    var posterPath = fileRepository.findById(film.getFileId())
                            .map(File::getPath)
                            .orElse("");
                    System.out.println("Poster path for film: " + posterPath);
                    return new FilmDto(
                            film.getId(),
                            film.getName(),
                            film.getDescription(),
                            film.getYear(),
                            film.getMinimalAge(),
                            film.getDurationInMinutes(),
                            genre,
                            posterPath,
                            film.getFileId()
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
            var posterPath = fileRepository.findById(film.getFileId())
                    .map(File::getPath)
                    .orElse("");
            return new FilmDto(
                    film.getId(),
                    film.getName(),
                    film.getDescription(),
                    film.getYear(),
                    film.getMinimalAge(),
                    film.getDurationInMinutes(),
                    genre,
                    posterPath,
                    film.getFileId()

            );
        });
    }
}

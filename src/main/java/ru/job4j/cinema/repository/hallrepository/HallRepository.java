package ru.job4j.cinema.repository.hallrepository;

import ru.job4j.cinema.model.Hall;

import java.util.List;
import java.util.Optional;

public interface HallRepository {
    List<Hall> findAll();
    Optional<Hall> findById(int id);
}

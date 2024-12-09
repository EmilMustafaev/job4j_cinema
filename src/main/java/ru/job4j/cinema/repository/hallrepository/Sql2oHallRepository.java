package ru.job4j.cinema.repository.hallrepository;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.hallrepository.HallRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class Sql2oHallRepository implements HallRepository {

    private final Sql2o sql2o;

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /*
    Возвращает список всех залов
     */
    @Override
    public List<Hall> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls");
            return query.setColumnMappings(Hall.COLUMN_MAPPING).executeAndFetch(Hall.class);
        }
    }

    /*
     Возвращает зал по id
     */
    @Override
    public Optional<Hall> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM halls WHERE id = :id");
            query.addParameter("id", id);
            var hall = query.setColumnMappings(Hall.COLUMN_MAPPING).executeAndFetchFirst(Hall.class);
            return Optional.ofNullable(hall);
        }
    }
}


package ru.job4j.cinema.repository;

import ru.job4j.cinema.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findById(int id);
    Optional<User> save(User user);
}

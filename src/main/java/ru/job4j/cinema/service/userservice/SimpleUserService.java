package ru.job4j.cinema.service.userservice;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.user.UserRepository;

import java.util.Optional;

/*
Этот сервис отвечает за управление пользователями.
Он работает с репозиторием пользователей (UserRepository) и выполняет регистрацию, вход и поиск пользователей.
 */
@Service
public class SimpleUserService implements UserService {
    private final UserRepository userRepository;

    public SimpleUserService(UserRepository sql2oUserRepository) {
        this.userRepository = sql2oUserRepository;
    }

    /*
    Регистрация пользователя
     */
    @Override
    public Optional<User> register(User user) {
        return userRepository.save(user);
    }


    /*
    Авторизация пользователя
     */
    @Override
    public Optional<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


    /*
    Найти пользователя по ID
     */
    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}

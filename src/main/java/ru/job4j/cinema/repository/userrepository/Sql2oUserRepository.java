package ru.job4j.cinema.repository.userrepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.repository.ticketrepository.Sql2oTicketRepository;
import ru.job4j.cinema.repository.userrepository.UserRepository;

import java.util.Optional;
@Repository
public class Sql2oUserRepository implements UserRepository {
    private final Sql2o sql2o;

    private static final Logger LOG = LoggerFactory.getLogger(Sql2oTicketRepository.class.getName());

    public Sql2oUserRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

/*
Возвращает пользователя по email(уникальный идентификатор) и паролю.
 */
    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = sql2o.open()) {
            var query = connection
                    .createQuery("SELECT * FROM users WHERE email = :email AND password = :password ");
            query.addParameter("email", email);
            query.addParameter("password", password);
            var user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    /*
     Возвращает пользователя по id
     */
    @Override
    public Optional<User> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM users WHERE id = :id");
            query.addParameter("id", id);
            var user = query.setColumnMappings(User.COLUMN_MAPPING).executeAndFetchFirst(User.class);
            return Optional.ofNullable(user);
        }
    }

    /*
    Сохраняет нового пользователя и возвращает его с заполненным id.
    Возвращает Optional.empty(), если email уже используется.
     */
    @Override
    public Optional<User> save(User user) {
        try (var connection = sql2o.open()) {
            var sql = """
                      INSERT INTO users(email, name, password)
                      VALUES (:email, :name, :password)
                      """;
            var query = connection.createQuery(sql, true)
                    .addParameter("email", user.getEmail())
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            user.setId(generatedId);
            return Optional.of(user);
        } catch (Sql2oException exception) {
            LOG.error("Пользователь с такой почтой уже существует!");
        }
        return Optional.empty();
    }
}

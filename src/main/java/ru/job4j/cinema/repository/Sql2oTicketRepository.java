package ru.job4j.cinema.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.model.Ticket;

import java.util.List;
import java.util.Optional;
@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    private static final Logger LOG = LoggerFactory.getLogger(Sql2oTicketRepository.class.getName());

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /*
    Сохраняет новый билет. Возвращает Optional.empty(), если место уже занято.
     */
    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var sql = """
                      INSERT INTO tickets(session_id, row_number, place_number, user_id)
                      VALUES (:filmSessionId, :rowNumber, :placeNumber, :userId)
                      """;
            var query = connection.createQuery(sql, true)
                    .addParameter("filmSessionId", ticket.getFilmSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        } catch (Sql2oException exception) {
            LOG.error("Место уже занято, купите другой билет!");
        }
        return Optional.empty();
    }

    /*
    Возвращает список билетов, купленных пользователем.
     */
    @Override
    public List<Ticket> findByUserId(int userId) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets WHERE user_id = :userId");
            query.addParameter("userId", userId);
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }

    /*
    Проверяет, занято ли место в конкретном сеансе.
     */
    @Override
    public boolean isPlaceTaken(int sessionId, int rowNumber, int placeNumber) {
        try (var connection = sql2o.open()) {
            var sql = """
                      SELECT COUNT(*) > 0
                      FROM tickets
                      WHERE session_id = :sessionId AND row_number = :rowNumber AND place_number = :placeNumber
                      """;
            var query = connection.createQuery(sql)
                    .addParameter("sessionId", sessionId)
                    .addParameter("rowNumber", rowNumber)
                    .addParameter("placeNumber", placeNumber);
            return query.executeScalar(Boolean.class);
        }
    }
}

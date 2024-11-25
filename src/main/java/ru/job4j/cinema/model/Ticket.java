package ru.job4j.cinema.model;

import java.util.Map;
import java.util.Objects;

public class Ticket {
    public static final Map<String, String> COLUMN_MAPPING = Map.of(
            "id", "id",
            "session_id", "filmSessionId",
            "row_number", "rowNumber",
            "place_number", "placeNumber",
            "user_id", "userId"
    );

    private int id;
    private int filmSessionId;
    private int rowNumber;
    private int placeNumber;
    private int userId;

    public Ticket(int id, int filmSessionId, int rowNumber, int placeNumber, int userId) {
        this.id = id;
        this.filmSessionId = filmSessionId;
        this.rowNumber = rowNumber;
        this.placeNumber = placeNumber;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmSessionId() {
        return filmSessionId;
    }

    public void setFilmSessionId(int filmSessionId) {
        this.filmSessionId = filmSessionId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return rowNumber == ticket.rowNumber && placeNumber == ticket.placeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowNumber, placeNumber);
    }
}

package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.service.filmsessionservice.FilmSessionService;

@Controller
@RequestMapping("/sessions")
public class FilmSessionController {

    private final FilmSessionService filmSessionService;

    public FilmSessionController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/schedule")
    public String getAllSessions(Model model) {
        model.addAttribute("sessions", filmSessionService.findAllSessions());
        return "sessions/schedule";
    }

    @GetMapping("/{id}")
    public String getByIdSession(Model model, @PathVariable int id) {
        var sessionOptional = filmSessionService.findSessionById(id);
        if (sessionOptional.isEmpty()) {
            model.addAttribute("message", "Данного сеанса не существует, выберите другой!");
            return "error";
        }
        return "tickets/buy";
    }


}

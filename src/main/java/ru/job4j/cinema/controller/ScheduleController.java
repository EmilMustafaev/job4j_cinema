package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.cinema.service.FilmSessionService;

@Controller
public class ScheduleController {

    private final FilmSessionService filmSessionService;

    public ScheduleController(FilmSessionService filmSessionService) {
        this.filmSessionService = filmSessionService;
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAttribute("sessions", filmSessionService.findAllSessions());
        return "schedule";
    }
}

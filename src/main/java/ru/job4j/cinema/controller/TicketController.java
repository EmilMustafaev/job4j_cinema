package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.FilmSessionService;
import ru.job4j.cinema.service.TicketService;

import java.util.Optional;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/buy")
    public String buyTicket(
            @RequestParam int sessionId,
            @RequestParam int rowNumber,
            @RequestParam int placeNumber,
            @RequestParam int userId,
            Model model
    ) {
        Optional<Ticket> ticketOptional = ticketService.buyTicket(sessionId, rowNumber, placeNumber, userId);
        if (ticketOptional.isEmpty()) {
            model.addAttribute("message", "Не удалось приобрести билет. Место уже занято.");
            return "errors/ticket_error";
        }
        model.addAttribute("ticket", ticketOptional.get());
        return "tickets/ticket_success";
    }


    @GetMapping("/my")
    public String getUserTickets(@RequestParam int userId, Model model) {
        var tickets = ticketService.findTicketsByUser(userId);
        model.addAttribute("tickets", tickets);
        return "tickets/my_tickets";
    }
}

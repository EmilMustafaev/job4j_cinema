package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.dto.TicketDto;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.ticketservice.TicketService;

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
            @RequestBody TicketDto ticketDTO,
            Model model
    ) {
        Optional<Ticket> ticketOptional = ticketService.buyTicket(
                ticketDTO.getSessionId(),
                ticketDTO.getRowNumber(),
                ticketDTO.getPlaceNumber(),
                ticketDTO.getUserId()
        );

        if (ticketOptional.isEmpty()) {
            model.addAttribute("message", "Не удалось приобрести билет. Место уже занято.");
            return "errors/error";
        }

        Ticket ticket = ticketOptional.get();
        ticketDTO.setTicketId(ticket.getId());
        ticketDTO.setSessionId(ticket.getFilmSessionId());
        ticketDTO.setRowNumber(ticket.getRowNumber());
        ticketDTO.setPlaceNumber(ticket.getPlaceNumber());
        ticketDTO.setUserId(ticket.getUserId());

        model.addAttribute("ticket", ticketDTO);
        return "tickets/success";
    }



    @GetMapping("/my")
    public String getUserTickets(@RequestParam int userId, Model model) {
        var tickets = ticketService.findTicketsByUser(userId);
        model.addAttribute("tickets", tickets);
        return "tickets/tickets";
    }
}

package mk.finki.ukim.wp.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/ticketOrder")
public class TicketOrderController {
    @PostMapping
    public String placeOrderSubmit(@RequestParam String selectedMovie,
                              @RequestParam long numTickets,
                              Model model) {
        model.addAttribute("select-movie", selectedMovie);
        model.addAttribute("numTickets", numTickets);

        return "orderConfirmation";
    }
}

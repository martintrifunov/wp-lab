package mk.finki.ukim.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.finki.ukim.wp.lab.model.Discount;
import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.TicketOrder;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.service.DiscountService;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.TicketOrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ticketorder")
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;
    private final DiscountService discountService;

    public TicketOrderController(TicketOrderService ticketOrderService,
                                 MovieService movieService, DiscountService discountService) {
        this.ticketOrderService = ticketOrderService;
        this.movieService = movieService;
        this.discountService = discountService;
    }
//    @PostMapping
//    public String placeOrderSubmit(@RequestParam String selectedMovie,
//                              @RequestParam long numTickets,
//                              Model model) {
//        model.addAttribute("select-movie", selectedMovie);
//        model.addAttribute("numTickets", numTickets);
//
//        return "orderConfirmation";
//    }
    @GetMapping
    public String showTicketOrderForm(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Movie> movies = movieService.listAll();
        model.addAttribute("movies", movies);
        return "ticketOrderForm";
    }

    @GetMapping("/orders-in-time-interval")
    public String getOrdersWithinTimeInterval(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime to,
            Model model) {
        List<TicketOrder> orders = ticketOrderService.getOrdersWithinTimeInterval(from, to);
        model.addAttribute("orders", orders);
        return "filteredOrders";
    }
    @PostMapping
    public String placeOrderSubmit(@RequestParam Long movieId,
                                   @RequestParam Long numTickets,
                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime dateCreated,
                                   @RequestParam double price,
                                   @RequestParam Long discountId,
                                   Model model,
                                   HttpServletRequest req) {
        User username = (User) req.getSession().getAttribute("user");
        Movie movie = movieService.findById(movieId).get();
        Discount discount = discountService.findById(discountId).get();

        TicketOrder ticketOrder = ticketOrderService.placeOrder(username, movie, numTickets, dateCreated, price, discount);
        model.addAttribute("ticketOrder", ticketOrder);
        return "orderConfirmation";
    }
}

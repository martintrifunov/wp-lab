package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.TicketOrder;
import mk.finki.ukim.wp.lab.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderService {
    TicketOrder placeOrder(User user, Movie movie, long numberOfTickets, LocalDateTime dateCreated);
    List<TicketOrder> getOrdersWithinTimeInterval(LocalDateTime from, LocalDateTime to);
}

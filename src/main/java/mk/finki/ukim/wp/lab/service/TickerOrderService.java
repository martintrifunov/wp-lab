package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.TicketOrder;

public interface TickerOrderService {
    TicketOrder placeOrder(String movieTitle, String clientName, String address, long numberOfTickets);
}

package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.TicketOrder;

public interface TicketOrderService {
    TicketOrder placeOrder(String movieTitle, String clientName, String address, long numberOfTickets);
}

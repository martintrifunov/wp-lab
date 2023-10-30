package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.TicketOrder;
import mk.finki.ukim.wp.lab.repository.MovieRepository;
import mk.finki.ukim.wp.lab.repository.TicketOrderRepository;
import mk.finki.ukim.wp.lab.service.TickerOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImpl implements TickerOrderService {
    private final TicketOrderRepository ticketOrderRepository;

    public TicketOrderServiceImpl(TicketOrderRepository ticketOrderRepository) {
        this.ticketOrderRepository = ticketOrderRepository;
    }
    @Override
    public TicketOrder placeOrder(String movieTitle, String clientName, String address, long numberOfTickets) {
        if (movieTitle == null || movieTitle.isEmpty()
                || clientName == null || clientName.isEmpty()
                || address == null || address.isEmpty()
                || numberOfTickets < 0) {
            throw new IllegalArgumentException();
        }

        TicketOrder ticketOrder = new TicketOrder(movieTitle, clientName, address, numberOfTickets);
        return this.ticketOrderRepository.save(ticketOrder);
    }

}

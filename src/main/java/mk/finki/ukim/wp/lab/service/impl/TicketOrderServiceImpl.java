package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.TicketOrder;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.repository.InMemoryTicketOrderRepository;
import mk.finki.ukim.wp.lab.repository.jpa.TicketOrderRepository;
import mk.finki.ukim.wp.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    private final InMemoryTicketOrderRepository ticketOrderRepository;
    private final TicketOrderRepository ticketOrderRepositoryJPA;

    public TicketOrderServiceImpl(InMemoryTicketOrderRepository ticketOrderRepository, TicketOrderRepository ticketOrderRepositoryJPA) {
        this.ticketOrderRepository = ticketOrderRepository;
        this.ticketOrderRepositoryJPA = ticketOrderRepositoryJPA;
    }
    @Override
    public TicketOrder placeOrder(User user, Movie movie, long numberOfTickets, LocalDateTime dateCreated) {
        if (numberOfTickets < 0) {
            throw new IllegalArgumentException();
        }

        TicketOrder ticketOrder = new TicketOrder(user, movie, numberOfTickets, dateCreated);
        return this.ticketOrderRepository.save(ticketOrder);
    }

    @Override
    public List<TicketOrder> getOrdersWithinTimeInterval(LocalDateTime from, LocalDateTime to) {
        return ticketOrderRepositoryJPA.findByDateCreatedBetween(from, to);
    }

}

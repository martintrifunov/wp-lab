package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketOrderRepository {

    public List<TicketOrder> ticketOrderList = new ArrayList<>();

    public TicketOrder save(TicketOrder ticketOrder) {
        if (ticketOrder == null
                || ticketOrder.getMovieTitle().isEmpty()
                || ticketOrder.getClientAddress().isEmpty()
                || ticketOrder.getClientName().isEmpty()
                || ticketOrder.getNumberOfTickets() < 0) {
            throw new IllegalArgumentException();
        }

        ticketOrderList.removeIf(t-> t.getMovieTitle().equals(ticketOrder.getMovieTitle()));
        ticketOrderList.add(ticketOrder);

        return ticketOrder;
    }

}

package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.TicketOrder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryTicketOrderRepository {

    public List<TicketOrder> ticketOrderList = new ArrayList<>();
//    public HashMap<String, Long> moviesWithTickets = new HashMap<String, Long>();
//    public String mostPopularMovie;

    public TicketOrder save(TicketOrder ticketOrder) {
        if (ticketOrder == null
//                || ticketOrder.getMovieTitle().isEmpty()
//                || ticketOrder.getClientAddress().isEmpty()
//                || ticketOrder.getClientName().isEmpty()
                || ticketOrder.getNumberOfTickets() < 0) {
            throw new IllegalArgumentException();
        }

//        ticketOrderList.removeIf(t-> t.getMovieTitle().equals(ticketOrder.getMovieTitle()));

//        if (moviesWithTickets.containsKey(ticketOrder.getMovieTitle())) {
//            long currentValue = moviesWithTickets.get(ticketOrder.getMovieTitle());
//            moviesWithTickets.put(ticketOrder.getMovieTitle(), currentValue + 1);
//        } else {
//            moviesWithTickets.put(ticketOrder.getMovieTitle(), ticketOrder.getNumberOfTickets());
//        }
//        mostPopularMovie = Collections.max(moviesWithTickets.entrySet(), Map.Entry.comparingByValue()).getKey();


        ticketOrderList.add(ticketOrder);

        return ticketOrder;
    }

}

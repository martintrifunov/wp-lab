package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Discount;
import mk.finki.ukim.wp.lab.model.ShoppingCart;
import mk.finki.ukim.wp.lab.model.TicketOrder;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartService {

    List<TicketOrder> listAllTicketsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addMovieToShoppingCart(String username, Long movieId, Long numberOfTickets, LocalDateTime dateCreated);
}

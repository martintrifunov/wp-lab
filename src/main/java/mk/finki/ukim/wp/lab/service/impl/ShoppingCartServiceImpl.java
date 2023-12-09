package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.*;
import mk.finki.ukim.wp.lab.model.enums.ShoppingCartStatus;
import mk.finki.ukim.wp.lab.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.wp.lab.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wp.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.DiscountService;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;
    private final DiscountService discountService;
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   MovieService movieService,
                                   DiscountService discountService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.movieService = movieService;
        this.discountService = discountService;
    }

    @Override
    public List<TicketOrder> listAllTicketsInShoppingCart(Long cartId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(cartId)
                .orElseThrow(() -> new ShoppingCartNotFoundException(cartId));
        return shoppingCart.getTicketOrders();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user= this.userRepository.findByUsername(username)
                .orElseThrow(()->new UserNotFoundException(username));
        return shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(()->{
                    ShoppingCart cart=new ShoppingCart(user);
                    return shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addMovieToShoppingCart(String username, Long movieId, Long numberOfTickets, LocalDateTime dateCreated) {
        ShoppingCart shoppingCart= getActiveShoppingCart(username);
        Movie movie= movieService.findById(movieId)
                .orElseThrow(()->new RuntimeException("Movie not found"));
        TicketOrder ticketOrder = new TicketOrder(shoppingCart.getUser(),movie,numberOfTickets,dateCreated, movie.getPrice(), movie.getDiscount());
        shoppingCart.getTicketOrders().add(ticketOrder);
        return shoppingCartRepository.save(shoppingCart);
    }
}
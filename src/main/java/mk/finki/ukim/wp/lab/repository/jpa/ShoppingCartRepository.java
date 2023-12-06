package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.ShoppingCart;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.enums.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus created);

    Optional<ShoppingCart> findByUserUsernameAndStatus(String username, ShoppingCartStatus created);
}


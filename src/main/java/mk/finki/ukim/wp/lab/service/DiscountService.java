package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Discount;
import mk.finki.ukim.wp.lab.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DiscountService {
    Optional<Discount> findById(Long id);
    Optional<Discount> saveDiscount(String discountName, int percent);
    List<Discount> findAll();
}

package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Discount;
import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;
import mk.finki.ukim.wp.lab.repository.jpa.DiscountRepository;
import mk.finki.ukim.wp.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.wp.lab.service.DiscountService;
import mk.finki.ukim.wp.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }
    @Override
    public Optional<Discount> findById(Long id){
        return discountRepository.findById(id);
    }

    public Optional<Discount> saveDiscount(String discountName, int percent) {
            Discount discount = new Discount(discountName, percent);
            return Optional.of(discountRepository.save(discount));
    }
    @Override
    public List<Discount> findAll() {
        return this.discountRepository.findAll();
    }
}

package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;
import mk.finki.ukim.wp.lab.repository.InMemoryMovieRepository;
import mk.finki.ukim.wp.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.wp.lab.repository.jpa.ProductionRepository;
import mk.finki.ukim.wp.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    public MovieServiceImpl(ProductionRepository productionRepository, MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.productionRepository = productionRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
//        return movieRepository.searchMovies(text);
        return null;
    }

    @Override
    public void increaseNumberOfTicketOrders(String title, long amount) {
//        movieRepository.incrementTicketOrders(title, amount);

    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findById(Long id){
        return movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> saveMovie(String title, String summary, double rating, Long newProduction) {
        Optional<Production> productionOptional = productionRepository.findById(newProduction);
        if (productionOptional.isPresent()) {
            Movie movie = new Movie(title, summary, rating, productionOptional.get());
            return Optional.of(movieRepository.save(movie));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Movie> editMovie(Long id, String title, String summary, double rating, Long production) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(title);
            movie.setSummary(summary);
            movie.setRating(rating);

            Optional<Production> productionOptional = productionRepository.findById(production);
            productionOptional.ifPresent(movie::setProduction);

            return Optional.of(movieRepository.save(movie));
        }
        return Optional.empty();
    }
}

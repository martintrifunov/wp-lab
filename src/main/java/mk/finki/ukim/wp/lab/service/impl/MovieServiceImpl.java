package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;
import mk.finki.ukim.wp.lab.repository.MovieRepository;
import mk.finki.ukim.wp.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public List<Movie> searchMovies(String text) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public void increaseNumberOfTicketOrders(String title, long amount) {
        movieRepository.incrementTicketOrders(title, amount);
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public Movie findById(Long id){
        return movieRepository.findById(id);
    }

    @Override
    public void saveMovie(String movieNewTitle, String movieNewSummary, double movieNewRating, Production newProduction) {
        movieRepository.saveMovie(movieNewTitle, movieNewSummary, movieNewRating, newProduction);
    }

    @Override
    public void editMovie(Long id, String movieNewTitle, String movieNewSummary, double movieNewRating, Production newProduction) {
        movieRepository.editMovie(id, movieNewTitle, movieNewSummary, movieNewRating, newProduction);
    }
}

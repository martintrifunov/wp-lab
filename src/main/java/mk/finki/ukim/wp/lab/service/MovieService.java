package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;

import java.util.List;
import java.util.Optional;


public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    void increaseNumberOfTicketOrders(String title, long amount);
    void deleteById(Long id);
    Optional<Movie> findById(Long id);
    Optional<Movie> saveMovie(String movieNewTitle, String movieNewSummary, double movieNewRating, Long newProduction);
    Optional<Movie> editMovie(Long id, String movieNewTitle, String movieNewSummary, double movieNewRating, Long productionId);
}

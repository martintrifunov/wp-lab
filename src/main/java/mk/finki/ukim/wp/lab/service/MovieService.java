package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;

import java.util.List;


public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    void increaseNumberOfTicketOrders(String title, long amount);
    void deleteById(Long id);
    Movie findById(Long id);
    void saveMovie(String movieNewTitle, String movieNewSummary, double movieNewRating, Production newProduction);
    void editMovie(Long id, String movieNewTitle, String movieNewSummary, double movieNewRating, Production newProduction);
}

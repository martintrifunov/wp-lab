package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Movie;
import java.util.List;


public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
}

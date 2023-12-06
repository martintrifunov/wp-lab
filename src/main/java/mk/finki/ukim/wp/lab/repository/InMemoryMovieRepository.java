package mk.finki.ukim.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryMovieRepository {
    public List<Movie> movieList = null;
    InMemoryProductionRepository production = new InMemoryProductionRepository();

    @PostConstruct
    public void init() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption (1994)", "A tale of hope and friendship as two inmates bond while serving life sentences in Shawshank State Penitentiary.", 9.6, production.findAll().get(1)));
        movieList.add(new Movie("The Godfather (1972)", "A powerful crime drama that explores the dynamics of the Corleone family and their involvement in organized crime.", 9.2, production.findAll().get(2)));
        movieList.add(new Movie("Pulp Fiction (1994)", "A non-linear narrative that weaves together the lives of gangsters, a boxer, and two hitmen in a darkly comedic and violent story.", 8.7, production.findAll().get(3)));
        movieList.add(new Movie("Forrest Gump (1994)", "The remarkable life story of a slow-witted but kind-hearted man who inadvertently influences major historical events.", 8.2, production.findAll().get(4)));
        movieList.add(new Movie("The Dark Knight (2008)", "The Caped Crusader faces off against the Joker in a psychological battle that tests the limits of heroism.", 9.9, production.findAll().get(0)));
        movieList.add(new Movie("The Matrix (1999)", "A mind-bending science fiction thriller that blurs the line between reality and simulation.", 9.9, production.findAll().get(4)));
        movieList.add(new Movie("Inception (2010)", "A group of thieves enters the dreams of others to steal their deepest secrets in this visually stunning and complex film.", 8.4, production.findAll().get(2)));
        movieList.add(new Movie("Schindler's List (1993)", "The true story of a German businessman who saved the lives of over a thousand Polish-Jewish refugees during the Holocaust.", 7.9, production.findAll().get(3)));
        movieList.add(new Movie("The Lord of the Rings: The Fellowship of the Ring (2001)", "The epic journey of a young hobbit, Frodo Baggins, who must destroy a powerful ring to save Middle-earth from evil.", 9.5, production.findAll().get(1)));
        movieList.add(new Movie("Eternal Sunshine of the Spotless Mind (2004)", "A unique love story about a couple who undergo a procedure to erase their memories of each other after a breakup.", 7.8, production.findAll().get(0)));
    }

    public List<Movie> findAll() {
        return movieList;
    }

    public List<Movie> searchMovies(String text) {
        return movieList
                .stream()
                .filter(movie -> movie.getTitle().contains(text) ||
                        movie.getSummary().contains(text))
                .collect(Collectors.toList());
    }

    public void incrementTicketOrders(String title, long amount) {
        long currentNumberOfTicketOrders = movieList.stream().filter(t -> t.getTitle().equals(title)).findFirst().get().getNumberOfTicketOrders();
        movieList.stream().filter(f -> f.getTitle().equals(title)).findFirst().get().setNumberOfTicketOrders(currentNumberOfTicketOrders + amount);
    }

    public void deleteById(Long id) {
        movieList.removeIf(i -> i.getId().equals(id));
    }

    public Movie findById(Long id){
        for (int i = 0; i < movieList.size(); i++){
            if(movieList.get(i).getId().equals(id)){
                return movieList.get(i);
            }
        }
        return null;
    }

    public void saveMovie(String movieNewTitle, String movieNewSummary, double movieNewRating, Production newProduction) {
        movieList.add(new Movie(movieNewTitle, movieNewSummary, movieNewRating, newProduction));
    }

    public void editMovie(Long id, String movieNewTitle, String movieNewSummary, double movieNewRating, Production newProduction) {
        Movie foundMovie = findById(id);
        if(foundMovie != null)
        {
            foundMovie.setTitle(movieNewTitle);
            foundMovie.setSummary(movieNewSummary);
            foundMovie.setRating(movieNewRating);
            foundMovie.setProduction(newProduction);
        }
    }
}

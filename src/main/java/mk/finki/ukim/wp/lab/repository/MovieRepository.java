package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepository {
    public List<Movie> movieList = null;

    public void movieData() {
        movieList = new ArrayList<>();
        movieList.add(new Movie("The Shawshank Redemption (1994)", "A tale of hope and friendship as two inmates bond while serving life sentences in Shawshank State Penitentiary.", 9.6));
        movieList.add(new Movie("The Godfather (1972)", "A powerful crime drama that explores the dynamics of the Corleone family and their involvement in organized crime.", 9.2));
        movieList.add(new Movie("Pulp Fiction (1994)", "A non-linear narrative that weaves together the lives of gangsters, a boxer, and two hitmen in a darkly comedic and violent story.", 8.7));
        movieList.add(new Movie("Forrest Gump (1994)", "The remarkable life story of a slow-witted but kind-hearted man who inadvertently influences major historical events.", 8.2));
        movieList.add(new Movie("The Dark Knight (2008)", "The Caped Crusader faces off against the Joker in a psychological battle that tests the limits of heroism.", 9.9));
        movieList.add(new Movie("The Matrix (1999)", "A mind-bending science fiction thriller that blurs the line between reality and simulation.", 9.9));
        movieList.add(new Movie("Inception (2010)", "A group of thieves enters the dreams of others to steal their deepest secrets in this visually stunning and complex film.", 8.4));
        movieList.add(new Movie("Schindler's List (1993)", "The true story of a German businessman who saved the lives of over a thousand Polish-Jewish refugees during the Holocaust.", 7.9));
        movieList.add(new Movie("The Lord of the Rings: The Fellowship of the Ring (2001)", "The epic journey of a young hobbit, Frodo Baggins, who must destroy a powerful ring to save Middle-earth from evil.", 9.5));
        movieList.add(new Movie("Eternal Sunshine of the Spotless Mind (2004)", "A unique love story about a couple who undergo a procedure to erase their memories of each other after a breakup.", 7.8));
    }

    public List<Movie> findAll() {
        movieData();
        return movieList;
    }

    public List<Movie> searchMovies(String text) {
        movieData();
        return movieList
                .stream()
                .filter(movie -> movie.getTitle().contains(text) ||
                        movie.getSummary().contains(text))
                .collect(Collectors.toList());
    }
}

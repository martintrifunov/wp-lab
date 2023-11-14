package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.Production;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    public final MovieService movieService;
    public final ProductionService productionService;

    public MovieController (MovieService movieService, ProductionService productionService) {
        this.movieService = movieService;
        this.productionService = productionService;
    }

    @GetMapping()
    public String getMoviesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.listAll();
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "movies");
        return "listMovies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMoviePage(@PathVariable Long id, Model model){
        Movie movie = movieService.findById(id);
        model.addAttribute("movie",movie);
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions",productions);
        return "editMovie";
    }

    @PostMapping("/edit/{id}")
    public String submitEditedMovie(@PathVariable Long id,
                            @RequestParam String movieTitle,
                            @RequestParam String summary,
                            @RequestParam double rating,
                            @RequestParam Long productionId) {
        movieService.editMovie(id, movieTitle, summary, rating, productionService.findById(productionId));
        return "redirect:/movies";
    }

    @GetMapping("/add")
    public String addMovie(Model  model) {
        List<Production> productions = productionService.findAll();
        model.addAttribute("productions", productions);
        return"addMovie";
    }

    @PostMapping("/add")
    public String submitAddedMovie(@RequestParam String movieTitle,
                                    @RequestParam String movieSummary,
                                    @RequestParam double movieRating,
                                    @RequestParam Long productionId) {
       Production foundProduction = productionService.findById(productionId);

       movieService.saveMovie(movieTitle, movieSummary, movieRating, foundProduction);
       return "redirect:/movies";
    }
}

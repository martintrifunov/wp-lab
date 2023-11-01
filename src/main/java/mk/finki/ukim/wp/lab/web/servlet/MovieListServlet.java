package mk.finki.ukim.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.wp.lab.model.Movie;
import mk.finki.ukim.wp.lab.model.TicketOrder;
import mk.finki.ukim.wp.lab.service.MovieService;
import mk.finki.ukim.wp.lab.service.TicketOrderService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name="movies", urlPatterns = "")
public class MovieListServlet extends HttpServlet {
    private final MovieService movieService;
    private final TicketOrderService ticketOrderService;
    private final SpringTemplateEngine springTemplateEngine;

    public MovieListServlet(MovieService movieService, SpringTemplateEngine springTemplateEngine, TicketOrderService ticketOrderService) {
        this.movieService = movieService;
        this.springTemplateEngine = springTemplateEngine;
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Movie mostPopularMovie = movieService.listAll().get(0);

        for(Movie movie : movieService.listAll()) {
            if(movie.getNumberOfTicketOrders() > mostPopularMovie.getNumberOfTicketOrders()) {
                mostPopularMovie = movie;
            }
        }

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("movies", this.movieService.listAll());
        context.setVariable("mostPopularMovie", mostPopularMovie);

        this.springTemplateEngine.process("listMovies.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("searchMovieSubmit") != null) {
            String searchString = req.getParameter("searchText");
            float searchRating = Float.parseFloat(req.getParameter("searchRating"));

            IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

            List<Movie> matchingMovies = this.movieService.listAll().stream().filter(found->found.getRating() >= searchRating || found.getTitle().toLowerCase().contains(searchString.toLowerCase())).toList();

            WebContext context = new WebContext(webExchange);
            context.setVariable("movies", matchingMovies);

            this.springTemplateEngine.process("listMovies.html", context, resp.getWriter());
        }

        if(req.getParameter("placeOrderSubmit") != null) {
            String movieName = req.getParameter("select-movie");
            Movie movie = movieService.searchMovies(movieName).stream().findFirst().get();
            long numberOfTickets = Long.parseLong(req.getParameter("numTickets"));

//            TicketOrder ticketOrder = new TicketOrder(movieName, req.getHeader("User-Agent"), req.getRemoteAddr(), numberOfTickets);

            movieService.increaseNumberOfTicketOrders(movieName, numberOfTickets);
            req.getSession().setAttribute("ticketOrder", this.ticketOrderService.placeOrder(movieName, req.getHeader("User-Agent"), req.getRemoteAddr(), numberOfTickets));
            resp.sendRedirect("/ticketOrder");
        }
    }
}

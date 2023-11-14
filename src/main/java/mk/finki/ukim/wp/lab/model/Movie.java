package mk.finki.ukim.wp.lab.model;

import lombok.Getter;

@Getter
public class Movie {
    private Long id;
    private String title;
    private String summary;
    private double rating;
    private long numberOfTicketOrders;
    private Production production;

    public Movie(String title, String summary, double rating, Production production) {
        this.id = (long) (Math.random() * 1000);
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumberOfTicketOrders(long numberOfTicketOrders) {
        this.numberOfTicketOrders = numberOfTicketOrders;
    }

    public void setProduction(Production production) {
        this.production = production;
    }
}

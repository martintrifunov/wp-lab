package mk.finki.ukim.wp.lab.model;

import lombok.Getter;

@Getter
public class Movie {
    private String title;
    private String summary;
    private double rating;

    public Movie(String title, String summary, double rating) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
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
}

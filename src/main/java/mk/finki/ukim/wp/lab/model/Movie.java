package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private double rating;
    private long numberOfTicketOrders;
    @ManyToOne
    private Production production;
    private double price;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    Discount discount;

    public Movie(String title, String summary, double rating, Production production, double price, Discount discount) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.price = price;
        this.production = production;
        this.discount = discount;
    }
}

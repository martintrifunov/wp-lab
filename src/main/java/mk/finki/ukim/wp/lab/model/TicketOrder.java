package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name="ticket_order")
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numberOfTickets;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public TicketOrder(User user, Movie movie, long numberOfTickets, LocalDateTime dateCreated) {
        this.movie = movie;
        this.user = user;
        this.dateCreated = dateCreated;
        this.numberOfTickets = numberOfTickets;
    }
}

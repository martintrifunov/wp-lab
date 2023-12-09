package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int percent;
    public Discount(String name, int percent) {
        this.name = name;
        this.percent = percent;
    }
}

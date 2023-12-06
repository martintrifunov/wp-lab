package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ShoppingCart> carts;

    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullname;

    public User(String username, UserFullName fullname, String password, LocalDate dateOfBirth) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}

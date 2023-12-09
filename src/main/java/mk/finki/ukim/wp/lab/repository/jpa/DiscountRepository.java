package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long> {

}
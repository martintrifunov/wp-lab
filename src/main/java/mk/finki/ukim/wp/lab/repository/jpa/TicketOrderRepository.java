package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {
    List<TicketOrder> findByDateCreatedBetween(LocalDateTime from, LocalDateTime to);
}

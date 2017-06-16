package pl.piotrcz.Jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piotrcz.Jpa.Models.Ticket;

import java.util.List;
import java.util.Optional;

/**
 * Created by Piotr Czubkowski on 2017-06-12.
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    Optional<Ticket> findOne(int id);
    List<Ticket> findByAuthor (String author);
    List<Ticket> findByMessageLike (String prefix);
}

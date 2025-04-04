package k25.events.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
    List<Event> findByName(String name);

    List<Event> findByTicketPrice(double price);

    List<Event> findByCategoryId(Long categoryId);

}

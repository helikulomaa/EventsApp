package k25.events.web;

import org.springframework.web.bind.annotation.RestController;

import k25.events.domain.Event;
import k25.events.domain.EventRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = "*") // Salli pyyntö frontendiltä

@RestController
public class EventRestController {

    @Autowired
    private EventRepository repository;

    @RequestMapping(value = "/api/events", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> eventListRest() {
        try {
            List<Event> events = (List<Event>) repository.findAll();
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/events/{id}", method = RequestMethod.GET)
    public Optional<Event> findEventRest(@PathVariable("id") Long eventId) {
        try {
            return repository.findById(eventId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @PostMapping("api/events/")
    public ResponseEntity<Event> saveNewEventRest(@RequestBody @Validated Event event) {
        try {
            System.out.println("Tapahtuma: " + event);
            Event savedEvent = repository.save(event);
            return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/events/{id}", method = RequestMethod.PUT)
    public Event updateEventRest(@PathVariable("id") Long EventId, @RequestBody Event event) {
        return repository.save(event);
    }

    @RequestMapping(value = "api/events/{id}", method = RequestMethod.DELETE)
    public void deleteEventRest(@PathVariable("id") Long eventId) {
        repository.deleteById(eventId);
    }

    @GetMapping("api/events/free")
    public List<Event> getFreeEvents() {
        return repository.findByTicketPrice(0.0);
    }

    @GetMapping("api/events/category/{id}")
    public List<Event> getEventsByCategory(@PathVariable("id") Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

}

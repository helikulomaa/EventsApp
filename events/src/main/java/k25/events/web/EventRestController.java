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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}

package k25.events.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;
import k25.events.domain.CategoryRepository;
import k25.events.domain.Event;
import k25.events.domain.EventRepository;
import k25.events.domain.OrganiserRepository;

@Controller
public class EventController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String returnIndex() {
        return "index";
    }

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CategoryRepository crepository;

    @Autowired
    private OrganiserRepository orepository;

    public EventController(EventRepository repository) {
        this.eventRepository = repository;
    }

    @RequestMapping(value = { "/", "/eventlist" })
    public String bookList(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "eventList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/add")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("categories", crepository.findAll());
        model.addAttribute("organisers", orepository.findAll());
        model.addAttribute("targetGroups", Event.TargetGroup.values());
        return "addevent";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("event") Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", event);
            model.addAttribute("categories", crepository.findAll());
            model.addAttribute("organisers", orepository.findAll());
            model.addAttribute("targetGroups", Event.TargetGroup.values());
            return "addEvent";
        }

        eventRepository.save(event);
        return "redirect:/eventList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteEvent(@PathVariable("id") Long eventId) {
        eventRepository.deleteById(eventId);
        return "redirect:../eventList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable("id") Long eventId, Model model) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + eventId));

        model.addAttribute("event", event);
        model.addAttribute("categories", crepository.findAll());
        model.addAttribute("organisers", orepository.findAll());
        model.addAttribute("targetGroups", Event.TargetGroup.values());
        return "editEvent";
    }

    @PostMapping("/edit")
    public String editEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("event", event);
            model.addAttribute("categories", crepository.findAll());
            model.addAttribute("organisers", orepository.findAll());
            model.addAttribute("targetGroups", Event.TargetGroup.values());
            return "editEvent";
        }
        eventRepository.save(event);
        return "redirect:/eventList";
    }

    @GetMapping("/events/free")
    public String showFreeEvents(Model model) {
        List<Event> freeEvents = eventRepository.findByTicketPrice(0.0);
        model.addAttribute("events", freeEvents);
        return "eventList";
    }

}

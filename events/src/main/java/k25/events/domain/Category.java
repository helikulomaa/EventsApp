package k25.events.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Category name is required")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Event> events;

    // -------------- Constructors ---------------
    public Category() {
    }

    public Category(Long id, @NotEmpty(message = "Category name is required") String name, List<Event> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }

    // -------------- Getters and Setters ---------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    // ----------------- toString() -------------------

    @Override
    public String toString() {
        return "Category [events=" + events + ", id=" + id + ", name=" + name + "]";
    }
}

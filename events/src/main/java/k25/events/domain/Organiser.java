package k25.events.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Organiser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Organiser name is required")
    private String name;
    private String contcatFirstName;
    private String contactLastName;
    private String contactEmail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organiser")
    private List<Event> events;

    @Enumerated(EnumType.STRING)
    private OrganiserType organiserType;

    // -------------- Constructors ---------------

    public Organiser() {
    }

    public Organiser(@NotEmpty(message = "Organiser name is required") String name, String contcatFirstName,
            String contactLastName, String contactEmail, OrganiserType organiserType) {
        this.name = name;
        this.contcatFirstName = contcatFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.organiserType = organiserType;
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

    public String getContcatFirstName() {
        return contcatFirstName;
    }

    public void setContcatFirstName(String contcatFirstName) {
        this.contcatFirstName = contcatFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public OrganiserType getOrganiserType() {
        return organiserType;
    }

    public void setOrganiserType(OrganiserType organiserType) {
        this.organiserType = organiserType;
    }

    // -------------- Enum --------------------------

    public enum OrganiserType {
        COMPANY, INDIVIDUAL, NONPROFIT
    }

    // --------------------ToString--------------------
    @Override
    public String toString() {
        return "Organiser [id=" + id + ", name=" + name + ", contcatFirstName=" + contcatFirstName
                + ", contactLastName=" + contactLastName + ", contactEmail=" + contactEmail + ", events=" + events
                + ", organiserType=" + organiserType + "]";
    }
}

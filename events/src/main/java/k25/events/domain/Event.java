package k25.events.domain;

import java.time.LocalDateTime;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Event name is required")
    private String name;
    private String description;
    private String location;
    private String locationAddress;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean approved;
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;

    @ManyToOne
    @JoinColumn(name = "organiser_id", nullable = false)
    @JsonIgnore
    private Organiser organiser;

    @Enumerated(EnumType.STRING)
    private TargetGroup targetGroup;

    // -------------- Enum --------------------------

    public enum TargetGroup {
        EVERYONE, WOMEN_AND_NON_BINARY, MEN_AND_NON_BINARY, NON_BINARY, ALL_ADULTS, YOUTH, CHILDREN
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser organiser) {
        this.organiser = organiser;
    }

    public TargetGroup getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(TargetGroup targetGroup) {
        this.targetGroup = targetGroup;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    // ------------------ Constructor -------------------

    public Event(Long id, @NotEmpty(message = "Event name is required") String name, String description,
            String location, String locationAddress, LocalDateTime startTime, LocalDateTime endTime, Category category,
            Organiser organiser, TargetGroup targetGroup, Boolean approved, double ticketPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.locationAddress = locationAddress;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.organiser = organiser;
        this.targetGroup = targetGroup;
        this.approved = approved;
        this.ticketPrice = ticketPrice;
    }

    public Event() {
    }

    // ------------------ toString -------------------
    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
                + ", locationAddress=" + locationAddress + ", startTime=" + startTime + ", endTime=" + endTime
                + ", category=" + category + ", organiser=" + organiser + ", targetGroup=" + targetGroup + ", approved="
                + approved + ", ticketPrice=" + ticketPrice + "]";
    }
}

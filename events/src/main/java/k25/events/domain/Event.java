package k25.events.domain;

import java.time.LocalDate;
import java.time.LocalTime;

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
import jakarta.validation.constraints.NotNull;

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
    @NotNull(message = "Event date is required")
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
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
        EVERYONE("Everyone"),
        WOMEN_AND_NON_BINARY("Women and Non-binary"),
        MEN_AND_NON_BINARY("Men and Non-binary"),
        NON_BINARY("Non-binary"),
        ALL_ADULTS("All adults"),
        YOUTH("Youth"),
        CHILDREN("Children");

        private final String displayName;

        TargetGroup(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
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

    public Event(@NotEmpty(message = "Event name is required") String name, String description,
            String location, String locationAddress, LocalDate date, LocalTime startTime, LocalTime endTime,
            Category category,
            Organiser organiser, TargetGroup targetGroup, Boolean approved, double ticketPrice) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.locationAddress = locationAddress;
        this.date = date;
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
                + ", locationAddress=" + locationAddress + ", date=" + date + ", startTime=" + startTime + ", endTime="
                + endTime
                + ", category=" + category + ", organiser=" + organiser + ", targetGroup=" + targetGroup + ", approved="
                + approved + ", ticketPrice=" + ticketPrice + "]";
    }
}

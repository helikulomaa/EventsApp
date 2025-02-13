package k25.events.domain;

import java.time.LocalDateTime;
import java.util.Locale.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
    private double longitude;
    private double latitude;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    private Organiser organiser;

    @ManyToMany
    private TargetGroup targetGroup;

    public Event() {
    }

    public Event(Long id, @NotEmpty(message = "Event name is required") String name, String description,
            String location, String locationAddress, double longitude, double latitude, LocalDateTime startTime,
            LocalDateTime endTime, Category category, Organiser organiser, TargetGroup targetGroup) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.locationAddress = locationAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.organiser = organiser;
        this.targetGroup = targetGroup;
    }

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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

    @Override
    public String toString() {
        return "Event [id=" + id + ", name=" + name + ", description=" + description + ", location=" + location
                + ", locationAddress=" + locationAddress + ", longitude=" + longitude + ", latitude=" + latitude
                + ", startTime=" + startTime + ", endTime=" + endTime + ", category=" + category + ", organiser="
                + organiser + ", targetGroup=" + targetGroup + "]";
    }

}

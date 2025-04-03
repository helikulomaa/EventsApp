package k25.events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import k25.events.domain.Category;
import k25.events.domain.CategoryRepository;
import k25.events.domain.Event;
import k25.events.domain.Event.TargetGroup;
import k25.events.domain.EventRepository;
import k25.events.domain.Organiser;
import k25.events.domain.Organiser.OrganiserType;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTests {

    @Autowired
    private EventRepository eRepository;

    @Autowired
    private CategoryRepository cRepository;

    @Test
    public void findEventsShouldReturnAtLeastOneEvent() throws Exception {
        assertThat(eRepository.count()).isGreaterThan(0);
    }

    @Test
    public void saveEvent() throws Exception {

        Category category = new Category("Kulttuuri");
        Organiser organiser = new Organiser("Testijärjestäjä", "Maija", "Esimerkki", "hello@hello.fi",
                OrganiserType.COMPANY);
        TargetGroup targetGroup = TargetGroup.ALL_ADULTS;

        Event event = new Event(
                "Testi", "Testikuvaus", "Sijainti",
                "Osoite", LocalDate.of(2025, 4, 23),
                LocalTime.of(19, 0), LocalTime.of(21, 0),
                category, organiser, targetGroup, true, 10.0);

        eRepository.save(event);
        assertThat(event.getId()).isNotNull();
    }

    @Test
    public void saveCategory() throws Exception {
        Category category = new Category("Testi");
        cRepository.save(category);
        assertThat(category.getId()).isNotNull();
    }

    @Test
    public void getCorrectCategory() {
        List<Category> categories = cRepository.findByName("Culture");
        assertThat(categories).hasSize(1);
    }

    @Test
    public void deleteEvent() {
        List<Event> events = eRepository.findByName("Kväärinpäin");
        Event event = events.get(0);
        eRepository.delete(event);
        List<Event> newEvents = eRepository.findByName("Kväärinpäin");
        assertThat(newEvents).hasSize(0);
    }

    @Test
    public void deleteCategory() {
        List<Category> categories = cRepository.findByName("Culture");
        Category category = categories.get(0);
        cRepository.delete(category);
        List<Category> newCategories = cRepository.findByName("Culture");
        assertThat(newCategories).hasSize(0);
    }

    @Test
    public void getCorrectData() {
        List<Event> events = eRepository.findByName("Kväärinpäin");
        assertThat(events).hasSize(1);
    }

}

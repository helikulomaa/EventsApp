package k25.events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k25.events.domain.AppUser;
import k25.events.domain.AppUserRepository;
import k25.events.domain.Category;
import k25.events.domain.CategoryRepository;
import k25.events.domain.Event;
import k25.events.domain.Event.TargetGroup;
import k25.events.domain.EventRepository;
import k25.events.domain.Organiser;
import k25.events.domain.OrganiserRepository;
import k25.events.domain.Organiser.OrganiserType;

@SpringBootApplication
public class EventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(EventRepository repository, CategoryRepository crepository,
			OrganiserRepository orepository,
			AppUserRepository urepository) {
		return (args) -> {

			// Create users: admin/admin user/user
			AppUser user1 = new AppUser("user", "$2a$10$EY1L6HC672X2i.1HgFsaheQncKBFJuK5o85KdOwlISXrmHovlX3GO", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$CSwyw8VBNrPKStUVXB1p2e5hgGOgcQUwFXQ4b8CDfc4SILb5Mxd3K",
					"ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			// expample categories, organisers and events have been created using ChatGPT

			// save a couple of categories
			Category category1 = new Category("Nightlife");
			Category category2 = new Category("Social and support groups");
			Category category3 = new Category("Culture");
			Category category4 = new Category("Sports and wellness");

			crepository.save(category1);
			crepository.save(category2);
			crepository.save(category3);
			crepository.save(category4);

			// save a couple of organisers
			Organiser organiser1 = new Organiser(
					"Bar Struts",
					"Essi",
					"Esimerkki",
					"hello@strutsbar.fi",
					OrganiserType.COMPANY);

			Organiser organiser2 = new Organiser(
					"Qoomikot-kollektiivi",
					"Elina",
					"Esimerkillinen",
					"moi@qoomikot.fi",
					OrganiserType.NONPROFIT);

			Organiser organiser3 = new Organiser(
					"Helsinki Pride",
					"Johanna",
					"Järjestäjä",
					"johanna.jarjestaja@pride.fi",
					OrganiserType.NONPROFIT);

			Organiser organiser4 = new Organiser(
					"Tiina testihenkilö",
					"Tiina",
					"Testihenkilö",
					"tiina.testihenkilo@gmail.com",
					OrganiserType.INDIVIDUAL);

			orepository.save(organiser1);
			orepository.save(organiser2);
			orepository.save(organiser3);
			orepository.save(organiser4);

			// save a couple of events

			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

			Event event1 = new Event(
					"Queer Speed Dating",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nisl ultricies lacinia.",
					"Struts Bar",
					"Fleminginkatu 13, Helsinki",
					LocalDateTime.parse("2025-04-23T19:00:00", formatter),
					LocalDateTime.parse("2025-04-23T21:00:00", formatter),
					category2,
					organiser1,
					TargetGroup.WOMEN_AND_NON_BINARY,
					true,
					5.0);

			Event event2 = new Event(
					"Kväärinpäin",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nisl ultricies lacinia.",
					"Mascot Bar & Live Stage",
					"Neljäs linja 2, Helsinki",
					LocalDateTime.parse("2025-04-01T19:00:00", formatter),
					LocalDateTime.parse("2025-04-01T21:00:00", formatter),
					category3,
					organiser2,
					TargetGroup.ALL_ADULTS,
					true,
					10.0);

			Event event3 = new Event(
					"Kahvikerho",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nisl ultricies lacinia.",
					"Kahvila Rakastan",
					"Mannerheimintie 13, Helsinki",
					LocalDateTime.parse("2025-04-10T17:00:00", formatter),
					LocalDateTime.parse("2025-04-10T19:00:00", formatter),
					category2,
					organiser4,
					TargetGroup.ALL_ADULTS,
					true,
					0.0);

			Event event4 = new Event(
					"Naisten ilta",
					"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc nec nisl ultricies lacinia.",
					"Helsinki Priden yhteisötila",
					"Tallberginkatu 1, Helsinki",
					LocalDateTime.parse("2025-04-28T18:00:00", formatter),
					LocalDateTime.parse("2025-04-28T20:00:00", formatter),
					category2,
					organiser3,
					TargetGroup.WOMEN_AND_NON_BINARY,
					true,
					0.0);

			repository.save(event1);
			repository.save(event2);
			repository.save(event3);
			repository.save(event4);

		};
	}

}

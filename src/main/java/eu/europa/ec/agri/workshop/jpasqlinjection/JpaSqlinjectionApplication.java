package eu.europa.ec.agri.workshop.jpasqlinjection;

import eu.europa.ec.agri.workshop.jpasqlinjection.persistence.Book;
import eu.europa.ec.agri.workshop.jpasqlinjection.persistence.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class JpaSqlinjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaSqlinjectionApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(BookRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Book("Jack", "Bauer"));
			repository.save(new Book("Chloe", "O'Brian"));
			repository.save(new Book("Kim", "Bauer"));
			repository.save(new Book("David", "Palmer"));
			repository.save(new Book("Michelle", "Dessler"));
			repository.save(new Book("SA", "SA"));

			// fetch all customers
			log.info("Books found with findAll():");
			log.info("-------------------------------");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}

package ma.emsi.demo;

import ma.emsi.demo.entities.Customer;
import ma.emsi.demo.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CostumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CostumerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start (
			CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration
			){
		restConfiguration.exposeIdsFor(Customer.class);
		return  args -> {
			customerRepository.save(new Customer(null, "Yassine","yassine@contact.ma"));
			customerRepository.save(new Customer(null, "Mohamed","mohamed@contact.ma"));
			customerRepository.save(new Customer(null, "Reda","reda@contact.ma"));
			customerRepository.findAll().forEach(System.out::println);

		};
	}
}

package ma.emsi.demo;

import ma.emsi.demo.entities.Product;
import ma.emsi.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null, "Computer Desk Top HP", 900, 9));
			productRepository.save(new Product(null, "Printer Epson, 80", 80,5));
			productRepository.save(new Product(null, "MacBook Pro Lap Top", 1800,10));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}

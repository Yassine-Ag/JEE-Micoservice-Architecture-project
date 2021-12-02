package ma.emsi.billingservice;

import com.fasterxml.jackson.databind.type.CollectionLikeType;
import ma.emsi.billingservice.entities.Bill;
import ma.emsi.billingservice.entities.ProductItem;
import ma.emsi.billingservice.feign.CustomerRestClient;
import ma.emsi.billingservice.feign.ProductItemRestClient;
import ma.emsi.billingservice.models.Customer;
import ma.emsi.billingservice.models.Product;
import ma.emsi.billingservice.repositories.BillRepositories;
import ma.emsi.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			BillRepositories billRepositories, ProductItemRepository productItemRepository,
			CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient
	){
		return args ->{

			Customer customer = customerRestClient.getCustomerById(1L);
			Bill bill_1 = billRepositories.save(new Bill(null, new Date(), null, customer.getId(), customer));
			PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
			productPagedModel.forEach(
					product -> {
						ProductItem productItem = new ProductItem();
						productItem.setPrice(product.getPrice());
						productItem.setQuantity(1 + new Random().nextInt(100));
						productItem.setBill(bill_1);
						productItem.setProductID(product.getId());
						productItemRepository.save(productItem);
					}
			);
			System.out.println(customer.toString());
		};
	}
}

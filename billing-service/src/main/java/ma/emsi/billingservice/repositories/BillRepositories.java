package ma.emsi.billingservice.repositories;

import ma.emsi.billingservice.entities.Bill;
import ma.emsi.billingservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface BillRepositories extends JpaRepository<Bill, Long> {
}

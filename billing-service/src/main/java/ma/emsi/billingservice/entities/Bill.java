package ma.emsi.billingservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.billingservice.models.Customer;
import ma.emsi.billingservice.models.Product;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
public class Bill {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    private Long customerID;

    @Transient
    private Customer customer;

}

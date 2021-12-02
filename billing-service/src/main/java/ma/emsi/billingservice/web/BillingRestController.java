package ma.emsi.billingservice.web;

import ma.emsi.billingservice.entities.Bill;
import ma.emsi.billingservice.feign.CustomerRestClient;
import ma.emsi.billingservice.feign.ProductItemRestClient;
import ma.emsi.billingservice.models.Customer;
import ma.emsi.billingservice.models.Product;
import ma.emsi.billingservice.repositories.BillRepositories;
import ma.emsi.billingservice.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillingRestController {
    private BillRepositories billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClient customerRestClient;
    private ProductItemRestClient productItemRestClient;

    public BillingRestController(BillRepositories billRepository, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
        this.productItemRestClient = productItemRestClient;
    }


    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(p ->{
            Product product = productItemRestClient.getProductById(p.getProductID());
            p.setProduct(product);
        });

        return bill;
    }
}

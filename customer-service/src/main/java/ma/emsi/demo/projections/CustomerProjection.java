package ma.emsi.demo.projections;

import ma.emsi.demo.entities.Customer;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullCustomer", types = Customer.class)
public interface CustomerProjection extends Projection{
    public Long getId();
    public String getName();
    public String getEmail();
}

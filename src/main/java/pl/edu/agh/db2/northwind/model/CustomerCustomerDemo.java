package pl.edu.agh.db2.northwind.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class CustomerCustomerDemo implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CustomerCustomerDemoPK id;

    public CustomerCustomerDemo() {
    }

    public CustomerCustomerDemoPK getId() {
        return this.id;
    }

    public void setId(CustomerCustomerDemoPK id) {
        this.id = id;
    }

}
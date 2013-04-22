package pl.edu.agh.db2.northwind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customerdemographics")
public class CustomerDemographic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customertypeid")
    private String id;

    private String customerDesc;

    public CustomerDemographic() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerDesc() {
        return this.customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }
}
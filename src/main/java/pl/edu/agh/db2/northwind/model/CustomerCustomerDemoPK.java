package pl.edu.agh.db2.northwind.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CustomerCustomerDemoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "CustomerID")
    private String customerID;

    @Column(name = "CustomerTypeID")
    private String customerTypeID;

    public CustomerCustomerDemoPK() {
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerTypeID() {
        return this.customerTypeID;
    }

    public void setCustomerTypeID(String customerTypeID) {
        this.customerTypeID = customerTypeID;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CustomerCustomerDemoPK)) {
            return false;
        }
        CustomerCustomerDemoPK castOther = (CustomerCustomerDemoPK) other;
        return
                this.customerID.equals(castOther.customerID)
                        && this.customerTypeID.equals(castOther.customerTypeID);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.customerID.hashCode();
        hash = hash * prime + this.customerTypeID.hashCode();

        return hash;
    }
}
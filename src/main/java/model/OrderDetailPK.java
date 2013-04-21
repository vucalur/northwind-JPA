package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
class OrderDetailPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;
    @Column(name = "\"OrderID\"")
    private Integer orderID;
    @Column(name = "\"ProductID\"")
    private Integer productID;

    public OrderDetailPK() {
    }

    public Integer getOrderID() {
        return this.orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getProductID() {
        return this.productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrderDetailPK)) {
            return false;
        }
        OrderDetailPK castOther = (OrderDetailPK) other;
        return
                this.orderID.equals(castOther.orderID)
                        && this.productID.equals(castOther.productID);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.orderID.hashCode();
        hash = hash * prime + this.productID.hashCode();

        return hash;
    }
}
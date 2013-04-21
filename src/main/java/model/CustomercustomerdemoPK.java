package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The primary key class for the customercustomerdemo database table.
 * 
 */
@Embeddable
public class CustomercustomerdemoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"CustomerID\"")
	private String customerID;

	@Column(name="\"CustomerTypeID\"")
	private String customerTypeID;

	public CustomercustomerdemoPK() {
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
		if (!(other instanceof CustomercustomerdemoPK)) {
			return false;
		}
		CustomercustomerdemoPK castOther = (CustomercustomerdemoPK)other;
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
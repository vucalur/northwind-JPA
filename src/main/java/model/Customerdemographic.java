package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the customerdemographics database table.
 * 
 */
@Entity
@Table(name="customerdemographics")
public class Customerdemographic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"CustomerTypeID\"")
	private String customerTypeID;

	@Column(name="\"CustomerDesc\"")
	private String customerDesc;

	public Customerdemographic() {
	}

	public String getCustomerTypeID() {
		return this.customerTypeID;
	}

	public void setCustomerTypeID(String customerTypeID) {
		this.customerTypeID = customerTypeID;
	}

	public String getCustomerDesc() {
		return this.customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

}
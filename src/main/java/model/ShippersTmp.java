package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the shippers_tmp database table.
 * 
 */
@Entity
@Table(name="shippers_tmp")
public class ShippersTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ShipperID\"")
	private Integer shipperID;

	@Column(name="\"CompanyName\"")
	private String companyName;

	@Column(name="\"Phone\"")
	private String phone;

	public ShippersTmp() {
	}

	public Integer getShipperID() {
		return this.shipperID;
	}

	public void setShipperID(Integer shipperID) {
		this.shipperID = shipperID;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
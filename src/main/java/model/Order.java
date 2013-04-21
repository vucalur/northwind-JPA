package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"OrderID\"")
	private Integer orderID;

	@Column(name="\"CustomerID\"")
	private String customerID;

	@Column(name="\"EmployeeID\"")
	private Integer employeeID;

	@Column(name="\"Freight\"")
	private float freight;

	@Temporal(TemporalType.DATE)
	@Column(name="\"OrderDate\"")
	private Date orderDate;

	@Temporal(TemporalType.DATE)
	@Column(name="\"RequiredDate\"")
	private Date requiredDate;

	@Column(name="\"ShipAddress\"")
	private String shipAddress;

	@Column(name="\"ShipCity\"")
	private String shipCity;

	@Column(name="\"ShipCountry\"")
	private String shipCountry;

	@Column(name="\"ShipName\"")
	private String shipName;

	@Temporal(TemporalType.DATE)
	@Column(name="\"ShippedDate\"")
	private Date shippedDate;

	@Column(name="\"ShipPostalCode\"")
	private String shipPostalCode;

	@Column(name="\"ShipRegion\"")
	private String shipRegion;

	@Column(name="\"ShipVia\"")
	private Integer shipVia;

	public Order() {
	}

	public Integer getOrderID() {
		return this.orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public Integer getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public float getFreight() {
		return this.freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getRequiredDate() {
		return this.requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getShipAddress() {
		return this.shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	public String getShipCity() {
		return this.shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public String getShipCountry() {
		return this.shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public String getShipName() {
		return this.shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public Date getShippedDate() {
		return this.shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getShipPostalCode() {
		return this.shipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}

	public String getShipRegion() {
		return this.shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	public Integer getShipVia() {
		return this.shipVia;
	}

	public void setShipVia(Integer shipVia) {
		this.shipVia = shipVia;
	}

}
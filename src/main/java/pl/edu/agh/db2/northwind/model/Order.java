package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@XmlRootElement
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "orderid")
	// IMPORTANT: in order to set PK values manually with PostgreSQL (needed for loading data from XML with OXM),
	// sequence generators must be disabled
	// @SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1, initialValue = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq_gen")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "customerid")
	private Customer customer;

	/**
	 * Storing both foreign key and referenced entity (both target the very same DB column)
	 * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
	 */
	@Column(name = "customerid", insertable = false, updatable = false)
	private String customerId;

	@ManyToOne
	@JoinColumn(name = "employeeid")
	private Employee employee;

	/**
	 * Storing both foreign key and referenced entity (both target the very same DB column)
	 * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
	 */
	@Column(name = "employeeid", insertable = false, updatable = false)
	private Integer employeeId;

	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@Temporal(TemporalType.DATE)
	private Date requiredDate;

	@Temporal(TemporalType.DATE)
	private Date shippedDate;

	@ManyToOne
	@JoinColumn(name = "shipvia")
	private Shipper shipper;

	/**
	 * Storing both foreign key and referenced entity (both target the very same DB column)
	 * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
	 */
	@Column(name = "shipvia", insertable = false, updatable = false)
	private Integer shipperId;

	// TODO: money type
	private float freight;

	@Column(length = 40)
	private String shipName;

	@Column(length = 60)
	private String shipAddress;

	@Column(length = 15)
	private String shipCity;

	@Column(length = 15)
	private String shipRegion;

	@Column(length = 10)
	private String shipPostalCode;

	@Column(length = 15)
	private String shipCountry;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails = new ArrayList<>();

	protected Order() {
	}

	@XmlElement(name = "OrderID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@XmlTransient
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@XmlElement(name = "OrderDate")
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@XmlElement(name = "RequiredDate")
	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	@XmlElement(name = "ShippedDate")
	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	@XmlTransient
	public Shipper getShipper() {
		return shipper;
	}

	public void setShipper(Shipper shipper) {
		this.shipper = shipper;
	}

	@XmlElement(name = "Freight")
	public float getFreight() {
		return freight;
	}

	public void setFreight(float freight) {
		this.freight = freight;
	}

	@XmlElement(name = "ShipName")
	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	@XmlElement(name = "ShipAddress")
	public String getShipAddress() {
		return shipAddress;
	}

	public void setShipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}

	@XmlElement(name = "ShipCity")
	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	@XmlElement(name = "ShipRegion")
	public String getShipRegion() {
		return shipRegion;
	}

	public void setShipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
	}

	@XmlElement(name = "ShipPostalCode")
	public String getShipPostalCode() {
		return shipPostalCode;
	}

	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}

	@XmlElement(name = "ShipCountry")
	public String getShipCountry() {
		return shipCountry;
	}

	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@XmlElement(name = "CustomerID")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@XmlElement(name = "EmployeeID")
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@XmlElement(name = "ShipVia")
	public Integer getShipperId() {
		return shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
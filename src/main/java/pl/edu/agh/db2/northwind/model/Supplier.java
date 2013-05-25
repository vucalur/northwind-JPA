package pl.edu.agh.db2.northwind.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "supplierid")
	@SequenceGenerator(name = "suppliers_seq_gen", sequenceName = "suppliers_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suppliers_seq_gen")
	private Integer id;

	@Column(length = 40, nullable = false)
	private String companyName;

	@Column(length = 30)
	private String contactName;

	@Column(length = 30)
	private String contactTitle;

	@Column(length = 60)
	private String address;

	@Column(length = 15)
	private String city;

	@Column(length = 15)
	private String region;

	@Column(length = 10)
	private String postalCode;

	@Column(length = 15)
	private String country;

	@Column(length = 24)
	private String phone;

	@Column(length = 24)
	private String fax;

	private String homePage;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
	private List<Product> products = new ArrayList<>();

	protected Supplier() {
	}

	public Supplier(String companyName) {
		this.companyName = companyName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@XmlRootElement
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employeeid")
	@SequenceGenerator(name = "employee_seq_gen", sequenceName = "employee_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_gen")
	private Integer id;

	@Column(length = 20)
	private String lastName;

	@Column(length = 20)
	private String firstName;

	@Column(length = 30)
	private String title;

	@Column(length = 25)
	private String titleOfCourtesy;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Temporal(TemporalType.DATE)
	private Date hireDate;

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
	private String homePhone;

	@Column(length = 4)
	private String extension;

	@Column(length = 40)
	private byte[] photo;

	@Column(columnDefinition = "TEXT")
	private String notes;

	private String photoPath;

	@ManyToOne(targetEntity = Employee.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "reportsto")
	private Employee supervisor;

	/**
	 * Storing both foreign key and referenced entity (both target the very same DB column)
	 * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
	 */
	@Column(name = "reportsto", insertable = false, updatable = false)
	private Integer reportsTo;

	@OneToMany(mappedBy = "supervisor")
	private List<Employee> subordinates;

	@ManyToMany
	@JoinTable(name = "employeeterritories",
			   joinColumns = {
					   @JoinColumn(name = "employeeid", nullable = false, updatable = false)},
			   inverseJoinColumns = {
					   @JoinColumn(name = "territoryid", nullable = false, updatable = false)})
	private List<Territory> territories = new ArrayList<>();

	public Employee(Integer id, String lastName, String firstName, String title, Date birthDate, String titleOfCourtesy, Date hireDate,
					String address, String city, String region, String postalCode, String country, String homePhone, String extension,
					String notes, Integer reportsTo) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.title = title;
		this.birthDate = birthDate;
		this.titleOfCourtesy = titleOfCourtesy;
		this.hireDate = hireDate;
		this.address = address;
		this.city = city;
		this.region = region;
		this.postalCode = postalCode;
		this.country = country;
		this.homePhone = homePhone;
		this.extension = extension;
		this.notes = notes;
		this.reportsTo = reportsTo;
	}

	protected Employee() {
	}

	@XmlElement(name = "ReportsTo")
	public Integer getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}

	@XmlElement(name = "EmployeeID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name = "LastName")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@XmlElement(name = "FirstName")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name = "Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "TitleOfCourtesy")
	public String getTitleOfCourtesy() {
		return titleOfCourtesy;
	}

	public void setTitleOfCourtesy(String titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}

	@XmlElement(name = "BirthDate")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@XmlElement(name = "HireDate")
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@XmlElement(name = "Address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement(name = "City")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name = "Region")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@XmlElement(name = "PostalCode")
	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name = "HomePhone")
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	@XmlElement(name = "Extension")
	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@XmlElement(name = "Notes")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Employee> subordinates) {
		this.subordinates = subordinates;
	}

	public List<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(List<Territory> territories) {
		this.territories = territories;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			Employee other = (Employee) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(getAddress(), other.getAddress());
			builder.append(getBirthDate(), other.getBirthDate());
			builder.append(getCity(), other.getCity());
			builder.append(getCountry(), other.getCountry());
			builder.append(getExtension(), other.getExtension());
			builder.append(getFirstName(), other.getFirstName());
			builder.append(getLastName(), other.getLastName());
			builder.append(getHireDate(), other.getHireDate());
			builder.append(getHomePhone(), other.getHomePhone());
			builder.append(getNotes(), other.getNotes());
			builder.append(getPhoto(), other.getPhoto());
			builder.append(getPhotoPath(), other.getPhotoPath());
			builder.append(getReportsTo(), other.getReportsTo());
			builder.append(getRegion(), other.getRegion());
			builder.append(getPostalCode(), other.getPostalCode());
			builder.append(getTitle(), other.getTitle());
			builder.append(getTitleOfCourtesy(), other.getTitleOfCourtesy());
			return builder.isEquals();
		}
		return false;
	}
}

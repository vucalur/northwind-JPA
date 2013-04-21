package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"EmployeeID\"")
	private Integer employeeID;

	@Column(name="\"Address\"")
	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="\"BirthDate\"")
	private Date birthDate;

	@Column(name="\"City\"")
	private String city;

	@Column(name="\"Country\"")
	private String country;

	@Column(name="\"Extension\"")
	private String extension;

	@Column(name="\"FirstName\"")
	private String firstName;

	@Temporal(TemporalType.DATE)
	@Column(name="\"HireDate\"")
	private Date hireDate;

	@Column(name="\"HomePhone\"")
	private String homePhone;

	@Column(name="\"LastName\"")
	private String lastName;

	@Column(name="\"Notes\"")
	private String notes;

	@Column(name="\"Photo\"")
	private byte[] photo;

	@Column(name="\"PhotoPath\"")
	private String photoPath;

	@Column(name="\"PostalCode\"")
	private String postalCode;

	@Column(name="\"Region\"")
	private String region;

	@Column(name="\"ReportsTo\"")
	private Integer reportsTo;

	@Column(name="\"Title\"")
	private String title;

	@Column(name="\"TitleOfCourtesy\"")
	private String titleOfCourtesy;

	public Employee() {
	}

	public Integer getEmployeeID() {
		return this.employeeID;
	}

	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getHireDate() {
		return this.hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getHomePhone() {
		return this.homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getReportsTo() {
		return this.reportsTo;
	}

	public void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleOfCourtesy() {
		return this.titleOfCourtesy;
	}

	public void setTitleOfCourtesy(String titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}

}
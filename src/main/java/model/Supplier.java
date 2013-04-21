package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "\"SupplierID\"")
    private Integer supplierID;
    @Column(name = "\"Address\"")
    private String address;
    @Column(name = "\"City\"")
    private String city;
    @Column(name = "\"CompanyName\"")
    private String companyName;
    @Column(name = "\"ContactName\"")
    private String contactName;
    @Column(name = "\"ContactTitle\"")
    private String contactTitle;
    @Column(name = "\"Country\"")
    private String country;
    @Column(name = "\"Fax\"")
    private String fax;
    @Column(name = "\"HomePage\"")
    private String homePage;
    @Column(name = "\"Phone\"")
    private String phone;
    @Column(name = "\"PostalCode\"")
    private String postalCode;
    @Column(name = "\"Region\"")
    private String region;

    public Supplier() {
    }

    public Integer getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return this.contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
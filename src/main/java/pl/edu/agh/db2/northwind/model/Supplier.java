package pl.edu.agh.db2.northwind.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;

    // TODO : id generation
    @Id
    @SequenceGenerator(name = "suppliers_seq_gen", sequenceName = "suppliers_seq", allocationSize = 1, initialValue = 30)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suppliers_seq_gen")
    @Column(name = "supplierid")
    private Integer id;

    private String address;

    private String city;

    @Column(nullable = false)
    private String companyName;

    private String contactName;

    private String contactTitle;

    private String country;

    private String fax;

    private String homePage;

    private String phone;

    private String postalCode;

    private String region;

    public Supplier() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
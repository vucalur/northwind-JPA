package pl.edu.agh.db2.northwind.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customerid", length = 5)
    @SequenceGenerator(name = "customer_seq_gen", sequenceName = "customer_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq_gen")
    private String id;

    @ManyToMany
    @JoinTable(name = "customercustomerdemo",
               joinColumns = {
                       @JoinColumn(name = "customerid", nullable = false, updatable = false)},
               inverseJoinColumns = {
                       @JoinColumn(name = "customertypeid", nullable = false, updatable = false)})
    private List<CustomerDemographic> demographics = new ArrayList<>();

    @Column(length = 40)
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

    protected Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CustomerDemographic> getDemographics() {
        return demographics;
    }

    public void setDemographics(List<CustomerDemographic> demographics) {
        this.demographics = demographics;
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
}
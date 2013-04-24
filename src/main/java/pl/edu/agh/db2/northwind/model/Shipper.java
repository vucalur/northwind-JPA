package pl.edu.agh.db2.northwind.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shippers")
public class Shipper implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "shipperid")
    @SequenceGenerator(name = "shipper_seq_gen", sequenceName = "shipper_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipper_seq_gen")
    private Integer id;

    @Column(length = 40)
    private String companyName;

    @Column(length = 24)
    private String phone;

    protected Shipper() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
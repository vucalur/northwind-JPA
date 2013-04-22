package pl.edu.agh.db2.northwind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Region implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "regionid")
    private Integer id;

    private String regionDescription;

    public Region() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionDescription() {
        return this.regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }
}
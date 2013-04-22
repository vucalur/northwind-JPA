package pl.edu.agh.db2.northwind.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "territories")
public class Territory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "territoryid")
    private String id;

    private Integer regionID;

    @Column(name = "territorydescription")
    private String description;

    public Territory() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRegionID() {
        return this.regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
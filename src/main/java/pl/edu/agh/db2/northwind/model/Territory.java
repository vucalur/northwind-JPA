package pl.edu.agh.db2.northwind.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "territories")
public class Territory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "territoryid")
    @SequenceGenerator(name = "territory_seq_gen", sequenceName = "territory_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "territory_seq_gen")
    private Integer id;

    private String territoryDescription;

    @ManyToOne
    @JoinColumn(name = "regionid")
    private Region region;

    @ManyToMany(mappedBy = "territories")
    private List<Employee> employees = new ArrayList<>();

    protected Territory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerritoryDescription() {
        return territoryDescription;
    }

    public void setTerritoryDescription(String territoryDescription) {
        this.territoryDescription = territoryDescription;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
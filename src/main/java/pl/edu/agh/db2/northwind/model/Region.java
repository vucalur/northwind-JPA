package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "regionid")
	// IMPORTANT: in order to set PK values manually with PostgreSQL (needed for loading data from XML with OXM),
	// sequence generators must be disabled
//	@SequenceGenerator(name = "region_seq_gen", sequenceName = "region_seq", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq_gen")
	private Integer id;

	private String regionDescription;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
	private List<Territory> territories;

	protected Region() {
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
}
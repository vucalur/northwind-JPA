package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the territories database table.
 * 
 */
@Entity
@Table(name="territories")
public class Territory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"TerritoryID\"")
	private String territoryID;

	@Column(name="\"RegionID\"")
	private Integer regionID;

	@Column(name="\"TerritoryDescription\"")
	private String territoryDescription;

	public Territory() {
	}

	public String getTerritoryID() {
		return this.territoryID;
	}

	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public Integer getRegionID() {
		return this.regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}

	public String getTerritoryDescription() {
		return this.territoryDescription;
	}

	public void setTerritoryDescription(String territoryDescription) {
		this.territoryDescription = territoryDescription;
	}

}
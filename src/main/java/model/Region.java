package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"RegionID\"")
	private Integer regionID;

	@Column(name="\"RegionDescription\"")
	private String regionDescription;

	public Region() {
	}

	public Integer getRegionID() {
		return this.regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}

	public String getRegionDescription() {
		return this.regionDescription;
	}

	public void setRegionDescription(String regionDescription) {
		this.regionDescription = regionDescription;
	}

}
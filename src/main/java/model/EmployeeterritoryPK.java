package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The primary key class for the employeeterritories database table.
 * 
 */
@Embeddable
public class EmployeeterritoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"EmployeeID\"")
	private Integer employeeID;

	@Column(name="\"TerritoryID\"")
	private String territoryID;

	public EmployeeterritoryPK() {
	}
	public Integer getEmployeeID() {
		return this.employeeID;
	}
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	public String getTerritoryID() {
		return this.territoryID;
	}
	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmployeeterritoryPK)) {
			return false;
		}
		EmployeeterritoryPK castOther = (EmployeeterritoryPK)other;
		return 
			this.employeeID.equals(castOther.employeeID)
			&& this.territoryID.equals(castOther.territoryID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.employeeID.hashCode();
		hash = hash * prime + this.territoryID.hashCode();
		
		return hash;
	}
}
package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * The persistent class for the employeeterritories database table.
 * 
 */
@Entity
@Table(name="employeeterritories")
public class Employeeterritory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmployeeterritoryPK id;

	public Employeeterritory() {
	}

	public EmployeeterritoryPK getId() {
		return this.id;
	}

	public void setId(EmployeeterritoryPK id) {
		this.id = id;
	}

}
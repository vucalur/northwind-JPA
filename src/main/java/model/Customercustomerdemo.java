package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the customercustomerdemo database table.
 * 
 */
@Entity
public class Customercustomerdemo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CustomercustomerdemoPK id;

	public Customercustomerdemo() {
	}

	public CustomercustomerdemoPK getId() {
		return this.id;
	}

	public void setId(CustomercustomerdemoPK id) {
		this.id = id;
	}

}
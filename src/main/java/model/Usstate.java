package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the usstates database table.
 * 
 */
@Entity
@Table(name="usstates")
public class Usstate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"StateAbbr\"")
	private String stateAbbr;

	@Id
	@Column(name="\"StateID\"")
	private Integer stateID;

	@Column(name="\"StateName\"")
	private String stateName;

	@Column(name="\"StateRegion\"")
	private String stateRegion;

	public Usstate() {
	}

	public String getStateAbbr() {
		return this.stateAbbr;
	}

	public void setStateAbbr(String stateAbbr) {
		this.stateAbbr = stateAbbr;
	}

	public Integer getStateID() {
		return this.stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateRegion() {
		return this.stateRegion;
	}

	public void setStateRegion(String stateRegion) {
		this.stateRegion = stateRegion;
	}

}
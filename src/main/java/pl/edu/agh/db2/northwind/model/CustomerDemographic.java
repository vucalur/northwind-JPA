package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customerdemographics")
public class CustomerDemographic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customertypeid")
	@SequenceGenerator(name = "customerdemographics_seq_gen", sequenceName = "customerdemographics_seq", allocationSize = 1,
					   initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerdemographics_seq_gen")
	private String id;

	@ManyToMany(mappedBy = "demographics")
	private List<Customer> customers = new ArrayList<>();

	private String customerDesc;

	protected CustomerDemographic() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getCustomerDesc() {
		return this.customerDesc;
	}

	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
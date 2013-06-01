package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "shippers")
@XmlRootElement
public class Shipper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shipperid")
	// IMPORTANT: in order to set PK values manually with PostgreSQL (needed for loading data from XML with OXM),
	// sequence generators must be disabled
//	@SequenceGenerator(name = "shipper_seq_gen", sequenceName = "shipper_seq", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipper_seq_gen")
	private Integer id;

	@Column(length = 40)
	private String companyName;

	@Column(length = 24)
	private String phone;

	protected Shipper() {
	}

	public Shipper(Integer id, String companyName, String phone) {
		this.id = id;
		this.companyName = companyName;
		this.phone = phone;
	}

	@XmlElement(name = "ShipperID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name = "CompanyName")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@XmlElement(name = "Phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Shipper) {
			Shipper other = (Shipper) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(getCompanyName(), other.getCompanyName());
			builder.append(getPhone(), other.getPhone());
			return builder.isEquals();
		}
		return false;
	}
}
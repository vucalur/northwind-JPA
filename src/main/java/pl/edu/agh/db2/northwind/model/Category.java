package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@XmlRootElement
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "categoryid")
	@SequenceGenerator(name = "category_seq_gen", sequenceName = "category_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
	private Integer id;

	@Column(length = 15)
	private String categoryName;

	private String description;

	@Column(length = 40)
	private String picture;

	@OneToMany(mappedBy = "category")
	private List<Product> products = new ArrayList<>();

	public Category(Integer id, String categoryName, String description) {
		this.id = id;
		this.categoryName = categoryName;
		this.description = description;
	}

	protected Category() {
	}

	@XmlElement(name = "CategoryID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement(name = "CategoryName")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Category) {
			Category other = (Category) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(getCategoryName(), other.getCategoryName());
			builder.append(getDescription(), other.getDescription());
			builder.append(getPicture(), other.getPicture());
			builder.append(getProducts(), other.getProducts());
			return builder.isEquals();
		}
		return false;
	}
}
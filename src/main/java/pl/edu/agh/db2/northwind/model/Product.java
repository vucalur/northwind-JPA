package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "products")
@XmlRootElement
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "productid")
	@SequenceGenerator(name = "products_seq_gen", sequenceName = "products_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq_gen")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;

	/**
	 * Storing both foreign key and referenced entity (both target the very same DB column)
	 * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
	 */
	@Column(name = "categoryid", insertable = false, updatable = false)
	private Integer categoryId;

	@ManyToOne
	@JoinColumn(name = "supplierid")
	private Supplier supplier;

	/**
	 * Storing both foreign key and referenced entity (both target the very same DB column)
	 * See {@linktourl http://stackoverflow.com/a/6312018/1432478}
	 */
	@Column(name = "supplierid", insertable = false, updatable = false)
	private Integer supplierId;

	@Column(length = 40)
	private String productName;

	//    FIXME: below doesn't work - doesn't generate table
//    @Column(name = "discontinued", columnDefinition = "INT(1)")
	private Boolean discontinued;

	@Column(length = 20)
	private String quantityPerUnit;

	// TODO: money type
	private float unitPrice;

	private Integer unitsInStock;

	private Integer unitsOnOrder;

	private Integer reorderLevel;

	protected Product() {
	}

	public Product(String productName) {
		this.productName = productName;
	}

	@XmlElement(name = "ProductID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@XmlElement(name = "ProductName")
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@XmlElement(name = "Discontinued")
	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	@XmlElement(name = "QuantityPerUnit")
	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	@XmlElement(name = "UnitPrice")
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@XmlElement(name = "UnitsInStock")
	public Integer getUnitsInStock() {
		return unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	@XmlElement(name = "UnitsOnOrder")
	public Integer getUnitsOnOrder() {
		return unitsOnOrder;
	}

	public void setUnitsOnOrder(Integer unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

	@XmlElement(name = "ReorderLevel")
	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@XmlElement(name = "SupplierID")
	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	@XmlElement(name = "CategoryID")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}

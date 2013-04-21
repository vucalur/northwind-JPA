package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name="products")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"ProductID\"")
	private Integer productID;

	@Column(name="\"CategoryID\"")
	private Integer categoryID;

	@Column(name="\"Discontinued\"")
	private Integer discontinued;

	@Column(name="\"ProductName\"")
	private String productName;

	@Column(name="\"QuantityPerUnit\"")
	private String quantityPerUnit;

	@Column(name="\"ReorderLevel\"")
	private Integer reorderLevel;

	@Column(name="\"SupplierID\"")
	private Integer supplierID;

	@Column(name="\"UnitPrice\"")
	private float unitPrice;

	@Column(name="\"UnitsInStock\"")
	private Integer unitsInStock;

	@Column(name="\"UnitsOnOrder\"")
	private Integer unitsOnOrder;

	public Product() {
	}

	public Integer getProductID() {
		return this.productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public Integer getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Integer getDiscontinued() {
		return this.discontinued;
	}

	public void setDiscontinued(Integer discontinued) {
		this.discontinued = discontinued;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getQuantityPerUnit() {
		return this.quantityPerUnit;
	}

	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}

	public Integer getReorderLevel() {
		return this.reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Integer getSupplierID() {
		return this.supplierID;
	}

	public void setSupplierID(Integer supplierID) {
		this.supplierID = supplierID;
	}

	public float getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getUnitsInStock() {
		return this.unitsInStock;
	}

	public void setUnitsInStock(Integer unitsInStock) {
		this.unitsInStock = unitsInStock;
	}

	public Integer getUnitsOnOrder() {
		return this.unitsOnOrder;
	}

	public void setUnitsOnOrder(Integer unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}

}
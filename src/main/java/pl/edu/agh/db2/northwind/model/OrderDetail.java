package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "odid")
	@SequenceGenerator(name = "order_details_seq_gen", sequenceName = "order_details_seq", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_seq_gen")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	// TODO: money type
	private float unitPrice = 0;

	private Integer quantity = 1;

	// TODO: money type (fixed point)
	private float discount = 0;

	protected OrderDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
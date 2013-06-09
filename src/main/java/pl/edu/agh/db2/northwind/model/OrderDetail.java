package pl.edu.agh.db2.northwind.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Entity
@Table(name = "order_details")
@XmlRootElement(name = "orderdetail")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "odid")
	// IMPORTANT: in order to set PK values manually with PostgreSQL (needed for loading data from XML with OXM),
	// sequence generators must be disabled
//	@SequenceGenerator(name = "order_details_seq_gen", sequenceName = "order_details_seq", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_details_seq_gen")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order order;

	@Transient
	private Integer orderId;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	@Transient
	private Integer productId;

	// TODO: money type
	private float unitPrice = 0;

	private Integer quantity = 1;

	// TODO: money type (fixed point)
	private float discount = 0;

	protected OrderDetail() {
	}

	public OrderDetail(Integer id, Order order, Product product, float unitPrice, Integer quantity, float discount) {
		this.id = id;
		this.order = order;
		this.product = product;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.discount = discount;
	}

	@XmlElement(name = "odID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlTransient
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@XmlTransient
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@XmlElement(name = "UnitPrice")
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@XmlElement(name = "Quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@XmlElement(name = "Discount")
	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	@XmlElement(name = "OrderID")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@XmlElement(name = "ProductID")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OrderDetail) {
			OrderDetail other = (OrderDetail) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(getDiscount(), other.getDiscount());
			builder.append(getUnitPrice(), other.getUnitPrice());
			builder.append(getOrder(), other.getOrder());
			builder.append(getProduct(), other.getProduct());
			builder.append(getQuantity(), other.getQuantity());
			return builder.isEquals();
		}
		return false;
	}
}
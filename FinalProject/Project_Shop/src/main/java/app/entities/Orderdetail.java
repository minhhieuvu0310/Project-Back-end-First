package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Orderdetail")
public class Orderdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderdetailid")
	private Integer orderdetailid;
	@Column(name = "amount")
	private Float amount;
	@Column(name = "price")
	private Float price;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "status")
	private Boolean status;
	@Column(name = "note")
	private String note;
	@Column(name = "color")
	private String color;


	@ManyToOne
	@JoinColumn(name = "productid", referencedColumnName = "productid")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "ordersid", referencedColumnName = "ordersid")
	private Orders orders;

	public Orderdetail() {
		super();
	}

	public Orderdetail(Integer orderdetailid, Float amount, Float price, Integer quantity, Boolean status, String note,
			Product product, Orders orders) {
		super();
		this.orderdetailid = orderdetailid;
		this.amount = amount;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.note = note;
		this.product = product;
		this.orders = orders;
	}

	public Integer getOrderdetailid() {
		return orderdetailid;
	}

	public void setOrderdetailid(Integer orderdetailid) {
		this.orderdetailid = orderdetailid;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}	
}

package app.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CartItem")
public class CartItem {
	@Id
	@Column(name = "cartItemId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartItemId;
	@Column(name = "quantity")
	private Integer quantity;
	@Column(name = "price")
	private Float price;
	@Column(name = "amount")
	private Float amount;
	@Column(name = "created")
	private Date created;
	@Column(name = "updated")
	private Date updated;
	@Column(name = "color")
	private String color;
	@Column(name = "note")
	private String note;
	@Column(name = "status")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "cartId", referencedColumnName = "cartId")
	private Cart cart;
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(Integer cartItemId, Integer quantity, Float price, Float amount, Date created, Date updated,
			String color, String note, Boolean status, Product product, Cart cart) {
		super();
		this.cartItemId = cartItemId;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.created = created;
		this.updated = updated;
		this.color = color;
		this.note = note;
		this.status = status;
		this.product = product;
		this.cart = cart;
	}

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}

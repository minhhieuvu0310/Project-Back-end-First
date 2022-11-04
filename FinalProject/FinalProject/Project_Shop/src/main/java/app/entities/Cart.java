package app.entities;

public class Cart {
	private Product product;
	private Integer quantity;
	private String note;

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Product product, Integer quantity, String note) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.note = note;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}

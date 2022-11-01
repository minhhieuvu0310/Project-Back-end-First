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
@Table(name = "ProductColor")
public class ProductColor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productColorId")
	private Integer productColorId;
	@Column(name = "Status")
	private Boolean status;
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "colorId", referencedColumnName = "colorId")
	private Color color;

	public ProductColor() {
		super();
	}

	public ProductColor(Integer productColorId, Boolean status, Product product, Color color) {
		super();
		this.productColorId = productColorId;
		this.status = status;
		this.product = product;
		this.color = color;
	}

	public Integer getProductColorId() {
		return productColorId;
	}

	public void setProductColorId(Integer productColorId) {
		this.productColorId = productColorId;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}

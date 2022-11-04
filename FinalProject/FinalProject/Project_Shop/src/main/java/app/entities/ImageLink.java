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
@Table(name = "ImageLink")
public class ImageLink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "imageLinkId")
	private Integer imageLinkId ;
	@Column(name = "imageLinkName")
	private String imageLinkName;
	@Column(name = "Status")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Product product;

	public ImageLink() {
		super();
	}

	public ImageLink(Integer imageLinkId, String imageLinkName, Boolean status, Product product) {
		super();
		this.imageLinkId = imageLinkId;
		this.imageLinkName = imageLinkName;
		this.status = status;
		this.product = product;
	}

	public Integer getImageLinkId() {
		return imageLinkId;
	}

	public void setImageLinkId(Integer imageLinkId) {
		this.imageLinkId = imageLinkId;
	}

	public String getImageLinkName() {
		return imageLinkName;
	}

	public void setImageLinkName(String imageLinkName) {
		this.imageLinkName = imageLinkName;
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
	
	
}


package app.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	@Id
	@Column(name = "productId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId ;
	@Column(name = "productName")
	private String productName;
	@Column(name = "productContent")
	private String productContent;
	@Column(name = "productContentDetail")
	private String productContentDetail;
	@Column(name = "images")
	private String images;
	@Column(name = "views")
	private Integer views;
	@Column(name = "buyItem")
	private Integer buyItem;
	@Column(name = "priceInput")
	private Float priceInput;
	@Column(name = "priceOutput")
	private Float priceOutput;
	@Column(name = "quantity")
	private Float quantity;
	@Column(name = "created")
	private Date created;
	@Column(name = "Discount")
	private Float discount;
	@Column(name = "status")
	private Boolean status;
	
	@ManyToOne
	@JoinColumn(name = "providerId",referencedColumnName = "providerId")
	private Provider provider;	
	@ManyToOne
	@JoinColumn(name = "catalogId",referencedColumnName = "catalogId")
	private CataLogs catalog;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<ProductColor> productColors;
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
	private Set<ImageLink> imagelink;

	
	public Product() {
		super();
	}

	public Product(Integer productId, String productName, String productContent, String productContentDetail,
			String images, Integer views, Integer buyItem, Float priceInput, Float priceOutput, Float quantity,
			Date created, Float discount, Boolean status, Provider provider, CataLogs catalog) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productContent = productContent;
		this.productContentDetail = productContentDetail;
		this.images = images;
		this.views = views;
		this.buyItem = buyItem;
		this.priceInput = priceInput;
		this.priceOutput = priceOutput;
		this.quantity = quantity;
		this.created = created;
		this.discount = discount;
		this.status = status;
		this.provider = provider;
		this.catalog = catalog;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public String getProductContentDetail() {
		return productContentDetail;
	}

	public void setProductContentDetail(String productContentDetail) {
		this.productContentDetail = productContentDetail;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getBuyItem() {
		return buyItem;
	}

	public void setBuyItem(Integer buyItem) {
		this.buyItem = buyItem;
	}

	public Float getPriceInput() {
		return priceInput;
	}

	public void setPriceInput(Float priceInput) {
		this.priceInput = priceInput;
	}

	public Float getPriceOutput() {
		return priceOutput;
	}

	public void setPriceOutput(Float priceOutput) {
		this.priceOutput = priceOutput;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public CataLogs getCatalog() {
		return catalog;
	}

	public void setCatalog(CataLogs catalog) {
		this.catalog = catalog;
	}

	
}

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
@Table(name = "Computer")
public class Computer {
	@Id
	@Column(name = "ComId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comId;
	@Column(name = "ComName")
	private String comName;
	@Column(name = "TypeId")
	private String typeId;
	@Column(name = "PriceInput")
	private Float priceInput;
	@Column(name = "PriceOutput")
	private Float priceOutput;
	@Column(name = "Discount")
	private Float discount;
	@Column(name = "Insurance")
	private String insurance;
	@Column(name = "Quantity")
	private Integer quantity;
	@Column(name = "QuantityExtant")
	private Integer quantityExtant;
	@Column(name = "PrimaryImage")
	private String primaryImage;
	@Column(name = "Views")
	private Integer views;
	@Column(name = "address")
	private String address;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "created")
	private Date created;
	@Column(name = "status")
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "produreId",referencedColumnName = "produreId")
	private Produre produre;
	
	public Computer() {
		super();
	}

	public Computer(Integer comId, String comName, String typeId, Float priceInput, Float priceOutput, Float discount,
			String insurance, Integer quantity, Integer quantityExtant, String primaryImage, Integer views,
			String address, String title, String description, Date created, Boolean status, Produre produre) {
		super();
		this.comId = comId;
		this.comName = comName;
		this.typeId = typeId;
		this.priceInput = priceInput;
		this.priceOutput = priceOutput;
		this.discount = discount;
		this.insurance = insurance;
		this.quantity = quantity;
		this.quantityExtant = quantityExtant;
		this.primaryImage = primaryImage;
		this.views = views;
		this.address = address;
		this.title = title;
		this.description = description;
		this.created = created;
		this.status = status;
		this.produre = produre;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
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

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantityExtant() {
		return quantityExtant;
	}

	public void setQuantityExtant(Integer quantityExtant) {
		this.quantityExtant = quantityExtant;
	}

	public String getPrimaryImage() {
		return primaryImage;
	}

	public void setPrimaryImage(String primaryImage) {
		this.primaryImage = primaryImage;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Produre getProdure() {
		return produre;
	}

	public void setProdure(Produre produre) {
		this.produre = produre;
	}

}

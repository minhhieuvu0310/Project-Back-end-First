package app.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Color")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "colorId")
	private Integer colorId ;
	@Column(name = "colorName")
	private String colorName;
	@Column(name = "natation")
	private String natation;
	@Column(name = "Status")
	private Boolean status;
	
	@OneToMany(mappedBy = "color",fetch = FetchType.EAGER)
	private Set<ProductColor> productColors;

	public Color() {
		super();
	}

	public Color(Integer colorId, String colorName, String natation, Boolean status, Set<ProductColor> productColors) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
		this.natation = natation;
		this.status = status;
		this.productColors = productColors;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getNatation() {
		return natation;
	}

	public void setNatation(String natation) {
		this.natation = natation;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<ProductColor> getProductColors() {
		return productColors;
	}

	public void setProductColors(Set<ProductColor> productColors) {
		this.productColors = productColors;
	}
}

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
@Table(name = "CataLogs")
public class CataLogs {
	@Id
	@Column(name = "catalogId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer catalogId;
	@Column(name = "catalogName")
	private String catalogName;
	@Column(name = "descriptions")
	private String descriptions;
	@Column(name = "parentId")
	private Integer parentId;
	@Column(name = "status")
	private Boolean status;
	@OneToMany(mappedBy = "catalog", fetch = FetchType.EAGER)
	private Set<Product> product;
	
	public CataLogs() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CataLogs(Integer catalogId, String catalogName, String descriptions, Integer parentId, Boolean status,
			Set<Product> product) {
		super();
		this.catalogId = catalogId;
		this.catalogName = catalogName;
		this.descriptions = descriptions;
		this.parentId = parentId;
		this.status = status;
		this.product = product;
	}

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
}

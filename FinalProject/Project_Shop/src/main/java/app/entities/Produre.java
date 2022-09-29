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
@Table(name = "Produre")
public class Produre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProdureId")
	private Integer produreId;
	@Column(name = "ProName")
	private String proName;
	@Column(name = "Address")
	private String address;
	@Column(name = "Email")
	private String email;
	@Column(name = "Phone")
	private String phone;
	@Column(name = "Status")
	private Boolean status;
	
	@OneToMany(mappedBy = "produre",fetch = FetchType.EAGER)
	private Set<Computer> computer;

	public Produre() {
		super();
	}

	public Produre(Integer produreId, String proName, String address, String email, String phone, Boolean status,
			Set<Computer> computer) {
		super();
		this.produreId = produreId;
		this.proName = proName;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.computer = computer;
	}

	public Integer getProdureId() {
		return produreId;
	}

	public void setProdureId(Integer produreId) {
		this.produreId = produreId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Computer> getComputer() {
		return computer;
	}

	public void setComputer(Set<Computer> computer) {
		this.computer = computer;
	}

	
}

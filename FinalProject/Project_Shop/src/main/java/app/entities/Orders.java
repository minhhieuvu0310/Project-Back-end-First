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
@Table(name = "Orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordersid")
	private Integer ordersId;
	@Column(name = "orderName")
	private String orderName;
	@Column(name = "orderNumber")
	private String orderNumber;
	@Column(name = "phone")
	private String phone;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "totalamount")
	private Float totalamount;
	@Column(name = "paymentdate")
	private Date paymentdate;
	@Column(name = "createddate")
	private Date createddate;
	@Column(name = "paymentmethod")
	private String paymentmethod;
	@Column(name = "status")
	private Boolean status;

	@OneToMany(mappedBy = "orders", fetch = FetchType.EAGER)
	private Set<Orderdetail> orderdetails;

	@ManyToOne
	@JoinColumn(name = "userid", referencedColumnName = "userid")
	private Users user;

	public Orders() {
		super();
	}

	public Orders(Integer ordersId, String orderName, String orderNumber, String phone, String address, String email,
			Float totalamount, Date paymentdate, Date createddate, String paymentmethod, Boolean status,
			Set<Orderdetail> orderdetails, Users user) {
		super();
		this.ordersId = ordersId;
		this.orderName = orderName;
		this.orderNumber = orderNumber;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.totalamount = totalamount;
		this.paymentdate = paymentdate;
		this.createddate = createddate;
		this.paymentmethod = paymentmethod;
		this.status = status;
		this.orderdetails = orderdetails;
		this.user = user;
	}

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Float getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(Float totalamount) {
		this.totalamount = totalamount;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(Set<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
}

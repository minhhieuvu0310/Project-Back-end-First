package app.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserId")
	private Integer UserId;
	
	@Column(name = "UserName")
	@NotEmpty(message = "Tên Đăng Nhập Không Được Bỏ Trống")
	private String userName;	
	@Column(name = "passWord")
	@NotEmpty(message = "Mật Khẩu Không Được Bỏ Trống")
	private String passWord;	
	@Column(name = "fullName")
	@NotEmpty(message = "Họ Và Tên Không Được Để Trống")
	private String fullName;	
	@Column(name = "email")
	@NotEmpty(message = "Email Không Được Bỏ Trống")
	private String email;	
	@Column(name = "phone")
	@NotEmpty(message = "Số Điện Thoại Không Được Bỏ Trống")
	private String phone;	
	@Column(name = "address")
	@NotEmpty(message = "Địa Chỉ Không Được Bỏ Trống")
	private String address;
	@Column(name = "created")
	private Date created;
	@Column(name = "updated")
	private Date updated;
	@Column(name = "userImage")
	private String userImage;
	@Column(name = "status")
	private Boolean status;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<User_Role> userRoles;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Orders> orders;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Cart> cart;
	
	public Users() {
		super();
	}

	public Users(Integer userId, String userName, String passWord, String fullName, String email, String phone,
			String address, Date created, Date updated, String userImage, Boolean status, Set<User_Role> userRoles,
			Set<Orders> orders, Set<Cart> cart) {
		super();
		UserId = userId;
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.created = created;
		this.updated = updated;
		this.userImage = userImage;
		this.status = status;
		this.userRoles = userRoles;
		this.orders = orders;
		this.cart = cart;
	}



	public Integer getUserId() {
		return UserId;
	}

	public void setUserId(Integer userId) {
		UserId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<User_Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User_Role> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Set<Orders> orders) {
		this.orders = orders;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}

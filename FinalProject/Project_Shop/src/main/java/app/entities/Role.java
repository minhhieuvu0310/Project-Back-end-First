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
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "roleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	@Column(name = "roleName")
	private String roleName;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
	private Set<User_Role> roleUsers;

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer roleId, String roleName, Set<User_Role> roleUsers) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleUsers = roleUsers;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User_Role> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<User_Role> roleUsers) {
		this.roleUsers = roleUsers;
	}

	
}

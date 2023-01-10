package app.dao;

import app.entities.User_Role;
import app.entities.Users;

public interface User_RoleDAO {
	//thêm quyền cho người dùng
	public boolean insertRoleForUser(User_Role user_role);
	//check quyền Admin của người dùng
	public boolean checkRoleAdminOfUser(Users users);
}

package app.dao;

import java.util.List;

import app.entities.Users;

public interface UsersDAO {
	//lấy tất cả người dùng
	public List<Users> getAllUser();
	//kiểm Tra người dùng đăng nhập
	public boolean checkLogin (Users users);
	//Lấy Users theo tài khoản
	public Users getUsers(Users users);
	
}

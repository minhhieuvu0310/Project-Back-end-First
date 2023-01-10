package app.dao;

import java.util.List;

import app.entities.Users;

public interface UsersDAO {
	// lấy tất cả người dùng
	public List<Users> getAllUser();

	// kiểm Tra người dùng đăng nhập
	public boolean checkLogin(Users users);

	// Lấy Users theo tài khoản
	public Users getUsers(Users users);

	// check xem userName đã tồn tại chưa
	public boolean checkUserName(Users users);

	// check xem email đã tồn tại chưa
	public boolean checkUserEmail(Users users);

	// check xem số điện thoại đã tồn tại chưa
	public boolean checkUserPhone(Users users);
	
	//Thêm mới một người dùng
	public boolean insertUsers(Users users);
	
	//Thêm mới một người dùng
	public boolean updateUsers(Users users);
	
	//Tính Tổng số Users
	public int getTotalUsers();
}

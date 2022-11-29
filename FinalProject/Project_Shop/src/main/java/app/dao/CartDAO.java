package app.dao;

import app.entities.Cart;

public interface CartDAO {
	
	//Tạo giỏ hàng cho người dùng
	public Boolean InsertCart(Cart cart);
	
	//Tạo giỏ hàng cho người dùng
	public Boolean UpdateCart(Cart cart);
	
	//Lấy ra Id của giỏ hàng
	public Cart CartOfUser(Integer userId);
	
}

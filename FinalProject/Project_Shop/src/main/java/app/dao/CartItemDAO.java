package app.dao;

import java.util.List;

import app.entities.CartItem;

public interface CartItemDAO {
	// Lấy ra list sản phẩm trong giỏ hàng
	public List<CartItem> getAllCartItemByCartId(Integer cartId);

	// Thêm sản phẩm vào giỏ hàng
	public Boolean InserCartItem(CartItem cartItem);

	// upadate sản phẩm trong giỏ hàng
	public Boolean UpdateCartItem(CartItem cartItem);
	
	//xóa sản phẩm khỏi giỏ hàng
	public Boolean DeleteCartItem(CartItem cartItem); 

}

package app.dao;

import java.util.List;

import app.entities.Orders;

public interface OrdersDAO {
	//thêm mới hóa đơn
	public boolean insertOrder(Orders orders);
	
	//cập nhật hóa đơn hóa đơn
	public boolean updateOrder(Orders orders);
	
	//lấy orderid cuối cùng của bảng order
	public Integer getOrderIdNew();
	
	//tìm kiếm hóa đơn theo Id
	public Orders findOrdersById(Integer orderId);
	
	//tìm kiếm hóa đơn theo uesrsId
	public List<Orders> findOrdersByUsersId(Integer userId);
	
	//Tình Tổng số hóa đơn 
	public Integer getTotalOrders();
	
	//Lấy tất cả hóa đơn
	public List<Orders> getAllOrders();
	
	//Tính Tổng doanh thu
	public Float getTotalMoney(List<Orders> lstOrders);
	
	
}

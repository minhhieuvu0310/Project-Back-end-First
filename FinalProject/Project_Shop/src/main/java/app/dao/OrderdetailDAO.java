package app.dao;

import java.util.List;

import app.entities.Orderdetail;

public interface OrderdetailDAO {
	//thêm mới chi tiết hóa đơn
	public boolean insertOrderDetail(Orderdetail orderdetail);

	//tìm kiếm chi tiết hóa đơn theo ordersId
	public List<Orderdetail> findOrderDetailByOrdersId(Integer ordersId);
	
}

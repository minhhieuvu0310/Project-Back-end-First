package app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dao.OrderdetailDAO;
import app.dao.OrdersDAO;
import app.entities.Orderdetail;
import app.entities.Orders;
import app.entities.Users;

@Controller
public class HistoryController {
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private OrderdetailDAO orderdetailDAO;
	
	@RequestMapping(value = { "/history" })
	public String getOrderByUserId(Model model, HttpSession session){
		Users users = (Users) session.getAttribute("users");
		if(users != null) {
			List<Orders> listOrders = ordersDAO.findOrdersByUsersId(users.getUserId());
			List<Orderdetail> listOrderdetail = new ArrayList<>();
			for (Orders orders : listOrders) {
				List<Orderdetail> listOrderdetailItem = orderdetailDAO.findOrderDetailByOrdersId(orders.getOrdersId());
				if(listOrderdetailItem.size() != 0 ) {
					listOrderdetail.addAll(listOrderdetailItem);
				}
			}
			model.addAttribute("listOrders",listOrders);
			model.addAttribute("users",users);
			model.addAttribute("listOrderdetail",listOrderdetail);		
			return "user/purchaseHistory";			
		}else {
			Users user = new Users();
			model.addAttribute("users", user);
			model.addAttribute("action", "history");
			return "user/login";
		}
		
	}
}

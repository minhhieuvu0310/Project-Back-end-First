package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dao.CartItemDAO;
import app.dao.OrderdetailDAO;
import app.dao.OrdersDAO;
import app.dao.ProductDAO;
import app.entities.CartItem;
import app.entities.Orderdetail;
import app.entities.Orders;
import app.entities.Users;

@Controller
public class PaymentController {
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private OrderdetailDAO orderdetailDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CartItemDAO cartItemDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sf, false));
	}
	
	@RequestMapping(value = { "/initPayment" })
	public String initPayment(HttpSession session, Model model) {		
		Users users = (Users) session.getAttribute("users");
		if(users != null) {
			float paymentAmount = (float) session.getAttribute("total");
			model.addAttribute("users", users);
			model.addAttribute("total", paymentAmount);
			System.out.println("tong so tien trong gio hang la" + paymentAmount);
			return "user/checkout1";			
		}else {
			Users user = new Users();
			model.addAttribute("users", user);
			model.addAttribute("action", "payment");
			return "user/login";
		}	
	}
	
	@RequestMapping(value = { "/prePayment" })
	public String prePayment(HttpServletRequest request, HttpSession session, Model model) {		
		Users users = (Users) session.getAttribute("users");
		Date date = java.util.Calendar.getInstance().getTime();
		List<CartItem> listCart = (List<CartItem>) session.getAttribute("listCart");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        float total = Float.parseFloat(request.getParameter("total")) ;
        Orders orders = new Orders();
        orders.setTotalamount(total);
        orders.setUser(users);
        orders.setStatus(false);
        orders.setCreateddate(date);
        boolean result= false;
        if(name != "" && phone != "" && address != "") {
        	orders.setOrderName(name);
        	orders.setPhone(phone);
        	orders.setAddress(address);
        	orders.setEmail(email);
        	result = ordersDAO.insertOrder(orders);
        }else {
        	orders.setOrderName(users.getFullName());
        	orders.setPhone(users.getPhone());
        	orders.setAddress(users.getAddress());
        	orders.setEmail(users.getEmail());
        	result = ordersDAO.insertOrder(orders);
        }
        
        if(result) {
        	for (CartItem cartItem : listCart) {
				Orderdetail orderdetail = new Orderdetail();
				orderdetail.setOrders(orders);
				orderdetail.setProduct(cartItem.getProduct());
				productDAO.updatebuyItem(cartItem.getProduct(), cartItem.getQuantity());
				orderdetail.setPrice(cartItem.getProduct().getProductHasDiscount());
				orderdetail.setQuantity(cartItem.getQuantity());
				orderdetail.setAmount(cartItem.getQuantity() * cartItem.getProduct().getProductHasDiscount());
				orderdetail.setStatus(true);
				orderdetail.setNote(cartItem.getNote());
				orderdetail.setColor(cartItem.getColor());
				orderdetailDAO.insertOrderDetail(orderdetail);
			}
        	model.addAttribute("Id", orders.getOrdersId());
        	return "user/checkout2";
        }else {       	
        	model.addAttribute("error", "Xin lỗi bạn quá trình đặt hàng không thành công");
        	return "user/error";
        }        
	}
	
	@RequestMapping(value = { "/payment" })
	public String payment(HttpServletRequest request, HttpSession session, Model model) {
		List<CartItem> listCart = (List<CartItem>) session.getAttribute("listCart");
		String paymentmethod = request.getParameter("paymentmethod");
		Integer orderId = Integer.parseInt(request.getParameter("orderId")) ;
		Orders orders = ordersDAO.findOrdersById(orderId);
		orders.setPaymentmethod(paymentmethod);
		boolean bl = ordersDAO.updateOrder(orders);
        if(bl) {
        	for (CartItem cartItem : listCart) {
				cartItemDAO.DeleteCartItem(cartItem);
			}
        	//xóa các session đang lưu
    		session.removeAttribute("listCart");
            session.removeAttribute("total");
            model.addAttribute("orderId", orderId);
            return "user/orderSuccess";
        }else {
        	model.addAttribute("error", "Xin lỗi bạn quá trình đặt hàng không thành công");
        	return "user/error";
        }
	}
}

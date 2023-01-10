package app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dao.OrdersDAO;
import app.dao.User_RoleDAO;
import app.dao.UsersDAO;
import app.entities.Orders;
import app.entities.Users;

@Controller
public class AdminController {
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private User_RoleDAO user_roleDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	/**
	 *Xử Lý Đăng Nhập Vào Trang Admin
	 * @return
	 */
	@RequestMapping(value="initBackendLogin")
	public String initBackendLogin(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "admin/login";
	}
	/**
	 * Tiến Hành Đăng Nhập
	 */
	@RequestMapping(value = "backendLogin")
	public String BackendLogin(@ModelAttribute("users") Users users,HttpSession session, Model model) {
		boolean reslut = usersDAO.checkLogin(users);
		if(reslut) {
			Users user = usersDAO.getUsers(users);
			boolean reslut2 = user_roleDAO.checkRoleAdminOfUser(user);
			if(reslut2) {
				session.setAttribute("userNameAdmin", user.getFullName());				
				return "redirect:/homeBackend";
			}else {
				model.addAttribute("message" , "Xin lỗi tài khoản của bạn không được phép truy cập.");
				return "admin/login";
			}
		}else {
			model.addAttribute("message" , "Sai thông tin đăng nhập.");
			return "admin/login";
		}
	}
	
	/**
	 * Trang chủ Đăng Nhập
	 */
	@RequestMapping(value = "homeBackend")
	public String homeBackend(Model model, HttpSession session) {
		if(session.getAttribute("userNameAdmin") != null) {
			int totalUsers = usersDAO.getTotalUsers();
			Integer totalOrders = ordersDAO.getTotalOrders();
			List<Orders> allOrders = ordersDAO.getAllOrders();
			Float totalMoney = ordersDAO.getTotalMoney(allOrders);
			model.addAttribute("totalMoney", totalMoney);
			model.addAttribute("totalUsers", totalUsers);
			model.addAttribute("totalOrders", totalOrders);
			return "admin/index"; 
		}else {
			Users users = new Users();
			model.addAttribute("users", users);
			return "admin/login";
		}
	}
	/** 
	 * Lấy  Toàn bộ Danh Sách Danh Mục
	 */
	@RequestMapping(value = "getAllCatalog")
	public String getAllCatalog(Model model,HttpSession session) {
		if(session.getAttribute("userNameAdmin") != null){
            return "admin/catalogs";
      } else {
    	  Users users = new Users();
			model.addAttribute("users", users);
			return "admin/login";           
      }
		
	}
}

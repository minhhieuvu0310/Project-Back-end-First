package app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.dao.ProductDAO;
import app.entities.Cart;
import app.entities.Product;
import app.entities.Users;

@Controller
public class CartController {
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = "/cart")
	public String addcart(HttpSession session, Model model) {
		Users users = (Users) session.getAttribute("users");
		if (users != null) {
			List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
			Float total = (Float) session.getAttribute("total");
			model.addAttribute("listCart", listCart);
			model.addAttribute("total", total);
			return "user/cart";
		} else {
			return "redirect:/initLoginFontend";
		}

	}

	@RequestMapping(value = "/addcart")
	public String addcart(@RequestParam("productId") Integer productId, @RequestParam("quantity") Integer quantity,
			HttpServletRequest request, HttpSession session, Model model) {
		String option = request.getParameter("option--selected");
		String note = "";
		String color = "";
		if (option != null) {
			note = "Color : " + option + "; Quantity : " + quantity;
			color = option;
		}

		Users users = (Users) session.getAttribute("users");
		if (users != null) {
			// Lay san pham cua user trong session
			List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
			if (listCart == null) {
				// giỏ hàng chưa có sản phẩm thì thêm luôn sản phẩm vào giỏ hàng
				listCart = new ArrayList<>();
				listCart.add(new Cart(productDAO.getProductById(productId), quantity, color, note));
			} else {
				boolean flag = false;
				for (Cart cart : listCart) {
					if (cart.getProduct().getProductId() == productId && cart.getColor().equals(color)) {
						Integer a = cart.getQuantity();
						cart.setQuantity(quantity + a);
						flag = true;
						
					}
				}
				if (!flag) {
					listCart.add(new Cart(productDAO.getProductById(productId), quantity, color, note));
				}
			}
			System.out.println(option + " " + productId + " " + quantity + " " + listCart.size());
			System.out.println("tổng tiền của giỏ hàng là : " + getTotal(listCart));
			session.setAttribute("listCart", listCart);
			session.setAttribute("total", getTotal(listCart));
			return "redirect:/cart";
		} else {
			Users user = new Users();
			model.addAttribute("users", user);
			model.addAttribute("action", "productDetails");
			model.addAttribute("productId", productId);
			return "user/login";
		}

	}

	/**
	 * sửa sản phẩm trong giỏ hàng
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/updateCart")
	public String updatecart(HttpServletRequest request, HttpSession session) {
		// lấy danh sách sản phẩm khách hàng trong session
		List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
		// lấy danh sách sản lượng muốn mua
		String[] arrQuantity = request.getParameterValues("quantity");
		for (int i = 0; i < listCart.size(); i++) {
			listCart.get(i).setQuantity(Integer.parseInt(arrQuantity[i]));
			if (Integer.parseInt(arrQuantity[i]) == 0) {
				listCart.remove(i);
			}
		}
		session.setAttribute("listCart", listCart);
		session.setAttribute("total", getTotal(listCart));

		System.out.println("So luong san pham : " + listCart.size());
		System.out.println("mang so luong san pham : " + arrQuantity.length);
		return "redirect:/cart";
	}
	
	/**
     * xóa sản phẩm trong giỏ hàng
     * @param id
     * @param session
     * @return 
     */
	@RequestMapping(value = "/removeCart", method = RequestMethod.GET)
    public String removeCart(@RequestParam("productId") Integer productId, HttpSession session) {
		
		// lấy danh sách sản phẩm khách hàng trong session
		List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
		if(listCart != null) {
			for (int i = 0; i < listCart.size(); i++) {
				if(listCart.get(i).getProduct().getProductId() == productId) {
					listCart.remove(i).getProduct().getProductId();
					break;
				}
			}
		}
		session.setAttribute("listCart", listCart);
        session.setAttribute("total", getTotal(listCart));
		System.out.println("Id cua san pham la : " + productId);
		return "redirect:/cart";
	}

	/**
	 * Tính tổng tiền trong giỏ hàng
	 * 
	 * @param listCart
	 * @return
	 */
	public float getTotal(List<Cart> listCart) {
		float total = 0;
		for (Cart cart : listCart) {
			if (cart.getProduct().getDiscount() == 0) {
				total += cart.getQuantity() * cart.getProduct().getPriceOutput();
			} else {
				total += cart.getQuantity() * (cart.getProduct().getPriceOutput()
						- cart.getProduct().getPriceOutput() * cart.getProduct().getDiscount());
			}
		}
		return total;
	}
}

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.dao.CartDAO;
import app.dao.CartItemDAO;
import app.dao.ProductDAO;
import app.entities.Cart;
import app.entities.CartItem;
import app.entities.Product;
import app.entities.Users;

@Controller
public class CartController {
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CartItemDAO cartItemDAO;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sf, false));
	}

	@RequestMapping(value = "/cart")
	public String addcart(HttpSession session, Model model) {
		Users users = (Users) session.getAttribute("users");
		if (users != null) {
			Cart cartOfUser = cartDAO.CartOfUser(users.getUserId());
			List<CartItem> listCart = cartItemDAO.getAllCartItemByCartId(cartOfUser.getCartId());
			Float total = getTotal(listCart);
			model.addAttribute("listCart", listCart);
			model.addAttribute("total", total);
			session.setAttribute("listCart", listCart);
			session.setAttribute("total", getTotal(listCart));
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
		Boolean bl = false;
		if (users != null) {
			// Lấy ra giỏ hàng của user
			Cart cartOfUser = cartDAO.CartOfUser(users.getUserId());
			// Lấy ra tất cả sản phẩm có trong giỏ hàng
			List<CartItem> listCart = cartItemDAO.getAllCartItemByCartId(cartOfUser.getCartId());
			// Lấy ra sản phẩm khách hàng muốn thêm
			Product product = productDAO.getProductById(productId);
			Date date = java.util.Calendar.getInstance().getTime();
			if (listCart == null || listCart.isEmpty()) {
				CartItem cartItem = new CartItem();
				cartItem.setCart(cartOfUser);
				cartItem.setProduct(product);
				cartItem.setQuantity(quantity);
				cartItem.setPrice(product.getProductHasDiscount());
				cartItem.setAmount(product.getProductHasDiscount() * quantity);;
				cartItem.setCreated(date);
				cartItem.setNote(note);
				cartItem.setColor(color);
				cartItem.setStatus(false);
				bl = cartItemDAO.InserCartItem(cartItem);
				//
			} else {
				boolean flag = false;
				for (CartItem cartItem : listCart) {
					if (cartItem.getProduct().getProductId() == productId && cartItem.getColor().equals(color)) {
						Integer a = cartItem.getQuantity();
						cartItem.setQuantity(quantity + a);
						cartItem.setAmount((quantity + a) * product.getProductHasDiscount());						
						cartItem.setUpdated(date);
						String noteUpdate = "Color : " + option + "; Quantity : " + (quantity + a);
						cartItem.setNote(noteUpdate);
						bl = cartItemDAO.UpdateCartItem(cartItem);
						flag = true;
					}
				}
				if (!flag) {
					CartItem cartItem = new CartItem();
					cartItem.setCart(cartOfUser);
					cartItem.setProduct(product);
					cartItem.setQuantity(quantity);
					cartItem.setPrice(product.getProductHasDiscount());
					cartItem.setAmount(product.getProductHasDiscount() * quantity);
					cartItem.setCreated(date);
					cartItem.setNote(note);
					cartItem.setColor(color);
					cartItem.setStatus(false);
					bl = cartItemDAO.InserCartItem(cartItem);
				}
				//
			}
			if (bl) {
				return "redirect:/cart";
			}
			else {
				model.addAttribute("error", "Thêm Sản phẩm vào giỏ hàng không thành công");
				model.addAttribute("productId", productId);
				return "addcart";
			}

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
		Users users = (Users) session.getAttribute("users");
		Cart cartOfUser = cartDAO.CartOfUser(users.getUserId());
		List<CartItem> listCart = cartItemDAO.getAllCartItemByCartId(cartOfUser.getCartId());
		Date date = java.util.Calendar.getInstance().getTime();
		// lấy danh sách sản lượng muốn mua
		String[] arrQuantity = request.getParameterValues("quantity");
		for (int i = 0; i < listCart.size(); i++) {
			listCart.get(i).setQuantity(Integer.parseInt(arrQuantity[i]));
			listCart.get(i).setUpdated(date);
			cartItemDAO.UpdateCartItem(listCart.get(i));
			if (Integer.parseInt(arrQuantity[i]) == 0) {
				cartItemDAO.DeleteCartItem(listCart.get(i));
			}
		}
		System.out.println("So luong san pham : " + listCart.size());
		System.out.println("mang so luong san pham : " + arrQuantity.length);
		return "redirect:/cart";
	}

	/**
	 * xóa sản phẩm trong giỏ hàng
	 * 
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/removeCart", method = RequestMethod.GET)
	public String removeCart(@RequestParam("productId") Integer productId, HttpSession session) {

		// lấy danh sách sản phẩm khách hàng trong session
		Users users = (Users) session.getAttribute("users");
		Cart cartOfUser = cartDAO.CartOfUser(users.getUserId());
		List<CartItem> listCart = cartItemDAO.getAllCartItemByCartId(cartOfUser.getCartId());
		if (listCart != null) {
			for (int i = 0; i < listCart.size(); i++) {
				if (listCart.get(i).getProduct().getProductId() == productId) {
					cartItemDAO.DeleteCartItem(listCart.get(i));
					break;
				}
			}
		}		
		
		System.out.println("Id cua san pham la : " + productId);
		return "redirect:/cart";
	}

	/**
	 * Tính tổng tiền trong giỏ hàng
	 * 
	 * @param listCart
	 * @return
	 */
	public float getTotal(List<CartItem> listCart) {
		float total = 0;
		for (CartItem cartItem : listCart) {
			if (cartItem.getProduct().getDiscount() == 0) {
				total += cartItem.getQuantity() * cartItem.getProduct().getPriceOutput();
			} else {
				total += cartItem.getQuantity() * (cartItem.getProduct().getPriceOutput()
						- cartItem.getProduct().getPriceOutput() * cartItem.getProduct().getDiscount());
			}
		}
		return total;
	}
}

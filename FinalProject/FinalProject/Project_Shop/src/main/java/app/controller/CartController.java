package app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
	@RequestMapping(value="/addcart")
	public String addcart(@RequestParam("productId") Integer productId,HttpServletRequest request) {
		String option = request.getParameter("option--selected");
		System.out.println(option + ' ' + productId);
		return null;
	}
}

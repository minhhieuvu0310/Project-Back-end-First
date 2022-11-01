package app.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;

import app.dao.CataLogsDAO;
import app.dao.ImageLinkDAO;
import app.dao.ProductColorDAO;
import app.dao.ProductDAO;
import app.dao.ProviderDAO;
import app.dao.UsersDAO;
import app.entities.CataLogs;
import app.entities.Product;
import app.entities.Provider;
import app.entities.Users;

@Controller
public class HomeController {
	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProviderDAO providerDAO;

	@Autowired
	private CataLogsDAO cataLogsDAO;
	
	@Autowired
	private ImageLinkDAO imageLinkDAO;
	
	@Autowired
	private ProductColorDAO productColorDAO;
	
	@Autowired
	private UsersDAO usersDAO;

	@RequestMapping(value = { "/", "home" })
	public String home(@RequestParam(name = "page", required = false) Integer page, Model model) {
		Integer offset, maxResult;
		maxResult = 10;
		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		List<Product> lstproducts = productDAO.getAllProduct();
		List<Product> products = productDAO.listProPa(lstproducts, offset, maxResult);
		List<CataLogs> allcatalog = cataLogsDAO.getAllCataLog();
		List<Provider> allProvider = providerDAO.getAllProvider();
		model.addAttribute("listProduct", products);
		model.addAttribute("listProvider", allProvider);
		model.addAttribute("listCatalogs", allcatalog);

		Long totalCom = (long) productDAO.getAllProduct().size();
		int totalPage = (int) (totalCom / maxResult);
		totalPage = totalPage + (totalCom % maxResult == 0 ? 0 : 1);
		List<Integer> listPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			listPage.add(i);
		}
		model.addAttribute("listPage", listPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("nameController", "home");
		return "user/index";
	}

	@RequestMapping(value = { "/getAllProductSearch" })
	public String getAllProductSearch(@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "providerName", required = false) String providerName,
			@RequestParam(name = "catalogName", required = false) String catalogName,
			@RequestParam(name = "sortBy", required = false) String sortBy,
			@RequestParam(name = "priceShortest", required = false) String priceShortest,
			@RequestParam(name = "priceTallest", required = false) String priceTallest,
			@RequestParam(name = "KeySearch", required = false) String KeySearch,
			@RequestParam(name = "sortByPrice", required = false) String sortByPrice, Model model) {
		Integer offset, maxResult;

		maxResult = 10;

		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		
		//Lấy sản phẩm theo Provider
		List<Product> productsProvider = new ArrayList<Product>();
		List<String> lstProvider = new ArrayList<String>();
		if (providerName == null || providerName.isEmpty()) {
			productsProvider = null;
		} else {
			String items[] = providerName.split(",");
			for (int i = 0; i < items.length; i++) {
				lstProvider.add(items[i]);
			}
			productsProvider = productDAO.getAllProductByProvider(lstProvider);
		}

		//Lấy sản Phẩm theo CataLog
		List<Product> productsCatalog = new ArrayList<Product>();
		List<String> lstCataLog = new ArrayList<String>();
		if (catalogName == null || catalogName.isEmpty()) {
			productsCatalog = null;
		} else {
			String items[] = catalogName.split(",");
			for (int i = 0; i < items.length; i++) {
				lstCataLog.add(items[i]);
			}
			productsCatalog = productDAO.getAllProductByCatalog(lstCataLog);
		}

		//Lấy sản phẩm theo giá
		List<Product> productsPrice = new ArrayList<Product>();
		if (priceShortest == null && priceTallest == null) {
			productsPrice = null;
		} else if (priceShortest != null && priceTallest == null) {
			Float MinPrice = Float.parseFloat(priceShortest);
			productsPrice = productDAO.getAllProductByPriceShortest(MinPrice);
		} else if (priceShortest == null && priceTallest != null) {
			Float MaxPrice = Float.parseFloat(priceTallest);
			productsPrice = productDAO.getAllProductByPriceTallest(MaxPrice);
		} else if (priceShortest != null && priceTallest != null) {
			Float MinPrice = Float.parseFloat(priceShortest);
			Float MaxPrice = Float.parseFloat(priceTallest);
			productsPrice = productDAO.getAllProductByPrice(MinPrice,MaxPrice);
		}
		
		//Lấy sản phẩm theo sắp xếp
		
		List<Product> productsSortBy = new ArrayList<Product>();
		if(sortBy == null ) {
			productsSortBy = null;
		}else if(sortBy.equals("new") || sortBy.equals("sales")|| sortBy.equals("relevancy")) {
			productsSortBy = productDAO.getAllProductBySortBy(sortBy);
		}else if(sortBy.equals("Price")) {
			productsSortBy = productDAO.getAllProductBySortPrice(sortByPrice);
		}
		
		//Tìm kiếm theo KeySearch
		List<Product> productsKeys = new ArrayList<Product>();
		if(KeySearch == null) {
			productsKeys = null;
		}else {
			productsKeys = productDAO.getAllProductByKey(KeySearch);
		}
		
		List<Product> productAll = productDAO.getAllProduct();
		List<Product> lst0 = productDAO.lstPro(productAll, productsKeys);
		List<Product> lst1 = productDAO.lstPro(lst0, productsProvider);
		List<Product> lst2 = productDAO.lstPro(lst1, productsCatalog);
		List<Product> lst3 = productDAO.lstPro(lst2, productsPrice);
		List<Product> lst4 = productDAO.lstPro(lst3, productsSortBy);
		List<Product> lstproduct = lst4;
		List<Product> products = productDAO.listProPa(lstproduct, offset, maxResult);
		List<CataLogs> allcatalog = cataLogsDAO.getAllCataLog();
		List<Provider> allProvider = providerDAO.getAllProvider();

		model.addAttribute("listProduct", products);
		model.addAttribute("listProvider", allProvider);
		model.addAttribute("listCatalogs", allcatalog);

		Long totalCom = (long) lstproduct.size();
		int totalPage = (int) (totalCom / maxResult);
		totalPage = totalPage + (totalCom % maxResult == 0 ? 0 : 1);
		List<Integer> listPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			listPage.add(i);
		}
		model.addAttribute("listPage", listPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("nameController", "getAllProductSearch");
		model.addAttribute("lstProvider", lstProvider);
		model.addAttribute("lstCataLog", lstCataLog);
		model.addAttribute("priceShortest", priceShortest);
		model.addAttribute("priceTallest", priceTallest);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("sortByPrice", sortByPrice);
		model.addAttribute("KeySearch", KeySearch);


		System.out.println("ProciderId la : " + lstProvider);
		System.out.println("catalogName la : " + lstCataLog);
		System.out.println("priceShortest la : " + priceShortest);
		System.out.println("priceTallest la : " + priceTallest);
		System.out.println("sortBy la : " + sortBy);
		System.out.println("sortByPrice la : " + sortByPrice);
		System.out.println("Page : " + page);
		System.out.println("KeySearch : " + KeySearch);
		return "user/index";
	}
	
	@RequestMapping(value = { "/productDetails" })
	public String Product(@PathParam("productId") Integer productId , Model model) {
		Product productById = productDAO.getProductById(productId);
		List<String> allImageProduct = imageLinkDAO.getAllImageProduct(productId);
		List<String> allColor = productColorDAO.getAllColorById(productId);
		model.addAttribute("allImages",allImageProduct);
		model.addAttribute("product", productById);
		model.addAttribute("allcolor", allColor);
		System.out.println("productId : " + productId);
		System.out.println("all image : " + allImageProduct);
		System.out.println("allColor : " + allColor);
		return "user/ProductDetails";
	}
	
	@RequestMapping(value = { "/initLoginFontend" })
	public String initLoginFontend(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "user/login";
	}
	
	@RequestMapping(value = { "/LoginFontend" })
	public String initLoginFontend(@ModelAttribute("users")  Users users,HttpSession session, Model model ) {
		System.out.println(users.getUserName() + ' ' + users.getPassWord() + ' '+ users.getFullName());
		if(usersDAO.checkLogin(users)) {
			Users userinfo = usersDAO.getUsers(users);
			session.setAttribute("users", userinfo);
			return "redirect:/home";
		}else {
			model.addAttribute("error", "Tên Đăng Nhập Hoặc mật khẩu không đúng!");
			
			return "user/login";
		}	
		
	}
	
}

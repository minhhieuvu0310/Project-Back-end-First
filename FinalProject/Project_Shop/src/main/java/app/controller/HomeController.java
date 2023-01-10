package app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import app.dao.CartDAO;
import app.dao.CartItemDAO;
import app.dao.CataLogsDAO;
import app.dao.ImageLinkDAO;
import app.dao.ProductColorDAO;
import app.dao.ProductDAO;
import app.dao.ProviderDAO;
import app.dao.User_RoleDAO;
import app.dao.UsersDAO;
import app.entities.Cart;
import app.entities.CartItem;
import app.entities.CataLogs;
import app.entities.Product;
import app.entities.Provider;
import app.entities.Role;
import app.entities.User_Role;
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

	@Autowired
	private CartDAO cartDAO;

	@Autowired
	private CartItemDAO cartItemDAO;

	@Autowired
	private User_RoleDAO user_roleDAO;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sf, false));
	}

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

		// Lấy sản phẩm theo Provider
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

		// Lấy sản Phẩm theo CataLog
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

		// Lấy sản phẩm theo giá
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
			productsPrice = productDAO.getAllProductByPrice(MinPrice, MaxPrice);
		}

		// Lấy sản phẩm theo sắp xếp

		List<Product> productsSortBy = new ArrayList<Product>();
		if (sortBy == null) {
			productsSortBy = null;
		} else if (sortBy.equals("new") || sortBy.equals("sales") || sortBy.equals("relevancy")) {
			productsSortBy = productDAO.getAllProductBySortBy(sortBy);
		} else if (sortBy.equals("Price")) {
			productsSortBy = productDAO.getAllProductBySortPrice(sortByPrice);
		}

		// Tìm kiếm theo KeySearch
		List<Product> productsKeys = new ArrayList<Product>();
		if (KeySearch == null) {
			productsKeys = null;
		} else {
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
	public String productDetails(@PathParam("productId") Integer productId, Model model) {
		Product productById = productDAO.getProductById(productId);
		List<String> allImageProduct = imageLinkDAO.getAllImageProduct(productId);
		List<String> allColor = productColorDAO.getAllColorById(productId);
		model.addAttribute("allImages", allImageProduct);
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
	public String initLoginFontend(@ModelAttribute("users") Users users, HttpSession session,
			HttpServletRequest request, Model model) {
		String action = request.getParameter("action");
		if (usersDAO.checkLogin(users) && !users.getUserName().isEmpty() && !users.getPassWord().isEmpty()) {
			Users userinfo = usersDAO.getUsers(users);
			session.setAttribute("users", userinfo);
			Cart cartOfUser = cartDAO.CartOfUser(userinfo.getUserId());
			List<CartItem> listCart = cartItemDAO.getAllCartItemByCartId(cartOfUser.getCartId());
			session.setAttribute("listCart", listCart);
			if (action.equals("productDetails")) {
				int productId = 0;
				if (request.getParameter("productId") != null) {
					productId = Integer.parseInt(request.getParameter("productId"));
				}
				model.addAttribute("productId", productId);
				return "redirect:/productDetails";
			} else if (action.equals("payment")) {
				return "redirect:/cart";
			} else if (action.equals("history")) {
				return "redirect:/history";
			} else if (action.equals("myAccount")) {
				return "redirect:/initUpdateMyAccount";
			} else {
				return "redirect:/home";
			}

		} else if (users.getUserName().isEmpty() || users.getPassWord().isEmpty()) {
			model.addAttribute("error", "Vui Lòng Điền Đầy Đủ Thông Tin!");
			if (action.equals("productDetails")) {
				model.addAttribute("action", action);
				model.addAttribute("productId", Integer.parseInt(request.getParameter("productId")));
			}
			return "user/login";
		} else {
			model.addAttribute("error", "Tên Đăng Nhập Hoặc mật khẩu không đúng !");
			if (action.equals("productDetails")) {
				model.addAttribute("action", action);
				model.addAttribute("productId", Integer.parseInt(request.getParameter("productId")));
			}
			return "user/login";
		}

	}

	@RequestMapping(value = { "/LogoutFontend" })
	public String LogoutFontend(Model model, HttpSession session, HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home";
	}

	@RequestMapping(value = { "/initRegister" })
	public String initRegister(Model model, HttpSession session, HttpServletRequest request) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "user/register";
	}

	@RequestMapping(value = { "/RegisterFontend" })
	public String RegisterFontend(@Valid @ModelAttribute("users") Users users, BindingResult result, Model model,
			HttpSession session, HttpServletRequest request) {
		boolean bl = usersDAO.checkUserName(users);
		boolean bl2 = usersDAO.checkUserEmail(users);
		boolean bl3 = usersDAO.checkUserPhone(users);
		String errUserName = (bl) ? " Tên đăng nhập đã tồn tại " : "";
		String errUserEmail = (bl2) ? " Email đã tồn tại " : "";
		String errUserPhone = (bl3) ? " Số điện thoại đã tồn tại " : "";
		List<String> lsterror = new ArrayList<String>(Arrays.asList(errUserName, errUserPhone, errUserEmail));
		lsterror.removeAll(Arrays.asList("", null));
		String error = ErrorRegister(lsterror);
		if (!bl && !bl2 && !bl3 && !result.hasErrors()) {
			users.setStatus(true);
			users.setUserImage("default.png");
			Date date = java.util.Calendar.getInstance().getTime();
			users.setCreated(date);
			// tạo tài khoản
			boolean insertUsers = usersDAO.insertUsers(users);
			// tạo giỏ hàng
			Cart cart = new Cart();
			cart.setUser(users);
			cart.setCreated(date);
			cart.setStatus(true);
			Boolean insertCart = cartDAO.InsertCart(cart);
			//tạo quyền cho tài khoản
			User_Role user_role = new User_Role();
			Role role = new Role();
			role.setRoleId(1);
			user_role.setRole(role);
			user_role.setUser(users);
			user_role.getUser().setUserId(users.getUserId());
			boolean insertRoleForUser = user_roleDAO.insertRoleForUser(user_role);
			if (insertUsers && insertCart && insertRoleForUser) {
				session.setAttribute("users", users);
				return "redirect:/home";
			} else {
				model.addAttribute("error", "Đã Xảy ra lỗi khi đăng Nhập !!!");
				return "user/error";
			}
		} else {
			model.addAttribute("error", error);
			return "user/register";
		}
	}

	@RequestMapping(value = { "/initUpdateMyAccount" })
	public String initUpdateMyAccount(Model model, HttpSession session) {
		Users users = (Users) session.getAttribute("users");
		if (users != null) {
			model.addAttribute("users", users);
			return "user/myAccount";
		} else {
			Users user = new Users();
			model.addAttribute("users", user);
			model.addAttribute("action", "myAccount");
			return "user/login";
		}
	}

	@RequestMapping(value = { "/UpdateMyAccount" })
	public String UpdateMyAccount(Model model, HttpSession session, @ModelAttribute("users") Users users,
			HttpServletRequest request, @RequestParam(name = "imageUser", required = false) MultipartFile imageFile) {
		Users usersUpdate = (Users) session.getAttribute("users");
		Date date = java.util.Calendar.getInstance().getTime();
		if (imageFile.getOriginalFilename() != "") {
			String path = request.getServletContext().getRealPath("resources/user/image/User");
			File f = new File(path);

			File dest = new File(f.getAbsolutePath() + "/" + imageFile.getOriginalFilename());
			if (!dest.exists()) {
				try {
					Files.write(dest.toPath(), imageFile.getBytes(), StandardOpenOption.CREATE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			usersUpdate.setUserImage(imageFile.getOriginalFilename());
		}
		usersUpdate.setFullName(users.getFullName());
		usersUpdate.setEmail(users.getEmail());
		usersUpdate.setPhone(users.getPhone());
		usersUpdate.setAddress(users.getAddress());
		usersUpdate.setUpdated(date);
		boolean bl = usersDAO.updateUsers(usersUpdate);
		if (bl) {
			session.setAttribute("users", usersUpdate);
			model.addAttribute("users", usersUpdate);
			return "user/myAccount";
		} else {
			System.out.println("userId : " + users.getUserId());
			model.addAttribute("error", "Cập Nhật Hồ Sơ Không Thành Công");
			return "user/error";
		}
	}

	public String ErrorRegister(List<String> lsterror) {
		String error = "";
		for (int i = 0; i < lsterror.size(); i++) {
			error += lsterror.get(i) + (i < lsterror.size() - 1 ? "," : "");
		}
		return error;
	}
}

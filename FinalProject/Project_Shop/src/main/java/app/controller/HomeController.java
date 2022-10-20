package app.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.dao.CataLogsDAO;
import app.dao.ProductDAO;
import app.dao.ProviderDAO;
import app.entities.CataLogs;
import app.entities.Product;
import app.entities.Provider;

@Controller
public class HomeController {
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProviderDAO providerDAO;
	
	@Autowired
	private CataLogsDAO cataLogsDAO;


	@RequestMapping(value = { "/", "home" })
	public String home(@RequestParam(name = "page", required = false) Integer page, Model model) {
		Integer offset, maxResult;

		maxResult = 10;

		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		List<Product> products = productDAO.getAllProduct(offset, maxResult);
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
	@RequestMapping(value = {"/getAllProductSearch"})
	public String getAllProductSearch(@RequestParam(name = "page", required = false) Integer page,@RequestParam(name = "providerName", required = false) String providerName
					,@RequestParam(name = "catalogName" , required = false) String catalogName
					,@RequestParam(name = "sortBy" , required = false) String sortBy
					, Model model) {
		Integer offset, maxResult;

		maxResult = 10;

		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		List<String> lstProvider = new ArrayList<String>();
		if (providerName == null || providerName.isEmpty()) {
			lstProvider = null;
		}else {
			String items[] = providerName.split(",");
			for (int i = 0; i < items.length; i++) {
				lstProvider.add(items[i]);
			}
		}
		
		List<String> lstCataLog = new ArrayList<String>();
		if (catalogName == null || catalogName.isEmpty()) {
			catalogName = null;
		}else {
			String items[] = catalogName.split(",");
			for (int i = 0; i < items.length; i++) {
				lstCataLog.add(items[i]);
			}
		}
		
		List<Product> products = productDAO.getAllProduct(offset, maxResult);
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
		model.addAttribute("lstProvider",lstProvider);
		model.addAttribute("lstCataLog",lstCataLog);
		System.out.println("ProciderId la : "+lstProvider);
		System.out.println("catalogName la : "+lstCataLog);
		System.out.println("sortBy : " + sortBy);
		return "user/index";
	}
}

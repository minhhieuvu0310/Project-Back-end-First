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

import app.dao.ComputerDAO;
import app.dao.ProdureDAO;
import app.entities.Computer;
import app.entities.Produre;

@Controller
public class HomeController {
	@Autowired
	private ComputerDAO computerDAO;

	@Autowired
	private ProdureDAO produreDAO;

	@RequestMapping(value = { "/", "home" })
	public String home(@RequestParam(name = "page", required = false) Integer page, Model model) {
		Integer offset, maxResult;

		maxResult = 10;

		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		List<Computer> computer = computerDAO.getComputer(offset, maxResult);
		List<String> allAddress = computerDAO.getAllAddress();
		List<Produre> allProdure = produreDAO.getAllProdure();
		model.addAttribute("list", computer);
		model.addAttribute("listPro", allProdure);
		model.addAttribute("listAdd", allAddress);

		Long totalCom = computerDAO.getTotalComputer();
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

	@RequestMapping(value = { "/listComputerByProdureName" })
	public String listComputerByProdureName(@RequestParam(name = "page", required = false) Integer page,
			@PathParam("proName") String proName, Model model) {
		Integer offset, maxResult;

		maxResult = 10;

		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		List<Computer> computer = computerDAO.getComputerByProdureName(proName, offset, maxResult);
		List<Produre> allProdure = produreDAO.getAllProdure();
		List<String> allAddress = computerDAO.getAllAddress();
		model.addAttribute("listAdd", allAddress);
		model.addAttribute("list", computer);
		model.addAttribute("listPro", allProdure);

		Long totalCom = computerDAO.getTotalComputerByProdureName(proName);
		int totalPage = (int) (totalCom / maxResult);
		totalPage = totalPage + (totalCom % maxResult == 0 ? 0 : 1);
		List<Integer> listPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			listPage.add(i);
		}
		model.addAttribute("listPage", listPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("proName", proName);
		model.addAttribute("nameController", "listComputerByProdureName");
		return "user/index";
	}

	@RequestMapping(value = { "/listComputerByAdderss" })
	public String listComputerByAdderss(@RequestParam(name = "page", required = false) Integer page,
			@PathParam("c") String address, Model model) {
		Integer offset, maxResult;

		maxResult = 10;

		if (page == null) {
			offset = 0;
		} else {
			offset = (page - 1) * maxResult;
		}
		List<Computer> computer = computerDAO.getComputerByAddress(address, offset, maxResult);
		List<Produre> allProdure = produreDAO.getAllProdure();
		List<String> allAddress = computerDAO.getAllAddress();
		model.addAttribute("listAdd", allAddress);
		model.addAttribute("list", computer);
		model.addAttribute("listPro", allProdure);

		Long totalCom = computerDAO.getTotalComputerByAddress(address);
		int totalPage = (int) (totalCom / maxResult);
		totalPage = totalPage + (totalCom % maxResult == 0 ? 0 : 1);
		List<Integer> listPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			listPage.add(i);
		}
		model.addAttribute("listPage", listPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("address", address);
		model.addAttribute("nameController", "listComputerByAdderss");
		return "user/index";
	}

	@RequestMapping(value = "/listComputerBySort")
	@ResponseBody
	public ModelAndView listComputerBySort(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// Xử lý phân trang
		Integer offset, maxResult;
		// trang page hiện tại
		String pagestr = request.getParameter("page");
		Integer page = null;
		Long totalCom = null;
		maxResult = 10;

		if (pagestr == null) {
			offset = 0;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
			offset = (page - 1) * maxResult;
		}

		String listId_str = request.getParameter("listId");
		String listAddress_str = request.getParameter("listAddress");
		String priceShortest_str = request.getParameter("priceShortest");
		String priceTallest_str = request.getParameter("priceTallest");
		String sortBy = request.getParameter("sortBy");
		List<Integer> listId = new ArrayList<Integer>();
		List<String> listAddress = new ArrayList<String>();
		List<Computer> computer = new ArrayList<Computer>();
		// giá thấp nhất
		float priceShortest = (!priceShortest_str.isEmpty() && priceShortest_str != null)
				? Float.parseFloat(priceShortest_str)
				: -1;
		// giá cao nhất
		float priceTallest = (!priceTallest_str.isEmpty() && priceTallest_str != null)
				? Float.parseFloat(priceTallest_str)
				: -1;
		if (listId_str == null || listId_str.isEmpty()) {
			listId = null;
		} else {
			String items[] = listId_str.split(",");
			for (int i = 0; i < items.length; i++) {
				Integer item = Integer.parseInt(items[i]);
				listId.add(item);
			}
		}

		if (listAddress_str == null || listAddress_str.isEmpty()) {
			listAddress = null;
		} else {
			String items[] = listAddress_str.split(",");
			for (int i = 0; i < items.length; i++) {
				listAddress.add(items[i]);
			}
		}

		if (sortBy.equals("relevancy")) {
			if (priceShortest == -1 && priceTallest == -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputer(offset, maxResult);
					totalCom = (long) computerDAO.getComputer().size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerBylstAddress(listAddress, offset, maxResult);
					totalCom = (long) computerDAO.getComputerBylstAddress(listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerBylstProdureId(listId, offset, maxResult);
					totalCom = (long) computerDAO.getComputerBylstProdureId(listId).size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerBylstProdureIAndlstAddress(listId, listAddress, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerBylstProdureIAndlstAddress(listId, listAddress).size();
				}
			} else if (priceShortest != -1 && priceTallest == -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerByPriceShortest(priceShortest, offset, maxResult);
					totalCom = (long) computerDAO.getComputerByPriceShortest(priceShortest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerByPriceShortestAndlstAddress(priceShortest, listAddress, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerByPriceShortestAndlstAddress(priceShortest, listAddress)
							.size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerByPriceShortestAndlstProdureId(priceShortest, listId, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerByPriceShortestAndlstProdureId(priceShortest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerByPriceShortest_lstAddress_lstId(priceShortest, listAddress,
							listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerByPriceShortest_lstAddress_lstId(priceShortest, listAddress, listId).size();
				}
			} else if (priceShortest == -1 && priceTallest != -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerByPriceTallest(priceTallest, offset, maxResult);
					totalCom = (long) computerDAO.getComputerByPriceTallest(priceTallest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerByPriceTallestAndlstAddress(priceTallest, listAddress, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerByPriceTallestAndlstAddress(priceTallest, listAddress)
							.size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerByPriceTallestAndlstProdureId(priceTallest, listId, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerByPriceTallestAndlstProdureId(priceTallest, listId).size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerByPriceTallest_lstAddress_lstId(priceTallest, listAddress, listId,
							offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerByPriceTallest_lstAddress_lstId(priceTallest, listAddress, listId).size();
				}
			} else {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest,
							offset, maxResult);
					totalCom = (long) computerDAO.getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest)
							.size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerByPriceTallestAndPriceShortest__lstAddress(priceShortest,
							priceTallest, listAddress, offset, maxResult);
					totalCom = (long) computerDAO.getComputerByPriceTallestAndPriceShortest__lstAddress(priceShortest,
							priceTallest, listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerByPriceTallestAndPriceShortest__lstId(priceShortest, priceTallest,
							listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerByPriceTallestAndPriceShortest__lstId(priceShortest, priceTallest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(priceShortest,
							priceTallest, listAddress, listId, offset, maxResult);
					totalCom = (long) computerDAO.getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(
							priceShortest, priceTallest, listAddress, listId).size();
				}
			}
		} else if (sortBy.equals("new")) {
			if (priceShortest == -1 && priceTallest == -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerNew(offset, maxResult);
					totalCom = (long) computerDAO.getComputerNew().size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerBylstAddressNew(listAddress, offset, maxResult);
					totalCom = (long) computerDAO.getComputerBylstAddressNew(listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerBylstProdureIdNew(listId, offset, maxResult);
					totalCom = (long) computerDAO.getComputerBylstProdureIdNew(listId).size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerBylstProdureIAndlstAddressNew(listId, listAddress, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerBylstProdureIAndlstAddressNew(listId, listAddress).size();
				}
			} else if (priceShortest != -1 && priceTallest == -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerNewByPriceShortest(priceShortest, offset, maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceShortest(priceShortest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerNewByPriceShortestAndlstAddress(priceShortest, listAddress,
							offset, maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceShortestAndlstAddress(priceShortest, listAddress)
							.size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerNewByPriceShortestAndlstProdureId(priceShortest, listId, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceShortestAndlstProdureId(priceShortest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerNewByPriceShortest_lstAddress_lstId(priceShortest, listAddress,
							listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerNewByPriceShortest_lstAddress_lstId(priceShortest, listAddress, listId).size();
				}
			} else if (priceShortest == -1 && priceTallest != -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerNewByPriceTallest(priceTallest, offset, maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceTallest(priceTallest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerNewByPriceTallestAndlstAddress(priceTallest, listAddress, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceTallestAndlstAddress(priceTallest, listAddress)
							.size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerNewByPriceTallestAndlstProdureId(priceTallest, listId, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceTallestAndlstProdureId(priceTallest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerNewByPriceTallest_lstAddress_lstId(priceTallest, listAddress,
							listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerNewByPriceTallest_lstAddress_lstId(priceTallest, listAddress, listId).size();
				}
			} else {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerNewByPriceTallestAndPriceShortest(priceShortest, priceTallest,
							offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerNewByPriceTallestAndPriceShortest(priceShortest, priceTallest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerNewByPriceTallestAndPriceShortest__lstAddress(priceShortest,
							priceTallest, listAddress, offset, maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceTallestAndPriceShortest__lstAddress(
							priceShortest, priceTallest, listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerNewByPriceTallestAndPriceShortest__lstId(priceShortest,
							priceTallest, listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerNewByPriceTallestAndPriceShortest__lstId(priceShortest, priceTallest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerNewByPriceTallestAndPriceShortest_lstAddress_lstId(priceShortest,
							priceTallest, listAddress, listId, offset, maxResult);
					totalCom = (long) computerDAO.getComputerNewByPriceTallestAndPriceShortest_lstAddress_lstId(
							priceShortest, priceTallest, listAddress, listId).size();
				}
			}
		} else if (sortBy.equals("sales")) {
			if (priceShortest == -1 && priceTallest == -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerSales(offset, maxResult);
					totalCom = (long) computerDAO.getComputerSales().size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerSalesBylstAddress(listAddress, offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesBylstAddress(listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerSalesBylstProdureId(listId, offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesBylstProdureId(listId).size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerSalesBylstProdureIAndlstAddress(listId, listAddress, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerSalesBylstProdureIAndlstAddress(listId, listAddress)
							.size();
				}
			} else if (priceShortest != -1 && priceTallest == -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerSalesByPriceShortest(priceShortest, offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceShortest(priceShortest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerSalesByPriceShortestAndlstAddress(priceShortest, listAddress,
							offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerSalesByPriceShortestAndlstAddress(priceShortest, listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerSalesByPriceShortestAndlstProdureId(priceShortest, listId, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceShortestAndlstProdureId(priceShortest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerSalesByPriceShortest_lstAddress_lstId(priceShortest, listAddress,
							listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerSalesByPriceShortest_lstAddress_lstId(priceShortest, listAddress, listId)
							.size();
				}
			} else if (priceShortest == -1 && priceTallest != -1) {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerSalesByPriceTallest(priceTallest, offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceTallest(priceTallest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerSalesByPriceTallestAndlstAddress(priceTallest, listAddress,
							offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceTallestAndlstAddress(priceTallest, listAddress)
							.size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerSalesByPriceTallestAndlstProdureId(priceTallest, listId, offset,
							maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceTallestAndlstProdureId(priceTallest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerSalesByPriceTallest_lstAddress_lstId(priceTallest, listAddress,
							listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerSalesByPriceTallest_lstAddress_lstId(priceTallest, listAddress, listId).size();
				}
			} else {
				if (listId == null && listAddress == null) {
					computer = computerDAO.getComputerSalesByPriceTallestAndPriceShortest(priceShortest, priceTallest,
							offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerSalesByPriceTallestAndPriceShortest(priceShortest, priceTallest).size();
				} else if (listId == null && listAddress != null) {
					computer = computerDAO.getComputerSalesByPriceTallestAndPriceShortest__lstAddress(priceShortest,
							priceTallest, listAddress, offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceTallestAndPriceShortest__lstAddress(
							priceShortest, priceTallest, listAddress).size();
				} else if (listId != null && listAddress == null) {
					computer = computerDAO.getComputerSalesByPriceTallestAndPriceShortest__lstId(priceShortest,
							priceTallest, listId, offset, maxResult);
					totalCom = (long) computerDAO
							.getComputerSalesByPriceTallestAndPriceShortest__lstId(priceShortest, priceTallest, listId)
							.size();
				} else if (listId != null && listAddress != null) {
					computer = computerDAO.getComputerSalesByPriceTallestAndPriceShortest_lstAddress_lstId(
							priceShortest, priceTallest, listAddress, listId, offset, maxResult);
					totalCom = (long) computerDAO.getComputerSalesByPriceTallestAndPriceShortest_lstAddress_lstId(
							priceShortest, priceTallest, listAddress, listId).size();
				}
			}
		}

		ModelAndView model = new ModelAndView("user/productSearchBybtnFilter");
		maxResult = 10;
		Integer totalPage = (int) (totalCom / maxResult);
		totalPage = totalPage + (totalCom % maxResult == 0 ? 0 : 1);
		List<Integer> listPage = new ArrayList<>();
		for (int i = 1; i <= totalPage; i++) {
			listPage.add(i);
		}
		model.getModelMap().put("page", page);
		model.getModelMap().put("listPage", listPage);
		model.getModelMap().put("list", computer);
		model.getModelMap().put("sortBy", sortBy);
		model.getModelMap().put("totalPage", totalPage);
		System.out.println("so page la : " + page + " totalpage : " + totalPage);
		return model;
	}
}

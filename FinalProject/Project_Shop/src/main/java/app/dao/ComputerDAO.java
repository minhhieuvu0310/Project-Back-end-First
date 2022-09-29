package app.dao;

import java.util.List;

import app.entities.Computer;

public interface ComputerDAO {
	public List<Computer> getComputer();

	public List<Computer> getComputer(Integer offset, Integer maxResult);

	public Long getTotalComputer();

	public List<String> getAllAddress();

	public List<Computer> getComputerByProdureId(Integer produreId);

	public List<Computer> getComputerByProdureName(String proName, Integer offset, Integer maxResult);

	public Long getTotalComputerByProdureName(String proName);

	public List<Computer> getComputerBylstProdureId(List<Integer> lstProdureId);

	// phân trang computer theo danh sách mã nhà sản xuất
	public List<Computer> getComputerBylstProdureId(List<Integer> lstProdureId, Integer offset, Integer maxResult);

	public List<Computer> getComputerBylstAddress(List<String> lstAddress);

	// phân trang computer theo danh sách nơi ở
	public List<Computer> getComputerBylstAddress(List<String> lstAddress, Integer offset, Integer maxResult);

	public List<Computer> getComputerByAddress(String address, Integer offset, Integer maxResult);

	public Long getTotalComputerByAddress(String address);

	// lấy danh sách sản phẩm theo list NSX và list Nơi bán
	public List<Computer> getComputerBylstProdureIAndlstAddress(List<Integer> lstProdureId, List<String> lstAddress);

	// phân trang sản phẩm theo list NSX và list Nơi bán
	public List<Computer> getComputerBylstProdureIAndlstAddress(List<Integer> lstProdureId, List<String> lstAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceShortest(Float priceShortest);

	// phân trang sản phẩm theo giá bán thấp nhất
	public List<Computer> getComputerByPriceShortest(Float priceShortest, Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallest(Float priceTallest);

	// phân trang sản phẩm theo giá bán cao nhất
	public List<Computer> getComputerByPriceTallest(Float priceTallest, Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest);

	// phân trang theo sản phẩm giá bán Max vs Min
	public List<Computer> getComputerByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress);

	// phân trang sản phẩm theo giá bán thấp nhất và danh sách nơi bán
	public List<Computer> getComputerByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> lstId);

	// phân trang sản phẩm theo giá bán thấp nhất và danh sách Id nhà sản xuất
	public List<Computer> getComputerByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> lstId,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId);

	// phân trang sản phẩm theo giá bán thấp nhất và danh sách Id nhà sản xuất và
	// nơi bán
	public List<Computer> getComputerByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> lstId);

	// phân trang theo giá bán cao nhất và danh sách Id nhà sản xuất
	public List<Computer> getComputerByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> lstId,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress);

	// phân hàng sản phẩm theo giá bán cao nhất và nơi ở
	public List<Computer> getComputerByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId);

	// phân trang sản phẩm giá bán cao nhất vs DS nơi ở vs DS Id NSX
	public List<Computer> getComputerByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstAddress(Float priceShortest, Float priceTallest,
			List<String> lstAddress);

	// phân trang sản phẩm giá bán Min vs Max vs DS Nơi ở
	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstAddress(Float priceShortest, Float priceTallest,
			List<String> lstAddress, Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> lstId);

	// phân trang sản phẩm giá bán Min vs Max vs DS Id NSX
	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> lstId, Integer offset, Integer maxResult);

	public List<Computer> getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> lstAddress, List<Integer> listId);

	// phân trang sản phẩm giá bán Min vs Max DS Id NSX vs DS Nơi ở
	public List<Computer> getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> lstAddress, List<Integer> listId, Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất
	public List<Computer> getComputerNew();

	// phân Trang
	public List<Computer> getComputerNew(Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất theo nơi ở
	public List<Computer> getComputerBylstAddressNew(List<String> lstAddress);

	// phân Trang
	public List<Computer> getComputerBylstAddressNew(List<String> lstAddress, Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất theo nhà sản xuất
	public List<Computer> getComputerBylstProdureIdNew(List<Integer> listId);

	// phân Trang
	public List<Computer> getComputerBylstProdureIdNew(List<Integer> listId, Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất theo nhà sản xuất
	public List<Computer> getComputerBylstProdureIAndlstAddressNew(List<Integer> listId, List<String> lstAddress);

	// phân Trang
	public List<Computer> getComputerBylstProdureIAndlstAddressNew(List<Integer> listId, List<String> lstAddress,
			Integer offset, Integer maxResult);

	// lấy sản phẩm mới nhất theo giá bán thấp nhất
	public List<Computer> getComputerNewByPriceShortest(Float priceShortest, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceShortest(Float priceShortest);

	public List<Computer> getComputerNewByPriceShortestAndlstAddress(Float priceShortest, List<String> listAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceShortestAndlstAddress(Float priceShortest, List<String> listAddress);

	public List<Computer> getComputerNewByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId);

	public List<Computer> getComputerNewByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> listAddress,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> listAddress,
			List<Integer> listId);

	// lấy sản phẩm mới nhất theo giá bán cao nhất
	public List<Computer> getComputerNewByPriceTallest(Float priceTallest, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallest(Float priceTallest);

	public List<Computer> getComputerNewByPriceTallestAndlstAddress(Float priceTallest, List<String> listAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallestAndlstAddress(Float priceTallest, List<String> listAddress);

	public List<Computer> getComputerNewByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId);

	public List<Computer> getComputerNewByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> listAddress,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> listAddress,
			List<Integer> listId);

	// lấy sản phẩm mới nhất theo giá bán thấp nhất và giá bán cao nhất
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstAddress(Float priceShortest,
			Float priceTallest, List<String> listAddress, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstAddress(Float priceShortest,
			Float priceTallest, List<String> listAddress);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> listId);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> listAddress, List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerNewByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> listAddress, List<Integer> listId);

	// lấy ra sản phẩm bán chạy nhất
	public List<Computer> getComputerSales();

	// phân Trang
	public List<Computer> getComputerSales(Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất theo nơi ở
	public List<Computer> getComputerSalesBylstAddress(List<String> lstAddress);

	// phân Trang
	public List<Computer> getComputerSalesBylstAddress(List<String> lstAddress, Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất theo nhà sản xuất
	public List<Computer> getComputerSalesBylstProdureId(List<Integer> listId);

	// phân Trang
	public List<Computer> getComputerSalesBylstProdureId(List<Integer> listId, Integer offset, Integer maxResult);

	// lấy ra sản phẩm mới nhất theo nhà sản xuất
	public List<Computer> getComputerSalesBylstProdureIAndlstAddress(List<Integer> listId, List<String> lstAddress);

	// phân Trang
	public List<Computer> getComputerSalesBylstProdureIAndlstAddress(List<Integer> listId, List<String> lstAddress,
			Integer offset, Integer maxResult);

	//
	public List<Computer> getComputerSalesByPriceShortest(Float priceShortest, Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceShortest(Float priceShortest);

	//
	public List<Computer> getComputerSalesByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress);

	//
	public List<Computer> getComputerSalesByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId);

	//
	public List<Computer> getComputerSalesByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId);

	//
	public List<Computer> getComputerSalesByPriceTallest(Float priceTallest, Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceTallest(Float priceTallest);

	//
	public List<Computer> getComputerSalesByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress);

	//
	public List<Computer> getComputerSalesByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId,
			Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId);

	//
	public List<Computer> getComputerSalesByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult);

	public List<Computer> getComputerSalesByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId);
	
	//
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest
																				, Integer offset, Integer maxResult);
	
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest);
	
	//
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstAddress(Float priceShortest, Float priceTallest
			,List<String> lstAddress, Integer offset, Integer maxResult);
	
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstAddress(Float priceShortest, Float priceTallest
			,List<String> lstAddress);
	//
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest
			,List<Integer> listId, Integer offset, Integer maxResult);
	
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest
			,List<Integer> listId);
	//
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest, Float priceTallest
			,List<String> lstAddress ,List<Integer> listId, Integer offset, Integer maxResult);
	
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest, Float priceTallest
			,List<String> lstAddress ,List<Integer> listId);
}

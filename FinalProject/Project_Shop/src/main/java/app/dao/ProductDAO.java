package app.dao;

import java.util.List;

import app.entities.Product;

public interface ProductDAO {
	/**
     * lấy sản phẩm chung trong 2 list sản phẩm
     * @return list product
     */
	public List<Product> lstPro(List<Product> lst1, List<Product> lst2);
	
	/**
     * phân trang cho list sản phẩm
     * @return list product
     */
	
	public List<Product> listProPa(List<Product> lstCom, Integer offset, Integer maxResult);
	
	/**
     * Lấy danh sách tất cả product
     * @return list product
     */
	
	public List<Product> getAllProduct();
	
	/**
     * Lấy danh sách tất cả product theo Tên Provider
     * @return list product
     */
	
	public List<Product> getAllProductByProvider(String ProviderName);
	
	/**
     * Lấy danh sách tất cả product theo Tên CataLog 
     * @return list product
     */
	
	public List<Product> getAllProductByCatalog(String CatalogName);
	
	/**
     * Lấy danh sách tất cả product theo List Provider
     * @return list product
     */
	
	public List<Product> getAllProductByProvider(List<String> lstProvider);
	
	/**
     * Lấy danh sách tất cả product theo List CataLog 
     * @return list product
     */
	
	public List<Product> getAllProductByCatalog(List<String> lstCataLog);
	
	/**
     * Lấy danh sách tất cả product theo giá bán thấp nhất  
     * @return list product
     */
	public List<Product> getAllProductByPriceShortest(float priceShortest);
	
	/**
     * Lấy danh sách tất cả product theo giá bán cao nhất  
     * @return list product
     */
	public List<Product> getAllProductByPriceTallest(float priceTallest);
	
	/**
     * Lấy danh sách tất cả product theo giá bán cao nhất  
     * @return list product
     */
	public List<Product> getAllProductByPrice(float priceShortest , float priceTallest);
	
	/**
     * Lấy danh sách tất cả product lọc 
     * @return list product
     */
	public List<Product> getAllProductBySortBy(String SortBy);
	
	/**
     * Lấy danh sách tất cả product theo Tên 
     * @return list product
     */
	public List<Product> getAllProductByKey(String KeySearch);
	
	/**
     * Lấy danh sách tất cả product theo giá tăng dần hoặc giảm
     * @return list product
     */
	public List<Product> getAllProductBySortPrice(String sortPrice);
	
}

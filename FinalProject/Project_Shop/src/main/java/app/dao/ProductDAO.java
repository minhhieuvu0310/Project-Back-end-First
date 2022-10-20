package app.dao;

import java.util.List;

import app.entities.Product;

public interface ProductDAO {
	/**
     * Lấy danh sách tất cả product
     * @return list product
     */
	public List<Product> getAllProduct(Integer offset, Integer maxResult);
	
	public List<Product> getAllProduct();
	
	/**
     * Lấy danh sách tất cả product theo Id Provider
     * @return list product
     */
	public List<Product> getAllProductByProvider(Integer offset, Integer maxResult);
	
	public List<Product> getAllProductByProvider();
}

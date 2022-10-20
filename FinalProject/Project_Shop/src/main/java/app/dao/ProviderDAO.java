package app.dao;

import java.util.List;

import app.entities.Provider;

public interface ProviderDAO {
	/**
     * Lấy danh sách tất cả provider
     * @return list provider
     */
	public List<Provider> getAllProvider();
}

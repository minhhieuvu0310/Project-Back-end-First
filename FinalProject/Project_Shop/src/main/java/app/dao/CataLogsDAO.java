package app.dao;

import java.util.List;

import app.entities.CataLogs;


public interface CataLogsDAO {
	/**
     * Lấy danh sách tất cả catalog
     * @return list provider
     */
	public List<CataLogs> getAllCataLog();
}

package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.CataLogsDAO;
import app.entities.CataLogs;

@Repository
public class CatalogsDAOimpl implements CataLogsDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CataLogs> getAllCataLog() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from CataLogs catalogs where catalogs.status = 1")
					.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}

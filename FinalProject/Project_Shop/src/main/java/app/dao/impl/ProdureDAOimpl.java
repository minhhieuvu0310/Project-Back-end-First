package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.ProdureDAO;
import app.entities.Produre;
@Repository
public class ProdureDAOimpl implements ProdureDAO{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<Produre> getAllProdure() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Produre").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}

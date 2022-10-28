package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.ProductColorDAO;

@Repository
public class ProductColorDAOimpl implements ProductColorDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<String> getAllColorById(Integer productId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("select color.natation from ProductColor pcolor where product.productId = :productId and pcolor.status = 1")
					.setParameter("productId", productId)
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

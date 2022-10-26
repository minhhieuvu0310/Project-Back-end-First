package app.dao.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.ImageLinkDAO;


@Repository
public class ImageLinkDAOimpl implements ImageLinkDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<String> getAllImageProduct(Integer proId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session
					.createQuery(
							"select imageLink.imageLinkName from ImageLink imageLink where product.productId = :proId and imageLink.status = 1")
					.setParameter("proId", proId).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}

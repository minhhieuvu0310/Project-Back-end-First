package app.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.CartDAO;
import app.entities.Cart;

@Repository
public class CartDAOimpl implements CartDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Boolean InsertCart(Cart cart) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(cart);
			session.getTransaction().commit();
			return true;
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public Cart CartOfUser(Integer userId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Cart where user.UserId = :userId")
					.setParameter("userId", userId)
					.list();
			Cart cart = (Cart) list.get(0);
			return cart;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}

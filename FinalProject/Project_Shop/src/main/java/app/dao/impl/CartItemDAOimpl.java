package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.CartItemDAO;
import app.entities.CartItem;

@Repository
public class CartItemDAOimpl implements CartItemDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<CartItem> getAllCartItemByCartId(Integer cartId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from CartItem where cart.cartId = :cartId and status = 0")
					.setParameter("cartId", cartId)
					.list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Boolean InserCartItem(CartItem cartItem) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(cartItem);
			session.getTransaction().commit();
			return true;
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public Boolean UpdateCartItem(CartItem cartItem) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(cartItem);
			session.getTransaction().commit();
			return true;
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public Boolean DeleteCartItem(CartItem cartItem) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.delete(cartItem);
			session.getTransaction().commit();
			return true;
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

}

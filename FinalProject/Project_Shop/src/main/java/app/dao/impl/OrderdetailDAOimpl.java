package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.OrderdetailDAO;
import app.entities.Cart;
import app.entities.Orderdetail;

@Repository
public class OrderdetailDAOimpl implements OrderdetailDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean insertOrderDetail(Orderdetail orderdetail) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(orderdetail);
			session.getTransaction().commit();
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<Orderdetail> findOrderDetailByOrdersId(Integer ordersId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Orderdetail ordt where ordt.orders.ordersId = :ordersId")
					.setParameter("ordersId", ordersId)
					.list();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}
	
}

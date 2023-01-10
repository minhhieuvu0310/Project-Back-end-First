package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.OrdersDAO;
import app.entities.Cart;
import app.entities.Orders;

@Repository
public class OrdersDAOimpl implements OrdersDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean insertOrder(Orders orders) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(orders);
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
	public Integer getOrderIdNew() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("select ord.ordersId from Orders ord order by ord.ordersId desc")
								.setFirstResult(0)
								.setMaxResults(1).list();
			return (Integer)list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public Orders findOrdersById(Integer orderId) {
		Session session = sessionFactory.openSession();
		try {
			Orders orders = session.get(Orders.class, orderId);
			return orders;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean updateOrder(Orders orders) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(orders);
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
	public List<Orders> findOrdersByUsersId(Integer userId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Orders ord where ord.user.UserId = :userId")
					.setParameter("userId", userId)
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

	@Override
	public Integer getTotalOrders() {
		Session session = sessionFactory.openSession();
		try {
			Integer total = (Integer) session.createQuery("from Orders").list().size();
			return total;
		} catch (Exception e1) {
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return 0;
	}

	@Override
	public Float getTotalMoney(List<Orders> lstOrders) {
		float totoalmoney = 0;
		for (Orders orders : lstOrders) {
			totoalmoney += orders.getTotalamount();
		}
		return totoalmoney;
	}

	@Override
	public List<Orders> getAllOrders() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Orders")
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

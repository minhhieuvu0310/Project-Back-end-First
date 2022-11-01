package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.UsersDAO;
import app.entities.Product;
import app.entities.Users;

@Repository
public class UsersDAOimpl implements UsersDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Users> getAllUser() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Users users where users.status = 1").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean checkLogin(Users users) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Users users where users.userName = :userName and users.passWord = :passWord and users.status = 1")
								.setParameter("userName", users.getUserName())
								.setParameter("passWord", users.getPassWord())
								.list();
			if(list.size() > 0) {
				return true;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public Users getUsers(Users users) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Users users where users.userName = :userName and users.passWord = :passWord and users.status = 1")
					.setParameter("userName", users.getUserName())
					.setParameter("passWord", users.getPassWord())
					.list();
			Users users1 = (Users) list.get(0);
			return users1;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

}

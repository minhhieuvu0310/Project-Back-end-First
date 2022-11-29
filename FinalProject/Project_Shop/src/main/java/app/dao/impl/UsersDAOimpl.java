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
			e.printStackTrace();
		}finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean checkUserName(Users users) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Users users where users.userName = :userName and users.status = 1")
								.setParameter("userName", users.getUserName())
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
	public boolean checkUserEmail(Users users) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Users users where users.email = :email and users.status = 1")
								.setParameter("email", users.getEmail())
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
	public boolean checkUserPhone(Users users) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Users users where users.phone = :phone and users.status = 1")
								.setParameter("phone", users.getPhone())
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
	public boolean insertUsers(Users users) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(users);
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
	public boolean updateUsers(Users users) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(users);
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

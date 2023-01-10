package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.User_RoleDAO;
import app.entities.User_Role;
import app.entities.Users;

@Repository
public class User_RoleDAOimpl implements User_RoleDAO{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean insertRoleForUser(User_Role user_role) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user_role);
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
	public boolean checkRoleAdminOfUser(Users users) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from User_Role ur where ur.user.UserId = :userid and ur.role.roleId = 2")
								.setParameter("userid", users.getUserId())
								.list();
			if(list.size() > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

}

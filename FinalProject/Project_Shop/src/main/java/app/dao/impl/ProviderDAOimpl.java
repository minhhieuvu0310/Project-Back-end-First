package app.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.ProviderDAO;
import app.entities.Provider;

@Repository
public class ProviderDAOimpl implements ProviderDAO{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Provider> getAllProvider() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Provider provider where provider.status = 1")
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

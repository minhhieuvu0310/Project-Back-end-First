package app.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.ProductDAO;
import app.entities.Product;

@Repository
public class ProductDAOimpl implements ProductDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Product> lstCom(List<Product> lst1, List<Product> lst2) {
		Set<String> collect = lst1.stream().map(i -> i.getProductId().toString().toLowerCase()).collect(Collectors.toSet());
		List<Product> lstComputer = lst2.stream().filter(tx -> collect.contains(tx.getProductId().toString()))
				.collect(Collectors.toList());
		return lstComputer;
	}
	
	public List<Product> listcomPa(List<Product> lstCom, Integer offset, Integer maxResult) {
		List<Product> listcomPa = new ArrayList<Product>();
		Integer n = offset + maxResult;
		if (lstCom.size() <= maxResult) {
			listcomPa = lstCom;
		} else {
			for (int i = offset; i < n; i++) {
				if (i < lstCom.size()) {
					listcomPa.add(lstCom.get(i));
				}
			}
		}
		return listcomPa;
	}
	
	@Override
	public List<Product> getAllProduct(Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Product product where product.status = 1").setFirstResult(offset)
					.setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProduct() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Product product where product.status = 1")
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
	public List<Product> getAllProductByProvider(Integer offset, Integer maxResult) {
		
		return null;
	}

	@Override
	public List<Product> getAllProductByProvider() {
		// TODO Auto-generated method stub
		return null;
	}

}

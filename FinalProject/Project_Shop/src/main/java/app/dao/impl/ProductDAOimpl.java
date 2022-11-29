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
public class ProductDAOimpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> lstPro(List<Product> lst1, List<Product> lst2) {
		List<Product> lstProduct = new ArrayList<Product>();
		if (lst1 == null && lst2 != null) {
			lstProduct = lst2;
		} else if (lst1 != null && lst2 == null) {
			lstProduct = lst1;
		} else if (lst1 != null && lst2 != null) {
			List<Product> lstproMax = (lst1.size() > lst2.size()) ? lst1 : lst2;
			List<Product> lstproMin = (lst1.size() < lst2.size()) ? lst1 : lst2;
			Set<String> collect = lstproMin.stream().map(i -> i.getProductId().toString().toLowerCase())
					.collect(Collectors.toSet());
			lstProduct = lstproMax.stream().filter(tx -> collect.contains(tx.getProductId().toString()))
					.collect(Collectors.toList());
		}
		return lstProduct;
	}

	@Override
	public List<Product> listProPa(List<Product> lstCom, Integer offset, Integer maxResult) {
		List<Product> listProPa = new ArrayList<Product>();
		Integer n = offset + maxResult;
		if (lstCom.size() <= maxResult) {
			listProPa = lstCom;
		} else {
			for (int i = offset; i < n; i++) {
				if (i < lstCom.size()) {
					listProPa.add(lstCom.get(i));
				}
			}
		}

		return listProPa;
	}

	@Override
	public List<Product> getAllProduct() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Product product where product.status = 1").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByProvider(String ProviderName) {
		Session session = sessionFactory.openSession();
		try {
			List list = session
					.createQuery(
							"from Product product where provider.providerName = :ProviderName and product.status = 1")
					.setParameter("ProviderName", ProviderName).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByCatalog(String CatalogName) {
		Session session = sessionFactory.openSession();
		try {
			List list = session
					.createQuery("from Product product where catalog.catalogName = :CatalogName and product.status = 1")
					.setParameter("CatalogName", CatalogName).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByProvider(List<String> lstProvider) {
		try {
			List<Product> lstpro = new ArrayList<Product>();
			for (String provider : lstProvider) {
				List<Product> list = getAllProductByProvider(provider);
				lstpro.addAll(list);
			}
			return lstpro;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByCatalog(List<String> lstCataLog) {
		try {
			List<Product> lstpro = new ArrayList<Product>();
			for (String catalog : lstCataLog) {
				List<Product> list = getAllProductByCatalog(catalog);
				lstpro.addAll(list);
			}
			return lstpro;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByPriceShortest(float priceShortest) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"from Product product where (product.priceOutput-(product.priceOutput * product.discount)) >= :priceShortest and product.status = 1")
					.setParameter("priceShortest", priceShortest).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByPriceTallest(float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"from Product product where (product.priceOutput-(product.priceOutput * product.discount)) <= :priceTallest and product.status = 1")
					.setParameter("priceTallest", priceTallest).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByPrice(float priceShortest, float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"from Product product where ((product.priceOutput-(product.priceOutput * product.discount)) between :priceShortest and :priceTallest) and product.status = 1")
					.setParameter("priceShortest", priceShortest).setParameter("priceTallest", priceTallest).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductBySortBy(String SortBy) {
		Session session = sessionFactory.openSession();
		try {
			if (SortBy.equals("new")) {
				List list = session.createQuery(
						"from Product product where DATEDIFF(month , product.created , GETDATE()) < 2 and (product.created < GETDATE()) and product.status = 1")
						.list();
				return list;
			} else if (SortBy.equals("sales")) {
				List list = session
						.createQuery("from Product product where product.buyItem >= 50 and product.status = 1").list();
				return list;
			} else if (SortBy.equals("relevancy")) {
				List<Product> allProduct = getAllProduct();
				return allProduct;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductByKey(String KeySearch) {
		Session session = sessionFactory.openSession();
		try {
			if (KeySearch == null || KeySearch.length() == 0)
				KeySearch = "%";
			else
				KeySearch = "%" + KeySearch + "%";
			List list = session.createQuery(
					"from Product product where ((product.productName like :KeySearch) or (product.productContent like :KeySearch) or (product.productContentDetail like :KeySearch)) and product.status = 1")
					.setParameter("KeySearch", KeySearch).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Product> getAllProductBySortPrice(String sortPrice) {
		Session session = sessionFactory.openSession();
		try {
			if(sortPrice.equals("desc")) {
				List list = session.createQuery(
					"from Product product where product.status = 1 order by (product.priceOutput-(product.priceOutput * product.discount)) desc")
					.list();
				return list;
			}else if(sortPrice.equals("asc")) {
				List list = session.createQuery(
						"from Product product where product.status = 1 order by (product.priceOutput-(product.priceOutput * product.discount)) asc")
						.list();
					return list;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Product getProductById(Integer proId) {
		Session session = sessionFactory.openSession();
		try {
			Product product = session.get(Product.class,proId);
			return product;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Boolean updateProduct(Product product) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(product);
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
	public Boolean updatebuyItem(Product product, Integer orderquantity) {
		Session session = sessionFactory.openSession();
		try {
			Integer slc = product.getQuantity() - product.getBuyItem();
			if(slc > orderquantity) {
				product.setBuyItem(slc - orderquantity);
				session.beginTransaction();
				session.update(product);
				session.getTransaction().commit();
				return true;
			}else {
				return false;
			}		
		} catch (Exception e1) {
			e1.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return false;
	}

}

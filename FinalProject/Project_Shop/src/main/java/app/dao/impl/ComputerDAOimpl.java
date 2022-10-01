package app.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import app.dao.ComputerDAO;
import app.entities.Computer;
import app.entities.Produre;

@Repository
public class ComputerDAOimpl implements ComputerDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public List<Computer> lstCom(List<Computer> lst1, List<Computer> lst2) {
//		List<Computer> lstComputer = new ArrayList<Computer>();
//		for (Computer com1 : lst1) {
//			for (Computer com2 : lst2) {
//				if (com1.getComId() == com2.getComId()) {
//					lstComputer.add(com2);
//				}
//			}
//		}
		// sử dụng hasmap
//		Set<String> collect = lst1.stream().map(i -> i.getComId().toString().toLowerCase())
//                .collect(Collectors.toSet());
//		for(Computer tx : lst2 )
//		    if(collect.contains(tx.getComId().toString()))
//		    	lstComputer.add(tx);
//		return lstComputer;
		Set<String> collect = lst1.stream().map(i -> i.getComId().toString().toLowerCase())
              .collect(Collectors.toSet());
		List<Computer> lstComputer = lst2.stream()
                .filter(tx -> collect.contains(tx.getComId().toString()))
                .collect(Collectors.toList());
		return lstComputer;
	}

	public List<Computer> listcomPa(List<Computer> lstCom, Integer offset, Integer maxResult) {
		List<Computer> listcomPa = new ArrayList<Computer>();
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
	public List<Computer> getComputer() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Computer com where com.status = 1").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputer(Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Computer com where com.status = 1").setFirstResult(offset)
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
	public Long getTotalComputer() {
		Session session = sessionFactory.openSession();
		try {
			Long total = (long) session.createQuery("from Computer com where com.status = 1").list().size();
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<String> getAllAddress() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("SELECT DISTINCT com.address from Computer com").list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByProdureId(Integer produreId) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("FROM Computer com WHERE produre.produreId = :produreId")
					.setParameter("produreId", produreId).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByProdureName(String proName, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("FROM Computer com WHERE produre.proName like :proName and com.status = 1")
					.setParameter("proName", proName).setFirstResult(offset).setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Long getTotalComputerByProdureName(String proName) {
		Session session = sessionFactory.openSession();
		try {
			Long total = (long) session
					.createQuery("FROM Computer com WHERE produre.proName like :proName and com.status = 1")
					.setParameter("proName", proName).list().size();
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// Tìm kiếm tất cả các sản phẩm Computer theo mã danh sách Id nhà sản xuất
	@Override
	public List<Computer> getComputerBylstProdureId(List<Integer> lstProdureId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> lstcom = new ArrayList<Computer>();
			for (Integer produre : lstProdureId) {
				List list = getComputerByProdureId(produre);
				lstcom.addAll(list);
			}
			return lstcom;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// phân trang computer theo danh sách mã nhà sản xuất
	public List<Computer> getComputerBylstProdureId(List<Integer> lstProdureId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> lstcom = new ArrayList<Computer>();
			for (Integer produre : lstProdureId) {
				List list = getComputerByProdureId(produre);
				lstcom.addAll(list);
			}
			List<Computer> listcomPa = listcomPa(lstcom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// Tìm kiếm tất cả các sản phẩm Computer theo mã danh sách của địa chỉ bán

	@Override
	public List<Computer> getComputerBylstAddress(List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> lstcom = new ArrayList<Computer>();
			for (String address : lstAddress) {
				List list = session.createQuery("FROM Computer com WHERE com.address = :address and com.status = 1")
						.setParameter("address", address).list();
				lstcom.addAll(list);
			}
			return lstcom;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// phân trang computer theo danh sách của địa chỉ bán
	@Override
	public List<Computer> getComputerBylstAddress(List<String> lstAddress, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> lstcom = new ArrayList<Computer>();
			for (String address : lstAddress) {
				List list = session.createQuery("FROM Computer com WHERE com.address = :address and com.status = 1")
						.setParameter("address", address).list();
				lstcom.addAll(list);
			}
			List<Computer> listcomPa = listcomPa(lstcom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByAddress(String address, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("FROM Computer com WHERE com.address = :address and com.status = 1")
					.setParameter("address", address).setFirstResult(offset).setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Long getTotalComputerByAddress(String address) {
		Session session = sessionFactory.openSession();
		try {
			Long total = (long) session.createQuery("FROM Computer com WHERE com.address = :address and com.status = 1")
					.setParameter("address", address).list().size();
			return total;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// lấy danh sách sản phẩm theo list NSX và list Nơi bán
	@Override
	public List<Computer> getComputerBylstProdureIAndlstAddress(List<Integer> lstProdureId, List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerBylstProdureId = getComputerBylstProdureId(lstProdureId);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerBylstProdureId, computerBylstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// phân trang sản phẩm theo list NSX và list Nơi bán
	@Override
	public List<Computer> getComputerBylstProdureIAndlstAddress(List<Integer> lstProdureId, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerBylstProdureId = getComputerBylstProdureId(lstProdureId);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerBylstProdureId, computerBylstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceShortest(Float priceShortest) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"FROM Computer com WHERE (com.priceOutput-(com.priceOutput * com.discount)) >= :priceShortest "
							+ "order by (com.priceOutput-(com.priceOutput * com.discount)) asc")
					.setParameter("priceShortest", priceShortest).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// phân trang theo giá bán thấp nhất
	@Override
	public List<Computer> getComputerByPriceShortest(Float priceShortest, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"FROM Computer com WHERE (com.priceOutput-(com.priceOutput * com.discount)) >= :priceShortest "
							+ "order by (com.priceOutput-(com.priceOutput * com.discount)) asc")
					.setParameter("priceShortest", priceShortest).setFirstResult(offset).setMaxResults(maxResult)
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
	public List<Computer> getComputerByPriceTallest(Float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"FROM Computer com WHERE (com.priceOutput-(com.priceOutput * com.discount)) <= :priceTallest"
							+ " order by (com.priceOutput-(com.priceOutput * com.discount)) asc")
					.setParameter("priceTallest", priceTallest).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// phân trang theo giá bán cao nhất
	@Override
	public List<Computer> getComputerByPriceTallest(Float priceTallest, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"FROM Computer com WHERE (com.priceOutput-(com.priceOutput * com.discount)) <= :priceTallest"
							+ " order by (com.priceOutput-(com.priceOutput * com.discount)) asc")
					.setParameter("priceTallest", priceTallest).setFirstResult(offset).setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"FROM Computer com WHERE (com.priceOutput-(com.priceOutput * com.discount)) between :priceShortest and :priceTallest "
							+ "order by (com.priceOutput-(com.priceOutput * com.discount)) asc")
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
	// phân trang theo sản phẩm giá bán Max vs Min
	public List<Computer> getComputerByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"FROM Computer com WHERE (com.priceOutput-(com.priceOutput * com.discount)) between :priceShortest and :priceTallest "
							+ "order by (com.priceOutput-(com.priceOutput * com.discount)) asc")
					.setParameter("priceShortest", priceShortest).setParameter("priceTallest", priceTallest)
					.setFirstResult(offset).setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm theo giá bán thấp nhất và danh sách nơi ở
	@Override
	public List<Computer> getComputerByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> lstId) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> computerBylstId = getComputerBylstProdureId(lstId);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstId);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm theo giá bán thấp nhất và danh sách id nhà sản xuất
	@Override
	public List<Computer> getComputerByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> lstId,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> computerBylstId = getComputerBylstProdureId(lstId);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstId);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> computerBylstId_lstAddress = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstId_lstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm theo giá bán thấp nhất và danh sách Id nhà sản xuất và
	// nơi bán
	@Override
	public List<Computer> getComputerByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> computerBylstId_lstAddress = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstId_lstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress) {
		try {
			List<Computer> computerByPriceTallest = getComputerByPriceTallest(priceTallest);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceTallest, computerBylstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân hàng sản phẩm theo giá bán cao nhất và nơi ở
	@Override
	public List<Computer> getComputerByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPriceTallest = getComputerByPriceTallest(priceTallest);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceTallest, computerBylstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> lstId) {
		try {
			List<Computer> computerByPriceTallest = getComputerByPriceTallest(priceTallest);
			List<Computer> computerBylstId = getComputerBylstProdureId(lstId);
			List<Computer> lstCom = lstCom(computerByPriceTallest, computerBylstId);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang theo giá bán cao nhất và danh sách Id nhà sản xuất
	@Override
	public List<Computer> getComputerByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> lstId,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPriceTallest = getComputerByPriceTallest(priceTallest);
			List<Computer> computerBylstId = getComputerBylstProdureId(lstId);
			List<Computer> lstCom = lstCom(computerByPriceTallest, computerBylstId);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceTallest(priceTallest);
			List<Computer> computerBylstId_lstAddress = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstId_lstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm giá bán cao nhất vs DS nơi ở vs DS Id NSX
	@Override
	public List<Computer> getComputerByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPriceShortest = getComputerByPriceTallest(priceTallest);
			List<Computer> computerBylstId_lstAddress = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstCom = lstCom(computerByPriceShortest, computerBylstId_lstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstAddress(Float priceShortest, Float priceTallest,
			List<String> lstAddress) {
		try {
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerByPrice, computerBylstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm giá bán Min vs Max vs DS nơi ở vs DS Id NSX
	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstAddress(Float priceShortest, Float priceTallest,
			List<String> lstAddress, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> lstCom = lstCom(computerByPrice, computerBylstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> lstId) {
		try {
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> computerBylstId = getComputerBylstProdureId(lstId);
			List<Computer> lstCom = lstCom(computerByPrice, computerBylstId);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm giá bán Min vs Max vs DS Id NSX
	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> lstId, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> computerBylstId = getComputerBylstProdureId(lstId);
			List<Computer> lstCom = lstCom(computerByPrice, computerBylstId);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> lstAddress, List<Integer> listId) {
		try {
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> computerBylstId_lstAddress = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstCom = lstCom(computerByPrice, computerBylstId_lstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// phân trang sản phẩm giá bán Min vs Max vs DS Id NSX
	@Override
	public List<Computer> getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> lstAddress, List<Integer> listId, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> computerBylstId_lstAddress = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstCom = lstCom(computerByPrice, computerBylstId_lstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNew() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"from Computer com where trunc(mod(months_between(sysdate,com.created),12)) <= 2 and com.status = 1")
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
	public List<Computer> getComputerNew(Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery(
					"from Computer com where trunc(mod(months_between(sysdate,com.created),12)) <= 2 and com.status = 1")
					.setFirstResult(offset).setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// lấy ra sản phẩm mới nhất theo nơi ở
	@Override
	public List<Computer> getComputerBylstAddressNew(List<String> lstAddress) {
		try {
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> computerNew = getComputerNew();
			List<Computer> lstCom = lstCom(computerNew, computerBylstAddress);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerBylstAddressNew(List<String> lstAddress, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerBylstAddress = getComputerBylstAddress(lstAddress);
			List<Computer> computerNew = getComputerNew();
			List<Computer> lstCom = lstCom(computerNew, computerBylstAddress);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerBylstProdureIdNew(List<Integer> listId) {
		try {
			List<Computer> computerBylstProdureId = getComputerBylstProdureId(listId);
			List<Computer> computerNew = getComputerNew();
			List<Computer> lstCom = lstCom(computerNew, computerBylstProdureId);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerBylstProdureIdNew(List<Integer> listId, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerBylstProdureId = getComputerBylstProdureId(listId);
			List<Computer> computerNew = getComputerNew();
			List<Computer> lstCom = lstCom(computerNew, computerBylstProdureId);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerBylstProdureIAndlstAddressNew(List<Integer> listId, List<String> lstAddress) {
		try {
			List<Computer> computer = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> computerNew = getComputerNew();
			List<Computer> lstCom = lstCom(computer, computerNew);
			return lstCom;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerBylstProdureIAndlstAddressNew(List<Integer> listId, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computer = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> computerNew = getComputerNew();
			List<Computer> lstCom = lstCom(computer, computerNew);
			List<Computer> listcomPa = listcomPa(lstCom, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortest(Float priceShortest, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> lstComputer = lstCom(computerNew, computerByPriceShortest);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortest(Float priceShortest) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPriceShortest = getComputerByPriceShortest(priceShortest);
			List<Computer> lstComputer = lstCom(computerNew, computerByPriceShortest);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortestAndlstAddress(Float priceShortest, List<String> listAddress,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceShortestAndlstAddress(priceShortest, listAddress);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortestAndlstAddress(Float priceShortest, List<String> listAddress) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceShortestAndlstAddress(priceShortest, listAddress);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceShortestAndlstProdureId(priceShortest, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceShortestAndlstProdureId(priceShortest, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> listAddress,
			List<Integer> listId, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceShortest_lstAddress_lstId(priceShortest, listAddress,
					listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> listAddress,
			List<Integer> listId) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceShortest_lstAddress_lstId(priceShortest, listAddress,
					listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallest(Float priceTallest, Integer offset, Integer maxResult) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallest(priceTallest);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallest(Float priceTallest) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallest(priceTallest);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndlstAddress(Float priceTallest, List<String> listAddress,
			Integer offset, Integer maxResult) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndlstAddress(priceTallest, listAddress);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndlstAddress(Float priceTallest, List<String> listAddress) {
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndlstAddress(priceTallest, listAddress);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndlstProdureId(priceTallest, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndlstProdureId(priceTallest, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> listAddress,
			List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallest_lstAddress_lstId(priceTallest, listAddress,
					listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> listAddress,
			List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallest_lstAddress_lstId(priceTallest, listAddress,
					listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// lấy sản phẩm mới nhất theo giá bán thấp nhất và giá bán cao nhất
	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> lstComputer = lstCom(computerByPrice, computerNew);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstAddress(Float priceShortest,
			Float priceTallest, List<String> listAddress, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest__lstAddress(priceShortest,
					priceTallest, listAddress);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstAddress(Float priceShortest,
			Float priceTallest, List<String> listAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest__lstAddress(priceShortest,
					priceTallest, listAddress);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest__lstId(priceShortest,
					priceTallest, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest__lstId(priceShortest,
					priceTallest, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> listAddress, List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(priceShortest,
					priceTallest, listAddress, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerNewByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> listAddress, List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerNew = getComputerNew();
			List<Computer> computerByPrice = getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(priceShortest,
					priceTallest, listAddress, listId);
			List<Computer> lstComputer = lstCom(computerNew, computerByPrice);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSales() {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Computer com where com.quantityExtant >= 10 and com.status = 1")
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
	public List<Computer> getComputerSales(Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List list = session.createQuery("from Computer com where com.quantityExtant >= 10 and com.status = 1")
					.setFirstResult(offset).setMaxResults(maxResult).list();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesBylstAddress(List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerBylstAddress(lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesBylstAddress(List<String> lstAddress, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerBylstAddress(lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesBylstProdureId(List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerBylstProdureId(listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesBylstProdureId(List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerBylstProdureId(listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesBylstProdureIAndlstAddress(List<Integer> listId, List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesBylstProdureIAndlstAddress(List<Integer> listId, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerBylstProdureIAndlstAddress(listId, lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortest(Float priceShortest, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortest(priceShortest);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortest(Float priceShortest) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortest(priceShortest);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortestAndlstAddress(priceShortest, lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortestAndlstAddress(Float priceShortest, List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortestAndlstAddress(priceShortest, lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortestAndlstProdureId(priceShortest, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortestAndlstProdureId(Float priceShortest, List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortestAndlstProdureId(priceShortest, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortest_lstAddress_lstId(priceShortest, lstAddress, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceShortest_lstAddress_lstId(Float priceShortest, List<String> lstAddress,
			List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceShortest_lstAddress_lstId(priceShortest, lstAddress, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallest(Float priceTallest, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallest(priceTallest);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallest(Float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallest(priceTallest);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;

	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndlstAddress(priceTallest, lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndlstAddress(Float priceTallest, List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndlstAddress(priceTallest, lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndlstProdureId(priceTallest, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndlstProdureId(Float priceTallest, List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndlstProdureId(priceTallest, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallest_lstAddress_lstId(priceTallest, lstAddress, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallest_lstAddress_lstId(Float priceTallest, List<String> lstAddress,
			List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallest_lstAddress_lstId(priceTallest, lstAddress, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest,
			Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest(Float priceShortest, Float priceTallest) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest(priceShortest, priceTallest);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstAddress(Float priceShortest,
			Float priceTallest, List<String> lstAddress, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest__lstAddress(priceShortest, priceTallest,
					lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstAddress(Float priceShortest,
			Float priceTallest, List<String> lstAddress) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest__lstAddress(priceShortest, priceTallest,
					lstAddress);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest__lstId(priceShortest, priceTallest,
					listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest__lstId(Float priceShortest, Float priceTallest,
			List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest__lstId(priceShortest, priceTallest,
					listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> lstAddress, List<Integer> listId, Integer offset, Integer maxResult) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(priceShortest,
					priceTallest, lstAddress, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			List<Computer> listcomPa = listcomPa(lstComputer, offset, maxResult);
			return listcomPa;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<Computer> getComputerSalesByPriceTallestAndPriceShortest_lstAddress_lstId(Float priceShortest,
			Float priceTallest, List<String> lstAddress, List<Integer> listId) {
		Session session = sessionFactory.openSession();
		try {
			List<Computer> computerSales = getComputerSales();
			List<Computer> computer = getComputerByPriceTallestAndPriceShortest_lstAddress_lstId(priceShortest,
					priceTallest, lstAddress, listId);
			List<Computer> lstComputer = lstCom(computerSales, computer);
			return lstComputer;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}

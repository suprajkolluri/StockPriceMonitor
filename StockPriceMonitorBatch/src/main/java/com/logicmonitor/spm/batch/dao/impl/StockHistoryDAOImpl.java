package com.logicmonitor.spm.batch.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logicmonitor.spm.batch.dao.StockHistoryDAO;
import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * DAO Implementation to add the stock information to the database
 * 
 * @author Supraj
 *
 */
@Repository
public class StockHistoryDAOImpl implements StockHistoryDAO {

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void saveStockHistory(List<StockDetailsDTO> stockList) throws StorageException {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder errors = new StringBuilder();
		stockList.parallelStream().forEach(stock -> {
			try {
				session.save(stock);
			} catch (HibernateException e) {
				errors.append("Unable to save the data for the company: ").append(stock).append("because of: ")
						.append(e.getMessage()).append("\n");
			}
		});

		if (errors.length() != 0) {
			throw new StorageException(errors.toString());
		}
	}

}

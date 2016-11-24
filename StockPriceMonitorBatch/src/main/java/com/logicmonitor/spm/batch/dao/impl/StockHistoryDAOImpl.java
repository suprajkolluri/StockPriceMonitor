package com.logicmonitor.spm.batch.dao.impl;

import java.util.ArrayList;
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
import com.logicmonitor.spm.batch.util.FailedDataStore;

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

	@Autowired
	FailedDataStore failedDataStore;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void saveStockHistory(List<StockDetailsDTO> stockList) throws StorageException {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder errors = new StringBuilder();
		stockList.stream().forEach(stock -> {
			try {
				session.save(stock);
			} catch (HibernateException e) {
				// if the data is not stored due to some error, we are storing
				// it in a data store to retry later
				failedDataStore.getFailedData().add(stock);
				errors.append("Unable to save the data for the company: ").append(stock).append("because of: ")
						.append(e.getMessage()).append("\n");
			}
		});

		// retrying to add the data
		if (failedDataStore.getFailedData().size() > 0) {
			List<StockDetailsDTO> failedData = new ArrayList<>(failedDataStore.getFailedData());
			failedDataStore.getFailedData().clear();

			failedData.stream().forEach(stock -> {
				try {
					session.save(stock);
				} catch (HibernateException e) {
					// if the data is not stored due to some error, we are
					// storing
					// it in a data store to retry later
					failedDataStore.getFailedData().add(stock);
					errors.append("Unable to save the data for the company: ").append(stock).append("because of: ")
							.append(e.getMessage()).append("\n");
				}
			});
		}

		if (errors.length() != 0) {
			throw new StorageException(errors.toString());
		}
	}

}

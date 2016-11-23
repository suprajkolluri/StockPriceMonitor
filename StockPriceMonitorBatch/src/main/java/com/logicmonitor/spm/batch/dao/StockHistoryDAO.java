package com.logicmonitor.spm.batch.dao;

import java.util.List;

import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * DAO to add the stock information to the database
 * 
 * @author Supraj
 *
 */
public interface StockHistoryDAO {

	/**
	 * Adds the stock information of companies to the database
	 * 
	 * @param stockList
	 *            List of {@link StockDetailsDTO} objects with contain stock
	 *            information for companies
	 * 
	 * 
	 * @throws StorageException
	 *             If there was an exception while persisting the data
	 */
	void saveStockHistory(List<StockDetailsDTO> stockList) throws StorageException;

}

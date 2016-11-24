package com.logicmonitor.spm.batch.service;

import java.util.List;

import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * Service to persist the stock information to the database
 * 
 * @author Supraj
 *
 */
public interface StockService {

	/**
	 * Saves the stock information in the database
	 * 
	 * @param stocks
	 *            list of {@link StockDetailsDTO} objects with current stock
	 *            information for companies
	 * 
	 * @throws StorageException
	 *             If there is an exception while saving the data
	 */
	void saveStockInfo(List<StockDetailsDTO> stocks) throws StorageException;

}

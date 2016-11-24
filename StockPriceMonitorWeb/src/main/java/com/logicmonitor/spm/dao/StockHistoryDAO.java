package com.logicmonitor.spm.dao;

import java.util.List;

import com.logicmonitor.spm.dto.StockDetailsDTO;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.model.CompanyInfo;

/**
 * DAO to retrieve the stock information from the database
 * 
 * @author Supraj
 *
 */
public interface StockHistoryDAO {

	/**
	 * Retrieves the names of all companies that are monitored with their last
	 * updated stock price
	 * 
	 * @return List of {@link CompanyInfo} objects which contains the company
	 *         name, symbol and last updated stock price
	 * @throws StorageException
	 *             If there was an exception while retrieving the data
	 */
	List<CompanyInfo> getAllCompanies() throws StorageException;

	/**
	 * Retrieves the historical information of a company
	 * 
	 * @param symbol
	 *            The stock symbol of the company. Example - Microsoft - MSFT
	 * 
	 * @return The list of {@link StockDetailsDTO} objects which contains the
	 *         history of stock prices for a given company symbol
	 * 
	 * @throws StorageException
	 *             If there was an exception while retrieving the data
	 */
	List<StockDetailsDTO> getCompanyHistory(String symbol) throws StorageException;
}

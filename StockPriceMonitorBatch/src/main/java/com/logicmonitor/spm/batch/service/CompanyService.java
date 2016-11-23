package com.logicmonitor.spm.batch.service;

import java.util.List;

import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * Service to get the company symbols from the database
 * 
 * @author Supraj
 *
 */
public interface CompanyService {

	/**
	 * Returns the list of company symbols for that companies that are monitored
	 * in the database
	 * 
	 * @return The list of strings with company symbols
	 * 
	 * @throws StorageException
	 *             If there was an exception retrieving the company information
	 */
	List<String> getCompanySymbols() throws StorageException;

}

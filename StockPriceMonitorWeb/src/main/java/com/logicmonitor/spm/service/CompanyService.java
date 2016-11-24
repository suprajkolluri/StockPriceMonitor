package com.logicmonitor.spm.service;

import java.util.List;

import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.exception.StorageException;

/**
 * Service that provides the ability to add, delete and view company information
 * 
 * @author Supraj
 *
 */
public interface CompanyService {
	/**
	 * Validates if a company symbol is correct, retrieves the name of the
	 * company from the Stock information service and then adds company
	 * information to the database
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * 
	 * @throws InvalidSymbolException
	 *             - If the company symbol that the user is trying to add is
	 *             invalid
	 * 
	 * @throws StorageException
	 *             - If there was an exception while persisting the data
	 * 
	 * 
	 * @throws InvalidJsonException
	 *             - If there was an exception while trying to read the JSON
	 *             provided by the Stock information provider
	 */
	void addCompany(String companySymbol) throws InvalidSymbolException, StorageException, InvalidJsonException;

	/**
	 * Deletes the company from the database and thus the company won't be
	 * monitored anymore
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * 
	 * @throws StorageException
	 *             - If there was an exception while deleting the company from
	 *             the database
	 */
	void deleteCompany(String companySymbol) throws StorageException;

	/**
	 * Lists the names of all companies that are monitored
	 * 
	 * @return - An array of {@link CompanyDTO} objects which contain the
	 *         company name, symbol and creation date
	 * 
	 * @throws StorageException
	 *             If there was an exception while retrieving the data
	 */
	List<CompanyDTO> getCompanies() throws StorageException;

	/**
	 * Returns the a particular company information
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * 
	 * 
	 * @return - A {@link CompanyDTO} object with its name, symbol and creation
	 *         time
	 * 
	 * @throws StorageException
	 *             If there was an exception while retrieving the data
	 */
	CompanyDTO getCompanyInfo(String companySymbol) throws StorageException;
}

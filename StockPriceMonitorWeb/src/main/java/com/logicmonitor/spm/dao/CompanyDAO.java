package com.logicmonitor.spm.dao;

import java.util.List;

import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.StorageException;

/**
 * DAO to perform CRUD operations on {@link CompanyDTO} entity
 * 
 * @author Supraj
 *
 */
public interface CompanyDAO {

	/**
	 * Add the company information to the database
	 * 
	 * @param company
	 *            The {@link CompanyDTO} object
	 * 
	 * @throws StorageException
	 *             If there is an exception while persisting the object
	 */
	void addCompany(CompanyDTO company) throws StorageException;

	/**
	 * Deletes the company information from the database
	 * 
	 * @param symbol
	 *            The symbol of the company
	 * 
	 * @throws StorageException
	 *             If there is an exception while deleting the object
	 */
	void deleteCompany(String symbol) throws StorageException;

	/**
	 * Retrieves the names of all companies that are monitored
	 * 
	 * @return An array of {@link CompanyDTO} objects which contain the company
	 *         name, symbol and creation date
	 * 
	 * @throws StorageException
	 *             If there is an exception while retrieving the information
	 */
	List<CompanyDTO> getCompanies() throws StorageException;

	/**
	 * 
	 * @param companySymbol
	 *            The symbol of the company
	 * 
	 * @return A {@link CompanyDTO} object with its name, symbol and creation
	 *         time
	 * 
	 * @throws StorageException
	 *             If there is an exception while retrieving the information
	 */
	CompanyDTO getCompanyInfo(String companySymbol) throws StorageException;
}

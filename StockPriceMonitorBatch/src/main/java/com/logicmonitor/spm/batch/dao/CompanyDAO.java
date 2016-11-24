package com.logicmonitor.spm.batch.dao;

import java.util.List;

import com.logicmonitor.spm.batch.dto.CompanyDTO;
import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * DAO to retrieve company information from the database
 * 
 * @author Supraj
 *
 */
public interface CompanyDAO {

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

}

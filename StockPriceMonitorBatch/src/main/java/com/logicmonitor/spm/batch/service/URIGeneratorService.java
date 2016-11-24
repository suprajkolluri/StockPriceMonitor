package com.logicmonitor.spm.batch.service;

import java.net.URI;

import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * Service to generate the Stock Provider URI
 * 
 * @author Supraj
 *
 */
public interface URIGeneratorService {

	/**
	 * Builds the service uri for a given symbol
	 * 
	 * @param symbol
	 *            The stock symbol of the company. Example - Microsoft - MSFT
	 * @return - The URI of the Stock Provider service
	 */
	URI getStockRestURI() throws StorageException;

}

package com.logicmonitor.spm.service;

import java.net.URI;

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
	URI getStockRestURI(String symbol);
}

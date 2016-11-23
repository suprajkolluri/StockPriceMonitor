package com.logicmonitor.spm.service;

import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;

/**
 * Service which fetches company details for a symbol from the Stock information
 * provider
 * 
 * @author Supraj
 *
 * 
 */
public interface CompanyDetailsService {

	/**
	 * Gets the complete company name for a symbol
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * 
	 * @return - The name of the company
	 * 
	 * @throws InvalidSymbolException
	 *             If the symbol is not registered with any stock
	 * 
	 * @throws InvalidJsonException
	 *             If there was an exception while trying to read the JSON
	 *             provided by the Stock information provider
	 */
	String getCompanyName(String companySymbol) throws InvalidSymbolException, InvalidJsonException;
}

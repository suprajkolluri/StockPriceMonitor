package com.logicmonitor.spm.service;

import com.logicmonitor.spm.exception.InvalidSymbolException;

/**
 * 
 * @author Supraj
 *
 *         Service which fetches company details for a symbol
 */
public interface CompanyDetailsService {

	/**
	 * Gets the complete company name for a symbol
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return - The name of the company
	 * @throws InvalidSymbolException
	 *             If the symbol is not registered with any stock
	 */
	String getCompanyName(String companySymbol) throws InvalidSymbolException;
}

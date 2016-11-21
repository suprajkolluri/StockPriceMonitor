package com.logicmonitor.spm.validator;

import com.logicmonitor.spm.exception.InvalidSymbolException;

/**
 * 
 * @author Supraj
 *
 *         Validator to check if a symbol is registered with a company or not
 */
public interface CompanySymbolValidator {

	/**
	 * Validates if a particular symbol is registered with a company or not
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @throws InvalidSymbolException
	 *             If the symbol is not registered with any stock
	 * 
	 */
	void validateSymbol(String companySymbol) throws InvalidSymbolException;
}

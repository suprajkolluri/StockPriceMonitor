package com.logicmonitor.spm.validator.impl;

import org.springframework.stereotype.Component;

import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.validator.CompanySymbolValidator;

/**
 * 
 * @author Supraj
 * 
 *         Validator Implementation to check if a symbol is registered with a
 *         company or not
 */
@Component
public class CompanySymbolValidatorImpl implements CompanySymbolValidator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validateSymbol(String companySymbol) throws InvalidSymbolException {
		// TODO

	}

}

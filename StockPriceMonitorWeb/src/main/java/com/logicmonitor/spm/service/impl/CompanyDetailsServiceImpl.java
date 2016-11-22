package com.logicmonitor.spm.service.impl;

import org.springframework.stereotype.Service;

import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.service.CompanyDetailsService;

/**
 * 
 * @author Supraj
 * 
 *         Validator Implementation to check if a symbol is registered with a
 *         company or not
 */
@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public String getCompanyName(String companySymbol) throws InvalidSymbolException {
		return companySymbol;
		// TODO

	}

}

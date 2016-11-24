package com.logicmonitor.spm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicmonitor.spm.dao.StockHistoryDAO;
import com.logicmonitor.spm.dto.StockDetailsDTO;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.model.CompanyInfo;
import com.logicmonitor.spm.service.StockDetailsService;

/**
 * Service implementation to provide stock information of companies
 * 
 * @author Supraj
 *
 */
@Service
public class StockDetailsServiceImpl implements StockDetailsService {

	@Autowired
	StockHistoryDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CompanyInfo> getAllCompanies() throws StorageException {
		return dao.getAllCompanies();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<StockDetailsDTO> getCompanyHistory(String symbol) throws StorageException {
		return dao.getCompanyHistory(symbol);
	}

}

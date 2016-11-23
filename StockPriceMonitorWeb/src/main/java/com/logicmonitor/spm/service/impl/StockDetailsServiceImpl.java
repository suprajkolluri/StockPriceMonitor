package com.logicmonitor.spm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicmonitor.spm.dao.StockHistoryDAO;
import com.logicmonitor.spm.dto.StockDetailsDTO;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.model.CompanyInfo;
import com.logicmonitor.spm.service.StockDetailsService;

@Service
public class StockDetailsServiceImpl implements StockDetailsService {

	@Autowired
	StockHistoryDAO dao;

	@Override
	public List<CompanyInfo> getAllCompanies() throws StorageException {
		return dao.getAllCompanies();
	}

	@Override
	public List<StockDetailsDTO> getCompanyHistory(String symbol) throws StorageException {
		return dao.getCompanyHistory(symbol);
	}

}

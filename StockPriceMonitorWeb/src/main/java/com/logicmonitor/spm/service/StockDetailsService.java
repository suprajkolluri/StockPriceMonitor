package com.logicmonitor.spm.service;

import java.util.List;

import com.logicmonitor.spm.dto.StockDetailsDTO;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.model.CompanyInfo;

public interface StockDetailsService {
	List<CompanyInfo> getAllCompanies() throws StorageException;

	List<StockDetailsDTO> getCompanyHistory(String symbol) throws StorageException;
}

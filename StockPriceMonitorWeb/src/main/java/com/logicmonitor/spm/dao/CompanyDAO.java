package com.logicmonitor.spm.dao;

import java.util.List;

import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.StorageException;

public interface CompanyDAO {

	void addCompany(CompanyDTO company) throws StorageException;

	void deleteCompany(String symbol) throws StorageException;

	List<CompanyDTO> getCompanies() throws StorageException;

	CompanyDTO getCompanyInfo(String companySymbol) throws StorageException;
}

package com.logicmonitor.spm.service;

import java.util.List;

import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.exception.StorageException;

public interface CompanyService {
	void addCompany(String companySymbol) throws InvalidSymbolException, StorageException, InvalidJsonException;

	void deleteCompany(String companySymbol) throws StorageException;

	List<CompanyDTO> getCompanies() throws StorageException;

	CompanyDTO getCompanyInfo(String companySymbol) throws StorageException;
}

package com.logicmonitor.spm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicmonitor.spm.dao.CompanyDAO;
import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.service.CompanyDetailsService;
import com.logicmonitor.spm.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	CompanyDetailsService detailsService;

	@Autowired
	CompanyDAO dao;

	@Override
	public void addCompany(String companySymbol) throws InvalidSymbolException, StorageException {
		String name = detailsService.getCompanyName(companySymbol);

		CompanyDTO company = new CompanyDTO(companySymbol, name);

		dao.addCompany(company);

	}

	@Override
	public void deleteCompany(String companySymbol) throws StorageException {
		dao.deleteCompany(companySymbol);
	}

	@Override
	public List<CompanyDTO> getCompanies() throws StorageException {

		return dao.getCompanies();
	}

	@Override
	public CompanyDTO getCompanyInfo(String companySymbol) throws StorageException {
		return dao.getCompanyInfo(companySymbol);
	}
}

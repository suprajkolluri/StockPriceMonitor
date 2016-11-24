package com.logicmonitor.spm.batch.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicmonitor.spm.batch.dao.CompanyDAO;
import com.logicmonitor.spm.batch.dto.CompanyDTO;
import com.logicmonitor.spm.batch.exception.StorageException;
import com.logicmonitor.spm.batch.service.CompanyService;

/**
 * Service Implementation to get the company symbols from the database
 * 
 * @author Supraj
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getCompanySymbols() throws StorageException {

		List<CompanyDTO> companyList = dao.getCompanies();

		return companyList.parallelStream().map(company -> company.getSymbol()).collect(Collectors.toList());
	}
}

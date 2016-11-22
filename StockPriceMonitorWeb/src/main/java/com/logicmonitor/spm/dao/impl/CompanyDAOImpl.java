package com.logicmonitor.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logicmonitor.spm.dao.CompanyDAO;
import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.StorageException;

@Repository
public class CompanyDAOImpl extends BaseDAOImpl<CompanyDTO> implements CompanyDAO {

	@Override
	public void addCompany(CompanyDTO company) throws StorageException {

		try {
			saveModel(company);
		} catch (HibernateException e) {
			throw new StorageException(e);
		}

	}

	@Override
	@Transactional
	public void deleteCompany(String companySymbol) throws StorageException {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from CompanyDTO where symbol =:symbol");
		query.setParameter("symbol", companySymbol);

		try {
			query.executeUpdate();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyDTO> getCompanies() throws StorageException {
		Query query = sessionFactory.getCurrentSession().createQuery("from CompanyDTO");
		List<CompanyDTO> companyList = new ArrayList<>();
		try {
			companyList = query.getResultList();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return companyList;
	}

	@Override
	@Transactional
	public CompanyDTO getCompanyInfo(String companySymbol) throws StorageException {
		Query query = sessionFactory.getCurrentSession().createQuery("from CompanyDTO where symbol =:symbol ");
		query.setParameter("symbol", companySymbol);
		CompanyDTO company = null;
		try {
			company = (CompanyDTO) query.getSingleResult();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return company;
	}

}

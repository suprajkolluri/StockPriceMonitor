package com.logicmonitor.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logicmonitor.spm.dao.CompanyDAO;
import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.StorageException;

/**
 * DAO Implementation to perform CRUD operations on {@link CompanyDTO} entity
 * 
 * @author Supraj
 *
 */
@Repository
@PropertySource("classpath:company-queries.properties")
public class CompanyDAOImpl extends BaseDAOImpl<CompanyDTO> implements CompanyDAO {

	@Autowired
	Environment env;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void addCompany(CompanyDTO company) throws StorageException {

		try {
			saveDTO(company);
		} catch (HibernateException e) {
			throw new StorageException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteCompany(String companySymbol) throws StorageException {

		try {
			Query query = sessionFactory.getCurrentSession().createQuery(env.getProperty("delete-company"));
			query.setParameter("symbol", companySymbol);
			query.executeUpdate();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<CompanyDTO> getCompanies() throws StorageException {
		List<CompanyDTO> companyList = new ArrayList<>();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(env.getProperty("get-companies"));
			companyList = query.getResultList();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return companyList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CompanyDTO getCompanyInfo(String companySymbol) throws StorageException {
		CompanyDTO company = null;
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(env.getProperty("get-company"));
			query.setParameter("symbol", companySymbol);
			company = (CompanyDTO) query.getSingleResult();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return company;
	}

}

package com.logicmonitor.spm.batch.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logicmonitor.spm.batch.dao.CompanyDAO;
import com.logicmonitor.spm.batch.dto.CompanyDTO;
import com.logicmonitor.spm.batch.exception.StorageException;

/**
 * DAO Implementation to retrieve company information from the database
 * 
 * @author Supraj
 *
 */
@Repository
@PropertySource("classpath:company-queries.properties")
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	Environment env;

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
			companyList = query.list();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return companyList;
	}

}

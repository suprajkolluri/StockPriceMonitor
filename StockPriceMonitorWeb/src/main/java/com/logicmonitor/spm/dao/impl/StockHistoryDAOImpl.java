package com.logicmonitor.spm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.logicmonitor.spm.dao.StockHistoryDAO;
import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.dto.StockDetailsDTO;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.model.CompanyInfo;

@Repository
@PropertySource("classpath:stock-info-queries.properties")
public class StockHistoryDAOImpl extends BaseDAOImpl<CompanyDTO> implements StockHistoryDAO {

	@Autowired
	Environment env;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional
	public List<CompanyInfo> getAllCompanies() throws StorageException {
		List<CompanyInfo> companyList = new ArrayList<>();

		try {
			Query query = sessionFactory.getCurrentSession()
					.createNativeQuery(env.getProperty("get-company-stock-details"))
					.setResultTransformer(Transformers.aliasToBean(CompanyInfo.class));
			companyList = query.getResultList();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return companyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<StockDetailsDTO> getCompanyHistory(String symbol) throws StorageException {

		List<StockDetailsDTO> companyHistory = new ArrayList<>();
		try {
			Query query = sessionFactory.getCurrentSession().createQuery(env.getProperty("company-history"));
			query.setParameter("symbol", symbol);
			companyHistory = query.getResultList();
		} catch (HibernateException e) {
			throw new StorageException(e);
		}
		return companyHistory;
	}

}

package com.logicmonitor.spm.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.logicmonitor.spm.dao.BaseDAO;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	@Transactional
	public void saveModel(T model) {
		sessionFactory.getCurrentSession().save(model);
	}

}

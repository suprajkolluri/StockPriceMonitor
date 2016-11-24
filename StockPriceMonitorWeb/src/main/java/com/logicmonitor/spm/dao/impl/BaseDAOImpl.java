package com.logicmonitor.spm.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.logicmonitor.spm.dao.BaseDAO;

/**
 * Abstract DAO class implementation that provides session factory to all DAOs
 * and a common method to persist a DTO
 * 
 * @author Supraj
 *
 * @param <T>
 *            The class of the entity object to persist
 */
public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveDTO(T dto) {
		sessionFactory.getCurrentSession().save(dto);
	}

}

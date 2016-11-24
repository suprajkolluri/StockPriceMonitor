package com.logicmonitor.spm.dao;

/**
 * Abstract DAO class that provides session factory to all DAOs and a common
 * method to persist a DTO
 * 
 * @author Supraj
 *
 * @param <T>
 *            The class of the entity object to persist
 */
public interface BaseDAO<T> {

	/**
	 * Common method for all DAOs to persist an DTO object
	 * 
	 * @param dto
	 *            the data transfer object that needs to be persisted
	 */
	void saveDTO(T dto);

}

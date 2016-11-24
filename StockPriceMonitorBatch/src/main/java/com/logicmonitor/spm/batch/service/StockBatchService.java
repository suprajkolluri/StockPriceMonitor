package com.logicmonitor.spm.batch.service;

/**
 * Service thats runs periodically and adds stock information to the database
 * 
 * @author Supraj
 *
 */
public interface StockBatchService {

	/**
	 * Runs periodically and adds stock information to the database
	 */
	void addStockEntries();

}

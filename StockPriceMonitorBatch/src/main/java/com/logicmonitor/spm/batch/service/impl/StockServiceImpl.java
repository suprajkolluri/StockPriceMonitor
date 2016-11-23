package com.logicmonitor.spm.batch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logicmonitor.spm.batch.dao.StockHistoryDAO;
import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.StorageException;
import com.logicmonitor.spm.batch.service.StockService;

/**
 * Service Implementation to persist the stock information to the database
 * 
 * @author Supraj
 *
 */
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockHistoryDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveStockInfo(List<StockDetailsDTO> stockList) throws StorageException {
		dao.saveStockHistory(stockList);
	}
}

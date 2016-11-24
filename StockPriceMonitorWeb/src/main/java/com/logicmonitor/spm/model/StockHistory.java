package com.logicmonitor.spm.model;

import java.util.ArrayList;
import java.util.List;

import com.logicmonitor.spm.dto.StockDetailsDTO;

/**
 * Model class to hold a companies stock history information
 * 
 * @author Supraj
 *
 */
public class StockHistory {

	/**
	 * The symbol of the company
	 */
	private String symbol;

	/**
	 * The monitored entries of a stock
	 */
	private List<StockDetailsDTO> stockHistory = new ArrayList<>();

	public StockHistory() {

	}

	public StockHistory(String symbol, List<StockDetailsDTO> stockHistory) {
		this.symbol = symbol;
		this.stockHistory = stockHistory;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<StockDetailsDTO> getStockHistory() {
		return stockHistory;
	}

	public void setStockHistory(List<StockDetailsDTO> stockHistory) {
		this.stockHistory = stockHistory;
	}

}

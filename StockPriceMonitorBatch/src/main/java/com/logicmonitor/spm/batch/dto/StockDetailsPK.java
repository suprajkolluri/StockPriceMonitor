package com.logicmonitor.spm.batch.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * ID Class to indicate that 2 fields of {@link StockDetailsDTO} are primary
 * keys
 * 
 * @author Supraj
 *
 */
public class StockDetailsPK implements Serializable {
	private static final long serialVersionUID = 5865088528411763876L;

	protected String symbol;

	protected Date time;

	public StockDetailsPK() {
	}

	public StockDetailsPK(String symbol, Date time) {
		this.symbol = symbol;
		this.time = time;
	}

}

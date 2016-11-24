package com.logicmonitor.spm.batch.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class to the hold the stock_history table information
 * 
 * @author Supraj
 *
 */
@Entity
@Table(name = "Stock_History")
@IdClass(StockDetailsPK.class)
public class StockDetailsDTO {

	/**
	 * The time the entry was added to the database
	 */
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	/**
	 * The stock price of a company
	 */
	@Column(name = "stock_price", nullable = false)
	private Double stockPrice;

	/**
	 * The symbol of a company
	 */
	@Id
	private String symbol;

	public StockDetailsDTO() {
	}

	public StockDetailsDTO(Double stockPrice, String symbol) {
		this.stockPrice = stockPrice;
		this.symbol = symbol;
		this.time = new Date();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "StockDetails{" + "time=" + time + ", stockPrice=" + stockPrice + ", symbol='" + symbol + '\'' + '}';
	}
}

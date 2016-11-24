package com.logicmonitor.spm.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Entity class to the hold the stock_history table information
 * 
 * @author Supraj
 *
 */
@Entity
@Table(name = "Stock_History")
@IdClass(StockDetailsPK.class)
@JsonIgnoreProperties("company")
public class StockDetailsDTO {

	/**
	 * Foreign key reference to the symbol column in the {@link CompanyDTO}
	 */
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symbol", referencedColumnName = "symbol")
	private CompanyDTO company;

	/**
	 * The time the entry was added to the database
	 */
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd mm:ss")
	private Date time;

	/**
	 * The stock price of a company
	 */
	@Column(name = "stock_price", nullable = false)
	private Double stockPrice;

	public CompanyDTO getCompany() {
		return company;
	}

	public void setCompany(CompanyDTO company) {
		this.company = company;
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

	@Override
	public String toString() {
		return "StockHistory [company=" + company + ", time=" + time + ", stockPrice=" + stockPrice + "]";
	}

}

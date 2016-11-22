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

@Entity
@Table(name = "Stock_History")
@IdClass(StockDetailsPK.class)
public class StockDetailsDTO {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "symbol", referencedColumnName = "symbol")
	private CompanyDTO company;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

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

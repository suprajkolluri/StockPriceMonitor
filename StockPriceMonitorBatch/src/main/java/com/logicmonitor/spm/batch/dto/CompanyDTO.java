package com.logicmonitor.spm.batch.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity class to hold the Company table information
 * 
 * @author Supraj
 * 
 */
@Entity
@Table(name = "Company")
public class CompanyDTO {

	/**
	 * The symbol of the company
	 */
	@Id
	private String symbol;

	/**
	 * The name of the company
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * The date the company is added to the database
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public CompanyDTO() {

	}

	public CompanyDTO(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
		this.created = new Date();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Company [symbol=" + symbol + ", name=" + name + ", created=" + created + "]";
	}

}

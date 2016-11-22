package com.logicmonitor.spm.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Supraj
 * 
 */
@Entity
@Table(name = "Company")
public class CompanyDTO {

	@Id
	private String symbol;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd mm:ss")
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

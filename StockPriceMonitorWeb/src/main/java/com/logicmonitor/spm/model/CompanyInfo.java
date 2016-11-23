package com.logicmonitor.spm.model;

/**
 * Model Class to hold the company information
 * 
 * @author Supraj
 *
 */
public class CompanyInfo {
	/**
	 * The name of the company
	 */
	private String name;

	/**
	 * The symbol of the company
	 */
	private String symbol;

	/**
	 * The latest updated stock price of a company
	 */
	private Double last_updated_price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getLast_updated_price() {
		return last_updated_price;
	}

	public void setLast_updated_price(Double last_updated_price) {
		this.last_updated_price = last_updated_price;
	}

	@Override
	public String toString() {
		return "CompanyInfo [name=" + name + ", symbol=" + symbol + ", last_updated_price=" + last_updated_price + "]";
	}

}

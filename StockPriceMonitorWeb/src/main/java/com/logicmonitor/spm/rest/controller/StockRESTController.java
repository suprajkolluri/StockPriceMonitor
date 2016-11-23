package com.logicmonitor.spm.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logicmonitor.spm.dto.StockDetailsDTO;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.model.CompanyInfo;
import com.logicmonitor.spm.model.StockHistory;
import com.logicmonitor.spm.service.StockDetailsService;

/**
 * REST Controller to provide stock information of companies
 * 
 * @author Supraj
 *
 */
@RestController
@RequestMapping("/rest/stocks")
public class StockRESTController {

	@Autowired
	StockDetailsService detailsService;

	final static Logger logger = Logger.getLogger(StockRESTController.class);

	/**
	 * Lists the names of all companies that are monitored with their last
	 * updated stock price
	 * 
	 * @return - List of {@link CompanyInfo} objects which contains the company
	 *         name, symbol and last updated stock price
	 * 
	 *         Internal server error if there was an exception while retrieving
	 *         the data
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompanyInfo>> listAllCompanies() {
		List<CompanyInfo> companyList = null;

		try {
			companyList = detailsService.getAllCompanies();
		} catch (StorageException e) {
			logger.error(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(companyList, HttpStatus.OK);

	}

	/**
	 * Returns the historical information of a company
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return A {@link StockHistory} object which contains the company symbol
	 *         and List of {@link StockDetailsDTO} objects which contains the
	 *         companies stock prices that are updated periodically
	 * 
	 *         Internal server error if there was an exception while retrieving
	 *         the data
	 */
	@GetMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockHistory> getCompanyHistory(@PathVariable("company-symbol") String companySymbol) {
		StockHistory companyHistory = null;
		try {
			List<StockDetailsDTO> stockHistory = detailsService.getCompanyHistory(companySymbol);
			companyHistory = new StockHistory(companySymbol, stockHistory);
		} catch (StorageException e) {
			logger.error(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(companyHistory, HttpStatus.OK);

	}
}

package com.logicmonitor.spm.rest.controller;

import java.util.List;

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

@RestController
@RequestMapping("/rest/stocks")
public class StockRESTController {

	@Autowired
	StockDetailsService detailsService;

	/**
	 * Lists the names of all companies that are monitored with their last
	 * updated stock price
	 * 
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompanyInfo>> listAllCompanies() {
		List<CompanyInfo> companyList = null;

		try {
			companyList = detailsService.getAllCompanies();
		} catch (StorageException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(companyList, HttpStatus.OK);

	}

	/**
	 * Returns the historical information of a company
	 * 
	 * @param companySymbol
	 * @return
	 */
	@GetMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockHistory> getCompanyHistory(@PathVariable("company-symbol") String companySymbol) {
		StockHistory companyHistory = null;
		try {
			List<StockDetailsDTO> stockHistory = detailsService.getCompanyHistory(companySymbol);
			companyHistory = new StockHistory(companySymbol, stockHistory);
		} catch (StorageException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(companyHistory, HttpStatus.OK);

	}
}

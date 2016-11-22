package com.logicmonitor.spm.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/stocks")
public class StockRESTController {

	/**
	 * Lists the names of all companies that are monitored with their last
	 * updated stock price
	 * 
	 * @return
	 */
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> listAllCompanies() {
		// TODO
		return new ResponseEntity<>("alld", HttpStatus.OK);

	}

	/**
	 * Returns the historical information of a company
	 * 
	 * @param companySymbol
	 * @return
	 */
	@GetMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCompanyHistory(@PathVariable("company-symbol") String companySymbol) {
		// TODO
		return new ResponseEntity<>(companySymbol, HttpStatus.OK);

	}
}

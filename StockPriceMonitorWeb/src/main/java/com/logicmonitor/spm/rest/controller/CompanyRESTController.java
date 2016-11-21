package com.logicmonitor.spm.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.validator.CompanySymbolValidator;

/**
 * 
 * @author Supraj
 * 
 *         Rest Controller to Add, Delete and Read monitored company information
 */
@RestController
@RequestMapping("/rest/company")
public class CompanyRESTController {

	@Autowired
	CompanySymbolValidator symbolValidator;

	/**
	 * Add's a company to the list of companies that are monitored in the
	 * database.
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return
	 */
	@PostMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addCompany(@PathVariable("company-symbol") String companySymbol) {
		// TODO
		try {
			symbolValidator.validateSymbol(companySymbol);
		} catch (InvalidSymbolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(companySymbol, HttpStatus.OK);

	}

	/**
	 * 
	 * Deletes a company from the list of companies that are monitored in the
	 * database
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return
	 */
	@DeleteMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteCompany(@PathVariable("company-symbol") String companySymbol) {
		// TODO
		return new ResponseEntity<>(companySymbol, HttpStatus.OK);

	}

	/**
	 * Lists the names of all companies that are monitored with their last
	 * updated stock price
	 * 
	 * @return
	 */
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
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
	@GetMapping(value = "/{company-symbol}/history", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCompanyHistory(@PathVariable("company-symbol") String companySymbol) {
		// TODO
		return new ResponseEntity<>(companySymbol, HttpStatus.OK);

	}

	/**
	 * Returns the last updated stock price of a company
	 * 
	 * @param companySymbol
	 * @return
	 */
	@GetMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCompanyInfo(@PathVariable("company-symbol") String companySymbol) {
		// TODO
		return new ResponseEntity<>(companySymbol, HttpStatus.OK);

	}

}

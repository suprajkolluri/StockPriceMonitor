package com.logicmonitor.spm.rest.controller;

import java.util.List;

import org.apache.log4j.Logger;
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

import com.logicmonitor.spm.dto.CompanyDTO;
import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.exception.StorageException;
import com.logicmonitor.spm.service.CompanyService;

/**
 * Rest Controller to Add, Delete and Read monitored company information
 * 
 * @author Supraj
 * 
 */
@RestController
@RequestMapping("/rest/companies")
public class CompanyRESTController {

	final static Logger logger = Logger.getLogger(CompanyRESTController.class);

	@Autowired
	CompanyService companyService;

	/**
	 * Add's a company to the list of companies that are monitored in the
	 * database.
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return - A success message if the company was added successfully
	 * 
	 *         An exception message with http status of 400, if the given
	 *         company symbol is invalid
	 * 
	 *         An exception message with http status of 500, if there was any
	 *         exception while validating the symbol or persisting the company
	 *         information
	 */
	@PostMapping(value = "/{company-symbol}")
	public ResponseEntity<String> addCompany(@PathVariable("company-symbol") String companySymbol) {

		try {
			companyService.addCompany(companySymbol);
		} catch (InvalidSymbolException e) {
			logger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (StorageException | InvalidJsonException e) {
			logger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Added the company:" + companySymbol + "successfully", HttpStatus.CREATED);

	}

	/**
	 * 
	 * Deletes a company from the list of companies that are monitored in the
	 * database
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return - A success message if the company was deleted successfully
	 * 
	 *         An exception message with http status of 500, if there was any
	 *         exception while deleting the company information
	 */
	@DeleteMapping(value = "/{company-symbol}")
	public ResponseEntity<String> deleteCompany(@PathVariable("company-symbol") String companySymbol) {
		try {
			companyService.deleteCompany(companySymbol);
		} catch (StorageException e) {
			logger.error(e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Deleted the company:" + companySymbol + "successfully", HttpStatus.OK);

	}

	/**
	 * Lists the names of all companies that are monitored
	 * 
	 * @return - An array of {@link CompanyDTO} objects which contain the
	 *         company name, symbol and created date
	 * 
	 *         Internal server error if there was an exception while retrieving
	 *         the data
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompanyDTO>> listAllCompanies() {
		List<CompanyDTO> companyList = null;
		try {
			companyList = companyService.getCompanies();
		} catch (StorageException e) {
			logger.error(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(companyList, HttpStatus.OK);

	}

	/**
	 * Returns the a particular company information
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return - A {@link CompanyDTO} object with its name, symbol and creation
	 *         time
	 * 
	 *         Internal server error if there was an exception while retrieving
	 *         the data
	 */
	@GetMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompanyDTO> getCompanyInfo(@PathVariable("company-symbol") String companySymbol) {
		CompanyDTO company = null;
		try {
			company = companyService.getCompanyInfo(companySymbol);
		} catch (StorageException e) {
			logger.error(e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(company, HttpStatus.OK);

	}

}

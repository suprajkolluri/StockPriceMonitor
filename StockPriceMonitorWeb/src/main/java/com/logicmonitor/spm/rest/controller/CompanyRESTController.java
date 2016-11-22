package com.logicmonitor.spm.rest.controller;

import java.util.List;

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
 * 
 * @author Supraj
 * 
 *         Rest Controller to Add, Delete and Read monitored company information
 */
@RestController
@RequestMapping("/rest/companies")
public class CompanyRESTController {

	@Autowired
	CompanyService companyService;

	/**
	 * Add's a company to the list of companies that are monitored in the
	 * database.
	 * 
	 * @param companySymbol
	 *            - The stock symbol of the company. Example - Microsoft - MSFT
	 * @return
	 */
	@PostMapping(value = "/{company-symbol}")
	public ResponseEntity<String> addCompany(@PathVariable("company-symbol") String companySymbol) {

		try {
			companyService.addCompany(companySymbol);
		} catch (InvalidSymbolException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (StorageException | InvalidJsonException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Success", HttpStatus.CREATED);

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
	@DeleteMapping(value = "/{company-symbol}")
	public ResponseEntity<String> deleteCompany(@PathVariable("company-symbol") String companySymbol) {
		try {
			companyService.deleteCompany(companySymbol);
		} catch (StorageException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Success", HttpStatus.OK);

	}

	/**
	 * Lists the names of all companies that are monitored
	 * 
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompanyDTO>> listAllCompanies() {
		List<CompanyDTO> companyList = null;
		try {
			companyList = companyService.getCompanies();
		} catch (StorageException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(companyList, HttpStatus.OK);

	}

	/**
	 * Returns the company information
	 * 
	 * @param companySymbol
	 * @return
	 */
	@GetMapping(value = "/{company-symbol}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompanyDTO> getCompanyInfo(@PathVariable("company-symbol") String companySymbol) {
		CompanyDTO company = null;
		try {
			company = companyService.getCompanyInfo(companySymbol);
		} catch (StorageException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(company, HttpStatus.OK);

	}

}

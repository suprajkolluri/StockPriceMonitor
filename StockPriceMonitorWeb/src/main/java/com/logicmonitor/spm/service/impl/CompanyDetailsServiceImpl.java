package com.logicmonitor.spm.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.service.CompanyDetailsService;
import com.logicmonitor.spm.service.URIGeneratorService;

/**
 * 
 * @author Supraj
 * 
 *         Validator Implementation to check if a symbol is registered with a
 *         company or not
 */
@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	@Autowired
	private URIGeneratorService uriGeneratorService;

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 * @throws InvalidJsonException
	 */
	@Override
	public String getCompanyName(String companySymbol) throws InvalidSymbolException, InvalidJsonException {
		String name = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			URI uri = uriGeneratorService.getStockRestURI(companySymbol);
			String res = restTemplate.getForObject(uri, String.class);
			Gson gson = new Gson();
			JsonObject json = gson.fromJson(res, JsonObject.class);
			name = json.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote").get("Name")
					.getAsString();

		} catch (Exception e) {
			throw new InvalidJsonException(e);
		}

		if (name == null) {
			throw new InvalidSymbolException("No Comanpy Registerd with the provided symbol: " + companySymbol);
		}
		return name;

	}

}

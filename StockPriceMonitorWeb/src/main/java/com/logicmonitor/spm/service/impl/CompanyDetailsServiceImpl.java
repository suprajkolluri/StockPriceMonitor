package com.logicmonitor.spm.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.service.CompanyDetailsService;
import com.logicmonitor.spm.service.URIGeneratorService;

/**
 * Service implementation which fetches company details for a symbol from the
 * Stock information provider
 * 
 * @author Supraj
 * 
 */
@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

	@Autowired
	private URIGeneratorService uriGeneratorService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCompanyName(String companySymbol) throws InvalidSymbolException, InvalidJsonException {
		String name = null;
		try {
			// Get URI of the stock provider
			URI uri = uriGeneratorService.getStockRestURI(companySymbol);

			// Get the response from the stock provider service
			RestTemplate restTemplate = new RestTemplate();
			String res = restTemplate.getForObject(uri, String.class);

			Gson gson = new Gson();

			// Retrieve the name of the company from the JSON
			JsonObject json = gson.fromJson(res, JsonObject.class);
			JsonElement nameElement = json.getAsJsonObject("query").getAsJsonObject("results").getAsJsonObject("quote")
					.get("Name");

			name = (nameElement.isJsonNull()) ? null : nameElement.getAsString();
		} catch (Exception e) {
			throw new InvalidJsonException(e);
		}

		if (name == null) {
			throw new InvalidSymbolException("No Company Registerd with the provided symbol: " + companySymbol);
		}
		return name;

	}

}

package com.logicmonitor.spm.batch.service.impl;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.logicmonitor.spm.batch.exception.StorageException;
import com.logicmonitor.spm.batch.service.URIGeneratorService;

/**
 * Service Implementation to generate the Stock Provider URI
 * 
 * @author Supraj
 *
 */
@Service
@PropertySource("classpath:stockprovider.properties")
public class URIGeneratorServiceImpl implements URIGeneratorService {

	@Autowired
	private Environment env;

	@Autowired
	private CompanyServiceImpl companyService;

	/**
	 * Get stock REST provider
	 * 
	 * @return {URI}
	 * @throws StorageException
	 */
	@Override
	public URI getStockRestURI() throws StorageException {
		List<String> symbols = companyService.getCompanySymbols();

		String symbolQueryString = getQueryFromSymbols(symbols);
		// System.out.println(symbolQueryString);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("q", String.format("%s %s", env.getProperty("stockprovider.query"), symbolQueryString));
		map.add("format", env.getProperty("stockprovider.format"));
		map.add("env", env.getProperty("stockprovider.env"));

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme(env.getProperty("stockprovider.scheme"))
				.host(env.getProperty("stockprovider.host")).path(env.getProperty("stockprovider.path"))
				.queryParams(map).build();
		return uriComponents.toUri();
	}

	/**
	 * Get resulting symbol in this format [1, 3, 4, 5] => ("1",)
	 * 
	 * @param symbols
	 *            List of company symbols
	 * @return {String}
	 */
	private String getQueryFromSymbols(List<String> symbols) {
		StringBuilder sb = new StringBuilder();
		String quote = "\"";
		String commaSepSymbols = symbols.stream().map(s -> quote + s + quote).collect(Collectors.joining(","));
		return sb.append("(").append(commaSepSymbols).append(")").toString();
	}
}

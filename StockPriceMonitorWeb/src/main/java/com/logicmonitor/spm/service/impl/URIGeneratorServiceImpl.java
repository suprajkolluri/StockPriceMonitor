package com.logicmonitor.spm.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.logicmonitor.spm.service.URIGeneratorService;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI getStockRestURI(String symbol) {
		// Add query parameters to the URL
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("q", String.format("%s %s", env.getProperty("stockprovider.query"), "(\"" + symbol + "\")"));
		map.add("format", env.getProperty("stockprovider.format"));
		map.add("env", env.getProperty("stockprovider.env"));

		// Build the URI
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme(env.getProperty("stockprovider.scheme"))
				.host(env.getProperty("stockprovider.host")).path(env.getProperty("stockprovider.path"))
				.queryParams(map).build();
		return uriComponents.toUri();
	}

}

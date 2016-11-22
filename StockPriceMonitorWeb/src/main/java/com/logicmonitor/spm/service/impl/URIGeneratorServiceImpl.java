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

@Service
@PropertySource("classpath:stockprovider.properties")
public class URIGeneratorServiceImpl implements URIGeneratorService {

	@Autowired
	private Environment env;

	/**
	 * Get stock REST provider
	 * 
	 * @return {URI}
	 */
	@Override
	public URI getStockRestURI(String symbol) {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("q", String.format("%s %s", env.getProperty("stockprovider.query"), "(\"" + symbol + "\")"));
		map.add("format", env.getProperty("stockprovider.format"));
		map.add("env", env.getProperty("stockprovider.env"));

		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme(env.getProperty("stockprovider.scheme"))
				.host(env.getProperty("stockprovider.host")).path(env.getProperty("stockprovider.path"))
				.queryParams(map).build();
		return uriComponents.toUri();
	}

}

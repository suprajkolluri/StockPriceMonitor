package com.logicmonitor.spm.service;

import java.net.URI;

public interface URIGeneratorService {
	URI getStockRestURI(String symbol);
}

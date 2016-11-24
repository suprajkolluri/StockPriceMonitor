package com.logicmonitor.spm.batch.service.impl;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.InvalidJsonException;
import com.logicmonitor.spm.batch.exception.StorageException;
import com.logicmonitor.spm.batch.service.StockBatchService;
import com.logicmonitor.spm.batch.service.StockService;
import com.logicmonitor.spm.batch.util.StockRESTUtilService;

/**
 * Service Implementation thats runs periodically and adds stock information to
 * the database
 * 
 * @author Supraj
 *
 */
@Service
public class StockBatchServiceImpl implements StockBatchService {

	@Autowired
	private URIGeneratorServiceImpl uriGeneratorService;

	@Autowired
	private StockRESTUtilService stockRestUtilService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	StockService stockService;

	final static Logger logger = Logger.getLogger(StockBatchServiceImpl.class);

	/**
	 * The cron Job will run every 5 minutes from Monday to Friday from 9 AM to
	 * 4 PM, since the stock market is open only during these timings
	 * {@inheritDoc}
	 */
	@Scheduled(cron = "0 0/5 9-16 * * 1-5")
	@Override
	public void addStockEntries() {

		try {
			URI uri = uriGeneratorService.getStockRestURI();
			String out = restTemplate.getForObject(uri, String.class);
			List<StockDetailsDTO> stocks = stockRestUtilService.convertMessageToObject(out);
			stockService.saveStockInfo(stocks);
			logger.info("Saved the following stocks successfully: " + stocks);
		} catch (RestClientException | InvalidJsonException | StorageException e) {
			logger.error(e);
		}

	}
}

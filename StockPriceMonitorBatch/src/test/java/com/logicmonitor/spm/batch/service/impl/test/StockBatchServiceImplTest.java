package com.logicmonitor.spm.batch.service.impl.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.InvalidJsonException;
import com.logicmonitor.spm.batch.service.StockService;
import com.logicmonitor.spm.batch.service.impl.StockBatchServiceImpl;
import com.logicmonitor.spm.batch.service.impl.URIGeneratorServiceImpl;
import com.logicmonitor.spm.batch.util.StockRESTUtilService;

/**
 * 
 * Unit test cases for StockBatchServiceImpl.
 * 
 * @author Supraj
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StockRESTUtilService.class)
public class StockBatchServiceImplTest {

	@Mock
	private URIGeneratorServiceImpl uriGeneratorService;

	@Mock
	private StockRESTUtilService stockRestUtilService;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private StockService stockService;

	@InjectMocks
	private StockBatchServiceImpl stockBatchServiceImpl;

	/**
	 * 
	 * Success Test case for addStockEntriesTest.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addStockEntriesTest() throws Exception {
		String uriString = "http://localhost:8080/sample/url";
		URI uri = new URI(uriString);
		String out = "out";
		List<StockDetailsDTO> list = new ArrayList<>();

		Mockito.doReturn(uri).when(uriGeneratorService).getStockRestURI();
		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
		PowerMockito.doReturn(out).when(restTemplate).getForObject(uri, String.class);
		Mockito.doReturn(list).when(stockRestUtilService).convertMessageToObject(out);
		Mockito.doNothing().when(stockService).saveStockInfo(list);

		stockBatchServiceImpl.addStockEntries();
	}

	/**
	 * 
	 * Test case for InvalidJsonException of addStockEntries method.
	 * 
	 * @throws Exception
	 */
	@Test
	public void addStockEntriesInvalidJSONExceptionTest() throws Exception {
		String uriString = "http://localhost:8080/sample/url";
		URI uri = new URI(uriString);
		String out = "out";
		List<StockDetailsDTO> list = new ArrayList<>();

		Mockito.doReturn(uri).when(uriGeneratorService).getStockRestURI();
		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
		PowerMockito.doReturn(out).when(restTemplate).getForObject(uri, String.class);
		Mockito.doThrow(new InvalidJsonException()).when(stockRestUtilService).convertMessageToObject(out);
		Mockito.doNothing().when(stockService).saveStockInfo(list);

		stockBatchServiceImpl.addStockEntries();
	}

}

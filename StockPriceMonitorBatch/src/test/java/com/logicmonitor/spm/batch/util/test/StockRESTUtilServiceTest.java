package com.logicmonitor.spm.batch.util.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.logicmonitor.spm.batch.dto.StockDetailsDTO;
import com.logicmonitor.spm.batch.exception.InvalidJsonException;
import com.logicmonitor.spm.batch.util.StockRESTUtilService;

/**
 * 
 * Unit test cases for StockRESTUtilService.
 * 
 * @author Supraj
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(StockRESTUtilService.class)
public class StockRESTUtilServiceTest {

	@InjectMocks
	StockRESTUtilService stockRESTUtilService;

	/**
	 * Positive test case for convertMessageToObject method.
	 * 
	 * @throws InvalidJsonException
	 */
	@Test
	public void convertMessageToObjectTest() throws InvalidJsonException {
		String jsonString = "{\"query\":{\"count\":1,\"created\":\"2016-11-23T09:55:57Z\",\"lang\":\"en-US\",\"results\":{\"quote\":[{\"Symbol\":\"ABC Corp!\",\"LastTradePriceOnly\":20}]}}}";
		List<StockDetailsDTO> actual = stockRESTUtilService.convertMessageToObject(jsonString);

		Assert.assertEquals(20.0, actual.get(0).getStockPrice(), 0);
		Assert.assertEquals("ABC Corp!", actual.get(0).getSymbol());
	}

	/**
	 * test case for InvalidJsonException.
	 * 
	 * @throws InvalidJsonException
	 */
	@Test(expected = InvalidJsonException.class)
	public void convertMessageToObjectInvalidJsonExceptionTest() throws InvalidJsonException {
		String jsonString = "{\"query\":{\"count\":1,\"created\":\"2016-11-23T09:55:57Z\",\"lang\":\"en-US\",\"results\":{\"quote\":[{\"Symbol\":\"ABC Corp!\"}]}}}";
		stockRESTUtilService.convertMessageToObject(jsonString);
	}
}

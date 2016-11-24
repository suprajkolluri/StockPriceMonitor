package com.logicmonitor.spm.service.impl.test;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import com.logicmonitor.spm.exception.InvalidJsonException;
import com.logicmonitor.spm.exception.InvalidSymbolException;
import com.logicmonitor.spm.service.URIGeneratorService;
import com.logicmonitor.spm.service.impl.CompanyDetailsServiceImpl;

/**
 * Unit test for CompanyDetailsServiceImpl.
 * 
 * @author Supraj
 * 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(CompanyDetailsServiceImpl.class)
public class CompanyDetailsServiceImplTest {

	@Mock
	private URIGeneratorService uriGeneratorService;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CompanyDetailsServiceImpl companyDetailsServiceImpl;

	/**
	 * 
	 * Positive Test case for getCompanyName.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCompanyNameTest() throws Exception {
		String comapanyName = "ABC";
		String uriString = "http://localhost:8080/sample/url";
		URI uri = new URI(uriString);
		String json = "{\"query\":{\"count\":1,\"created\":\"2016-11-23T09:55:57Z\",\"lang\":\"en-US\",\"results\":{\"quote\":{\"Name\":\"ABC Corp!\"}}}}";
		String expected = "ABC Corp!";

		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
		PowerMockito.doReturn(json).when(restTemplate).getForObject(uri, String.class);
		Mockito.when(uriGeneratorService.getStockRestURI(comapanyName)).thenReturn(uri);
		String actual = companyDetailsServiceImpl.getCompanyName(comapanyName);

		Assert.assertEquals(expected, actual);
	}

	/**
	 * 
	 * InvalidJsonException test case for getCompanyName.
	 * 
	 * @throws Exception
	 */
	@Test(expected = InvalidJsonException.class)
	public void getCompanyNameInvalidJsonExceptionTest() throws Exception {
		String comapanyName = "ABC";
		String uriString = "http://localhost:8080/sample/url";
		URI uri = new URI(uriString);
		String json = "{\"query\":{\"count\":1,\"created\":\"2016-11-23T09:55:57Z\",\"lang\":\"en-US\",\"results\":{\"quote\":{}}}}";

		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
		PowerMockito.doReturn(json).when(restTemplate).getForObject(uri, String.class);
		Mockito.when(uriGeneratorService.getStockRestURI(comapanyName)).thenReturn(uri);
		companyDetailsServiceImpl.getCompanyName(comapanyName);
	}

	/**
	 * 
	 * InvalidSymbolException test case for getCompanyName.
	 * 
	 * @throws Exception
	 */
	@Test(expected = InvalidSymbolException.class)
	public void getCompanyNameInvalidSymbolExceptionTest() throws Exception {
		String comapanyName = "ABC";
		String uriString = "http://localhost:8080/sample/url";
		URI uri = new URI(uriString);
		String json = "{\"query\":{\"count\":1,\"created\":\"2016-11-23T09:55:57Z\",\"lang\":\"en-US\",\"results\":{\"quote\":{\"Name\":null}}}}";

		PowerMockito.whenNew(RestTemplate.class).withNoArguments().thenReturn(restTemplate);
		PowerMockito.doReturn(json).when(restTemplate).getForObject(uri, String.class);
		Mockito.when(uriGeneratorService.getStockRestURI(comapanyName)).thenReturn(uri);
		companyDetailsServiceImpl.getCompanyName(comapanyName);
	}
}

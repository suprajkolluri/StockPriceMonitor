package com.logicmonitor.spm.batch.service.impl.test;

import java.net.URI;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import com.logicmonitor.spm.batch.exception.StorageException;
import com.logicmonitor.spm.batch.service.impl.CompanyServiceImpl;
import com.logicmonitor.spm.batch.service.impl.URIGeneratorServiceImpl;

/**
 * Unit test cases for URIGeneratorServiceImpl
 * 
 * @author Supraj
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class URIGeneratorServiceImplTest {
	@Mock
	private Environment env;

	@Mock
	private CompanyServiceImpl companyService;

	@InjectMocks
	URIGeneratorServiceImpl uriGeneratorServiceImpl;

	/**
	 * Test case to test getStockRestURI.
	 * 
	 * @throws StorageException
	 */
	@Test
	public void getStockRestURITest() throws StorageException {
		Mockito.doReturn(new ArrayList<String>()).when(companyService).getCompanySymbols();
		Mockito.doReturn("query").when(env).getProperty("stockprovider.query");
		Mockito.doReturn("format").when(env).getProperty("stockprovider.format");
		Mockito.doReturn("env").when(env).getProperty("stockprovider.env");
		Mockito.doReturn("scheme").when(env).getProperty("stockprovider.scheme");
		Mockito.doReturn("host").when(env).getProperty("stockprovider.host");
		Mockito.doReturn("path").when(env).getProperty("stockprovider.path");

		String expected = "scheme://host/path?q=query%20()&format=format&env=env";

		URI uri = uriGeneratorServiceImpl.getStockRestURI();

		Assert.assertEquals(expected, uri.toString());
	}
}

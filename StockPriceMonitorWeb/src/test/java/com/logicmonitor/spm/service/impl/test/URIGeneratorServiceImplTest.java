package com.logicmonitor.spm.service.impl.test;

import java.net.URI;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import com.logicmonitor.spm.service.impl.URIGeneratorServiceImpl;

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

	@InjectMocks
	URIGeneratorServiceImpl uriGeneratorServiceImpl;

	/**
	 * Test case to test building the URI
	 */
	@Test
	public void getStockRestURITest() {
		Mockito.doReturn("query").when(env).getProperty("stockprovider.query");
		Mockito.doReturn("format").when(env).getProperty("stockprovider.format");
		Mockito.doReturn("env").when(env).getProperty("stockprovider.env");
		Mockito.doReturn("scheme").when(env).getProperty("stockprovider.scheme");
		Mockito.doReturn("host").when(env).getProperty("stockprovider.host");
		Mockito.doReturn("path").when(env).getProperty("stockprovider.path");

		String expected = "scheme://host/path?q=query%20(%22abc%22)&format=format&env=env";

		URI uri = uriGeneratorServiceImpl.getStockRestURI("abc");

		Assert.assertEquals(expected, uri.toString());
	}

}

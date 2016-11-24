package com.logicmonitor.spm.spring.configuration.initialization;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.logicmonitor.spm.spring.configuration.ApplicationConfig;

/**
 * Default Spring configuration class which sets the root, servlet configuration
 * classes and servlet mappings.
 * 
 * @author Supraj
 * 
 */
public class SpringConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
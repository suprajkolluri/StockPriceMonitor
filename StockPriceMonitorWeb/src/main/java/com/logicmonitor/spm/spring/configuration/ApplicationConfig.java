package com.logicmonitor.spm.spring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Supraj
 * 
 *         Default application configuration class that will load all the spring
 *         related configurations and inject all the dependencies.
 *
 */
@ComponentScan({ "com.logicmonitor.spm.*" })
@Configuration
@EnableWebMvc
@Import({ DBConfig.class })
public class ApplicationConfig {

}
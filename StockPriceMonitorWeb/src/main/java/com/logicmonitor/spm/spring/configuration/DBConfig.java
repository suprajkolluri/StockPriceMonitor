package com.logicmonitor.spm.spring.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.logicmonitor.spm.util.jasypt.PasswordDecryptUtil;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database-${env}.properties")
public class DBConfig {

	@Autowired
	Environment env;

	@Autowired
	PasswordDecryptUtil decryptor;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		String url = "jdbc:" + env.getProperty("db") + "://" + env.getProperty("host") + ":" + env.getProperty("port")
				+ "/" + env.getProperty("dbname");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUrl(url);
		dataSource.setUsername(env.getProperty("user"));
		String password = decryptor.decrypt(env.getProperty("password"));
		dataSource.setPassword(password);

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.scanPackages("com.logicmonitor.spm.dto");

		sessionBuilder.addProperties(getHibernateProperties());

		return sessionBuilder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "none");
		return properties;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
}

package com.rudra.aks.pool;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PoolDBConfig {

	@Value("${jdbc.driverClassName}")
	private	String 	driverClassName;
	
	@Value("${jdbc.connectionUrl}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	
	@Bean
	public	BasicDataSource	pooledDataSource() {
		BasicDataSource		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		
		//Initial Pool size after start
		dataSource.setInitialSize(5);
		
		//Max number of active connections [ default - 8]
		dataSource.setMaxTotal(10);
		
		//Max number of connections which may remain idle in pool [ default - 8]
		dataSource.setMaxIdle(2);
		
		//Max lifetime of a connection
		dataSource.setMaxConnLifetimeMillis(15000);
		
		//Max time pool will wait for returning connection, if no connection remains in pool
		dataSource.setMaxWaitMillis(30000);
		return dataSource;
	}
	
	@Bean
	@Scope("prototype")
	public	JdbcTemplate	jdbcTemplate() {
		return new JdbcTemplate(pooledDataSource(), true);
	
	}
	
	@Bean
	@Scope("prototype")
	public	PlatformTransactionManager	txManager() {
		return new DataSourceTransactionManager(pooledDataSource());
	}
}

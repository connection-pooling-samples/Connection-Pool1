package com.rudra.aks.pool;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PoolApp {

	
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(PoolApp.class, args);
		
	}
}

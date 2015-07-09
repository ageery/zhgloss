package com.zixinxi;

import java.net.URI;
import java.net.URISyntaxException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource newDataSource() throws URISyntaxException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
		int port = dbUri.getPort();
		String url = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();
    	DataSourceBuilder builder = DataSourceBuilder.create()
    		.type(HikariDataSource.class)
    		.url(url);
		if (dbUri.getUserInfo() != null) {
			String[] connectionInfo = dbUri.getUserInfo().split(":");
			builder.username(connectionInfo[0]);
			builder.password(connectionInfo[1]);
		}
		return builder.build();
    }

}

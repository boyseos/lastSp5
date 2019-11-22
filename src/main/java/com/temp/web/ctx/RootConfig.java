package com.temp.web.ctx;
import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@MapperScan(basePackages = {"com.temp.web"})
@ComponentScan(basePackages = {"com.temp.web"})

@Import({ServletConfig.class, MybatisConfig.class})
public class RootConfig {
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		
		//hikariConfig.setDriverClassName("org.mariadb.jdbc.Driver");
		//hikariConfig.setJdbcUrl("jdbc:mariadb://172.168.0.75/shipdb");
		 
		hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/ship?serverTimezone=UTC");
		hikariConfig.setUsername("ship");
		hikariConfig.setPassword("ship");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
}
	@Bean 
		public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}

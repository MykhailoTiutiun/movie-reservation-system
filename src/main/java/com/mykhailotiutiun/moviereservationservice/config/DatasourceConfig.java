package com.mykhailotiutiun.moviereservationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TransactionTemplate transactionTemplate(DataSource dataSource) {
        return new TransactionTemplate(new DataSourceTransactionManager(dataSource));
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/movie-reservation-system");
        dataSource.setUsername("movie-reservation-system");
        dataSource.setPassword("KqJh5RiA9ur44yJjC7Qq");
        return dataSource;
    }

}

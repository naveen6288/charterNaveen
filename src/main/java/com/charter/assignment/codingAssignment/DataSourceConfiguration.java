package com.charter.assignment.codingAssignment;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate configureDataSource() {
        EmbeddedDatabaseConnection embeddedDatabaseConnection = EmbeddedDatabaseConnection.H2;

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(embeddedDatabaseConnection.getDriverClassName());
        dataSourceBuilder.url("jdbc:h2:mem:naveenmangu");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return new NamedParameterJdbcTemplate(dataSourceBuilder.build());
    }

}

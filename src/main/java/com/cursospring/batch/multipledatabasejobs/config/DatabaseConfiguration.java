package com.cursospring.batch.multipledatabasejobs.config;

import com.cursospring.batch.multipledatabasejobs.utils.Constants;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class DatabaseConfiguration {

    private final Environment env;

    @Bean(name = Constants.H2_CONFIG)
    @Primary
    @ConfigurationProperties(prefix = "spring.h2.datasource")
    public DataSource getH2BatchDataSource() {
        return DataSourceBuilder.create()
                .url(env.getProperty("spring.h2.datasource.url"))
                .driverClassName(env.getProperty("spring.h2.datasource.driverClassName"))
                .password(env.getProperty("spring.h2.datasource.password"))
                .username(env.getProperty("spring.h2.datasource.username"))
                .build();
    }

    @Bean(name = Constants.SQL_SERVER_CONFIG)
    @ConfigurationProperties(prefix = "spring.sqlserver.datasource")
    public DataSource getSqlServerBatchDataSource() {
        return DataSourceBuilder.create()
                .url(env.getProperty("spring.sqlserver.datasource.url"))
                .driverClassName(env.getProperty("spring.sqlserver.datasource.driverClassName"))
                .password(env.getProperty("spring.sqlserver.datasource.password"))
                .username(env.getProperty("spring.sqlserver.datasource.username"))
                .build();
    }

    @Bean(name = Constants.POSTGRES_CONFIG)
    @ConfigurationProperties(prefix = "spring.postgres.datasource")
    public DataSource getPostgresBatchDataSource() {
        return DataSourceBuilder.create()
                .url(env.getProperty("spring.postgres.datasource.url"))
                .driverClassName(env.getProperty("spring.postgres.datasource.driverClassName"))
                .password(env.getProperty("spring.postgres.datasource.password"))
                .username(env.getProperty("spring.postgres.datasource.username"))
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean managerFactoryBean(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        return builder.dataSource(getH2BatchDataSource())
                .packages("com.cursospring.batch.multipledatabasejobs")
                .persistenceUnit("default")
                .properties(properties)
                .build();
    }

}

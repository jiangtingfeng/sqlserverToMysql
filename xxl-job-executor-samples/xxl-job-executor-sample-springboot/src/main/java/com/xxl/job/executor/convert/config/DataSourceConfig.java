package com.xxl.job.executor.convert.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author jiangtingfeng
 * @description
 * @data 2019/9/24
 */
@Configuration
public class DataSourceConfig {


    @Bean("sqlserverDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.sqlserver")
    public DataSource sqlserverDataSource(){
        return new DruidDataSource();
    }

    @Bean(name = "sqlserverJdbcTemplate")
    public JdbcTemplate sqlserverJdbcTemplate(@Qualifier("sqlserverDataSource")DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("mysqlSharedDataSource")
    @Primary
    @Qualifier("mysqlSharedDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql-shared")
    public DataSource mysqlSharedDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "mysqlSharedJdbcTemplate")
    public JdbcTemplate mysqlSharedJdbcTemplate(@Qualifier("mysqlSharedDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}

package com.xxl.job.executor.convert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @author jiangtingfeng
 * @description
 * @data 2019/9/24
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySqlServer", transactionManagerRef = "transactionManagerSqlServer", basePackages = {
        SqlServerConfig.DEFAULT_DAO_PACKAGE }) // 设置Repository所在位置
public class SqlServerConfig {

    //实体所在路径
    protected final static String DEFAULT_ENTITY_PACKAGE = "com.xxl.job.executor.entity.sqlserver";
    //dao所在路径
    protected final static String DEFAULT_DAO_PACKAGE = "com.xxl.executor.repository.sqlserver";
    @Autowired
    @Qualifier("sqlserverDataSource")
    private DataSource sqlserverDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "entityManagerSqlServer")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySqlServer(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactoryPostgresql")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySqlServer(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(sqlserverDataSource).properties(getVendorProperties())
                .packages(DEFAULT_ENTITY_PACKAGE) // 设置实体类所在位置
                .persistenceUnit("postgresqlPersistenceUnit").build();
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean(name = "transactionManagerSqlServer")
    public PlatformTransactionManager transactionManagerPostgresql(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySqlServer(builder).getObject());
    }
}

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
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMysqlShared", transactionManagerRef = "transactionManagerMysqlShared", basePackages = {
        MysqlSharedConfig.DEFAULT_DAO_PACKAGE }) // 设置Repository所在位置
public class MysqlSharedConfig {

    //实体所在路径
    protected final static String DEFAULT_ENTITY_PACKAGE = "com.xxl.job.executor.entity.mysql";

    protected final static String DEFAULT_DAO_PACKAGE = "com.xxx.executor.repository.mysql";

    @Autowired
    @Qualifier("mysqlSharedDataSource")
    private DataSource mysqlSharedDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Primary
    @Bean(name = "entityManagerMysqlShared")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMysqlShared(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "entityManagerFactoryMysqlShared")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMysqlShared(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryMysql = builder.dataSource(mysqlSharedDataSource)
                .properties(getVendorProperties()).packages(DEFAULT_ENTITY_PACKAGE) // 设置实体类所在位置
                .persistenceUnit("mysqlSharedPersistenceUnit").build();
        return entityManagerFactoryMysql;
    }

    private Map<String, Object> getVendorProperties() {
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Primary
    @Bean(name = "transactionManagerMysqlShared")
    public PlatformTransactionManager transactionManagerMysqlShared(EntityManagerFactoryBuilder builder) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactoryMysqlShared(builder).getObject());
        return txManager;
    }

}

package com.store.fake.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class DataConfiguration {

    private String url ;
    private String username ="DBAppStore";
    private String pass = "St0r3App$160624";

    @Bean(name = "transactionalDataSource")
    public DataSource userDatasource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlserver://devstoretest.database.windows.net:1433;database=Strore;user=DBAdminStore@devstoretest;password=D3v@dmin*2024;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        dataSource.setUsername(username);
        dataSource.setPassword(pass);
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");


        return dataSource;
    }

    @Bean(name = "transactionalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDatasource());
        em.setPackagesToScan("com.store.fake.domain");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;

    }

    @Bean(name = "transactionalTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }

    @Bean(name = "jdbcTransactional")
    public JdbcTemplate jdbcTemplate1(@Qualifier("transactionalDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}

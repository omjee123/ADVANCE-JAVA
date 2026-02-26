package com.example.CollegeMannagementSystem.Config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "getOracleFactoryBean",transactionManagerRef = "getOracleTransaction",
basePackages = "com.example.CollegeMannagementSystem.RepoOracle")
public class OracleConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.oracle.datasource")
    public DataSourceProperties getOracleDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
     public DataSource getOracleDataSource(@Qualifier("getOracleDataSourceProperties")DataSourceProperties dataSourceProperties){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        assert dataSourceProperties.getDriverClassName() != null;
        driverManagerDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        driverManagerDataSource.setUrl(dataSourceProperties.getUrl());
        driverManagerDataSource.setUsername(dataSourceProperties.getUsername());
        driverManagerDataSource.setPassword(dataSourceProperties.getPassword());

        return driverManagerDataSource;
    }

    @Bean

    public JpaVendorAdapter getOracleJpaVendorAdaptor(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean getOracleFactoryBean
            (@Qualifier("getOracleJpaVendorAdaptor")JpaVendorAdapter jpaVendorAdapter,
             @Qualifier("getOracleDataSource")DataSource dataSource){
        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setPackagesToScan("com.example.CollegeMannagementSystem.EntityOracle");
        bean.setDataSource(dataSource);
        bean.setJpaVendorAdapter(jpaVendorAdapter);

        return bean;
    }

    @Bean
    public PlatformTransactionManager getOracleTransaction(@Qualifier("getOracleFactoryBean")LocalContainerEntityManagerFactoryBean bean){
        assert bean.getObject() !=null;
        return new JpaTransactionManager(bean.getObject());
    }

}

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
@EnableJpaRepositories(entityManagerFactoryRef ="getMysqlFactoryBean",
        transactionManagerRef = "getMysqlTransaction",
basePackages = "com.example.CollegeMannagementSystem.RepoMysql")
public class MySqlConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSourceProperties getMysqldatasourceproperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource getMysqldatasource(@Qualifier("getMysqldatasourceproperties") DataSourceProperties dataSourceProperties)
    {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        driverManagerDataSource.setUrl(dataSourceProperties.getUrl());
        driverManagerDataSource.setUsername(dataSourceProperties.getUsername());
        driverManagerDataSource.setPassword(dataSourceProperties.getPassword());

        return driverManagerDataSource;
    }

    @Bean
    public JpaVendorAdapter getMysqlVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter=new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getMysqlFactoryBean
            (@Qualifier("getMysqlVendorAdapter")
             JpaVendorAdapter jpaVendorAdapter ,@Qualifier("getMysqldatasource")
            DataSource dataSource){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.CollegeMannagementSystem.EntityMysql");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        return localContainerEntityManagerFactoryBean;

    }
    @Bean
    public PlatformTransactionManager getMysqlTransaction(@Qualifier("getMysqlFactoryBean")LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean){
        assert localContainerEntityManagerFactoryBean.getObject() != null;
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}

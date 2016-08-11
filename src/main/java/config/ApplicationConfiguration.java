package config;

import dao.BookCheckOutDAO;
import dao.BookCheckOutDAOImpl;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import service.BookCheckOutService;
import service.BookCheckOutServiceImpl;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Damian on 2016-08-10.
 */
@Configuration
public class ApplicationConfiguration {
    @Bean
    public BookCheckOutDAO getBookCheckoutDAO() {
        return new BookCheckOutDAOImpl();
    }

    @Bean
    public BookCheckOutService getBookCheckoutService() {
        return new BookCheckOutServiceImpl();
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(oracleDataSource());
        sessionFactory.setPackagesToScan("model");
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    private static Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }


    private DataSource oracleDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//127.0.0.1:1521/xe");
        dataSource.setUsername("LIBRARY_CATALOG");
        dataSource.setPassword("pass");
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}

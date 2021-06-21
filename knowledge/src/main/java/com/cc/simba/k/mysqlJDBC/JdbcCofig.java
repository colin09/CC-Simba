package com.cc.simba.k.mysqlJDBC;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;


@Configuration
@@ComponentScan
@PropertySource("jdbc.properties")
public class JdbcCofig {

    @Value($"jdbc.driver")
    private String driver;
    @Value($"jdbc.url")
    private String url;
    @Value($"jdbc.userName")
    private String userName;
    @Value($"jdbc.password")
    private String password;


    /**
     * 创建数据源并存入Ioc容器
     *
     * @return
     */
    @Bean
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * 创建 JdbcTemplate 对象
     *
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 操作 clob 和 blob
     * @return
     */
    @Bean
    public LobHandler createLobHandler() {
        return new DefaultLobHandler();
    }

}

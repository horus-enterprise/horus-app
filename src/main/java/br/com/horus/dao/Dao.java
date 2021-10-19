package br.com.horus.dao;

import org.apache.commons.dbcp2.BasicDataSource;

public class Dao {
    private BasicDataSource dataSource;
    
    public Dao() {
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.dataSource.setUrl("jdbc:mysql://localhost/Horus?serverTimezone=UTC");
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("********");
    }
    
    public BasicDataSource getDataSource() {
        return this.dataSource;
    }
}

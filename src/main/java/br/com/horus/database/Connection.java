package br.com.horus.database;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {
    private BasicDataSource dataSource;
    
    public Connection() {
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.dataSource.setUrl("jdbc:mysql://localhost/Horus?serverTimezone=UTC");
        this.dataSource.setUsername("root");
        this.dataSource.setPassword("@mysql2001");
    }
    
    public BasicDataSource getDataSource() {
        return this.dataSource;
    }
}

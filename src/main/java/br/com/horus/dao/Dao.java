package br.com.horus.dao;
import org.apache.commons.dbcp2.BasicDataSource;

public class Dao {

    private BasicDataSource dataSource;

    public Dao() {
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            this.dataSource.setUrl("jdbc:mysql://localhost:3306/horus?serverTimezone=UTC");
            this.dataSource.setUsername("horus");
            this.dataSource.setPassword("@horus2021");
    }

    public BasicDataSource getDataSource() {
        return this.dataSource;
    }
}

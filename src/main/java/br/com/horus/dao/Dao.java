package br.com.horus.dao;

import br.com.horus.utils.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

public class Dao {

    private BasicDataSource dataSource;

    public Dao() {
        try {
            this.dataSource = new BasicDataSource();
            this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            this.dataSource.setUrl("jdbc:mysql://localhost:3306/horus?serverTimezone=UTC");
            this.dataSource.setUsername("horus");
            this.dataSource.setPassword("@horus2021");
            Logger.loger("> Servidor conectado ao banco horus");
        } catch (Exception e) {
            Logger.loger(e);
        }
    }

    public BasicDataSource getDataSource() {
        return this.dataSource;
    }
}

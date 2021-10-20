package br.com.horus.dao;

import br.com.horus.model.Funcionario;
import br.com.horus.model.Maquina;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao extends Dao {
    JdbcTemplate con;
    
    public MaquinaDao() {
        this.con = new JdbcTemplate(getDataSource());
    }
    
    public Maquina listar (String hostname) {
        String sql = "SELECT * FROM Maquina WHERE hostname = '" + hostname + "'";
        
        List<Maquina> maquina = con.query(sql,
                new BeanPropertyRowMapper(Maquina.class));
        
        return maquina.get(0);
    }
}

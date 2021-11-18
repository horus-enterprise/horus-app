package br.com.horus.dao;

import br.com.horus.model.Maquina;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao extends Dao {

    JdbcTemplate con;

    public MaquinaDao() {
        this.con = new JdbcTemplate(getDataSource());
    }

    public Maquina listar(String hostname, Integer fkEmpresa) {
        String sql = "";
        try {
            sql = "SELECT * FROM Maquina WHERE hostname = '" + hostname
                    + "' AND fkEmpresa = " + fkEmpresa;
            Logger.loger("> Select das máquinas ok");
        } catch (Exception e) {
            Logger.loger(e);
        }
        List<Maquina> maquina = con.query(sql,
                new BeanPropertyRowMapper(Maquina.class));

        return maquina.get(0);

    }

    public Boolean validaMaquina() {
        Maquina maquina = listar(Hostname.getHostname(), Session.getFkEmpresa());

        if (maquina != null) {
            try {
                System.out.println("validada");
                Logger.loger("> Monitorando máquina.");
            } catch (Exception e) {
                Logger.loger(e);
            }
            return true;
        } else {
            try {
                System.out.println("Vai ser cadastrada");
                Logger.loger("> Cadastre a máquina antes de monitorar");
            } catch (Exception e) {
                Logger.loger(e);
            }
            cadastraMaquina();
            return true;
        }
    }

    public void cadastraMaquina() {
        try {
            Looca looca = new Looca();
            String insertStatement = "insert into Maquina (idMaquina,hostname,fkEmpresa,nomeCpu,modeloDisco) values(null,?,?,?,?);";
            con.update(insertStatement, Hostname.getHostname(), Session.getFkEmpresa(), looca.getProcessador().getNome(),
                    looca.getGrupoDeDiscos().getDiscos().get(0).getModelo());
            System.out.println("Nova maquina cadastrada!");
            Logger.loger("> Máquina cadastrada.");
        } catch (Exception e) {
            Logger.loger(e);
        }
    }

}

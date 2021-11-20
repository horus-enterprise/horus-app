package br.com.horus.dao;

import br.com.horus.model.Maquina;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
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
            Logger.escreverLogger("> Select das máquinas ok");
        } catch (IOException e) {
            Logger.loggerException(e);
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
                Logger.escreverLogger("> Monitorando máquina.");
            } catch (IOException e) {
                Logger.loggerException(e);
            }
            return true;
        } else {
            try {
                System.out.println("Vai ser cadastrada");
                Logger.escreverLogger("> Cadastre a máquina antes de monitorar");
            } catch (IOException e) {
                Logger.loggerException(e);
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
            Logger.escreverLogger("> Máquina cadastrada.");
        } catch (IOException e) {
            Logger.loggerException(e);
        }
    }

}

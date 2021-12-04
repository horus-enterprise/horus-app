/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.dao;

import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.model.Slack;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class SlackDao extends Dao{
     JdbcTemplate con;

     public SlackDao(){
         this.con = new JdbcTemplate(getDataSource());
     }
     
     public Slack listar(Integer fkEmpresa){
         String sql = "";
        try {
             sql = "SELECT * FROM Slack WHERE fkEmpresa = " + fkEmpresa;
            
             Logger.escreverLogger("Select do Slack ok. - "+ Logger.geradorDatas());
        } catch (IOException e) {
            Logger.loggerException(e);
        }
         List<Slack> slack = con.query(sql,
                 new BeanPropertyRowMapper(Slack.class));
        try {
            Logger.escreverLogger("URL Slack: "+ slack.get(0).getUrlSlack() + " ok. -"+ Logger.geradorDatas());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(SlackDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slack.get(0);
     }
     
     public String alertaOcorrencia(Integer idFuncionario){
         MonitoramentoHardware m = new MonitoramentoHardware();
         String sql = "";
         try {
             sql = " select * from monitoramentohardware where idOcorrencia =" + 
                     "(select max(idOcorrencia) from monitoramentohardware where fkFuncionario =" + idFuncionario +")";
            
             Logger.escreverLogger("Select do Slack ok. - "+ Logger.geradorDatas());
        } catch (IOException e) {
            Logger.loggerException(e);
        }
        
        List<MonitoramentoHardware> ocorrencia = con.query(sql,
                 new BeanPropertyRowMapper(MonitoramentoHardware.class));
        m = ocorrencia.get(0);
        try {
            Logger.escreverLogger(""+ Logger.geradorDatas());
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(SlackDao.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        if(m.getCpuUso() >= 75.0){
           return String.format("!Alerta! A máquina %s que está sendo operada por %s, esta excedendo a utilização recomendável da cpu Uso: %.1f",
                   Hostname.getHostname(),Session.getNome(), m.getCpuUso());
        }
        
        if(m.getDisco() > 75.0){
           return String.format("!Alerta! A máquina %s que está sendo operada por %s, esta excedendo a utilização recomendável do Disco Uso: %.1f",
                   Hostname.getHostname(),Session.getNome(),m.getDisco());
        }
        
        if(m.getRam() > 75.0){
           return String.format("!Alerta! A máquina %s que está sendo operada por %s, esta excedendo a utilização recomendável do Disco Uso: %.1f",
                   Hostname.getHostname(),Session.getNome(),m.getRam());
        }
        return null;
     }
}

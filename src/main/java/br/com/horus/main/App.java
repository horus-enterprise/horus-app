package br.com.horus.main;

import br.com.horus.dao.MaquinaDao;
import br.com.horus.dao.MonitoramentoHardwareDao;
import br.com.horus.dao.SlackDao;
import br.com.horus.gui.Login;
import br.com.horus.model.Maquina;
import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.model.Slack;
import br.com.horus.utils.ConexaoSlack;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Logger;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;

public class App {


    public static void main(String[] args) throws IOException, InterruptedException{
        Logger.criarLogger();
        System.out.println("Iniciando...");
        Login login = new Login();
        login.setVisible(true);
        
        SlackDao s = new SlackDao(); 
        Slack slk = s.listar(1);
        ConexaoSlack.setURL(slk.getUrlSlack());       
    }

    public static void start() throws IOException, InterruptedException {
        MaquinaDao maquinaDAO = new MaquinaDao();

        Maquina maquina = maquinaDAO.listar(Hostname.getHostname(),Session.getFkEmpresa());

        Looca looca = new Looca();

        MonitoramentoHardware ocorrencia = new MonitoramentoHardware();

        ocorrencia.setFkMaquina(maquina.getIdMaquina());

        Double cpuUso = looca.getProcessador().getUso();

        ocorrencia.setCpuUso(cpuUso);

        ocorrencia.setCpuTemperatura(looca.getTemperatura().getTemperatura());

        Double volumeTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal()/ Math.pow(1024, 3);
        Double volumeDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel()/ Math.pow(1024, 3);
        Double volumeUso = volumeTotal - volumeDisponivel;

        Double percentVolumeUso = volumeUso * 100.0 / volumeTotal;
        ocorrencia.setDisco(percentVolumeUso); 
        
        
        Double totalRam = looca.getMemoria().getTotal()/ Math.pow(1024, 3);
        Double ramEmUso = looca.getMemoria().getEmUso()/ Math.pow(1024, 3);
        
        Double usoRam = ((ramEmUso * 100) / totalRam);       
        ocorrencia.setRam(usoRam);

        
        Long uptime = looca.getSistema().getTempoDeAtividade();
        ocorrencia.setUptime(uptime);

        System.out.println(ocorrencia);

        
        MonitoramentoHardwareDao monitoramentoHardwareDAO = new MonitoramentoHardwareDao();
        monitoramentoHardwareDAO.enviar(ocorrencia);       
    }
}

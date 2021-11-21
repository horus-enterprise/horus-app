package br.com.horus.main;

import br.com.horus.dao.MaquinaDao;
import br.com.horus.dao.MonitoramentoHardwareDao;
import br.com.horus.gui.Login;
import br.com.horus.model.Maquina;
import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;



public class App {

    public static void main(String[] args) {
        System.out.println("Iniciando...");
        Login login = new Login();
        login.setVisible(true);
    }

    public static void start() {
        MaquinaDao maquinaDAO = new MaquinaDao();

        Maquina maquina = maquinaDAO.listar(Hostname.getHostname(),Session.getFkEmpresa());

        Looca looca = new Looca();

        MonitoramentoHardware ocorrencia = new MonitoramentoHardware();

        ocorrencia.setFkMaquina(maquina.getIdMaquina());

        Double cpuUso = looca.getProcessador().getUso();

        ocorrencia.setCpuUso(cpuUso);

        ocorrencia.setCpuTemperatura(looca.getTemperatura().getTemperatura());

        Long volumeTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal();
        Long volumeDisponivel = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel();
        Long volumeUso = volumeTotal - volumeDisponivel;

        Double percentVolumeUso = volumeUso * 100.0 / volumeTotal;

        ocorrencia.setDisco(percentVolumeUso);
        ocorrencia.setRam(looca.getMemoria().getEmUso() / Math.pow(1024, 3));

        Long uptime = looca.getSistema().getTempoDeAtividade();
        ocorrencia.setUptime(uptime);

        System.out.println(ocorrencia);

        MonitoramentoHardwareDao monitoramentoHardwareDAO = new MonitoramentoHardwareDao();
        monitoramentoHardwareDAO.enviar(ocorrencia);        
    }
}

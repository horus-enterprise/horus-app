package br.com.horus.main;

import br.com.horus.dao.MaquinaDao;
import br.com.horus.dao.MonitoramentoHardwareDao;
import br.com.horus.gui.Login;
import br.com.horus.model.Maquina;
import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.utils.Hostname;
import com.github.britooo.looca.api.core.Looca;

import java.util.Timer;
import java.util.TimerTask;

public class App {

    public static void main(String[] args) {
        final long segundos = (1000 * 10);

        System.out.println("Iniciando...");
        Login login = new Login();
        login.setVisible(true);
        
        
        Timer tempo = new Timer();

        TimerTask monitoramento = new TimerTask() {

            @Override
            public void run() {
                System.out.println("Dados coletados");
            }
        };

        tempo.scheduleAtFixedRate(monitoramento, 1, segundos);
    }

    public static void start() {
        MaquinaDao maquinaDAO = new MaquinaDao();

        Maquina maquina = maquinaDAO.listar(Hostname.getHostname());

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

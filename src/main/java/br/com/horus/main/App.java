package br.com.horus.main;

import br.com.horus.dao.MaquinaDao;
import br.com.horus.dao.MonitoramentoHardwareDao;
import br.com.horus.model.Maquina;
import br.com.horus.model.MonitoramentoHardware;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Time;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Volume;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App {

    public static void main(String[] args) {
        Looca looca = new Looca();

        String uptime = Time.secondsToHHmmss(looca.getSistema().getTempoDeAtividade().intValue());
        System.out.println(uptime);
    }

    public void start() {
        MaquinaDao maquinaDAO = new MaquinaDao();

        Maquina maquina = maquinaDAO.listar(Hostname.getHostname());

        Looca looca = new Looca();

        MonitoramentoHardware ocorrencia = new MonitoramentoHardware();
        ocorrencia.setFkMaquina(maquina.getIdMaquina());
        ocorrencia.setCpuUso(looca.getProcessador().getUso());
        ocorrencia.setCpuTemperatura(looca.getTemperatura().getTemperatura());

        Volume volumePrincipal = looca.getGrupoDeDiscos().getVolumes().get(0);
        Double espacoDisponivel = volumePrincipal.getDisponivel() / (1 * Math.pow(10, 9));

        ocorrencia.setDisco(espacoDisponivel);
        ocorrencia.setRam(looca.getMemoria().getEmUso().doubleValue());
        ocorrencia.setUptime(looca.getSistema().getTempoDeAtividade());

        MonitoramentoHardwareDao monitoramentoHardwareDAO = new MonitoramentoHardwareDao();
        monitoramentoHardwareDAO.enviar(ocorrencia);
    }
}

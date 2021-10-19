package br.com.horus.model;

import java.util.Date;

public class MonitoramentoHardware {
    private Integer idOcorrencia;
    private Integer fkMaquina;
    private Date dataHora;
    private Double cpuUso;
    private Float cpuTemperatura;
    private Double disco;
    private Double ram;
    private String uptime;

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Double getCpuUso() {
        return cpuUso;
    }

    public void setCpuUso(Double cpu) {
        this.cpuUso = cpuUso;
    }

    public Double getCpuTemperatura() {
        return cpuTemperatura;
    }

    public void setCpuTemperatura(Double cpuTemperatura) {
        this.cpuTemperatura = cpuTemperatura;
    }

    public Double getDisco() {
        return disco;
    }

    public void setDisco(Double disco) {
        this.disco = disco;
    }

    public Double getRam() {
        return ram;
    }

    public void setRam(Double ram) {
        this.ram = ram;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
}

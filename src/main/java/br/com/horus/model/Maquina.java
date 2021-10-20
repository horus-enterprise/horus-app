package br.com.horus.model;

public class Maquina {
    private Integer idMaquina;
    private Integer fkEmpresa;
    private String hostname;

    public Integer getIdMaquina() {
        return idMaquina;
    }

    @Override
    public String toString() {
        return "Maquina{" + "idMaquina=" + idMaquina + ", fkEmpresa=" + fkEmpresa + ", hostname=" + hostname + '}';
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}

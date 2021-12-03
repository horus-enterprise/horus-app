/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import br.com.horus.dao.SlackDao;
import br.com.horus.model.MonitoramentoHardware;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author Anderson
 */
public class ConexaoSlack {

    private static HttpClient cliente = HttpClient.newHttpClient();
    private static String URL = "";

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(String.format("Status: %s", response.statusCode()));
        System.out.println(String.format("Response: %s", response.body()));
    }

    public static void enviarAlerta(MonitoramentoHardware m) throws IOException, InterruptedException {
        SlackDao s = new SlackDao();
        s.listar(Integer.BYTES);

        String hostname = Hostname.getHostname();
        String nomeFuncionario = Session.getNome();
        String mensagem;
        String componente;
        Double porcentagemUso;

        if (m.getCpuUso() >= 75.0) {
            componente = "da CPU";
            porcentagemUso = m.getCpuUso();

            if (m.getCpuUso() >= 90.0) {
                mensagem = String.format("!Alerta Critico! A máquina %s que está sendo operada por %s, esta em estado critico %s  %Uso : %.1f",
                        hostname, nomeFuncionario, componente, porcentagemUso);
            } else {
                mensagem = String.format("!Alerta Emergencial! A máquina %s que está sendo operada por %s, esta excedendo a utilização recomendável %s  %Uso : %.1f",
                        hostname, nomeFuncionario, componente, porcentagemUso);

                JSONObject json = new JSONObject();
                json.put("text", mensagem);

                sendMessage(json);
            }
        }

        if (m.getDisco() >= 75.0) {
            componente = "do Disco";
            porcentagemUso = m.getDisco();

            if (m.getDisco() >= 90.0) {
                mensagem = String.format("!Alerta Critico! A máquina %s que está sendo operada por %s, esta em estado critico %s  %Uso : %.1f",
                        hostname, nomeFuncionario, componente, porcentagemUso);
            } else {
                mensagem = String.format("!Alerta Emergencial! A máquina %s que está sendo operada por %s, esta excedendo a utilização recomendável %s  %Uso : %.1f",
                        hostname, nomeFuncionario, componente, porcentagemUso);

                JSONObject json = new JSONObject();
                json.put("text", mensagem);

                sendMessage(json);
            }
        }

        if (m.getRam() >= 75.0) {
            componente = "da memória Ram";
            porcentagemUso = m.getRam();

            if (m.getDisco() >= 90.0) {
                mensagem = String.format("!Alerta Critico! A máquina %s que está sendo operada por %s, esta em estado critico %s  %Uso : %.1f",
                        hostname, nomeFuncionario, componente, porcentagemUso);
            } else {
                mensagem = String.format("!Alerta Emergencial! A máquina %s que está sendo operada por %s, esta excedendo a utilização recomendável %s  %Uso : %.1f",
                        hostname, nomeFuncionario, componente, porcentagemUso);

                JSONObject json = new JSONObject();
                json.put("text", mensagem);

                sendMessage(json);
            }
        }
    }

    public static void mensagemInicial() throws IOException, InterruptedException {
        String mensagemInicial = String.format("Iniciando monitoramento da maquina << %s >> que esta sendo operada pelo proficional %s",
                Hostname.getHostname(), Session.getNome());
        JSONObject json = new JSONObject();

        json.put("text", mensagemInicial);
        sendMessage(json);
    }

    
    
    public static void setURL(String URL) {
        ConexaoSlack.URL = URL;
    }
}

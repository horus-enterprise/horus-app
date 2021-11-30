/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import br.com.horus.model.Funcionario;
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
public class Slack {

    private static HttpClient cliente = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

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
}

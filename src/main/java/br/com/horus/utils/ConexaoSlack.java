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

    public static void enviarAlerta() throws IOException, InterruptedException{
        SlackDao slc = new SlackDao();
        String mensagem = slc.alertaOcorrencia(Session.getIdFuncionario());
        JSONObject json = new JSONObject();

        json.put("text", mensagem);
        sendMessage(json);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

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

    private static final HttpClient cliente = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();
        
        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
    }

}

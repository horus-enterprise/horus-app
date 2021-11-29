/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.main;

import br.com.horus.utils.Slack;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author Anderson
 */
public class Teste {
    public static void main(String[] args) throws IOException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("text","Olá usuário");
        
        Slack.sendMessage(json);
    }
}

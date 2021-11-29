/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import com.github.britooo.looca.api.core.Looca;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author HP 420
 */
public class Logger {

    static FileOutputStream arquivo;
    static FileOutputStream sessaoJson;
    static String timeStamp;
    static Integer caminho;

    public static void criaCaminho() throws IOException {
        File horusSessao = new File("D:\\horus-sessao");
        File horus = new File("D:\\horus-loggers");
        if (!horus.exists()) {
            horus.mkdirs();
        }
        if (!horusSessao.exists()) {
            horusSessao.mkdirs();
        }

        sessaoJson = new FileOutputStream("D:\\horus-loggers\\" + Session.getNome() + ".txt");
        arquivo = new FileOutputStream("D:\\horus-sessao\\" + timeStamp + ".txt");
        caminho = 1;
    }

    public static void criarLogger() throws IOException {

        Looca looca = new Looca();

        if (looca.getSistema().getSistemaOperacional().equals("Windows")) {
            criaCaminho();
            caminho = 1;
        } else if (looca.getSistema().getSistemaOperacional().equals("Windows") && arquivo == null) {
            criaCaminho();
            caminho = 2;
        } else if (looca.getSistema().getSistemaOperacional().equals("Linux")
                || looca.getSistema().getSistemaOperacional().equals("Ubuntu")) {
            criaCaminho();
            caminho = 3;
        } else {
            System.out.println("Não temos suporte para esse sistema operacional.");
        }
    }
    
    public static void escreverArquivo(){
       switch(caminho){
           case 1 :
               break;
           
           default:
               break;
       }
                 
    }

    public static void escreverLogger(String texto) throws IOException {
        if (caminho == 1) {
            try (
                     FileWriter caminhoTxt = new FileWriter("D:\\" + "horus-loggers\\" + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
                escreverTexto.println(texto);

            } catch (IOException e) {
                Logger.loggerException(e);
            }
        } else if (caminho == 2) {
            try (
                     FileWriter caminhoTxt = new FileWriter("C:\\" + "horus-loggers\\" + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
                escreverTexto.println(texto);

            } catch (IOException e) {
                Logger.loggerException(e);
            }
        } else if (caminho == 3) {
            try (
                     FileWriter caminhoTxt = new FileWriter("/home/urubu100/horus-loggers/" + timeStamp + ".txt", true);  BufferedWriter loopEscrever = new BufferedWriter(caminhoTxt);  PrintWriter escreverTexto = new PrintWriter(loopEscrever)) {
                escreverTexto.println(texto);
            } catch (IOException e) {
                Logger.loggerException(e);
            }
        } else {
            System.out.println("Sem sucesso, ainda não temos suporte para esse sistema operacional");
        }
    }

    public static void fecharLogger() throws IOException {
        arquivo.close();
    }

    public static void loggerException(IOException e) {
        throw new UnsupportedOperationException("Not supported yet." + e);
//To change body of generated methods, choose Tools | Templates.
    }

    public static String geradorDatas() {
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        return dataFormatada;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import java.io.BufferedWriter;
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

    static FileWriter arquivo;
    static String timeStamp;

    public static void criarLogger() throws IOException {
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        arquivo = new FileWriter("D:\\horus-loggers\\" + timeStamp + ".txt");
        if(arquivo == null){
            arquivo = new FileWriter("C:\\horus-loggers\\" + timeStamp + ".txt");
        }
    }

    public static void escreverLogger(String texto) throws IOException {
        try ( FileWriter fw = new FileWriter("D:\\" + "\\" + "horus-loggers\\" + "\\" + timeStamp + ".txt", true);  BufferedWriter bw = new BufferedWriter(fw);  PrintWriter out = new PrintWriter(bw)) {
            out.println(texto);
        } catch (IOException e) {
            Logger.loggerException(e);
        }

    }

    public static void fecharLogger() throws IOException {
        arquivo.close();
    }

    public static void loggerException(IOException e) {
        throw new UnsupportedOperationException("Not supported yet." + e);
//To change body of generated methods, choose Tools | Templates.
    }
}

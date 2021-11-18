/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author HP 420
 */
public class Logger {

    static ArrayList dadosTexto = new ArrayList();

    public static void loger(String texto) {

        dadosTexto.add(texto);
    }

    static void caminhoLogger(FileWriter arquivo) throws IOException {
        Integer cont = 0;

        ArrayList arquivos = new ArrayList();

        PrintWriter gravarArq = new PrintWriter(arquivo);
        arquivos.add(arquivo);

        for (Integer i = 0; i < arquivos.size(); i++) {
            Integer tamanhoArray = arquivos.size() - 1;

            if (arquivo.equals(arquivos.get(tamanhoArray))) {
                gravarArq.printf("Bem-vindo à Horus\n");
                do {

                    gravarArq.printf("%s\n", dadosTexto.get(cont));
                    System.out.println(dadosTexto.get(cont));
                    cont++;
                } while (cont < dadosTexto.size());
            }
        }
    }

    public static void loger(Exception e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

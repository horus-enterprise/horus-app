package br.com.horus.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {
    private static String nome;
    private static String email;
    private static Integer fkEmpresa;
    static FileWriter arquivo;

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Session.nome = nome;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public static void setFkEmpresa(Integer fkEmpresa) {
        Session.fkEmpresa = fkEmpresa;
    }
    
    
    public static void criarSessao(String nome, String email, Integer fkEmpresa) {
        Session.nome = nome;
        Session.email = email;
        Session.fkEmpresa = fkEmpresa;
    }
    public static void criarLogger()throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String data = sdf.format(new Date());
        arquivo = new FileWriter("D:\\horus-loggers\\" + data + ".txt");
        Logger.caminhoLogger(arquivo);
    }
    public static void deletarSessao(){
        Session.nome = null;
        Session.email = null;
        Session.fkEmpresa = null;
      
    }
    public static void fecharLogger()throws IOException{
          arquivo.close();
    }
}

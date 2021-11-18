package br.com.horus.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        try{
        Session.nome = nome;
        Session.email = email;
        Session.fkEmpresa = fkEmpresa;
        Logger.loger("> Autenticação de usúario ok.");
        }catch(Exception e){
            Logger.loger(e);
        }
    }
    public static void criarLogger()throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        arquivo = new FileWriter("D:\\horus-loggers\\" + timeStamp + ".txt");
        Logger.caminhoLogger(arquivo);
    }
    public static void deletarSessao(){
        try{
        Session.nome = null;
        Session.email = null;
        Session.fkEmpresa = null;
        Logger.loger("> Deslogar ok.");
        }catch(Exception e){
            Logger.loger(e);
        }
      
    }
    public static void fecharLogger()throws IOException{
          arquivo.close();
    }
}

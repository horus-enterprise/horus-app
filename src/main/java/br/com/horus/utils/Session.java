package br.com.horus.utils;

public class Session {
    private static String nome;
    private static String email;
    private static Integer fkEmpresa;

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
    
    public static void deletarSessao() {
        Session.nome = null;
        Session.email = null;
        Session.fkEmpresa = null;
    }
}

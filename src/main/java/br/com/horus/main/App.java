package br.com.horus.main;

import br.com.horus.dao.*;
import br.com.horus.model.*;

public class App {
    public static void main(String[] args) {
        FuncionarioDao funcionarioDAO = new FuncionarioDao();
        
        Funcionario funcionario = funcionarioDAO.listar("rcamara@horus.com", "123rcamara");
        
        if (funcionario == null) {
            System.out.println("Login inválido");
        } else {
            System.out.println("Login válido");
        } 
    }
}

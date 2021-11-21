/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.main;

import br.com.horus.dao.MaquinaDao;
import br.com.horus.utils.Hostname;
import br.com.horus.utils.Session;
import com.github.britooo.looca.api.core.Looca;
import java.text.DecimalFormat;

/**
 *
 * @author Anderson
 */
public class Teste {
    
    
    public static void main(String[] args) {
        MaquinaDao maquina = new MaquinaDao();
        
        Looca looca = new Looca();
        System.out.println(Hostname.getHostname() + "\n" + Session.getFkEmpresa() + "\n" + looca.getProcessador().getNome()
                + "\n" + looca.getGrupoDeDiscos().getDiscos().get(0).getModelo() + "\n" + looca.getMemoria());
        
        System.out.println( );
        
        Double num = (looca.getMemoria().getTotal()/1.07 / 1000000000);
        //String a = new Double(num).toString();
        System.out.printf("%.2f", num);
        
       
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.horus.utils;

/**
 *
 * @author gabs
 */
public class Time {
    public static String secondsToHHmmss(Integer seconds) {
        Long horas = seconds / 36000;
        Long minutos = (seconds - (horas * 3600)) / 60;
        Long segundos = seconds - (horas * 3600) - (minutos * 60);
        return String.format("%l:%l:%l", horas, minutos, segundos);
    }
}

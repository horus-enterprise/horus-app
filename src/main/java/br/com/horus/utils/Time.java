package br.com.horus.utils;

public class Time {
    public static String secondsToHHmmss(Long seconds) {
        Long horas = seconds / 3600;
        Long minutos = (seconds - (horas * 3600)) / 60;
        Long segundos = seconds - (horas * 3600) - (minutos * 60);
        return String.format("%d:%d:%d", horas, minutos, segundos);
    }
}

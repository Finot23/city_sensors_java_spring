package com.example.alertservice.model;

public class EvaluacionRequest {
    private String barrio;
    private double temperatura;
    private double calidad_aire;

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getCalidad_aire() {
        return calidad_aire;
    }

    public void setCalidad_aire(double calidad_aire) {
        this.calidad_aire = calidad_aire;
    }
}

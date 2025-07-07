/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.alertservice.controller;

import com.example.alertservice.model.EvaluacionRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AlertController {

    private final List<String> alertas = new ArrayList<>();

    @PostMapping("/evaluar")
    public String evaluar(@RequestBody EvaluacionRequest data) {
        String barrio = data.getBarrio();
        double temp = data.getTemperatura();
        double calidad = data.getCalidad_aire();

        if (temp > 35 || calidad > 60) {
            String alerta = "ALERTA en " + barrio + ": Temp=" + temp + " °C, Calidad aire=" + calidad;
            alertas.add(alerta);
            System.out.println(alerta);
        }

        return "{\"status\":\"evaluado\"}";
    }

    @GetMapping("/alertas")
    public List<String> verAlertas() {
        return alertas;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.alertservice;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {
    private final List<String> alertas = new ArrayList<>();

    public void agregarAlerta(String alerta) {
        alertas.add(alerta);
        System.out.println("[+] Guardada alerta: " + alerta);
    }

    public List<String> obtenerAlertas() {
        return alertas;
    }
}

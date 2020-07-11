/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.Random;

/**
 *
 * @author rebeca
 */
public class Mecanico {
    private String idMecanico;

    public Mecanico(String idMecanico) {
        this.idMecanico = idMecanico;
    }  
    
    public double trocarPneu(Carro carro){
        carro.setDesgaste(0);
        return Math.random();
    }
    
    public double abastece(Carro carro){
        Random random = new Random();
        carro.setComb(10+random.nextInt(5));
        return Math.random();
    }
    
    public void imprimir(){
        System.out.println("Mecânico: "+ idMecanico);
    }

    public String getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(String idMecanico) {
        this.idMecanico = idMecanico;
    }
    
    
}

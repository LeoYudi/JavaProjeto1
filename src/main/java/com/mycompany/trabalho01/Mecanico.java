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
    
    /**
     * Método que zera o desgaste de um carro
     * @param carro Carro que terá o pneu trocado
     * @return double Valor entre 0 e 1 que representa o tempo levado para troca do pneu
     */
    public double trocarPneu(Carro carro){
        carro.setDesgaste(0);
        return Math.random();
    }
    
    /**
     * Método que deixa o tanque com um valor entre 10 e 15 litros de combustível
     * @param carro Carro que será abastecido
     * @return double Valor entre 0 e 1 que representa o tempo levado para abastecimento do carro
     */
    public double abastece(Carro carro){
        Random random = new Random();
        carro.setComb(10+random.nextInt(5));
        return Math.random();
    }

    public String getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(String idMecanico) {
        this.idMecanico = idMecanico;
    }
    
    
}

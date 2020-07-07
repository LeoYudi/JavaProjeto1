/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

/**
 *
 * @author rebeca
 */
public class Mecanico {
    private String idEquipe;

    public Mecanico(String idEquipe) {
        this.idEquipe = idEquipe;
    }  
    
    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }
    
    public void trocarPneu(){
        
    }
    
    public void abastecer(){
        
    }
    
    public void imprimir(){
        System.out.println("Mecânico: "+ idEquipe);
    }
}

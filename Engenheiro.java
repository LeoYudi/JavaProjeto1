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
public class Engenheiro {
    private String idPiloto;

    public Engenheiro(String idPiloto) {
        this.idPiloto = idPiloto;
    }
    
    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }
    
    public void trocarPneu(){
        
    }
    
    public void abastecer(){
        
    }
    
    public void trocarPosicao(){
        
    }
    
    public void imprimir(){
        System.out.println("Engenheiro:" +idPiloto);
    }
}

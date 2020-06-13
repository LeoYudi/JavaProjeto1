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
public class Piloto{
    String idEquipe;
    String id;
    Engenheiro engenheiro;
    Carro carro;
    int pontos;

    public Piloto(String idEquipe, String id, Engenheiro engenheiro, Carro carro, int pontos) {
        this.idEquipe = idEquipe;
        this.id = id;
        this.engenheiro = engenheiro;
        this.carro = carro;
        this.pontos = pontos;
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Engenheiro getEngenheiro() {
        return engenheiro;
    }

    public void setEngenheiro(Engenheiro engenheiro) {
        this.engenheiro = engenheiro;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public void imprimir(){
        System.out.println("Id piloto:" +id);
        System.out.println("Id carro:" +carro.getIdCarro());
    }
}

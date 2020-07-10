/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;

/**
 *
 * @author rebeca
 */
public class Equipe {
    String id;
    ArrayList<Piloto> pilotos;
    ArrayList<Engenheiro> engenheiros;
    ArrayList<Mecanico> mecanicos;
    int pontosTotal;

    public Equipe(String id) {
        this.id = id;
        this.pontosTotal = 0;
    }

    public Equipe() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Piloto> getPilotos() {
        return pilotos;
    }

    public void setPilotos(ArrayList<Piloto> pilotos) {
        this.pilotos = pilotos;
    }

    public ArrayList<Engenheiro> getEngenheiros() {
        return engenheiros;
    }

    public void setEngenheiros(ArrayList<Engenheiro> engenheiros) {
        this.engenheiros = engenheiros;
    }

    public ArrayList<Mecanico> getMecanicos() {
        return mecanicos;
    }

    public void setMecanico(ArrayList<Mecanico> mecanicos) {
        this.mecanicos = mecanicos;
    }

    /*
    Faz o somatório dos pontos de todos os pilotos da equipe
    */
    public int getPontosTotal() {
        int pontos = 0;
        
        for(Piloto piloto: pilotos){
            pontos += piloto.getPontuacaoTotal();
        }
        
        return pontos;
    }

    public void setPontosTotal(int pontosTotal) {
        this.pontosTotal = pontosTotal;
    }

    public void imprimir(){
        System.out.println("Id equipe:" +id);
        for(Mecanico m: mecanicos){
            m.imprimir();
        }
        for(Engenheiro e : engenheiros){
            e.imprimir();
        }
        for(Piloto p: pilotos){
            p.imprimir();
        }
        
    }
}

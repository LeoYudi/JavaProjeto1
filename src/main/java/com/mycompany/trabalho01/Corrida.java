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
public class Corrida {
    String cidade;
    ArrayList<Equipe> equipes;
    Eventos eventos;
    

    public Corrida(String cidade) {
        this.cidade = cidade;
        equipes = new ArrayList();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(ArrayList<Equipe> equipes) {
        this.equipes = equipes;
    }

    public Eventos getEventos() {
        return eventos;
    }

    public void setEventos(Eventos eventos) {
        this.eventos = eventos;
    }
    public void criarEquipes(int k){
        
        for(int i=0; i<k; i++){
            ArrayList<Mecanico> mecanicos = new ArrayList();
            ArrayList<Engenheiro> engenheiros = new ArrayList();
            ArrayList<Piloto> pilotos = new ArrayList();
            Equipe equipe = new Equipe("E"+i);
            for(int m = 0; m<4;m++){
                Mecanico mecanico = new Mecanico("E"+i);
                mecanicos.add(mecanico);
            }
            for(int p = 0; p<2;p++){
                Engenheiro engenheiro = new Engenheiro("E"+i+"Piloto"+p);
                engenheiros.add(engenheiro);
                Carro carro = new Carro("E"+i+"Piloto"+p,"E"+i+"Carro"+p,i);
                Piloto piloto = new Piloto("E"+i,"E"+i+"Piloto"+p,engenheiro,carro,0);
                pilotos.add(piloto);
            }
            equipe.setEngenheiros(engenheiros);
            equipe.setPilotos(pilotos);
            equipe.setMecanico(mecanicos);
            this.equipes.add(equipe);
        }     
    }
    public void inicia(){
        Thread thread ;
            for (Equipe e : equipes) {
                ArrayList<Piloto> pilotos = e.getPilotos();
                for(Piloto p : pilotos){
                    Carro c = p.getCarro();
                   // System.out.println(c.getIdCarro());
                    thread= new Thread(c);
                    thread.start();
                }
            }

    }
    
    public void imprimir(){
        for(Equipe e: equipes){
            e.imprimir();
        }
    }
}

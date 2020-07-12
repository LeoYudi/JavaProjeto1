/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.text.DecimalFormat;
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

    /**
     * Método que realiza o somatório dos pontos de todos os pilotos da equipe
     * @return int - Quantidade de pontos da equipe
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
    
    /**
     * Método que adiciona um tempo aleatório na última volta 
     * do carro devido a parada no pitstop
     * @param carro Carro que irá para o pitstop
     * @return String - Texto com as informações do pitstop
     */
    public String pitStop(Carro carro){
        String aux = "";
        if(carro.isAcidente()) {
           aux += "Acidente--> " + carro.getIdCarro()+ " em "; 
        }
        double tempo = Math.random()*5;
        while (tempo == 0) tempo = Math.random();
        carro.setTempoUltimaVolta(carro.getTempoUltimaVolta() + tempo);
        aux += "pitstop " +" + "+ carro.minutesToTime(tempo);
        return aux;
    }
    
    /**
     * Método que faz o engenheiro solicitar uma troca de pneu em um carro
     * @param carro Carro a ter o pneu trocado
     * @return String - Texto com as informações da troca de pneu
     */
    public String trocarPneu(Carro carro){
        String aux = "";
        if(!carro.isPitstop()){
            aux += carro.getPiloto().getEngenheiro().trocarPneu(mecanicos, carro);
        }
        
        return aux;
    }
    
    /**
     * Método que faz o engenheiro solicitar abastecimento de combustível em um carro
     * @param carro Carro a ser abastecido
     * @return String - Texto com as informações do abastecimento
     */
    public String abastecer(Carro carro){
        String aux= "";
        if(!carro.isPitstop()){
            aux+= carro.getPiloto().getEngenheiro().abastecer(mecanicos.get(0), carro);
        }
        
        return aux;
    }
}

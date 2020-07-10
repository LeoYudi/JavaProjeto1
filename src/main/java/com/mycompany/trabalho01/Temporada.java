/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.trabalho01;

import java.util.ArrayList;

public class Temporada {
    
    private ArrayList<Corrida> corridas;
    private ArrayList<Carro> carros;
    private int qtdTotalCorridas;
    private int qtdCorridasTerminadas;
    
    public Temporada(ArrayList<Corrida> corridas, ArrayList<Carro> carros){
        this.corridas = corridas;
        this.carros = carros;
        this.qtdTotalCorridas = corridas.size();
        this.qtdCorridasTerminadas = 0;
    }

    /*
    Verifica se todas as corridas já ocorreram
    */
    public boolean terminou(){
        return qtdCorridasTerminadas == qtdTotalCorridas;
    }
    
    /*
    Verifica se ainda há alguma corrida a ser realizada. Caso haja, ela é iniciada e é retornado seu log.
    Caso contrário, retorna null.
    */
    public String iniciarProximaCorrida() throws InterruptedException{
        if(!terminou()){
            Corrida corridaAtual = corridas.get(qtdCorridasTerminadas);
            
            for(Carro carro: carros){
                carro.setCorridaAtual(corridaAtual);
            }
            
            corridaAtual.inicia();
            qtdCorridasTerminadas++;
            return corridaAtual.getLog();
        }
        return null;
    }
    
    public String colocacaoFinal(){
        ArrayList<Piloto> pilotos = new ArrayList<>();
        for(Carro carro: carros){
            pilotos.add(carro.getPiloto());
        }
        pilotos.sort(new ScoreComparator());
        
        String str = "";
        for(Piloto piloto: pilotos){
            str += piloto.getId() +" "+ piloto.getPontuacaoTotal() + "\n";
        }
        return str;
    }
    
    public ArrayList<Corrida> getCorridas() {
        return corridas;
    }

    public void setCorridas(ArrayList<Corrida> corridas) {
        this.corridas = corridas;
    }

    public ArrayList<Carro> getCarros() {
        return carros;
    }

    public void setCarros(ArrayList<Carro> carros) {
        this.carros = carros;
    }

    public int getQtdTotalCorridas() {
        return qtdTotalCorridas;
    }

    public void setQtdTotalCorridas(int qtdTotalCorridas) {
        this.qtdTotalCorridas = qtdTotalCorridas;
    }

    public int getQtdCorridasTerminadas() {
        return qtdCorridasTerminadas;
    }

    public void setQtdCorridasTerminadas(int qtdCorridasTerminadas) {
        this.qtdCorridasTerminadas = qtdCorridasTerminadas;
    }
    
    
}

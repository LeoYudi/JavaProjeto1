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
    private ArrayList<Equipe> equipes;
    private int qtdTotalCorridas;
    private int qtdCorridasTerminadas;
    
    private ArrayList<Piloto> resultPiloto;
    private ArrayList<Integer> resultPontuacaoPiloto;
    
    private ArrayList<String> resultEquipeId;
    private ArrayList<Integer> resultPontuacaoEquipe;
    
    public Temporada(ArrayList<Corrida> corridas, ArrayList<Carro> carros, ArrayList<Equipe> equipes){
        this.corridas = corridas;
        this.carros = carros;
        this.equipes = equipes;
        this.qtdTotalCorridas = corridas.size();
        this.qtdCorridasTerminadas = 0;
        this.resultPiloto = new ArrayList<>();
        this.resultPontuacaoPiloto = new ArrayList<>();
        this.resultEquipeId = new ArrayList<>();
        this.resultPontuacaoEquipe = new ArrayList<>();
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

    /*
    Essa função inicia todas as corridas de uma temporada
    */
    public void iniciarTodasCorridas() throws InterruptedException{
        for(Corrida corrida: corridas){
            iniciarProximaCorrida();
        }
        montarResultadosPilotos();
        montarResultadosEquipes();
    }
    
    /*
    Retorna uma string com o nome, colocação e pontuação do piloto na temporada
    Essa string está ordenada de acordo com a pontuação do piloto
    */
    public String colocacaoPilotosFinalTemporada(){
        ArrayList<Piloto> pilotos = new ArrayList<>();
        StringBuilder str = new StringBuilder();
        for(Carro carro: carros){
            Piloto piloto = carro.getPiloto();
            pilotos.add(piloto);
        }
        pilotos.sort(new PontuacaoTemporadaPilotoComparator()); //ordenando de acordo com a pontuação total na competição
        for(int i = 0; i < pilotos.size(); i++){
            Piloto piloto = pilotos.get(i);
            resultPiloto.add(piloto);
            resultPontuacaoPiloto.add(piloto.getPontuacaoTotal());
            str.append(String.format("Nome: "+piloto.getId()+" Colocação: "+(i+1)+ 
                    " Pontuação: "+piloto.getPontuacaoTotal()+"\n"));
        }
        return str.toString();
    }
    
    /*
    Popula os arraylists de resultados da corrida
    */
    public void montarResultadosPilotos(){
        ArrayList<Piloto> pilotos = new ArrayList<>();
        for(Carro carro: carros){
            Piloto piloto = carro.getPiloto();
            pilotos.add(piloto);
        }
        
        pilotos.sort(new PontuacaoTemporadaPilotoComparator()); //ordenando de acordo com a pontuação total na competição
        
        for(int i = 0; i < pilotos.size(); i++){
            Piloto piloto = pilotos.get(i);
            resultPiloto.add(piloto);
            resultPontuacaoPiloto.add(piloto.getPontuacaoTotal());
        }
    }
    
    public void montarResultadosEquipes(){
        equipes.sort(new PontuacaoTemporadaEquipeComparator());
        
        for(Equipe equipe: equipes){
            resultEquipeId.add(equipe.getId());
            resultPontuacaoEquipe.add(equipe.getPontosTotal());
        }
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

    public ArrayList<Piloto> getResultPiloto() {
        return resultPiloto;
    }

    public ArrayList<Integer> getResultPontuacaoPiloto() {
        return resultPontuacaoPiloto;
    }

    public ArrayList<Integer> getResultPontuacaoEquipe() {
        return resultPontuacaoEquipe;
    }

    public ArrayList<String> getResultEquipeId() {
        return resultEquipeId;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rebeca
 */
public class Corrida {
    String cidade;
    ArrayList<Equipe> equipes;
    Eventos eventos;
    
    ArrayList<Carro> carros;
    int qtdVoltas;
    int distanciaVolta;

    public Corrida(String cidade, int qtdVoltas, int distanciaVolta) {
        this.cidade = cidade;
        this.qtdVoltas = qtdVoltas;
        equipes = new ArrayList();
        carros = new ArrayList();
        this.distanciaVolta = distanciaVolta;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getQtdVoltas() {
        return qtdVoltas;
    }

    public void setQtdVoltas(int qtdVoltas) {
        this.qtdVoltas = qtdVoltas;
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

    public ArrayList<Carro> getCarros() {
        return carros;
    }

    public void setCarros(ArrayList<Carro> carros) {
        this.carros = carros;
    }
    
    public void gerarPosicoesDeLargada(){
        ArrayList<Integer> posicoes = new ArrayList<>();
        
        for(int i = 1; i <= carros.size(); i++){
            posicoes.add(i); //1,2,3...
        }
        
        Collections.shuffle(posicoes); //embaralhou as posições
        
        for(int i = 0; i < posicoes.size(); i++){ //cada carro recebe uma posição aleatória
            int posicao = posicoes.get(i);
            carros.get(i).setPosicao(posicao);
        }
        
        ordenarCarrosPorPosicao();
    }
    
    public void ordenarCarrosPorPosicao(){
        carros.sort(new PositionComparator());
    }
    
    public void ordenarCarrosPorTempoAcumulado(){
        carros.sort(new TimeComparator());
    }
    
    public void atualizarPosicaoDepoisDaVolta(){
        ordenarCarrosPorTempoAcumulado();
        
        for (int i = 0; i < carros.size(); i++) {
            carros.get(i).setPosicao(i+1);
        }
    }
        
    public synchronized void trocarPosicao(int i, int j){
        //no parâmetro é passado a posição, que começa de 1. O arraylist começa de 0
        carros.get(i-1).setPosicao(j);
        carros.get(j-1).setPosicao(i);
        Collections.swap(carros, i-1, j-1);
    }
    
    public void inicia() throws InterruptedException{
        gerarPosicoesDeLargada();
                
        Thread[] threads = new Thread[carros.size()];
        
        for(int volta = 0; volta < qtdVoltas; volta++){
            
            for (int i = 0; i < carros.size(); i++) {
                threads[i] = new Thread(carros.get(i));
            }
            
            for (int i = 0; i < carros.size(); i++) {
                threads[i].start();
            }
            
            for (Thread thread : threads) {
                thread.join();
            }
            
            atualizarPosicaoDepoisDaVolta();
            imprimirCarrosEmOrdem();
            System.out.println("");
        }
        

    }
    
    public void imprimirCarrosEmOrdem(){
        for (Carro carro : carros) {
            System.out.printf("%s %.4f %d\n", carro.getIdCarro(), carro.getTempoAcumulado(), carro.getPosicao());
        }
    }
    
    public void imprimir(){
        for(Equipe e: equipes){
            e.imprimir();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import static java.lang.System.nanoTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author rebeca
 */
public class Corrida {
    String cidade;
    ArrayList<Equipe> equipes;
    Eventos eventos;
    boolean run = true;
    ArrayList<Carro> carros;
    int qtdVoltas;
    int distanciaVolta;
    boolean chuva = false;
    
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
        imprimirCarrosEmOrdem();
    }
        
    public synchronized void trocarPosicao(int i, int j){
        //no parâmetro é passado a posição, que começa de 1. O arraylist começa de 0
        carros.get(i-1).setPosicao(j);
        carros.get(j-1).setPosicao(i);
        Collections.swap(carros, i-1, j-1);
    }
    
    public void inicia() throws InterruptedException{
        gerarPosicoesDeLargada();
        Timer time = new Timer(); 
        Random random = new Random();
        final int tempoChuva = random.nextInt(20);
        for(int volta = 0; volta < qtdVoltas; volta++){
            ArrayList<Thread> threads = new ArrayList<>();

            for (int i = 0; i < carros.size(); i++) {
                carros.get(i).setVoltaCorrida(volta);
                threads.add(new Thread(carros.get(i)));
                threads.get(i).start();
            }
            
            for (Thread thread : threads) {
                thread.join();
            }
            
            TimerTask tempo = new TimerTask(){
                public void run (){
                    if(run) atualizarPosicaoDepoisDaVolta();
                }
            };
            time.scheduleAtFixedRate(tempo, 0, 10); //posições a cada 10 ms
            
            TimerTask choveu = new TimerTask(){
                public void run (){
                    if(!chuva) {
                       chuva(tempoChuva);
                    }
                }
            };
            time.scheduleAtFixedRate(choveu, 0, tempoChuva);
            
        }
        
       run = false;
    }
    
    public void imprimirCarrosEmOrdem(){
        for (Carro carro : carros) {
            System.out.printf("%s %.4f %d\n", carro.getIdCarro(), carro.getTempoAcumulado(), carro.getPosicao());
            System.out.println(" Volta: "+carro.getVoltaCorrida());
            System.out.printf("%s %s %d\n", carro.getIdCarro(), carro.getStringTempoAcumulado(), carro.getPosicao());
        }
    }
    
    public void imprimir(){
        for(Equipe e: equipes){
            e.imprimir();
        }
    }
    
    public void chuva(int tempoChuva){
        chuva = true;
        double porcentagem = Math.random();
        while(porcentagem == 0) porcentagem = Math.random(); 
        for(Carro c: carros){
            c.setChuva(porcentagem);
        }
        System.out.printf("Chuva reduz velocidade em %.4f\n", porcentagem*100);
    }
    
    public boolean acidente(){
        Random random = new Random();
        int acidente = random.nextInt(3);
        if(acidente == 2){
            return true;
        }
        return false;
    }
    
    //public void acidenteCarros(){
      //  Random random = new Random();
       // int quantCarros = random.nextInt(100);
      //  if(quantCarros )
    //}
}
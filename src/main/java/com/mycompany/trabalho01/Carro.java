/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import static java.lang.System.nanoTime;

/**
 *
 * @author rebeca
 */
public class Carro implements Runnable{
    private String idPiloto;
    private String idCarro;
    private int posicao;
    private int comb;
    final int VOLTAS = 10;
    private estado e;
    
    private Corrida corridaAtual;
    private double tempoAcumulado;
    private double tempoUltimaVolta;
    
    public enum estado{
        PARADO("Parado"),
        CORRENDO("Correndo"),
        PITSTOP("PitStop");
        
        private final String estado;
        estado(String estado) {
            this.estado = estado;
        }
        public String getValor(){
            return estado;
        }
        
        public static estado fromInteger(int x){
            switch(x){
                case 0:
                    return PARADO;
                case 1:
                    return CORRENDO;
                case 2:
                    return PITSTOP;
            }
            return null;
        }      
    }
    
    public Carro(String idPiloto, String id, int posicao) {   
        this.idPiloto = idPiloto;
        this.idCarro = id;
        this.posicao = posicao;
        e = estado.fromInteger(0);
        tempoAcumulado = 0;
    }
    
    public Carro(String idPiloto, String id, int posicao, Corrida corridaAtual) {   
        this.idPiloto = idPiloto;
        this.idCarro = id;
        this.posicao = posicao;
        this.corridaAtual = corridaAtual;
        e = estado.fromInteger(0);
        tempoAcumulado = 0;
    }
    
    //uma volta
    @Override
    public void run() {
        int x = (int) (Math.random() * 100); //entre 0 e 100
        e = estado.fromInteger(1);
        tempoUltimaVolta = 0;
        double tempoInicial = nanoTime();
        
        if(x % 2 == 0){
            System.out.println(idCarro+" parou pra trocar pneu. Mais 0.05ms");
            tempoUltimaVolta += 0.05;
        }
        
        tempoUltimaVolta += (nanoTime()-tempoInicial)/1000000;
        tempoAcumulado += tempoUltimaVolta;
    }

    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getComb() {
        return comb;
    }

    public void setComb(int comb) {
        this.comb = comb;
    }

    public Corrida getCorridaAtual() {
        return corridaAtual;
    }

    public void setCorridaAtual(Corrida corridaAtual) {
        this.corridaAtual = corridaAtual;
    }

    public double getTempoAcumulado() {
        return tempoAcumulado;
    }

    public void setTempoAcumulado(double tempoAcumulado) {
        this.tempoAcumulado = tempoAcumulado;
    }

    public double getTempoUltimaVolta() {
        return tempoUltimaVolta;
    }

    public void setTempoUltimaVolta(double tempoUltimaVolta) {
        this.tempoUltimaVolta = tempoUltimaVolta;
    }
    
    
  
}
//ronaldo monobola
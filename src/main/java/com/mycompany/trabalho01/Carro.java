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
    }
    
    @Override
    public void run() {
        double tempoInicial = nanoTime();
        e = estado.fromInteger(1);
        for(int i=0;i<VOLTAS;i++){
            System.out.println("ID:"+idCarro);
        }
        System.out.println("ID:"+idCarro + "  Completou em:" +(nanoTime()-tempoInicial)/1000000+"ms");        
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

    /*public void imprimir(){
        System.out.println("Id carro: "+idCarro);
    }*/
  
}

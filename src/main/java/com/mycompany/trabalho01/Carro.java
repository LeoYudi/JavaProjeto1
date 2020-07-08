/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import com.mycompany.trabalho01.Corrida;
import static java.lang.System.nanoTime;

/**
 *
 * @author rebeca
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
    private int desgaste;
    private double velocidade;
    private int voltaCorrida;
    private double chuva =1;
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
        this.desgaste = 0;
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
        e = estado.fromInteger(1);
        Eventos eventos = new Eventos();
        boolean pitstop = true;
        this.velocidade = (200 + Math.random()*100)*chuva;
        if(chuva!=1) chuva=1;
        this.velocidade -= this.desgaste*1.2;
        tempoUltimaVolta = (this.corridaAtual.distanciaVolta/(double)this.velocidade)*60; //tempo em minutos 
        if(pitstop){
            if(eventos.pitStop(this)){
                tempoUltimaVolta += 3.5;
                pitstop = false;
                this.desgaste = 0;
            }
            else{
                this.desgaste += this.corridaAtual.distanciaVolta;
            }
        }
        tempoAcumulado += tempoUltimaVolta + 0.1*this.desgaste;
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

    public int getDesgaste() {
        return desgaste;
    }

    public void setDesgaste(int desgaste) {
        this.desgaste = desgaste;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public double getChuva() {
        return chuva;
    }

    public void setChuva(double chuva) {
        this.chuva = chuva;
    }

    public int getVoltaCorrida() {
        return voltaCorrida;
    }

    public void setVoltaCorrida(int voltaCorrida) {
        this.voltaCorrida = voltaCorrida;
    }
    
}
//ronaldo monobola
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
    private int desgaste;
    private boolean quebrado;
    private double velocidade;
    
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
        this.quebrado = false;
    }
    
    public Carro(String idPiloto, String id, int posicao, Corrida corridaAtual) {   
        this.idPiloto = idPiloto;
        this.idCarro = id;
        this.posicao = posicao;
        this.corridaAtual = corridaAtual;
        e = estado.fromInteger(0);
        tempoAcumulado = 0;
        this.quebrado = false;
    }
    
    //uma volta
    @Override
    public void run() {
        if(!quebrado){
            e = estado.fromInteger(1);
            Eventos eventos = new Eventos();
            boolean pitstop = true;
            this.velocidade = 200 + Math.random()*100;
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
            if(eventos.quebraCarro(this)){
                System.out.println("Carro "+idCarro+" quebrou");
                quebrado = true;
                tempoUltimaVolta = 0;
                tempoAcumulado = Double.MAX_VALUE;
                desgaste = 0;
            }
            tempoAcumulado += tempoUltimaVolta + 0.1*this.desgaste;
        }
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
    
    public String getStringTempoAcumulado(){
        if(tempoAcumulado == Double.MAX_VALUE)
            return "Quebrado";
        else 
            return minutesToTime(tempoAcumulado);
    }
    
    public String minutesToTime(double minutes){
        String[] aux = String.format("%.6f", minutes).split(",");
        int parteInteira = Integer.parseInt(aux[0]);
        int parteDecimal = Integer.parseInt(aux[1]);
        
        int hora = (int) (parteInteira / 60);
        int minuto = (int) (parteInteira % 60);
        int segundo = (int) (parteDecimal * 60 / 1000000); //assumindo 6 casas decimais
        return String.format("%02d:%02d:%02d", hora, minuto, segundo);
    }
    
    public void resetar(Corrida novaCorrida){
        this.corridaAtual = novaCorrida;
        this.quebrado = false;
        this.tempoAcumulado = 0;
        this.tempoUltimaVolta = 0;
        this.desgaste = 0;
        this.e = estado.fromInteger(0);
    }
   
}
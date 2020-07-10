/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import com.mycompany.trabalho01.Corrida;
import static java.lang.System.nanoTime;
import javax.swing.JTextArea;
import java.text.DecimalFormat;

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
    private Piloto piloto;
    private String idCarro;
    private int posicao;
    private int comb;
   // final int VOLTAS = 10;
    private estado e;
    private int desgaste;
    private boolean chuva;
    private boolean acidente;
    private boolean quebrado;
    boolean pitstop;
    private double velocidade;
    private Corrida corridaAtual;
    private double tempoAcumulado;
    private double tempoUltimaVolta;
    StringBuffer log;
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
    
    public Carro(Piloto piloto, String id, int posicao) {   
        this.piloto = piloto;
        this.idCarro = id;
        this.posicao = posicao;
        e = estado.fromInteger(0);
        tempoAcumulado = 0;
        this.desgaste = 0;
        this.quebrado = false;
        this.chuva = false;
        this.acidente = false;
        this.log = new StringBuffer();
    }
    
    public Carro(Piloto piloto, String id, int posicao, Corrida corridaAtual) {   
        this.piloto = piloto;
        this.idCarro = id;
        this.posicao = posicao;
        this.corridaAtual = corridaAtual;
        e = estado.fromInteger(0);
        tempoAcumulado = 0;
        this.quebrado = false;
        this.chuva = false;
        this.acidente = false;
        this.log = new StringBuffer();
    }
    
    //uma volta
    @Override
    public void run() {
        
        if(!quebrado){
            e = estado.fromInteger(1);
            Eventos eventos = new Eventos();
            boolean pitstop = true;
            this.velocidade = 230 + Math.random()*20;
            if(chuva) chuva();
            if(acidente) acidente();
            this.velocidade -= this.desgaste*1.2;
            tempoUltimaVolta = (this.corridaAtual.distanciaVolta/(double)this.velocidade)*60; //tempo em minutos 
            if(pitstop){
                if(eventos.pitStop(this)){
                    corridaAtual.appendLog(idCarro+" parou no pitstop. Mais 20s\n");
                    pitStop();
                }
                else{
                    this.desgaste += this.corridaAtual.distanciaVolta;
                }
            }
            if(eventos.quebraCarro(this)){
                corridaAtual.appendLog(idCarro+" quebrou\n");
                quebrar();
            }
            
            tempoAcumulado += tempoUltimaVolta + 0.1*this.desgaste;
        }
    }
    
    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
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

    public boolean isChuva() {
        return chuva;
    }

    public void setChuva(boolean chuva) {
        this.chuva = chuva;
    }

    public boolean isAcidente() {
        return acidente;
    }

    public void setAcidente(boolean acidente) {
        this.acidente = acidente;
    }

    public boolean isQuebrado() {
        return quebrado;
    }

    public void setQuebrado(boolean quebrado) {
        this.quebrado = quebrado;
    }

    public boolean isPitstop() {
        return pitstop;
    }

    public void setPitstop(boolean pitstop) {
        this.pitstop = pitstop;
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
    
    public void reduzVelocidade(double porcentagem){
        DecimalFormat df = new DecimalFormat("0.00");
        this.velocidade -= this.velocidade*porcentagem;
        if(acidente){
            System.out.println("ACIDENTE> carro " +idCarro+ " reduz velocidade em: "+df.format(porcentagem*100)+ "%");
        }
    }
    
    public void chuva(){
        DecimalFormat df = new DecimalFormat("0.00");
        double porcentagem = Math.random();
        while (porcentagem> 0.5)  porcentagem = Math.random();
        if(this.velocidade >=230) {
            reduzVelocidade(porcentagem);
            String aux = "Chuva--> "+idCarro+ " velocidade reduzida em "+df.format(porcentagem*100)+ "%";
            System.out.println(aux);
            log.append(aux);
            log.append("\n");
        }
        chuva = false;
    }
    
    public void acidente(){
        System.out.println("Carro acidente:" +idCarro );
        Eventos eventos = new Eventos();
        double porcentagem = Math.random();
        while (porcentagem> 0.5)  porcentagem = Math.random();
            reduzVelocidade(porcentagem);
            pitStop();
            if(eventos.quebraCarro(this)){
                quebrar();
               String aux = "Carro " +idCarro+ " quebrou no acidente posicao: "+posicao;
               log.append(aux);
               log.append("\n");
            }
        acidente = false;
    }
   
    public void quebrar(){
        System.err.println("Carro "+idCarro+" quebrou");
        quebrado = true;
        tempoUltimaVolta = 0;
        tempoAcumulado = Double.MAX_VALUE;
        desgaste = 0;
    }
    
    public void pitStop(){
        tempoUltimaVolta += 0.33;
        pitstop = false;
        this.desgaste = 0;
        if(acidente) {
            System.out.println("ACIDENTE> carro " + idCarro+ " em pitStop");
            String aux = "ACIDENTE> carro " + idCarro+ " em pitStop";
            log.append(aux);
            log.append("\n");
        }
    }

    public StringBuffer getLog() {
        return log;
    }

    public void setLog(StringBuffer log) {
        this.log = log;
    }
    
    
}

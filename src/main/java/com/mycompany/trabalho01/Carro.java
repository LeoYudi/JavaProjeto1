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
import java.util.Random;

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
    private Equipe equipe;
    private String idEquipe;
    private String idPiloto;
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
    
    public Carro(String idEquipe, String idPiloto, String id, int posicao, Corrida corridaAtual) {   
        Random random = new Random();
        this.idEquipe = idEquipe;
        this.idPiloto = idPiloto;
        this.idCarro = id;
        this.posicao = posicao;
        this.corridaAtual = corridaAtual;
        e = estado.fromInteger(0);
        tempoAcumulado = 0;
        this.comb = 10 + random.nextInt(5);
        this.quebrado = false;
        this.chuva = false;
        this.acidente = false;
    }
    
    //uma volta
    @Override
    public void run() {
        DecimalFormat df = new DecimalFormat("0.000");
        if(!quebrado){
            
            e = estado.fromInteger(1);
            Eventos eventos = new Eventos();
            boolean pitstop = true;
            
            this.velocidade = 230 + Math.random()*20;
            
            if(comb>0) {
                if(comb - velocidade/230 >= 0)
                comb -= velocidade/230;
            }
            
            else if(comb == 0){
                e =  estado.fromInteger(0);
                String aux = e +" "+idCarro + " sem combustível";
                
                corridaAtual.appendLog(aux);
                corridaAtual.appendLog("\n");
                
                e= estado.fromInteger(2);
                aux = e +" "+idCarro + ">>> abasteceu em "+ df.format(equipe.abastecer(this))+ "ms";
                pitstop = false;
                corridaAtual.appendLog(aux);
                corridaAtual.appendLog("\n");
                e = estado.fromInteger(1);
            }
            
            if(comb <= 5){
                e = estado.fromInteger(2);
                double tempoAbastecer = equipe.abastecer(this);
                String aux = e +" "+idCarro + ">>> abasteceu em "+ df.format(tempoAbastecer)+ "ms";
               
                corridaAtual.appendLog(aux);
                corridaAtual.appendLog("\n");
                
                pitstop=false;
                e = estado.fromInteger(1);
            }
            
            if(chuva) chuva();
            if(acidente) acidente();
            
            this.velocidade -= this.desgaste*1.2;
            
            tempoUltimaVolta = (this.corridaAtual.distanciaVolta/(double)this.velocidade)*60; //tempo em minutos 
            
            if(pitstop){
                if(eventos.pitStop(this)){
                   // corridaAtual.appendLog(idCarro+" parou no pitstop. Mais 20s\n");
                    String aux = equipe.pitStop(this);
                    corridaAtual.appendLog(aux);
                    corridaAtual.appendLog("\n");
                }
                else{
                    this.desgaste += this.corridaAtual.distanciaVolta;
                }
            }
            
            if(eventos.quebraCarro(this)){
                corridaAtual.appendLog(idCarro+" quebrou\n");
                quebrar();
            }

            if(desgaste>50){
                e = estado.fromInteger(2);
                String aux = e +" "+idCarro + ">>> desgaste de "+desgaste+">>> trocando pneus. ";
                double trocaPneuDesgaste= equipe.trocarPneu(this);
                aux += "Mais "+df.format(trocaPneuDesgaste)+ "ms";
               
                corridaAtual.appendLog(aux);
                corridaAtual.appendLog("\n");
                
                pitstop = false; 
                e = estado.fromInteger(1);
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
            String aux ="Acidente--> carro " +idCarro+ " reduz velocidade em: "+df.format(porcentagem*100)+ "%";
            System.out.println(aux);
            corridaAtual.appendLog(aux);
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
            corridaAtual.appendLog(aux);
            corridaAtual.appendLog("\n");
        }
                                                             //troca Pneu por conta da chuva
        double trocaPneuChuva = equipe.trocarPneu(this);
        String aux = "Chuva-->"+idCarro + " trocando pneus + "+ df.format(trocaPneuChuva);
        corridaAtual.appendLog(aux);
        corridaAtual.appendLog("\n");
        
        chuva = false;
    }
    
    public void acidente(){
        System.out.println("Carro acidente:" +idCarro );
        Eventos eventos = new Eventos();
        double porcentagem = Math.random();
        while (porcentagem> 0.5)  porcentagem = Math.random();
        reduzVelocidade(porcentagem);
        e = estado.fromInteger(2);
        String aux1 = equipe.pitStop(this);
        corridaAtual.appendLog(aux1);
        corridaAtual.appendLog("\n");
        e = estado.fromInteger(1);
        pitstop=false;
        if(eventos.quebraCarro(this)){
            quebrar();
            e = estado.fromInteger(0);
            String aux = "Carro " +idCarro+ " quebrou no acidente posicao: "+posicao;
            corridaAtual.appendLog(aux);
            corridaAtual.appendLog("\n");
        }
        acidente = false;
    }
   
    public void quebrar(){
        System.err.println("Carro "+idCarro+" quebrou");
        quebrado = true;
        tempoUltimaVolta = 0;
        tempoAcumulado = Double.MAX_VALUE;
        desgaste = 0;
        e = estado.fromInteger(0);
    }

    public estado getE() {
        return e;
    }

    public void setE(estado e) {
        this.e = e;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }
    
    
    
}

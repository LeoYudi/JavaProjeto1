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
import javax.swing.JTextArea;

/**
 *
 * @author rebeca
 */
public class Corrida {
    String nomeGP;
    String cidade;
    ArrayList<Equipe> equipes;
    Eventos eventos;
    boolean run = true;
    ArrayList<Carro> carros;
    int qtdVoltas;
    int distanciaVolta;
    boolean chuva = false;
    
    StringBuffer log;
    
    public Corrida(String nomeGP, String cidade, int qtdVoltas, int distanciaVolta) {
        this.nomeGP = nomeGP;
        this.cidade = cidade;
        this.qtdVoltas = qtdVoltas;
        equipes = new ArrayList();
        carros = new ArrayList();
        this.distanciaVolta = distanciaVolta;
        log = new StringBuffer();
        eventos = new Eventos();
    }

    public String getNomeGP() {
        return nomeGP;
    }

    public void setNomeGP(String nomeGP) {
        this.nomeGP = nomeGP;
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

    public int getDistanciaVolta() {
        return distanciaVolta;
    }

    public void setDistanciaVolta(int distanciaVolta) {
        this.distanciaVolta = distanciaVolta;
    }

    public String getLog() {
        return log.toString();
    }

    public void setLog(StringBuffer log) {
        this.log = log;
    }
    
    public void appendLog(String str){
        log.append(str);
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
        atualizarPosicaoDepoisDaVolta();
        
        final Timer time = new Timer(); 
        final Timer time1 = new Timer();
        final Timer time2 = new Timer();
        
        final double tempoChuva = eventos.randomTempo();
        final double tempoAcidente = eventos.randomTempo();
        
        for(int volta = 0; volta < qtdVoltas; volta++){
            ArrayList<Thread> threads = new ArrayList<>();
            System.out.println("VOLTA>>>> "+volta);
            for (int i = 0; i < carros.size(); i++) {
                threads.add(new Thread(carros.get(i)));
                threads.get(i).start();
            }
            
            for (Thread thread : threads) {
                thread.join(); 
            } 
            
            String str = "Volta" + (volta+1) + "\n";
            log.append(str);
            atualizarPosicaoDepoisDaVolta();
            log.append("\n");

            Thread atualizaPosicao = new Thread(new Runnable(){
                public void run(){    
                    time.scheduleAtFixedRate(new TimerTask(){
                        public void run (){
                            atualizarPosicaoDepoisDaVolta();
                        }
                    }, 0, 15);
                }
             //posições a cada 2 ms
            });
            
            
            Thread chover =new Thread(new Runnable(){
                public void run(){
                    time1.scheduleAtFixedRate(new TimerTask(){
                        public void run (){
                            chuva();
                        }
                    }, 0, (long) tempoChuva); 
                }
            });            
          
            Thread acidente =new Thread(new Runnable(){
                public void run(){
                    time2.scheduleAtFixedRate(new TimerTask(){
                        public void run(){
                            acidenteCarros();        
                        }
                    }, 5, (long) tempoAcidente);
                }
            });
            //
            
            atualizaPosicao.start();
            atualizaPosicao.join();
            chover.start();
            chover.join();
            acidente.start();
            acidente.join();
            
        }      
        time.cancel();
        time1.cancel();
        time2.cancel();
        String aux = "\nFIM DA CORRIDA EM "+ cidade+"\n";
        System.err.print(aux);
    }
    
    public void imprimirCarrosEmOrdem(){
        for (Carro carro : carros) {
            log.append(String.format("%s %s %d\n", carro.getIdCarro(), carro.getStringTempoAcumulado(), carro.getPosicao()));
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
    
    public void chuva(){
        if(eventos.chuva()){
            chuva = true;
            synchronized (carros){
                for(Carro carro: carros){
                    carro.setChuva(chuva);
                }
            }
        }
        chuva=false;
    }
    
    public synchronized int carrosQuebrados(){
        int quebrados = 0;
        for(Carro carro: carros){
            if(carro.isQuebrado()) quebrados++;
        }
        return quebrados;
    }
    
    public void acidenteCarros(){
        if(eventos.acidente()){
            System.out.println("ACIDENTE");
            Random random = new Random();
            
            int quantCarrosEnvolvidos = eventos.quantCarrosAcidente(carros.size()+1);
            int quantCarrosQuebrados = carrosQuebrados();
            int posicao = random.nextInt(carros.size()+1 - quantCarrosQuebrados - quantCarrosEnvolvidos);
            posicao++;
            System.out.println("posicao acidente>>>>" + posicao + " quantCarros acidente>>>>"+quantCarrosEnvolvidos);
           
            int i=0;
            synchronized (carros){ 
                while(i<quantCarrosEnvolvidos){
                    carros.get(posicao+i).setAcidente(true);
                    System.out.println("Carro envolvido posicao: "+ (posicao+i)+ "ID: "+ carros.get(posicao+i).getIdCarro());
                    i++;
                }
            }
        }        
    }
   
}
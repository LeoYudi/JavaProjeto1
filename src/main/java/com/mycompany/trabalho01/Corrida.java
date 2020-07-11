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
    int[] regraPontuacao = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1}; //primeiro lugar ganha 25 pontos; segundo ganha 18...
                                                                //a partir do décimo não ganha ponto
    private ArrayList<Piloto> resultPiloto;
    private ArrayList<String> resultTempoAcumulado;
    private ArrayList<Integer> resultPontuacao;
    
    public Corrida(String nomeGP, String cidade, int qtdVoltas, int distanciaVolta) {
        this.nomeGP = nomeGP;
        this.cidade = cidade;
        this.qtdVoltas = qtdVoltas;
        equipes = new ArrayList();
        carros = new ArrayList();
        this.distanciaVolta = distanciaVolta;
        log = new StringBuffer();
        eventos = new Eventos();
        this.resultPiloto = new ArrayList<>();
        this.resultTempoAcumulado = new ArrayList<>();
        this.resultPontuacao = new ArrayList<>();
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

    public ArrayList<Piloto> getResultPiloto() {
        return resultPiloto;
    }

    public ArrayList<String> getResultTempoAcumulado() {
        return resultTempoAcumulado;
    }

    public ArrayList<Integer> getResultPontuacao() {
        return resultPontuacao;
    }
    
    public void resetarTempoDosCarros(){
        for(Carro carro: carros){
            carro.resetar(this);
        }
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
        resetarTempoDosCarros();
        gerarPosicoesDeLargada();
        log.append("Posições de largada\n\n");
        atualizarPosicaoDepoisDaVolta();
        log.append("\n");
        
        int voltaChuva = eventos.randomVolta(qtdVoltas-2);
        int voltaAcidente = eventos.randomVolta(qtdVoltas-2);
        while (voltaChuva== voltaAcidente) voltaAcidente = eventos.randomVolta(qtdVoltas-2);
        
//        System.out.println("Volta que pode ocorrer chuva>>" +(voltaChuva+2)); 
//        System.out.println("Volta que pode ocorrer acidentede>>"+(voltaAcidente+2));
        
        for(int volta = 0; volta < qtdVoltas; volta++){
            String str = "Volta" + (volta+1) + "\n";
            log.append(str);
            ArrayList<Thread> threads = new ArrayList<>();

            for (int i = 0; i < carros.size(); i++) {
                threads.add(new Thread(carros.get(i)));
                threads.get(i).start();
            }
            
            for (Thread thread : threads) {
                thread.join(); 
            } 
                
            if(volta == voltaChuva){
                chuva();
            }
            else if(volta == voltaAcidente) {
                acidenteCarros();
            }
            
            atualizarPosicaoDepoisDaVolta();
            log.append("\n");
            
        }
        
        atribuirPontuacao();
        montarResultados();
        String aux = "\nFIM DA CORRIDA EM "+ cidade+"\n";
        System.err.print(aux);
        //log.append(aux);
    }
    
    /*
    Popula os arraylists de resultados da corrida
    */
    public void montarResultados(){
        for(int i = 0; i < carros.size(); i++){
            Carro carro = carros.get(i);
            resultPiloto.add(carro.getPiloto());
            resultPontuacao.add(carro.getPiloto().getPontuacaoUltimaCorrida());
            resultTempoAcumulado.add(carro.getStringTempoAcumulado());
        }
    }
    
    /*
    Retorna uma string com o nome, colocação, pontuação e tempo da corrida de cada piloto naquela corrida
    */
    public String resultadoCorrida(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < carros.size(); i++){
            str.append(String.format("Nome: "+resultPiloto.get(i).getId()+" Colocação: "+(i+1)+ 
                    " Pontuação: "+resultPontuacao.get(i)+" Tempo da corrida: "+resultTempoAcumulado.get(i)+"\n"));
        }
        return str.toString();
    }
    
    /*
    Adiciona a pontuação e a colocação final no piloto. Note que regraPontuacao.length é 10, 
    então só os primeiros dez carros pontuarão. A partir da 11ª colocação, a pontuação é 0
    */
    public void atribuirPontuacao(){
        for(int i = 0; i < carros.size(); i++){
            Piloto piloto = carros.get(i).getPiloto();
            if(i < regraPontuacao.length)
                piloto.addResultado(regraPontuacao[i], i + 1);
            else
                piloto.addResultado(0, i + 1);
        }        
    }
    
    public void imprimirCarrosEmOrdem(){
        log.append("\n");
        for (Carro carro : carros) {
            log.append(String.format("%dº lugar: %s %s  \n", carro.getPosicao(), carro.getIdCarro(), carro.getStringTempoAcumulado()));
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
        chuva = false;
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
            Random random = new Random();
            
            int quantCarrosQuebrados = carrosQuebrados();
            int quantCarrosEnvolvidos = eventos.quantCarrosAcidente(carros.size() - quantCarrosQuebrados);
            int posicao;
            if(carros.size() - quantCarrosQuebrados - quantCarrosEnvolvidos >= 0){
                posicao = 1;
            }
            else posicao = random.nextInt(carros.size() - quantCarrosQuebrados - quantCarrosEnvolvidos);
            posicao++;
                     
            int i=0;
            synchronized (carros){ 
                while(i<quantCarrosEnvolvidos){
                    carros.get(posicao+i-1).setAcidente(true);
                    i++;
                }
            }
        }        
    }
   
}
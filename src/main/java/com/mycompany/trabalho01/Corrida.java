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
    private String nomeGP;
    private String cidade;
    private ArrayList<Equipe> equipes;
    private Eventos eventos;
    private boolean run = true;
    private ArrayList<Carro> carros;
    private int qtdVoltas;
    private int distanciaVolta;
    private boolean chuva = false;
    private StringBuffer log;
    private int[] regraPontuacao = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1}; //primeiro lugar ganha 25 pontos; segundo ganha 18...
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
    
    /**
     * Método que reseta as informações necessárias do 
     * carro para iniciar uma nova corrida
     */
    public void resetarTempoDosCarros(){
        for(Carro carro: carros){
            carro.resetar(this);
        }
    }
    
    /**
     * Método que atribui posições aleatórias de largada aos carros
     */
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
    
    /**
    * Método que ordena os carros de forma crescente baseado na posição de cada um
    */
    public void ordenarCarrosPorPosicao(){
        carros.sort(new PositionComparator());
    }
    
    /**
     * Método que ordena os carros de forma crescente baseado no tempo acumulado de cada um
     */
    public void ordenarCarrosPorTempoAcumulado(){
        carros.sort(new TimeComparator());
    }
    
    /**
     * Método que ordena os carros de forma crescente baseado no 
     * tempo acumulado e atualiza as posições
     */
    public void atualizarPosicaoDepoisDaVolta(){
        ordenarCarrosPorTempoAcumulado();
        
        for (int i = 0; i < carros.size(); i++) {
            carros.get(i).setPosicao(i+1);
        }
        imprimirCarrosEmOrdem();
    }
    
    /**
     * Método que inicia uma corrida. Os dados de tempo e desgaste dos carros, por exemplo,
     * são resetados. São geradas posições de largada e definidas em quais voltas haverão
     * acidentes ou chuva. Ao final de cada volta as posições são atualizadas e as informações
     * dos carros são gravadas no log da corrida. Ao final da corrida, é atribuída a pontuação
     * aos pilotos e os resultados são gravados em resultPiloto, resultTempoAcumulado e resultPontuacao.
     * @throws InterruptedException 
     */
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
    
    /**
     * Método que armazena os resultados da corrida nos Arraylists correspondentes
    */
    public void montarResultados(){
        for(int i = 0; i < carros.size(); i++){
            Carro carro = carros.get(i);
            resultPiloto.add(carro.getPiloto());
            resultPontuacao.add(carro.getPiloto().getPontuacaoUltimaCorrida());
            resultTempoAcumulado.add(carro.getStringTempoAcumulado());
        }
    }
    
    /**
     * Método que adiciona a pontuação e a colocação final para cada piloto.
     * A respectiva pontuação para os dez primeiros lugares é feita de acordo com 
     * a variável regraPontuacao. Do 11° lugar em diante os pilotos não pontuam
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
    
    /**
     * Método que adiciona as informações dos carros no log da corrida.
     */
    public void imprimirCarrosEmOrdem(){
        log.append("\n");
        for (Carro carro : carros) {

            log.append(String.format("%dº lugar: %s %s  \n", carro.getPosicao(), carro.getIdCarro(), carro.getStringTempoAcumulado()));
        }
    }
    
    
    
    /**
     * Método responsável por verificar se está chovendo e diminuir a 
     * velocidade de todos os carros devido à pista molhada
     */
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
    
    /**
     * Método que calcula a quantidade de carros quebrados na corrida
     * @return int - Quantidade de carros quebrados na corrida
     */
    public synchronized int carrosQuebrados(){
        int quebrados = 0;
        for(Carro carro: carros){
            if(carro.isQuebrado()) quebrados++;
        }
        return quebrados;
    }
    
    /**
     * Método responsável por realizar o acidente entre dois ou mais carros.
     * É calculado o tanto de carros envolvidos e a posição do primeiro carro envolvido.
     * Se o acidente for entre três carros e a posição escolhida for 2, significa que 
     * houve um acidente entre os carros nas posições 2, 3 e 4
     */
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
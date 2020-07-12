/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;

/**
 *
 * 
 */
public class Piloto{
    private String idEquipe;
    private String id;
    private Engenheiro engenheiro;
    private String idCarro;
    private ArrayList<Integer> pontuacao; //de cada corrida
    private ArrayList<Integer> colocacao; //de cada corrida
    private int pontuacaoTotal; //acumulado de pontos das corridas

    public Piloto(String idEquipe, String id, Engenheiro engenheiro, String idCarro) {
        this.idEquipe = idEquipe;
        this.id = id;
        this.engenheiro = engenheiro;
        this.idCarro = idCarro;
        this.pontuacaoTotal = 0;
        
        pontuacao = new ArrayList<>();
        colocacao = new ArrayList<>();
    }

    public String getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(String idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Engenheiro getEngenheiro() {
        return engenheiro;
    }

    public void setEngenheiro(Engenheiro engenheiro) {
        this.engenheiro = engenheiro;
    }

    public String getIdCarro() {
        return idCarro;
    }

    public void setCarro(String idCarro) {
        this.idCarro = idCarro;
    }

    public ArrayList<Integer> getPontuacao() {
        return pontuacao;
    }
    
    /** Método que retorna a pontuação da última corrida
     * @return int Pontuação da última corrida
    */
    public int getPontuacaoUltimaCorrida(){
        return pontuacao.get(pontuacao.size() - 1);
    }

    public void setPontuacao(ArrayList<Integer> pontuacao) {
        this.pontuacao = pontuacao;
    }

    public ArrayList<Integer> getColocacao() {
        return colocacao;
    }

    public void setColocacao(ArrayList<Integer> colocacao) {
        this.colocacao = colocacao;
    }

    public int getPontuacaoTotal() {
        return pontuacaoTotal;
    }

    public void setPontuacaoTotal(int pontuacaoTotal) {
        this.pontuacaoTotal = pontuacaoTotal;
    }

    /**
     * Método que adiciona a pontuação e a colocação na última corrida. 
     * A pontuação total do piloto na temporada também é incrementada
     * @param pontosCorrida Quantos pontos o piloto obteve na corrida
     * @param posicaoFinal Em qual posição o piloto terminou a corrida
     */
    public void addResultado(int pontosCorrida, int posicaoFinal){
        pontuacao.add(pontosCorrida);
        colocacao.add(posicaoFinal);
        pontuacaoTotal += pontosCorrida;
    }
    
}

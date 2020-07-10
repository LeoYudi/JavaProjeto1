/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;

/**
 *
 * @author rebeca
 */
public class Piloto{
    String idEquipe;
    String id;
    Engenheiro engenheiro;
    String idCarro;
    ArrayList<Integer> pontuacao; //de cada corrida
    ArrayList<Integer> colocacao; //de cada corrida
    int pontuacaoTotal; //acumulado de pontos das corridas

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

    public void addResultado(int pontosCorrida, int posicaoFinal){
        pontuacao.add(pontosCorrida);
        colocacao.add(posicaoFinal);
        pontuacaoTotal += pontosCorrida;
    }
    
    public void imprimir(){
        System.out.println("Id piloto:" +id);
        System.out.println("Id carro:" +idCarro);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author rebeca
 */
public class Engenheiro {
    private String idPiloto;
    private String idEngenheiro;

    public Engenheiro(String idPiloto, String idEngenheiro) {
        this.idPiloto = idPiloto;
        this.idEngenheiro = idEngenheiro;
    }
    
    public String getIdPiloto() {
        return idPiloto;
    }

    public void setIdPiloto(String idPiloto) {
        this.idPiloto = idPiloto;
    }

    public String getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(String idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }
    
    public double maiorTempo(double tempos[]){
        double maior = tempos[0]; 
        for(int i =1; i<tempos.length;i++){
            System.out.println("tempos:: "+tempos[i]);
            if(tempos[i]>=maior){
                maior = tempos[i];
            }
        }
        return maior;
    }
    
    public double trocarPneu(ArrayList<Mecanico> mecanicos, Piloto piloto){
        DecimalFormat df = new DecimalFormat("0.000");
        double tempos[] = new double[4];
        
        Carro carro = piloto.getCarro();
        int i =0;
        
        System.out.println("Engenheiro "+ idEngenheiro + " comunica piloto "+piloto.id + " para trocar pneu");
        
        for(Mecanico mecanico: mecanicos){
            tempos[i] =  mecanico.trocarPneu(carro);
            System.out.println("Mecânico:"+ mecanico.getIdMecanico()+ " trocou pneu em " + df.format(tempos[i]));
            i++;
        }
        
        double maiorTempo = maiorTempo(tempos);
       
        return maiorTempo;
    }
    
    public double abastecer(Mecanico mecanico, Piloto piloto){
        DecimalFormat df = new DecimalFormat("0.000");
        double tempo;
        
        Carro carro = piloto.getCarro();
       
        System.out.println("Engenheiro "+ idEngenheiro + " comunica piloto "+piloto.id + " para abastecer");
        
        tempo =  mecanico.abastece(carro);
        System.out.println("Mecânico:"+ mecanico.getIdMecanico()+ " abasteceu em" + df.format(tempo) + "ms");
        
        return tempo;
    }
    
    public void trocarPosicao(){
        
    }
    
    public void imprimir(){
        System.out.println("Engenheiro:" +idPiloto);
    }
}

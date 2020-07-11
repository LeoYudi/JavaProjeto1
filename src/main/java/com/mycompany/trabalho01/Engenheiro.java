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
    
    public String trocarPneu(ArrayList<Mecanico> mecanicos, Carro carro){
        double tempos[] = new double[4];
        
        int i =0;
        String aux = "Engenheiro "+ idEngenheiro + " comunica piloto "+carro.getPiloto().getId() + " para trocar pneu";
        
        aux+= " //Mecânicos: ";
        for(Mecanico mecanico: mecanicos){
            tempos[i] =  mecanico.trocarPneu(carro);
            aux+=  mecanico.getIdMecanico()+ " ";
            i++;
        }
         
        double maiorTempo = maiorTempo(tempos);
        carro.setTempoUltimaVolta(carro.getTempoUltimaVolta()+ maiorTempo);
        aux+= " trocaram pneus em " + carro.minutesToTime(maiorTempo);
        return aux;
    }
    
    public String abastecer(Mecanico mecanico, Carro carro){
        double tempo;
        tempo =  mecanico.abastece(carro);
        String aux = "Engenheiro "+ idEngenheiro + " comunica piloto "+carro.getPiloto().getId() + " para abastecer";
      
        
        while(tempo == 0)  tempo =  mecanico.abastece(carro);
        carro.setTempoUltimaVolta(carro.getTempoUltimaVolta()+ tempo);
        aux+= " //Mecânico: "+mecanico.getIdMecanico() +" abastece em "+ carro.minutesToTime(tempo);
        return aux;
    }
    
    public void trocarPosicao(){
        
    }
    
    public void imprimir(){
        System.out.println("Engenheiro:" +idPiloto);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author rebeca
 */
public class Eventos {
    private int num;
    
    
    
   /* public int randomTempo(){
        return 10 + (int) Math.random();
    }*/
    
    /**
     * Método auxiliar que sorteia alguma volta da corrida
     * @param voltas - quantidade de voltas
     * @return int - número da volta sorteada
     */
    public int randomVolta(int voltas){
        Random random = new Random();
        return random.nextInt(voltas);
    }
    
//    public ArrayList<Integer> randomVetor(int quantVoltas, int voltasTotais){
//        Random intRandom = new Random ();
//        ArrayList<Integer> random = new ArrayList<Integer>();
//        for(int i = 0; i< quantVoltas; i++){
//            random.add(intRandom.nextInt(voltasTotais));
//        }
//        
//        return random;
//    }
    
    /**
     * Método que retorna se a chuva irá acontecer
     * @return boolean
     */
    public boolean chuva(){
        double x = Math.random() * 100;
        return x < 0.5;
    }
    
    /**
     * Método que retorna se um acidente vai acontecer
     * @return  boolean
     */
    public boolean acidente(){
        double x = Math.random() * 100;
        return x < 0.4;
    }
    
    /**
     * Método para quantificar o número de carros envolvidos em um acidente
     * @param quantCarros - quantidade total de carros
     * @return int - quantidade de carros envolvidos
     */
    public int quantCarrosAcidente(int quantCarros){
        double x = Math.random();
        Random r = new Random();
        if(x < 0.6) return 2;
        else if(x>=0.6 && x<0.8) return 3;
        else if(x>= 0.8 && x <= 0.97) return 4;
        else return 4+r.nextInt(quantCarros-4);
    }
    
    /**
     * Método que retorna se o carro vai quebrar
     * @param carro
     * @return boolean
     */
    public boolean quebraCarro(Carro carro){
        double x = Math.random() * 100;
        return x < 0.5;
    }
    
    /**
     * Método que retorna se o carro vai para o pitstop
     * @param carro
     * @return boolean
     */
    public boolean pitStop(Carro carro){
        int x = (int) (Math.random() * 100);
        return x < carro.getDesgaste();
    }
    
//    public void gerarEvento(){
//        switch(num){
//            case 0:
//                chuva();
//            case 1:
//                acidente();
//            case 2:
//                quebraCarro();
//            case 3:
//                pitStop();
//        }
//    }
    
}

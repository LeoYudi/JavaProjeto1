/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

/**
 *
 * @author rebeca
 */
public class Eventos {
    private int num;
    
    
    public void chuva(){
        
    }
    
    public void acidente(){
        
    }
    
    public boolean quebraCarro(Carro carro){
        double x = Math.random() * 100;
        return x < 0.5;
    }
    
    public boolean pitStop(Carro carro){
        int x = (int) (Math.random() * 100);
        if(x < carro.getDesgaste()){
            System.out.println(carro.getIdCarro()+" parou no pitstop. Mais 20s");
            return true;
        }
        return false;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.trabalho01;

import java.util.Comparator;

public class PositionComparator implements Comparator<Carro>{
    @Override
    public int compare(Carro c1, Carro c2){
        return new Integer(c1.getPosicao()).compareTo(c2.getPosicao());
    }
}

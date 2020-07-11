/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.trabalho01;

import java.util.Comparator;

public class PontuacaoTemporadaPilotoComparator implements Comparator<Piloto>{
    @Override
    public int compare(Piloto p1, Piloto p2){
        return new Integer(p2.getPontuacaoTotal()).compareTo(p1.getPontuacaoTotal());
    }
}

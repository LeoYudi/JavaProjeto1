/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.trabalho01;

import java.util.Comparator;

public class PontuacaoTemporadaEquipeComparator implements Comparator<Equipe>{
    @Override
    public int compare(Equipe e1, Equipe e2){
        return new Integer(e2.getPontosTotal()).compareTo(e1.getPontosTotal());
    }
}

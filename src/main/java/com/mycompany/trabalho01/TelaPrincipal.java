/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class TelaPrincipal extends javax.swing.JFrame {

    ArrayList<Carro> carros;
    ArrayList<Equipe> equipes;
    ArrayList<Corrida> corridas;
    Temporada temporada;
    
    int qtdCorridasFinalizadas;
    
    public TelaPrincipal() {
        initComponents();
        qtdCorridasFinalizadas = 0;
        
        this.carros = new ArrayList();
        this.equipes = new ArrayList();
        this.corridas = new ArrayList();
        criarEquipes(2);

        corridas.add(new Corrida("GP do Brasil", "Machado", 20, 8));
        corridas.add(new Corrida("GP da Alemanha", "Berlim", 30, 10));
        corridas.add(new Corrida("GP da Argentina", "Buenos Aires", 20, 8));

        for(Corrida corrida: corridas){
            corrida.setCarros(carros);
            corrida.setEquipes(equipes);
        }

        temporada = new Temporada(corridas, carros);
        
        for(Corrida corrida: corridas){
            corridasComboBox.addItem(corrida.nomeGP);
        }
        
        resultadoCorridasPanel.setVisible(false);
        iniciarTemporadaPanel.setVisible(false);
        iniciarCorridaPanel.setVisible(false);
    }
    
    private void criarEquipes(int k){
        for(int i=0; i<k; i++){
            ArrayList<Mecanico> mecanicos = new ArrayList();
            ArrayList<Engenheiro> engenheiros = new ArrayList();
            ArrayList<Piloto> pilotos = new ArrayList();
            Equipe equipe = new Equipe("E"+i);
            for(int m = 0; m<4;m++){
                Mecanico mecanico = new Mecanico("E"+i);
                mecanicos.add(mecanico);
            }
            for(int p = 0; p<2;p++){
                Engenheiro engenheiro = new Engenheiro("E"+i+"Piloto"+p);
                engenheiros.add(engenheiro);
                Piloto piloto = new Piloto("E"+i,"E"+i+"Piloto"+p,engenheiro,"E"+i+"Carro"+p);
                Carro carro = new Carro(piloto, "E"+i+"Carro"+p, i, null);
                this.carros.add(carro);
                pilotos.add(piloto);
            }
            equipe.setEngenheiros(engenheiros);
            equipe.setPilotos(pilotos);
            equipe.setMecanico(mecanicos);
            this.equipes.add(equipe);
        }
    }
    
    /*
    Inicia somente uma corrida e incrementa a quantidade de corridas finalizadas
    */
    private void iniciarCorrida() throws InterruptedException{
        logCorridaTextArea.setText("");
        String log = temporada.iniciarProximaCorrida();
        logCorridaTextArea.setText(log);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        resultadoCorridasPanel = new javax.swing.JPanel();
        corridasComboBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        resultadoCorridaTextArea = new javax.swing.JTextArea();
        iniciarTemporadaPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logCorridaTextArea1 = new javax.swing.JTextArea();
        iniciarCorridaPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        nomeGPLabel = new javax.swing.JLabel();
        cidadeGPLabel = new javax.swing.JLabel();
        qtdVoltasLabel = new javax.swing.JLabel();
        kmPorVoltaLabel = new javax.swing.JLabel();
        qtdCorridasLabel = new javax.swing.JLabel();
        iniciarCorridaBtn = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logCorridaTextArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        opIniciarCorrida = new javax.swing.JMenuItem();
        opIniciarTemporada = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        corridasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corridasComboBoxActionPerformed(evt);
            }
        });

        resultadoCorridaTextArea.setColumns(20);
        resultadoCorridaTextArea.setRows(5);
        jScrollPane3.setViewportView(resultadoCorridaTextArea);

        javax.swing.GroupLayout resultadoCorridasPanelLayout = new javax.swing.GroupLayout(resultadoCorridasPanel);
        resultadoCorridasPanel.setLayout(resultadoCorridasPanelLayout);
        resultadoCorridasPanelLayout.setHorizontalGroup(
            resultadoCorridasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoCorridasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultadoCorridasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultadoCorridasPanelLayout.createSequentialGroup()
                        .addComponent(corridasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 737, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        resultadoCorridasPanelLayout.setVerticalGroup(
            resultadoCorridasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoCorridasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(corridasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        logCorridaTextArea1.setColumns(20);
        logCorridaTextArea1.setRows(5);
        jScrollPane2.setViewportView(logCorridaTextArea1);

        javax.swing.GroupLayout iniciarTemporadaPanelLayout = new javax.swing.GroupLayout(iniciarTemporadaPanel);
        iniciarTemporadaPanel.setLayout(iniciarTemporadaPanelLayout);
        iniciarTemporadaPanelLayout.setHorizontalGroup(
            iniciarTemporadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iniciarTemporadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addContainerGap())
        );
        iniciarTemporadaPanelLayout.setVerticalGroup(
            iniciarTemporadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iniciarTemporadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações da corrida"));

        nomeGPLabel.setText("Nome GP");

        cidadeGPLabel.setText("Cidade:");

        qtdVoltasLabel.setText("Quantidade de voltas:");

        kmPorVoltaLabel.setText("Km por volta:");

        qtdCorridasLabel.setText("Corrida 1 de 20");

        iniciarCorridaBtn.setText("Iniciar Temporada");
        iniciarCorridaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarCorridaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeGPLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cidadeGPLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qtdVoltasLabel)
                    .addComponent(kmPorVoltaLabel))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(iniciarCorridaBtn)
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(qtdCorridasLabel)
                        .addGap(47, 47, 47))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomeGPLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qtdVoltasLabel)
                    .addComponent(qtdCorridasLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cidadeGPLabel)
                        .addComponent(kmPorVoltaLabel))
                    .addComponent(iniciarCorridaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        logCorridaTextArea.setColumns(20);
        logCorridaTextArea.setRows(5);
        jScrollPane1.setViewportView(logCorridaTextArea);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout iniciarCorridaPanelLayout = new javax.swing.GroupLayout(iniciarCorridaPanel);
        iniciarCorridaPanel.setLayout(iniciarCorridaPanelLayout);
        iniciarCorridaPanelLayout.setHorizontalGroup(
            iniciarCorridaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, iniciarCorridaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(iniciarCorridaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        iniciarCorridaPanelLayout.setVerticalGroup(
            iniciarCorridaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(iniciarCorridaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layeredPane.setLayer(resultadoCorridasPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(iniciarTemporadaPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(iniciarCorridaPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iniciarTemporadaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(resultadoCorridasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(iniciarCorridaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iniciarTemporadaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(resultadoCorridasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layeredPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(iniciarCorridaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jMenu1.setText("Iniciar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        opIniciarCorrida.setText("Iniciar corrida");
        opIniciarCorrida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opIniciarCorridaMouseClicked(evt);
            }
        });
        opIniciarCorrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opIniciarCorridaActionPerformed(evt);
            }
        });
        jMenu1.add(opIniciarCorrida);

        opIniciarTemporada.setText("Iniciar temporada");
        opIniciarTemporada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opIniciarTemporadaActionPerformed(evt);
            }
        });
        jMenu1.add(opIniciarTemporada);

        menuBar.add(jMenu1);

        jMenu2.setText("Relatórios");

        jMenuItem3.setText("Equipes");
        jMenu2.add(jMenuItem3);

        menuBar.add(jMenu2);

        jMenu3.setText("Resultados");

        jMenuItem4.setText("Resultado de corrida");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Resultado da temporada");
        jMenu3.add(jMenuItem5);

        menuBar.add(jMenu3);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layeredPane)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_jMenu1MouseClicked

    private void opIniciarCorridaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opIniciarCorridaMouseClicked
        // TODO add your handling code here:        
    }//GEN-LAST:event_opIniciarCorridaMouseClicked

    private void opIniciarCorridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opIniciarCorridaActionPerformed
        iniciarCorridaPanel.setVisible(true);
        
        if(!temporada.terminou()){
            Corrida corridaAtual = corridas.get(temporada.getQtdCorridasTerminadas());
            System.out.println("CORRIDA: "+temporada.getQtdCorridasTerminadas());
            nomeGPLabel.setText(corridaAtual.getNomeGP());
            nomeGPLabel.paintImmediately(nomeGPLabel.getVisibleRect());
            cidadeGPLabel.setText("Cidade: " + corridaAtual.getCidade());
            qtdVoltasLabel.setText("Quantidade de voltas: " + corridaAtual.getQtdVoltas());
            kmPorVoltaLabel.setText("Km por volta: " + corridaAtual.getDistanciaVolta());
            qtdCorridasLabel.setText("Corrida "+ (qtdCorridasFinalizadas+1) + " de " + corridas.size());
        }
        else{ //todas as corridas já foram executadas
            
        }
        
    }//GEN-LAST:event_opIniciarCorridaActionPerformed

    private void iniciarCorridaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarCorridaBtnActionPerformed
        try {
            iniciarCorrida();
        } catch (InterruptedException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_iniciarCorridaBtnActionPerformed

    private void opIniciarTemporadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opIniciarTemporadaActionPerformed
    
//        try {
//            temporada.iniciarTodasCorridas();
//        } catch (InterruptedException ex){
//            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//        }
        iniciarTemporadaPanel.setVisible(true);
    }//GEN-LAST:event_opIniciarTemporadaActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        resultadoCorridasPanel.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void corridasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corridasComboBoxActionPerformed

        switch(corridasComboBox.getSelectedIndex()){
            case 0:
                resultadoCorridaTextArea.setText(temporada.getCorridas().get(0).nomeGP);
                break;
            
            case 1:
                resultadoCorridaTextArea.setText(temporada.getCorridas().get(1).nomeGP);
                break;
        }
    }//GEN-LAST:event_corridasComboBoxActionPerformed

    private void switchPanels(JPanel panel){
        layeredPane.removeAll();
        layeredPane.add(panel);
        layeredPane.repaint();
        layeredPane.revalidate();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cidadeGPLabel;
    private javax.swing.JComboBox<String> corridasComboBox;
    private javax.swing.JButton iniciarCorridaBtn;
    private javax.swing.JPanel iniciarCorridaPanel;
    private javax.swing.JPanel iniciarTemporadaPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel kmPorVoltaLabel;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JTextArea logCorridaTextArea;
    private javax.swing.JTextArea logCorridaTextArea1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel nomeGPLabel;
    private javax.swing.JMenuItem opIniciarCorrida;
    private javax.swing.JMenuItem opIniciarTemporada;
    private javax.swing.JLabel qtdCorridasLabel;
    private javax.swing.JLabel qtdVoltasLabel;
    private javax.swing.JTextArea resultadoCorridaTextArea;
    private javax.swing.JPanel resultadoCorridasPanel;
    // End of variables declaration//GEN-END:variables
}

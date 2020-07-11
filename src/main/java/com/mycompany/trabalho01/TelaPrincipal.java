/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.trabalho01;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;

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
    
    ArrayList<JPanel> panels;
    
    public TelaPrincipal() {
        initComponents();
        qtdCorridasFinalizadas = 0;
        
        this.carros = new ArrayList();
        this.equipes = new ArrayList();
        this.corridas = new ArrayList();
        criarEquipes(3);

        corridas.add(new Corrida("GP do Brasil", "Machado", 20, 8));
        corridas.add(new Corrida("GP da Alemanha", "Berlim", 30, 10));
        corridas.add(new Corrida("GP da Argentina", "Buenos Aires", 20, 8));

        for(Corrida corrida: corridas){
            corrida.setCarros(carros);
            corrida.setEquipes(equipes);
        }

        temporada = new Temporada(corridas, carros, equipes);
        
        resultadoCorridasPanel.setVisible(false);
        iniciarTemporadaPanel.setVisible(false);
        resultadoTemporadaPanel.setVisible(false);
        relatorioCorridaPanel.setVisible(false);
        
        panels = new ArrayList<>();
        panels.add(iniciarTemporadaPanel);
        panels.add(resultadoCorridasPanel);
        panels.add(resultadoTemporadaPanel);
        panels.add(relatorioCorridaPanel);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0;i<5;i++)
            resultadoCorridaTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        
        for(int i=0;i<4;i++)
            resultadoPilotoTemporadaTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        
        for(int i=0;i<3;i++)
            resultadoEquipeTemporadaTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        
    }
    
    private void criarEquipes(int k){
        for(int i=0; i<k; i++){
            ArrayList<Mecanico> mecanicos = new ArrayList();
            ArrayList<Engenheiro> engenheiros = new ArrayList();
            ArrayList<Piloto> pilotos = new ArrayList();
            Equipe equipe = new Equipe("E"+i);
            for(int m = 0; m<4;m++){
                Mecanico mecanico = new Mecanico("E"+i+"Mecanico"+m);
                mecanicos.add(mecanico);
            }
            for(int p = 0; p<2;p++){
                Engenheiro engenheiro = new Engenheiro("E"+i+"Piloto"+p, "E"+i+"Eng"+p);
                engenheiros.add(engenheiro);
                Piloto piloto = new Piloto("E"+i,"E"+i+"Piloto"+p,engenheiro,"E"+i+"Carro"+p);
                Carro carro = new Carro(piloto, "E"+i+"Carro"+p, i, null, "E"+i);
                this.carros.add(carro);
                pilotos.add(piloto);
            }
            equipe.setEngenheiros(engenheiros);
            equipe.setPilotos(pilotos);
            equipe.setMecanico(mecanicos);
            this.equipes.add(equipe);
        }
        
        for(Carro carro: carros){
            for(Equipe equipe: equipes){
                if(equipe.getId().equals(carro.getIdEquipe()))
                    carro.setEquipe(equipe);
            }
        }
        
    }
    
    /*
    Inicia somente uma corrida e incrementa a quantidade de corridas finalizadas
    */
    
    private void iniciarCorrida() throws InterruptedException{
//        logCorridaTextArea.setText("");
//        String log = temporada.iniciarProximaCorrida();
//        logCorridaTextArea.setText(log);
        temporada.iniciarTodasCorridas();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        resultadoCorridasPanel = new javax.swing.JPanel();
        corridasComboBox = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        resultadoCorridaTable = new javax.swing.JTable();
        iniciarTemporadaPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        logIniciarTemporadaTextArea = new javax.swing.JTextArea();
        resultadoTemporadaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultadoEquipeTemporadaTable = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        resultadoPilotoTemporadaTable = new javax.swing.JTable();
        relatorioCorridaPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        relatorioCorridaTextArea = new javax.swing.JTextArea();
        relatorioComboBox = new javax.swing.JComboBox<>();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        opIniciarTemporada = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        opRelatorioCorrida = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        opResultadoCorrida = new javax.swing.JMenuItem();
        opResultadoTemporada = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        corridasComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corridasComboBoxActionPerformed(evt);
            }
        });

        resultadoCorridaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Posição", "Nome do Piloto", "Nome da Equipe", "Pontuação", "Tempo da Corrida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(resultadoCorridaTable);

        javax.swing.GroupLayout resultadoCorridasPanelLayout = new javax.swing.GroupLayout(resultadoCorridasPanel);
        resultadoCorridasPanel.setLayout(resultadoCorridasPanelLayout);
        resultadoCorridasPanelLayout.setHorizontalGroup(
            resultadoCorridasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoCorridasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultadoCorridasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultadoCorridasPanelLayout.createSequentialGroup()
                        .addComponent(corridasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
                .addContainerGap())
        );
        resultadoCorridasPanelLayout.setVerticalGroup(
            resultadoCorridasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoCorridasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(corridasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        logIniciarTemporadaTextArea.setColumns(20);
        logIniciarTemporadaTextArea.setRows(5);
        jScrollPane2.setViewportView(logIniciarTemporadaTextArea);

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

        resultadoTemporadaPanel.setPreferredSize(new java.awt.Dimension(785, 370));

        resultadoEquipeTemporadaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Posição", "Nome da Equipe", "Pontuação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(resultadoEquipeTemporadaTable);

        resultadoPilotoTemporadaTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Posição", "Nome do Piloto", "Nome da Equipe", "Pontuação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(resultadoPilotoTemporadaTable);

        javax.swing.GroupLayout resultadoTemporadaPanelLayout = new javax.swing.GroupLayout(resultadoTemporadaPanel);
        resultadoTemporadaPanel.setLayout(resultadoTemporadaPanelLayout);
        resultadoTemporadaPanelLayout.setHorizontalGroup(
            resultadoTemporadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoTemporadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultadoTemporadaPanelLayout.setVerticalGroup(
            resultadoTemporadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultadoTemporadaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultadoTemporadaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(resultadoTemporadaPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );

        relatorioCorridaPanel.setPreferredSize(new java.awt.Dimension(785, 480));

        relatorioCorridaTextArea.setColumns(20);
        relatorioCorridaTextArea.setRows(5);
        jScrollPane5.setViewportView(relatorioCorridaTextArea);

        relatorioComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout relatorioCorridaPanelLayout = new javax.swing.GroupLayout(relatorioCorridaPanel);
        relatorioCorridaPanel.setLayout(relatorioCorridaPanelLayout);
        relatorioCorridaPanelLayout.setHorizontalGroup(
            relatorioCorridaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(relatorioCorridaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(relatorioCorridaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addGroup(relatorioCorridaPanelLayout.createSequentialGroup()
                        .addComponent(relatorioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        relatorioCorridaPanelLayout.setVerticalGroup(
            relatorioCorridaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, relatorioCorridaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(relatorioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addContainerGap())
        );

        layeredPane.setLayer(resultadoCorridasPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(iniciarTemporadaPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(resultadoTemporadaPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPane.setLayer(relatorioCorridaPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(resultadoTemporadaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(relatorioCorridaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layeredPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(resultadoTemporadaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(relatorioCorridaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jMenu1.setText("Iniciar");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        opIniciarTemporada.setText("Iniciar temporada");
        opIniciarTemporada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opIniciarTemporadaActionPerformed(evt);
            }
        });
        jMenu1.add(opIniciarTemporada);

        menuBar.add(jMenu1);

        jMenu2.setText("Relatórios");

        opRelatorioCorrida.setText("Corrida");
        opRelatorioCorrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opRelatorioCorridaActionPerformed(evt);
            }
        });
        jMenu2.add(opRelatorioCorrida);

        menuBar.add(jMenu2);

        jMenu3.setText("Resultados");

        opResultadoCorrida.setText("Resultado de corrida");
        opResultadoCorrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opResultadoCorridaActionPerformed(evt);
            }
        });
        jMenu3.add(opResultadoCorrida);

        opResultadoTemporada.setText("Resultado da temporada");
        opResultadoTemporada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opResultadoTemporadaActionPerformed(evt);
            }
        });
        jMenu3.add(opResultadoTemporada);

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

    private void opIniciarTemporadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opIniciarTemporadaActionPerformed
    
        try {
            iniciarCorrida();
        } catch (InterruptedException ex){
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        logIniciarTemporadaTextArea.setText("As corridas foram iniciadas automaticamente!\n"
                + "Para visualizar o resultado veja a aba Resultados\n"
                + "Para visualizar os relatórios veja a aba de Relatórios");
        switchPanels(iniciarTemporadaPanel);
        for(Corrida corrida: temporada.getCorridas()){
            corridasComboBox.addItem(corrida.nomeGP);
            relatorioComboBox.addItem(corrida.nomeGP);
        }
        
    }//GEN-LAST:event_opIniciarTemporadaActionPerformed

    private void opResultadoCorridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opResultadoCorridaActionPerformed
        switchPanels(resultadoCorridasPanel);
    }//GEN-LAST:event_opResultadoCorridaActionPerformed

    private void corridasComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corridasComboBoxActionPerformed
        int corrida = corridasComboBox.getSelectedIndex();
        ArrayList<Integer> pontuacoes = temporada.getCorridas().get(corrida).getResultPontuacao(); //primeira pontuaçao eh do primeiro lugar, ta em cordem decrescente
        ArrayList<Piloto> pilotos = temporada.getCorridas().get(corrida).getResultPiloto(); //colocaçao do piloto eh a posiçao no array
        ArrayList<String> tempo = temporada.getCorridas().get(corrida).getResultTempoAcumulado(); //crescente, primeiro eh do primeiro lugar
        
        for(int i=0;i<pontuacoes.size();i++){
            resultadoCorridaTable.setValueAt(i+1, i, 0);
            resultadoCorridaTable.setValueAt(pilotos.get(i).getId(), i, 1);
            resultadoCorridaTable.setValueAt(pilotos.get(i).getIdEquipe(), i, 2);
            resultadoCorridaTable.setValueAt(pontuacoes.get(i), i, 3);
            resultadoCorridaTable.setValueAt(tempo.get(i), i, 4);
        }
    }//GEN-LAST:event_corridasComboBoxActionPerformed

    private void opResultadoTemporadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opResultadoTemporadaActionPerformed
        switchPanels(resultadoTemporadaPanel);
        ArrayList<Piloto> pilotos = temporada.getResultPiloto();
        ArrayList<Integer> pontuacoesPiloto = temporada.getResultPontuacaoPiloto();
        ArrayList<Integer> pontuacoesEquipe = temporada.getResultPontuacaoEquipe();
        ArrayList<String> equipes = temporada.getResultEquipeId();
        for(int i=0;i<pilotos.size();i++){
            resultadoPilotoTemporadaTable.setValueAt(i+1, i, 0);
            resultadoPilotoTemporadaTable.setValueAt(pilotos.get(i).getId(), i, 1);
            resultadoPilotoTemporadaTable.setValueAt(pilotos.get(i).getIdEquipe(), i, 2);
            resultadoPilotoTemporadaTable.setValueAt(pontuacoesPiloto.get(i), i, 3);
        }
        
        for(int i=0;i<equipes.size();i++){
            resultadoEquipeTemporadaTable.setValueAt(i+1, i, 0);
            resultadoEquipeTemporadaTable.setValueAt(equipes.get(i), i, 1);
            resultadoEquipeTemporadaTable.setValueAt(pontuacoesEquipe.get(i), i, 2);
        }
    }//GEN-LAST:event_opResultadoTemporadaActionPerformed

    private void relatorioComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioComboBoxActionPerformed
        relatorioCorridaTextArea.setText(temporada.getCorridas().get(relatorioComboBox.getSelectedIndex()).getLog());
    }//GEN-LAST:event_relatorioComboBoxActionPerformed

    private void opRelatorioCorridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opRelatorioCorridaActionPerformed
        switchPanels(relatorioCorridaPanel);
    }//GEN-LAST:event_opRelatorioCorridaActionPerformed

    private void switchPanels(JPanel target){
        for(JPanel panel: panels){
            if(panel != target)
                panel.setVisible(false);
        }
        target.setVisible(true);
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
    private javax.swing.JComboBox<String> corridasComboBox;
    private javax.swing.JPanel iniciarTemporadaPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JTextArea logIniciarTemporadaTextArea;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem opIniciarTemporada;
    private javax.swing.JMenuItem opRelatorioCorrida;
    private javax.swing.JMenuItem opResultadoCorrida;
    private javax.swing.JMenuItem opResultadoTemporada;
    private javax.swing.JComboBox<String> relatorioComboBox;
    private javax.swing.JPanel relatorioCorridaPanel;
    private javax.swing.JTextArea relatorioCorridaTextArea;
    private javax.swing.JTable resultadoCorridaTable;
    private javax.swing.JPanel resultadoCorridasPanel;
    private javax.swing.JTable resultadoEquipeTemporadaTable;
    private javax.swing.JTable resultadoPilotoTemporadaTable;
    private javax.swing.JPanel resultadoTemporadaPanel;
    // End of variables declaration//GEN-END:variables
}

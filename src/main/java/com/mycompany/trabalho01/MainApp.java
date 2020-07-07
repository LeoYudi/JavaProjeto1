package com.mycompany.trabalho01;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    ArrayList<Carro> carros;
    ArrayList<Equipe> equipes;
    ArrayList<Corrida> corridas;
    

    @Override
    public void start(Stage stage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();*/
        
        this.carros = new ArrayList();
        this.equipes = new ArrayList();
        this.corridas = new ArrayList();
        
        Corrida corrida = new Corrida("Machado", 20, 8);
        corrida.setCarros(carros);
        corrida.setEquipes(equipes);
        this.corridas.add(corrida);
        
        criarEquipes(2);
        try {
            //corrida.imprimir();
            for(int i=0;i<corridas.size();i++){
                for(int j=0;j<this.carros.size();j++){
                    carros.get(j).setCorridaAtual(corridas.get(i));
                }
                corridas.get(i).inicia();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void criarEquipes(int k){
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
                Carro carro;
                carro = new Carro("E"+i+"Piloto"+p, "E"+i+"Carro"+p, i, null);
                Piloto piloto = new Piloto("E"+i,"E"+i+"Piloto"+p,engenheiro,carro,0);
                this.carros.add(carro);
                pilotos.add(piloto);
            }
            equipe.setEngenheiros(engenheiros);
            equipe.setPilotos(pilotos);
            equipe.setMecanico(mecanicos);
            this.equipes.add(equipe);
        }
    }
    
}

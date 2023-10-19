/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restoratings;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ltifi
 */
public class NewFXMain extends Application {

    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("GestionBadge.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("Gestion des Avis");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
   
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("SideBar.fxml")); // Remplacez "VotreFichierFXML.fxml" par le nom de votre fichier FXML initial
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Titre de votre application"); // Remplacez par le titre souhaité
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
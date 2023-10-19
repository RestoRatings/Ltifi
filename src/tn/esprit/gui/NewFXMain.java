/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Ltifi
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Custom Button Example");

        // Créez un bouton personnalisé
        Button customButton = new Button("Mon Bouton");
        customButton.getStyleClass().add("custom-button"); // Appliquez la classe CSS personnalisée

        VBox vbox = new VBox(customButton);
        Scene scene = new Scene(vbox, 300, 200);

        // Chargez le fichier CSS
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

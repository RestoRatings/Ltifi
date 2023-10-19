/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.esprit.entities.Restaurant;

/**
 *
 * @author Ltifi
 */
public class Servicerestaurant {

    
    private Connection connection; // Initialisez votre connexion à la base de données
    private static Servicerestaurant instance;
     public Servicerestaurant() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static Servicerestaurant getInstance() {
        if (instance == null) {
            instance = new Servicerestaurant();
        }
        return instance;
    }

    public List<String> getRestaurantNames() throws SQLException {
    List<String> restaurantNames = new ArrayList<>();
    String query = "SELECT nom FROM restaurant";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()) {
        while (resultSet.next()) {
            String nom = resultSet.getString("nom");
            restaurantNames.add(nom);
        }
    }
    return restaurantNames;
}
    
     public int getIdRestaurantParNom(String nomRestaurant) throws SQLException {
        int idRestaurant = -1; // Une valeur par défaut en cas d'erreur

        String query = "SELECT idrestau FROM restaurant WHERE nom = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nomRestaurant);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    idRestaurant = resultSet.getInt("idrestau");
                }
            }
        }

        return idRestaurant;
    }
     
        public Restaurant getRestaurantById(int idresto) throws SQLException {
    Restaurant restaurant = null; // Une valeur par défaut en cas d'erreur

    String query = "SELECT * FROM restaurant WHERE idrestau = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        preparedStatement.setInt(1, idresto);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                restaurant = createRestaurantFromResultSet(resultSet);
            }
        }
    }

    return restaurant;
}

private Restaurant createRestaurantFromResultSet(ResultSet resultSet) throws SQLException {
    Restaurant restaurant = new Restaurant();
    restaurant.setIdrestau(resultSet.getInt("idrestau"));
    restaurant.setNom(resultSet.getString("nom"));

    // Vous pouvez ajouter d'autres attributs de restaurant ici si nécessaire

    return restaurant;
}


 
}


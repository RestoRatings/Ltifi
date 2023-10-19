/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tn.esprit.entities.User;

/**
 *
 * @author Ltifi
 */
public class ServiceUser {
    
      private static ServiceAvis instance;
    PreparedStatement preparedStatement;
    Connection connection;
    
    public ServiceUser() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
    
    public static ServiceAvis getInstance() {
        if (instance == null) {
            instance = new ServiceAvis();
        }
        return instance;
    }
    
    
    
    
    
    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM user WHERE iduser = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setIduser(resultSet.getInt("iduser"));
                    user.setUsername(resultSet.getString("username"));

                    // Vous pouvez ajouter d'autres attributs de l'utilisateur ici si nécessaire

                    return user;
                }
            }
        }

        return null; // Retourne null si l'utilisateur n'est pas trouvé
    }

}

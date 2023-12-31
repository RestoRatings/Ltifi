/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Timestamp;
import tn.esprit.entities.Avis;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import tn.esprit.entities.Restaurant;
import tn.esprit.entities.User;

/**
 *
 * @author Ltifi
 */
public class ServiceAvis implements IserviceAvis<Avis>{
    private static ServiceAvis instance;
    PreparedStatement preparedStatement;
    Connection connection;
    
    public ServiceAvis() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
    
    public static ServiceAvis getInstance() {
        if (instance == null) {
            instance = new ServiceAvis();
        }
        return instance;
    }
    
    
    
    
    
    
    
    
    
    
    //-------------------------------------------------Ajouter--------------------------------------------------------------------
    @Override
    public void ajouter(Avis av, Restaurant rs, User us) throws SQLException {
    String req = "INSERT INTO avis (dateAvis, pubAvis, titreAvis, id_resto, iduser) VALUES (?, ?, ?, ?, ?)";
    PreparedStatement ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
    
    ps.setDate(1, Date.valueOf(av.getDateAvis()));
    ps.setString(2, av.getPubAvis());
    ps.setString(3, av.getTitreAvis());
    ps.setInt(4, rs.getIdrestau());
    ps.setInt(5, us.getIduser());
    
    ps.executeUpdate();
    
    ResultSet generatedKeys = ps.getGeneratedKeys();
    if (generatedKeys.next()) {
        int autoGeneratedID = generatedKeys.getInt(1);
        av.setId(autoGeneratedID);
        
    
         av.setRestaurant(rs);
        av.setUser(us);
        
         rs.ajouterAvis(av);
        us.ajouterAvis(av);
        
    }
}










    //-------------------------------------------------Modifier--------------------------------------------------------------------


    @Override
    public void modifier(int id, Avis avisModifie,int idr) throws SQLException {
    // Vérifiez d'abord si l'ID existe dans la base de données
    
    if (existeAvis(id)) {
    } else {
        System.err.println("L'ID spécifié n'existe pas dans la base de données.");
        return; }
    
    
    String req = "UPDATE avis SET dateAvis=?, pubAvis=?, titreAvis=?,id_resto=? WHERE id=?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setDate(1, Date.valueOf(avisModifie.getDateAvis()));
    ps.setString(2, avisModifie.getPubAvis());
    ps.setString(3, avisModifie.getTitreAvis());
    ps.setInt(4, idr);
    ps.setInt(5, id);
    
    ps.executeUpdate();
}
    

    @Override
    public boolean existeAvis(int id) throws SQLException {
         String req = "SELECT id FROM avis WHERE id=?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, id);
    
    ResultSet rs = ps.executeQuery();
    
    return rs.next();
    }

    
    
    
    
    
    
    
    
    
    
     //-------------------------------------------------Supprimer--------------------------------------------------------------------

    @Override
    public void supprimer(int id) throws SQLException {
       if (!existeAvis(id)) {
        System.err.println("L'ID spécifié n'existe pas dans la base de données.");
        return; 
    }
    
    
    String req = "DELETE FROM avis WHERE id=?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, id); 
    
    ps.executeUpdate();
    }

    
    
    
    
    
    
    
     //-------------------------------------------------Recuperer--------------------------------------------------------------------

    @Override
public List<Avis> recuperer() throws SQLException {
    List<Avis> listeAvis = new ArrayList<>();

      String req = "SELECT avis.id, avis.dateAvis, avis.pubAvis, avis.titreAvis,restaurant.idrestau, restaurant.nom,user.iduser, user.username FROM avis  INNER JOIN restaurant ON avis.id_resto = restaurant.idrestau INNER JOIN user ON avis.iduser = user.iduser";




    PreparedStatement ps = connection.prepareStatement(req);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        
        Avis avis = new Avis();
        avis.setId(rs.getInt("id"));
        avis.setDateAvis(rs.getDate("dateAvis").toLocalDate());
        avis.setPubAvis(rs.getString("pubAvis"));
        avis.setTitreAvis(rs.getString("titreAvis"));

        
        Restaurant restaurant = new Restaurant();
        restaurant.setIdrestau(rs.getInt("idrestau"));
        restaurant.setNom(rs.getString("nom"));
       
        avis.setRestaurant(restaurant);
        
        User user = new User();
        user.setIduser(rs.getInt("iduser"));
        user.setUsername(rs.getString("username"));
        avis.setUser(user);
        

    
        listeAvis.add(avis);
    }

    return listeAvis;
}

 
///////////////////////////rechercheParNomDeResto/////////////////////////////


    public List<Avis> getAvisByUsername(String username) throws SQLException {
        String query = "SELECT * FROM avis WHERE iduser = (SELECT iduser FROM user WHERE username = ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Avis> avisList = new ArrayList<>();

                while (resultSet.next()) {
                    Avis avis = createAvisFromResultSet(resultSet);
                    avisList.add(avis);
                }

                return avisList;
            }
        }
    }

    public List<Avis> getAvisByUsernameAndRestaurant(String username, String nomRestaurant) throws SQLException {
        String query = "SELECT * FROM avis WHERE iduser = (SELECT iduser FROM user WHERE username = ?) "
                     + "AND id_resto = (SELECT idrestau FROM restaurant WHERE nom = ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, nomRestaurant);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Avis> avisList = new ArrayList<>();

                while (resultSet.next()) {
                    Avis avis = createAvisFromResultSet(resultSet);
                    avisList.add(avis);
                }

                return avisList;
            }
        }
    }

public List<Avis> getAvisByRestaurantName(String nomRestaurant) throws SQLException {
    String query = "SELECT * FROM avis WHERE id_resto = (SELECT idrestau FROM restaurant WHERE nom = ?)";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, nomRestaurant);

        try (ResultSet resultSet = statement.executeQuery()) {
            List<Avis> avisList = new ArrayList<>();

            while (resultSet.next()) {
                Avis avis = createAvisFromResultSet(resultSet);
                avisList.add(avis);
            }

            return avisList;
        }
    }
}

     private Servicerestaurant Servicerestaurant = new Servicerestaurant();

   private Avis createAvisFromResultSet(ResultSet resultSet) throws SQLException {
    Avis avis = new Avis();
    avis.setId(resultSet.getInt("id"));
    avis.setTitreAvis(resultSet.getString("titreAvis"));
    avis.setPubAvis(resultSet.getString("pubAvis"));
    avis.setDateAvis(resultSet.getDate("dateAvis").toLocalDate());

    int restaurantId = resultSet.getInt("id_resto");
    int userId = resultSet.getInt("iduser");

 

    return avis;
}

}


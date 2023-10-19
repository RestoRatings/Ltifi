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
import tn.esprit.entities.Commande;
import java.sql.Statement;
import java.util.ArrayList;
import tn.esprit.entities.TypeC;



/**
 *
 * @author Med-Amine
 */
public class ServiceCommande implements IserviceCommande<Commande> {
    private static ServiceCommande instance;
    PreparedStatement preparedStatement;
    Connection connection;

    public ServiceCommande() {
        connection = tn.esprit.utils.Datasource.getInstance().getCnx();    

}
     public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    @Override
    public void ajouter(Commande cmd) throws SQLException {
                 String req = "insert into commande(montanttotal,date,type) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(req);   
        ps.setFloat(2,cmd.getMontanttotal());
        ps.setDate(3, new java.sql.Date(cmd.getDate().getTime()));
        ps.setObject(4, cmd.getTypec());
        
        ps.executeUpdate();
    }

    @Override
    public void modifier(Commande cmd) throws SQLException {
       String req = "update actualite set montanttotal = ?, date = ?, type = ?";
        PreparedStatement ps = connection.prepareStatement(req);

        ps.setFloat(2,cmd.getMontanttotal());
        ps.setDate(3, new java.sql.Date(cmd.getDate().getTime()));
        ps.setObject(4, cmd.getTypec());
    
        ps.executeUpdate();

    }

    @Override
    public void supprimer(int i) throws SQLException {
    String req = "DELETE FROM commande WHERE idcmnd = ?";
    PreparedStatement ps = connection.prepareStatement(req);
    ps.setInt(1, i);
    
    ps.executeUpdate();
}


    @Override
    public List<Commande> recuperer() throws SQLException {
        
        List<Commande> commandes = new ArrayList<>();
        String req = "select * from commande";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
           Commande com = new Commande();
            com.setIdcmnd(rs.getInt("idcmnd"));
            com.setMontanttotal(rs.getFloat("montanttotal"));
            com.setTypec(TypeC.valueOf(rs.getString("type")));

            commandes.add(com);
        }
        return commandes;
    }



}


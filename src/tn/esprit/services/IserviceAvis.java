/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;
import tn.esprit.entities.Avis;
import tn.esprit.entities.Restaurant;
import tn.esprit.entities.User;
/**
 *
 * @author Ltifi
 */
public interface IserviceAvis<Avis> {
    void ajouter(Avis av,Restaurant rs,User us) throws SQLException ;

    public void modifier(int id, Avis avisModifie,int idr) throws SQLException ;

    void supprimer(int id) throws SQLException ;
   
    List<Avis> recuperer() throws SQLException ;
    
    boolean existeAvis(int id) throws SQLException;
    
    
}
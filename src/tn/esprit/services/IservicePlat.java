/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Med-Amine
 */
public interface IservicePlat<Plat> {
     void ajouter(Plat plt) throws SQLException ;

    void modifier(Plat plt) throws SQLException ;

    void supprimer(int i) throws SQLException ;

    List<Plat> recuperer() throws SQLException ;
    
    
}

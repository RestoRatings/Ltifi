/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;
import tn.esprit.entities.Badge;
import tn.esprit.entities.Restaurant;
import tn.esprit.entities.TypeBadge;
import tn.esprit.entities.User;
/**
 *
 * @author Ltifi
 */
public interface IserviceBadge<Badge> {
    public void ajouter(Badge ba,Restaurant rs, User us) throws SQLException ;

    public void modifier(int id, Badge badgeModifie,int idr) throws SQLException ;
    public boolean existeBadge(int id) throws SQLException;
    void supprimer(int id) throws SQLException ;
    public List<String> getTypeBadge() throws SQLException ;
    List<Badge> recuperer() throws SQLException ;
    public boolean badgeExistePourRestaurant(TypeBadge typeBadge, int idRestaurant) throws SQLException;

}
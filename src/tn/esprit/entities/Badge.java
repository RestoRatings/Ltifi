/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.transformation.FilteredList;

/**
 *
 * @author Ltifi
 */
public class Badge {
    private int id;
    private String commantaire ;
    private TypeBadge typeBadge;
    private LocalDate dateBadge;
    private Restaurant restaurant;
    private User user ;
    List<Restaurant> restaurants = new ArrayList<>();


    public Badge() {
    }

    public Badge(int id, String commantaire, TypeBadge typeBadge, LocalDate dateBadge) {
        this.id = id;
        this.commantaire = commantaire; 
        this.typeBadge = typeBadge;
        this.dateBadge = dateBadge;
    }

    public Badge(int id, String commantaire, TypeBadge typeBadge, LocalDate dateBadge, Restaurant restaurant, User user) {
        this.id = id;
        this.commantaire = commantaire;
        this.typeBadge = typeBadge;
        this.dateBadge = dateBadge;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommantaire() {
        return commantaire;
    }

    public void setCommantaire(String commantaire) {
        this.commantaire = commantaire;
    }

    public TypeBadge getTypeBadge() {
        return typeBadge;
    }

    public void setTypeBadge(TypeBadge typeBadge) {
        this.typeBadge = typeBadge;
    }

    public LocalDate getDateBadge() {
        return dateBadge;
    }

   

    public void setDateBadge(LocalDate dateBadge) {
        this.dateBadge = dateBadge;
    }

    @Override
    public String toString() {
        return "Badge{" + "id=" + id + ", commantaire=" + commantaire + ", typeBadge=" + typeBadge + ", dateBadge=" + dateBadge + '}';
    }

   

    
    
}
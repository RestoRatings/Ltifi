/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Med-Amine
 */
public class Avis {
    private int id;
    private String titreAvis;
    private String pubAvis;
    private LocalDate dateAvis;
    private Restaurant restaurant; 
    private User user;
    public Avis() {
    }

    public Avis(int id, String titreAvis, String pubAvis, LocalDate dateAvis, Restaurant restaurant, User user) {
        this.id = id;
        this.titreAvis = titreAvis;
        this.pubAvis = pubAvis;
        this.dateAvis = dateAvis;
        this.restaurant = restaurant;
        this.user = user;
    }

    public Avis(int id, String titreAvis, String pubAvis, LocalDate dateAvis, User user) {
        this.id = id;
        this.titreAvis = titreAvis;
        this.pubAvis = pubAvis;
        this.dateAvis = dateAvis;
        this.user = user;
    }
    
    
    public Avis(int id, String titreAvis, String pubAvis, LocalDate dateAvis) {
        this.id = id;
        this.titreAvis = titreAvis;
        this.pubAvis = pubAvis;
        this.dateAvis = dateAvis;
    }

    public Avis(int id, String titreAvis, String pubAvis, LocalDate dateAvis, Restaurant restaurant) {
        this.id = id;
        this.titreAvis = titreAvis;
        this.pubAvis = pubAvis;
        this.dateAvis = dateAvis;
        this.restaurant=restaurant;
    }

    
    
    public class AvisService {
    
}
    
    
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitreAvis() {
        return titreAvis;
    }

    public void setTitreAvis(String titreAvis) {
        this.titreAvis = titreAvis;
    }

    public String getPubAvis() {
        return pubAvis;
    }

    public void setPubAvis(String pubAvis) {
        this.pubAvis = pubAvis;
    }

    public LocalDate getDateAvis() {
        return dateAvis;
    }

    public void setDateAvis(LocalDate dateAvis) {
        this.dateAvis = dateAvis;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", titreAvis=" + titreAvis + ", pubAvis=" + pubAvis + ", dateAvis=" + dateAvis + '}';
    }

   

   
    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.Date;

/**
 *
 * @author Med-Amine
 */
public class Reservation {
    private int idreservation;
    private String nom;
    private String prenom;
    private Date date;

    public Reservation() {
    }

    public Reservation(int idreservation, String nom, String prenom, Date date) {
        this.idreservation = idreservation;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    public int getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(int idreservation) {
        this.idreservation = idreservation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idreservation=" + idreservation + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + '}';
    }
    
    
    
}

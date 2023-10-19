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
public class Evennement {
    private int id;
    private String titre;
    private Date date;
    private String  description;
    private String adresse;
    private String lieu;

    public Evennement() {
    }

    public Evennement(int id, String titre, Date date, String description, String adresse, String lieu) {
        this.id = id;
        this.titre = titre;
        this.date = date;
        this.description = description;
        this.adresse = adresse;
        this.lieu = lieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Evennement{" + "id=" + id + ", titre=" + titre + ", date=" + date + ", description=" + description + ", adresse=" + adresse + ", lieu=" + lieu + '}';
    }
    
    
}

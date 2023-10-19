/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Med-Amine
 */
public class Restaurant {
    private int idrestau;
    private String nom;
    private String lieu;
    private  String Specialiter;
    private String Datedetarv;
    private List<Avis> avis = new ArrayList<>(); 
    List<Badge> badge = new ArrayList<>();

    public Restaurant(int idrestau, String nom) {
        this.idrestau = idrestau;
        this.nom = nom;
    }

    
    
    public void ajouterAvis(Avis avis) {
        this.avis.add(avis);
    }
    

    public List<Avis> getAvis() {
        return avis;
    }

    public void setAvis(List<Avis> avis) {
        this.avis = avis;
    }
    
    
    public Restaurant(int idrestau, String nom, String lieu, String Specialiter, String Datedetarv) {
        
        this.idrestau = idrestau;
        this.nom = nom;
        this.lieu = lieu;
        this.Specialiter = Specialiter;
        this.Datedetarv = Datedetarv;
    }



   

    public Restaurant() {
    }
    
    
    

    
    public int getIdrestau() {
        return idrestau;
    }

    public void setIdrestau(int idrestau) {
        this.idrestau = idrestau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getSpecialiter() {
        return Specialiter;
    }

    public void setSpecialiter(String Specialiter) {
        this.Specialiter = Specialiter;
    }

    public String getDatedetarv() {
        return Datedetarv;
    }

    public void setDatedetarv(String Datedetarv) {
        this.Datedetarv = Datedetarv;
    }
    
    
    @Override
    public String toString() {
        return "Restaurant{" + "idrestau=" + idrestau + ", nom=" + nom + ", lieu=" + lieu + ", Specialiter=" + Specialiter + ", Datedetarv=" + Datedetarv + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author Med-Amine
 */
public class Plat {
private int idplat;  
private String nom;      
private String description;        
private String image;      
private float prix;
private CategorieP categorie ;

    public Plat() {
    }

    public Plat(int idplat, String nom, String description, String image, float prix, CategorieP categorie) {
        this.idplat = idplat;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.categorie = categorie;
    }

    public int getIdplat() {
        return idplat;
    }

    public void setIdplat(int idplat) {
        this.idplat = idplat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public CategorieP getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieP categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Plat{" + "idplat=" + idplat + ", nom=" + nom + ", description=" + description + ", image=" + image + ", prix=" + prix + ", categorie=" + categorie + '}';
    }

    

}

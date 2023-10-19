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
public class Participant {
    
    private Date datepar ;
    private int numero;
    private Evennement Evennement;
    private User User;

    public Participant() {
    }

    public Participant(Date datepar, int numero, Evennement Evennement, User User) {
        this.datepar = datepar;
        this.numero = numero;
        this.Evennement = Evennement;
        this.User = User;
    }

   

    public Date getDatepar() {
        return datepar;
    }

    public void setDatepar(Date datepar) {
        this.datepar = datepar;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Evennement getEvennement() {
        return Evennement;
    }

    public void setEvennement(Evennement Evennement) {
        this.Evennement = Evennement;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User User) {
        this.User = User;
    }

    @Override
    public String toString() {
        return "Participant{" + "  datepar=" + datepar + ", numero=" + numero + ", Evennement=" + Evennement + ", User=" + User + '}';
    }
    
    
    
    
    
}

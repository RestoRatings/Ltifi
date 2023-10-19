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
public class Commande {
private int idcmnd;
private float montanttotal;
private Date date;
private TypeC typec;

    public Commande() {
    }

    public Commande(int idcmnd, float montanttotal, Date date, TypeC typec) {
        this.idcmnd = idcmnd;
        this.montanttotal = montanttotal;
        this.date = date;
        this.typec = typec;
    }

    public int getIdcmnd() {
        return idcmnd;
    }

    public void setIdcmnd(int idcmnd) {
        this.idcmnd = idcmnd;
    }

    public float getMontanttotal() {
        return montanttotal;
    }

    public void setMontanttotal(float montanttotal) {
        this.montanttotal = montanttotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeC getTypec() {
        return typec;
    }

    public void setTypec(TypeC typec) {
        this.typec = typec;
    }

    @Override
    public String toString() {
        return "Commande{" + "idcmnd=" + idcmnd + ", montanttotal=" + montanttotal + ", date=" + date + ", typec=" + typec + '}';
    }

   
    
}

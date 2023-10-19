/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Med-Amine
 */
    public class Reclamation {
    private int idrec ;
    private int iduser;
    private Date date ;
    private TypeRec typerec;
    private EtatRec etatrec;

    public Reclamation() {
    }

    public Reclamation(int idrec, int iduser, Date date, TypeRec typerec, EtatRec etatrec) {
        this.idrec = idrec;
        this.iduser = iduser;
        this.date = date;
        this.typerec = typerec;
        this.etatrec = etatrec;
    }

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TypeRec getTyperec() {
        return typerec;
    }

    public void setTyperec(TypeRec typerec) {
        this.typerec = typerec;
    }

    public EtatRec getEtatrec() {
        return etatrec;
    }

    public void setEtatrec(EtatRec etatrec) {
        this.etatrec = etatrec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idrec=" + idrec + ", iduser=" + iduser + ", date=" + date + ", typerec=" + typerec + ", etatrec=" + etatrec + '}';
    }

   
    
}

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
public class User {
    private int iduser;
    private String username;
    private String email;
    private  String password;
    private String firstName;
    private String lastName;
    private String tel;
    
    
    ///////////////////////////////////////////////////////////
    private List<Avis> avis = new ArrayList<>();
    public void ajouterAvis(Avis avis) {
        this.avis.add(avis);
    }

    public List<Avis> getAvis() {
        return avis;
    }
    //////////////////////////////////////////////////////////////
    
    
    public User() {
        
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "User{" + "iduser=" + iduser + ", username=" + username + ", email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", tel=" + tel + '}';
    }
    
    
}

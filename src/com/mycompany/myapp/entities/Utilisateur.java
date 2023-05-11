/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Lenovo
 */
//taw n7oto fi description
public class Utilisateur {

    private int id;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private int num_tel;
    private String role;
    private String etat;
    
    // constructeur par defaut
        public Utilisateur(){}
        
        // les constructeur parametrer

    public Utilisateur(int id, String login, String password, String nom, String prenom, String email, int num_tel, String role, String etat) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_tel = num_tel;
        this.role = role;
        this.etat = etat;
    }

    public Utilisateur(String login, String password, String nom, String prenom, String email, int num_tel, String role, String etat) {
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.num_tel = num_tel;
        this.role = role;
        this.etat = etat;
    }
        
    
    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    // toString

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", num_tel=" + num_tel + ", role=" + role + ", etat=" + etat + '}';
    }
    
    
    // utilisateur connecter
    public static Utilisateur user_connecter;

}

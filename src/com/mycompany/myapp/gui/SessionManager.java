/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManager {

    public static Preferences pref; // 3ibara memoire sghira nsajlo fiha data 

    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static String userName;
        private static String nom;
    private static String prenom;
    private static String email;
    private static String passowrd;
       private static String role;
 

    
     public static String getRole() {
        return pref.get("Role", role);
    }

    public static void setRole(String role) {
        pref.set("Role", role);
    }
    public static String getNumero() {
        return numero;
    }

    public static void setNumero(String numero) {
        SessionManager.numero = numero;
    }
    private static String numero;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static String getUserName() {
        return pref.get("username", userName);
    }

    public static void setUserName(String userName) {
        pref.set("username", userName);
    }

    public static String getPrenom() {
        return pref.get("prenom", prenom);
    }

    public static void setprenom(String prenom) {
        pref.set("prenom", prenom);
    }
    
     public static String getNom() {
        return pref.get("Nom", nom);
    }

    public static void setNom(String nom) {
        pref.set("Nom", nom);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd", passowrd);
    }

    public static void setPassowrd(String passowrd) {
        pref.set("passowrd", passowrd);
    }

}

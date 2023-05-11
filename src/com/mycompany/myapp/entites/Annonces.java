/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author chino
 */
public class Annonces {
    int ids,numeros;
    String noms,emails,adresses;

    
    public Annonces(){}

    public Annonces(int ids, String noms, String emails, String adresses, int numeros) {
        this.ids = ids;
        this.noms = noms;
        this.emails = emails;
        this.numeros = numeros;
        this.adresses = adresses;
        
    }
     public Annonces( String noms, String emails, String adresses, int numeros) {
        this.noms = noms;
        this.emails = emails;
        this.numeros = numeros;
        this.adresses = adresses;
        
    }
     
        public Annonces( String noms, String emails,int numeros,String adresses) {
        this.noms = noms;
        this.emails = emails;
        this.numeros = numeros;
        this.adresses = adresses;
        
    }



    public int getIds() {
        return ids;
    }

    public int getNumeros() {
        return numeros;
    }

    public String getNoms() {
        return noms;
    }

    public String getEmails() {
        return emails;
    }

    public String getAdresses() {
        return adresses;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public void setNumeros(int numeros) {
        this.numeros = numeros;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public void setAdresses(String adresses) {
        this.adresses = adresses;
    }

    @Override
    public String toString() {
        return "Annonces{" + "ids=" + ids + ", noms=" + noms + ", emails=" + emails +", numeros=" + numeros + ", adresses=" + adresses +  '}';
    }
    
    

    
}

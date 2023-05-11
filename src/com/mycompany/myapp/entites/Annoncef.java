/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author imtinen
 */
public class Annoncef {
    int idf,idu;
    String nomf,adresse,emailf,descf;

    
    public Annoncef(){}

    public Annoncef(int idf, String nomf) {
        this.idf = idf;
        this.nomf = nomf;
    }
    
   
    public Annoncef(int idf ,String nomf, String adresse, String emailf, String descf) {
        this.idf = idf;
        this.nomf = nomf;
        this.adresse = adresse;
        this.emailf = emailf;
        this.descf = descf;
  
    }

 public Annoncef(String nomf, String adresse, String emailf, String descf) {
        this.nomf = nomf;
        this.adresse = adresse;
        this.emailf = emailf;
        this.descf = descf;

        
    }
    public int getIdf() {
        return idf;
    }

    public void setIdf(int idf) {
        this.idf = idf;
    }

    public String getNomf() {
        return nomf;
    }

    public void setNomf(String nomf) {
        this.nomf = nomf;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmailf() {
        return emailf;
    }

    public void setEmailf(String emailf) {
        this.emailf = emailf;
    }

    public String getDescf() {
        return descf;
    }

    public void setDescf(String descf) {
        this.descf = descf;
    }


    public void setIdu(int idu) {
        this.idu = idu;
    }

    public int getIdu() {
        return idu;
    }
    @Override
    public String toString() {
        return "Annoncef{" + "nomf=" + nomf + ", adresse=" + adresse + ", emailf=" + emailf + ", descf=" + descf + '}';
    }



  

    
}

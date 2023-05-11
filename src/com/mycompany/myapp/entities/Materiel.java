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
public class Materiel {

    int idm;
    String nomm,marque,descm,prix;
    public Materiel(){}

    public Materiel( int idm, String prix, String nomm, String marque, String descm) {

        this.idm = idm;
        this.prix = prix;
       
        this.nomm = nomm;
        this.marque = marque;
        this.descm = descm;
 
     
    }

 public Materiel(  String nomm, String marque,String prix, String descm) {

       
        this.prix = prix;
       
        this.nomm = nomm;
        this.marque = marque;
        this.descm = descm;
 
     
    }



 

    public int getIdm() {
        return idm;
    }

    public String getMarque() {
        return marque;
    }

    public String getNomm() {
        return nomm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setNomm(String nomm) {
        this.nomm = nomm;
        
    }

    public String getPrix() {
        return prix;
    }

    public String getDescm() {
        return descm;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDescm(String descm) {
        this.descm = descm;
    }

   
   

    @Override
    public String toString() {
        return "materiel{" + "nomm=" + nomm + ", marque=" + marque + ", descm=" + descm + ", prix=" + prix + '}';
    }

 


}

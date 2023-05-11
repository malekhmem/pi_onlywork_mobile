

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
public class Evenement {
    int ide;
    String titre,description,nomss;

    
    public Evenement(){}

    public Evenement(int ide, String titre) {
        this.ide = ide;
        this.titre = titre;
    }
    
   
    public Evenement(int ide ,String titre, String description, String nomss) {
        this.ide = ide;
        this.titre = titre;
        this.description = description;
        this.nomss = nomss;
  
    }

 public Evenement(String titre, String description, String nomss) {
        this.titre = titre;
        this.description = description;
        this.nomss = nomss;

        
    }

    public int getIde() {
        return ide;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getNomss() {
        return nomss;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNomss(String nomss) {
        this.nomss = nomss;
    }
    
    @Override
    public String toString() {
        return "Evenement{" + "ide=" + ide + ", titre=" + titre + ", description=" + description + ", nomss=" + nomss + '}';
    }


   

  

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author houssem
 */
public class reclamation {
       int  idr  ;
     String nomr,emailr,descr,type;

    public reclamation() {
    }
      public reclamation(int idr, String nomr) {
        this.idr = idr;
        this.nomr = nomr;
    }

      public reclamation( int idr ,String descr, String nomr, String emailr, String type) {
        this.idr= idr;
        this.descr = descr;
        this.nomr = nomr;
        this.emailr = emailr;
        this.type = type;
    }

   public reclamation( String descr, String nomr, String emailr, String type) {
      
        this.descr = descr;
        this.nomr = nomr;
        this.emailr = emailr;
        this.type = type;
    }

 
 

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getNomr() {
        return nomr;
    }

    public void setNomr(String nomr) {
        this.nomr = nomr;
    }

    
  public String getEmailr() {
        return emailr;
    }

    public void setEmailr(String emailr) {
        this.emailr = emailr;
    } 
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
      public String toString() {
        return "reclamation{" + "idr=" + idr + ", descr=" + descr + ", nomr=" + nomr +  ", emailr=" + emailr + ", type=" + type + "\n}";
    }
    
}

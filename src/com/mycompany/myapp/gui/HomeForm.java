/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.gui.ListEvenementForm;

import com.mycompany.myapp.gui.MapForm;


/**
 *
 * @author Imtinen
 */
public class HomeForm extends Form {
    Resources res;
    Form current;

 
    public HomeForm(){
        current=this;
           if (MyApplication.theme == null) {
    System.out.println("MyApplication.theme est null");
} else if (MyApplication.theme.getImage("1.jpg") == null) {
    System.out.println("MyApplication.theme.getImage(\"1.jpg\") est null");
} else {
    getUnselectedStyle().setBgImage(MyApplication.theme.getImage("1.jpg"));
}

        setTitle("Mon application");
        setLayout(BoxLayout.y());
       
       
       setTitle("Gestion  das Evenement");
       setLayout(BoxLayout.y());
       
       add(new Label("choisir une option"));
       Button btnAddEvenement = new Button("Add Evenement");
       Button btnListEvenement = new Button("List Evenement") ;
       Button btnAddAnnonces = new Button("Add Annonces societe");
       Button btnListAnnonces = new Button("List Annonces societe") ;
       Button map = new Button("Map") ;

       Button btnModifierEvenement = new Button ("Modifier");
       Button btnModifierAnnonces = new Button ("Modifier");


         btnAddEvenement.addActionListener(e-> new AddEvenementfForm(current).show());
         btnListEvenement.addActionListener(e -> new ListEvenementForm(current).show());
          btnAddAnnonces.addActionListener(e-> new AddAnnoncesForm(current).show());
         btnListAnnonces.addActionListener(e -> new ListAnnoncesForm(current).show());
         //map.addActionListener(e -> new MapForm(current).show());
          map.addActionListener(e-> new MapForm());

       //  btnModifierEvenement.addActionListener(e->new ModiferEvenementForm(current).show());
         addAll(btnAddEvenement,btnListEvenement ,btnAddAnnonces ,btnListAnnonces,map);
       
       
    }

 
   
}

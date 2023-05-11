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
       
       
       setTitle("OnlyWork");
       setLayout(BoxLayout.y());
       
       add(new Label("choisir une option"));
       Button profile = new Button("Mon profile");
       Button btnAddAnnoncef = new Button("Add Annonce fournisseur");
       Button btnListAnnoncef = new Button("List Annonce fournisseur") ;
        Button btnAddMateriel = new Button("Add Materiel");
       Button btnListMateriel= new Button("List Materiel") ;
       Button btnModifierAnnoncef = new Button ("Modifier");
           Button map = new Button ("Map de fournisseur");
                    Button btnAddPoste = new Button("Add Poste");
       Button btnListPoste = new Button("List Poste") ;
       Button btnModifierPoste = new Button ("Modifier");
              Button btnAddEvenement = new Button("Add Evenement");
       Button btnListEvenement = new Button("List Evenement") ;
       Button btnAddAnnonces = new Button("Add Annonces societe");
       Button btnListAnnonces = new Button("List Annonces societe") ;
       Button map2 = new Button("Map DE SOCIETE") ;
        Button btnAddreclamation = new Button("Add reclamtion");
       Button btnListreclamation = new Button("List reclamation") ;
       Button btnModifierreclamation = new Button ("Modifier");

       Button btnModifierEvenement = new Button ("Modifier");
       Button btnModifierAnnonces = new Button ("Modifier");
          map.addActionListener(e -> new MapForm());
         profile.addActionListener(e-> new ProfileForm().show());
         btnAddAnnoncef.addActionListener(e-> new AddAnnoncefForm(current).show());
        btnListAnnoncef.addActionListener(e->new ListAnnoncefForm(current).show());
          btnAddMateriel.addActionListener(e-> new AddMaterielForm(current).show()); 
          btnListMateriel.addActionListener(e->new ListMaterielForm(current).show());
         btnAddPoste.addActionListener(e-> new AddPosteForm(current).show());
        btnListPoste.addActionListener(e->new ListPosteForm(current).show());
       //  btnModifierAnnoncef.addActionListener(e->new ModiferAnnoncefForm(current).show());
       
         btnAddEvenement.addActionListener(e-> new AddEvenementfForm(current).show());
         btnListEvenement.addActionListener(e -> new ListEvenementForm(current).show());
          btnAddAnnonces.addActionListener(e-> new AddAnnoncesForm(current).show());
         btnListAnnonces.addActionListener(e -> new ListAnnoncesForm(current).show());
          btnAddreclamation.addActionListener(e-> new AddreclamationForm(current).show());
        btnListreclamation.addActionListener(e->new ListreclamationForm(current).show());




         //map.addActionListener(e -> new MapForm(current).show());
          map2.addActionListener(e-> new MapForm());

       //  btnModifierEvenement.addActionListener(e->new ModiferEvenementForm(current).show());
         addAll(btnAddEvenement,btnListEvenement ,btnAddAnnonces ,btnListAnnonces,map2);
                addAll(btnAddPoste,btnListPoste);
         addAll(btnAddAnnoncef,btnListAnnoncef,map);
         addAll(btnAddMateriel,btnListMateriel);
           addAll(btnAddreclamation,btnListreclamation);
          addAll(profile);

    }

 
   
}

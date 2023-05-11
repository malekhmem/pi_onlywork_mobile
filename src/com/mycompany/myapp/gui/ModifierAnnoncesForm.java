/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
/**
 *
 * @author chino
 */

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Annonces;
import com.mycompany.myapp.gui.ListAnnoncesForm;
import com.mycompany.myapp.services.ServiceAnnonces;


public class ModifierAnnoncesForm extends Form {
    private Form previous;
    private Annonces annonces;

    private TextField tfnom;
    private TextField tfemail;
    private TextField tfnumero;
    private TextField tfadresse;

    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierAnnoncesForm(Form previous, Annonces annonces) {
        super("Modifier annonces", BoxLayout.y());
        this.previous = previous;
        this.annonces = annonces;

        tfnom = new TextField(annonces.getNoms(), "Nom du annonces");

        tfemail = new TextField(String.valueOf(annonces.getEmails()), "Email du annonces");
        tfnumero = new TextField(String.valueOf(annonces.getNumeros()), "numero du annonces");

        tfadresse = new TextField(annonces.getAdresses(), "Adresse du annonces");


        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annonces.setNoms(tfnom.getText());
            annonces.setEmails(tfemail.getText());
            annonces.setNumeros(Integer.parseInt(tfnumero.getText()));
            annonces.setAdresses(tfadresse.getText());


            if (ServiceAnnonces.getInstance().modifierAnnonces(annonces)) {
                Dialog.show("Annonce modifiée", "Votre annonce a été modifiée avec succès", "OK", null);
                new ListAnnoncesForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListAnnoncesForm(previous).show());
        addAll(tfnom,tfemail ,  tfnumero,tfadresse , btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}



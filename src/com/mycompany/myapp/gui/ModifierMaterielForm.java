package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Materiel;
import com.mycompany.myapp.gui.ListMaterielForm;
import com.mycompany.myapp.services.ServiceMateriel;

public class ModifierMaterielForm extends Form {
    private Form previous;
    private Materiel Materiel;

    private TextField tfnom;
    private TextField tfMarque;
    private TextField tfprix;
    private TextField tfdescription;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierMaterielForm(Form previous, Materiel Materiel) {
        super("Modifier Materiel", BoxLayout.y());
        this.previous = previous;
        this.Materiel = Materiel;

        tfnom = new TextField(Materiel.getNomm(), "Nom du Materiel");
        tfMarque = new TextField(Materiel.getMarque(), "Marque du Materiel");
        tfprix = new TextField(String.valueOf(Materiel.getPrix()), "prix du Materiel");
        tfdescription = new TextField(String.valueOf(Materiel.getDescm()), "Description du Materiel");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            Materiel.setNomm(tfnom.getText());
            Materiel.setMarque(tfMarque.getText());
            Materiel.setPrix(tfprix.getText());
            Materiel.setDescm(tfdescription.getText());

            if (ServiceMateriel.getInstance().modifierMateriel(Materiel)) {
                Dialog.show("Annonce modifiée", "Votre annonce a été modifiée avec succès", "OK", null);
                new ListMaterielForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListMaterielForm(previous).show());
        addAll(tfnom, tfMarque, tfprix, tfdescription, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}

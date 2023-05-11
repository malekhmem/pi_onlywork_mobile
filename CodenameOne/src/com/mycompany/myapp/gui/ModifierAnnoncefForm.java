package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Annoncef;
import com.mycompany.myapp.gui.ListAnnoncefForm;
import com.mycompany.myapp.services.ServiceAnnoncef;

public class ModifierAnnoncefForm extends Form {
    private Form previous;
    private Annoncef annoncef;

    private TextField tfnom;
    private TextField tfadresse;
    private TextField tfemail;
    private TextField tfdescription;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierAnnoncefForm(Form previous, Annoncef annoncef) {
        super("Modifier annoncef", BoxLayout.y());
        this.previous = previous;
        this.annoncef = annoncef;

        tfnom = new TextField(annoncef.getNomf(), "Nom du annoncef");
        tfadresse = new TextField(annoncef.getAdresse(), "Adresse du annoncef");
        tfemail = new TextField(String.valueOf(annoncef.getEmailf()), "Email du annoncef");
        tfdescription = new TextField(String.valueOf(annoncef.getDescf()), "Description du annoncef");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            annoncef.setNomf(tfnom.getText());
            annoncef.setAdresse(tfadresse.getText());
            annoncef.setEmailf(tfemail.getText());
            annoncef.setDescf(tfdescription.getText());

            if (ServiceAnnoncef.getInstance().modifierAnnoncef(annoncef)) {
                Dialog.show("Annonce modifiée", "Votre annonce a été modifiée avec succès", "OK", null);
                new ListAnnoncefForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListAnnoncefForm(previous).show());
        addAll(tfnom, tfadresse, tfemail, tfdescription, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}

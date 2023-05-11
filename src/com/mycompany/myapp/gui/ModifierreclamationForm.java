package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.reclamation;
import com.mycompany.myapp.gui.ListreclamationForm;
import com.mycompany.myapp.services.Servicereclamation;

public class ModifierreclamationForm extends Form {
    private Form previous;
    private reclamation reclamation;

    private TextField tfnom;
    private TextField tfadresse;
    private TextField tfemail;
    private TextField tfdescription;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierreclamationForm(Form previous, reclamation reclamations) {
        super("Modifier reclamation", BoxLayout.y());
        this.previous = previous;
        this.reclamation = reclamations;
        
        tfdescription = new TextField(String.valueOf(reclamation.getDescr()), "Description du reclamation");
        tfnom = new TextField(reclamation.getNomr(), "Nom du reclamation");
        tfemail = new TextField(String.valueOf(reclamation.getEmailr()), "Email du reclamation");
         tfadresse = new TextField(reclamation.getType(), "type du reclamation");


        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            reclamation.setDescr(tfdescription.getText());
            reclamation.setNomr(tfnom.getText());
            reclamation.setEmailr(tfemail.getText());
            reclamation.setType(tfadresse.getText());

            if (Servicereclamation.getInstance().modifierAnnoncef(reclamation)) {
                Dialog.show(" reclamation", "Votre reclamation a été modifiée avec succès", "OK", null);
                new ListreclamationForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListreclamationForm(previous).show());
        addAll( tfemail,tfnom, tfadresse, tfdescription, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}

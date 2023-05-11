package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.gui.ListEvenementForm;
import com.mycompany.myapp.services.ServiceEvenement;

public class ModifierEvenementForm extends Form {
    private Form previous;
    private Evenement evenement;

    private TextField tftitre;
    private TextField tfdescription;
    private TextField tfnomss;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierEvenementForm(Form previous, Evenement evenement) {
        super("Modifier evenement", BoxLayout.y());
        this.previous = previous;
        this.evenement = evenement;

        tftitre = new TextField(evenement.getTitre(), "Titre du evenement");
        tfdescription = new TextField(evenement.getDescription(), "Description du evenement");
        tfnomss = new TextField(String.valueOf(evenement.getNomss()), "Nom du societe");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            evenement.setTitre(tftitre.getText());
            evenement.setDescription(tfdescription.getText());

            if (ServiceEvenement.getInstance().modifierEvenement(evenement)) {
                Dialog.show("evenement modifiée", "Votre evenement a été modifiée avec succès", "OK", null);
                new ListEvenementForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListEvenementForm(previous).show());
        addAll(tftitre, tfdescription, tfnomss, btnModifier, btnAnnuler);
     
    }

    public void show() {
        super.show();
    }
}

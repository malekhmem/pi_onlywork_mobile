package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.gui.ProfileForm;
import com.mycompany.myapp.services.ServiceUtilisateur;

public class ModifierProfileForm extends Form {
    private Form previous;
    private Utilisateur utilisateur;

    private TextField tfnom;
    private TextField tfprenom;
    private TextField tfemail;
    private TextField tfrole;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierProfileForm(Form previous, Utilisateur utilisateur) {
        super("Modifier poste", BoxLayout.y());
       

        tfnom = new TextField(utilisateur.getNom(), "Nom du l'utilisateur");
        tfprenom = new TextField(utilisateur.getPrenom(), "Prenom du l'utilisateur");
        tfemail = new TextField(utilisateur.getEmail(), "Email du l'utilisateur");
        tfrole = new TextField(utilisateur.getRole(), "Role du l'utilisateur");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            utilisateur.setNom(tfnom.getText());
            utilisateur.setPrenom(tfprenom.getText());
            utilisateur.setEmail(tfemail.getText());
            utilisateur.setRole(tfrole.getText());

            if (ServiceUtilisateur.getInstance().modifierProfile(utilisateur)) {
                Dialog.show("poste modifiée", "Votre poste a été modifiée avec succès", "OK", null);
                new ProfileForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ProfileForm(previous).show());
        addAll(tfnom, tfprenom, tfemail, tfrole, btnModifier, btnAnnuler);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->new ProfileForm(previous).show());
     
    }

    ModifierProfileForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void show() {
        super.show();
    }
}

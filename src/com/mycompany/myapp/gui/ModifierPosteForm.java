package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Poste;
import com.mycompany.myapp.gui.ListPosteForm;
import com.mycompany.myapp.services.ServicePoste;

public class ModifierPosteForm extends Form {
    private Form previous;
    private Poste poste;

    private TextField tfnom;
    private TextField tfdomaine;
    private TextField tfemail;
    private TextField tfdescription;
    private Button btnModifier;
    private Button btnAnnuler;

    public ModifierPosteForm(Form previous, Poste poste) {
        super("Modifier poste", BoxLayout.y());
        this.previous = previous;
        this.poste = poste;

        tfnom = new TextField(poste.getNomp(), "Nom du poste");
        tfdomaine = new TextField(poste.getDomaine(), "Domaine du poste");
        tfemail = new TextField(String.valueOf(poste.getEmailp()), "Email du poste");
        tfdescription = new TextField(String.valueOf(poste.getDescp()), "Description du poste");

        btnModifier = new Button("Modifier");
        btnModifier.addActionListener(l -> {
            poste.setNomp(tfnom.getText());
            poste.setDomaine(tfdomaine.getText());
            poste.setEmailp(tfemail.getText());
            poste.setDescp(tfdescription.getText());

            if (ServicePoste.getInstance().modifierPoste(poste)) {
                Dialog.show("poste modifiée", "Votre poste a été modifiée avec succès", "OK", null);
                new ListPosteForm(previous).show();
            }
        });

        btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new ListPosteForm(previous).show());
        addAll(tfnom, tfdomaine, tfemail, tfdescription, btnModifier, btnAnnuler);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->new ListPosteForm(previous).show());
     
    }

    public void show() {
        super.show();
    }
}

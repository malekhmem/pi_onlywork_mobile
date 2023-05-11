package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entites.Annoncef;
import com.mycompany.myapp.services.ServiceAnnoncef;

public class AddAnnoncefForm extends Form {
   private Form previous;

    public AddAnnoncefForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new product");

        TextField tfNom = new TextField("", "Announce name");
        TextField tfAdresse = new TextField("", "Address");
        TextField tfEmail = new TextField("", "Email");
        TextField tfDesc = new TextField("", "Description");
        Button addAnnonceButton = new Button("Add announce");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfAdresse.getText().isEmpty() ||
                    tfEmail.getText().isEmpty() || tfDesc.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Annoncef annonce = new Annoncef(tfNom.getText(), tfAdresse.getText(),
                            tfEmail.getText(), tfDesc.getText());
                        if (ServiceAnnoncef.getInstance().addAnnoncef(annonce)) {
                            Dialog.show("Success", "Announcement added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom, tfAdresse, tfEmail, tfDesc, addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      

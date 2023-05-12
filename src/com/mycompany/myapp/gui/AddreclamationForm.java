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
import com.mycompany.myapp.entites.reclamation;
import com.mycompany.myapp.services.Servicereclamation;

public class AddreclamationForm extends Form {
   private Form previous;

    public AddreclamationForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new reclamation");
       
        TextField tfDesc = new TextField("", "Description");
        TextField tfNom = new TextField("", "reclamation name");
        TextField tfEmail = new TextField("", "Email");
                TextField tfAdresse = new TextField("", "type");

        Button addAnnonceButton = new Button("Add reclamation");

        addAnnonceButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfAdresse.getText().isEmpty() ||
                    tfEmail.getText().isEmpty() || tfDesc.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        reclamation reclamation = new reclamation(tfNom.getText(), tfAdresse.getText(),
                            tfEmail.getText(), tfDesc.getText());
                        if (Servicereclamation.getInstance().addreclamation(reclamation)) {
                            Dialog.show("Success", "reclamation added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfEmail,tfNom, tfDesc, tfAdresse, addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      

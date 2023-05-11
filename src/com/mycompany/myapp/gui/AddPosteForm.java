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
import com.mycompany.myapp.entites.Poste;
import com.mycompany.myapp.services.ServicePoste;

public class AddPosteForm extends Form {
   private Form previous;

    public AddPosteForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new poste");

        TextField tfNom = new TextField("", "Poste name");
        TextField tfDomaine = new TextField("", "Domaine");
        TextField tfDesc = new TextField("", "Description");
        TextField tfEmail = new TextField("", "Email");

        Button addPosteButton = new Button("Add poste");

        addPosteButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfDomaine.getText().isEmpty() ||
                    tfDesc.getText().isEmpty() || tfEmail.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Poste poste = new Poste(tfNom.getText(), tfDomaine.getText(),
                             tfDesc.getText(),tfEmail.getText());
                        if (ServicePoste.getInstance().addPoste(poste)) {
                            Dialog.show("Success", "Poste added", new Command("OK"));
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfNom, tfDomaine, tfDesc, tfEmail, addPosteButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      

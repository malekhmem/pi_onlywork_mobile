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
import com.mycompany.myapp.entites.Annonces;
import com.mycompany.myapp.services.ServiceAnnonces;

public class AddAnnoncesForm extends Form {
    private Form previous;

    public AddAnnoncesForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new product");

        TextField tfNom = new TextField("", "Announce name");
        TextField tfEmail = new TextField("", "Email");
        TextField tfNumero = new TextField("", "Numero");
        TextField tfAdresseAnn = new TextField("", "Address");
        Button addAnnonceButton = new Button("Add announce");

        addAnnonceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfEmail.getText().isEmpty()
                        || tfNumero.getText().isEmpty() || tfAdresseAnn.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                   try {
    int numeros = Integer.parseInt(tfNumero.getText().trim());
    Annonces annonce = new Annonces(tfNom.getText(), tfEmail.getText(), numeros, tfAdresseAnn.getText());
    if (ServiceAnnonces.getInstance().addAnnonces(annonce)) {
        Dialog.show("Success", "Announcement added", new Command("OK"));
    } else {
        Dialog.show("Error", "Server error", new Command("OK"));
    }
} catch (NumberFormatException e) {
    Dialog.show("Error", "Please enter a valid number", new Command("OK"));
} catch (Exception e) {
    Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
}

                }
            }
        });

        addAll(tfNom, tfEmail, tfNumero, tfAdresseAnn, addAnnonceButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }
}

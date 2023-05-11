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
import com.mycompany.myapp.entites.Materiel;
import com.mycompany.myapp.services.ServiceMateriel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class AddMaterielForm extends Form {
    private Form previous;
   
public static final String ACCOUNT_SID = "ACfa4edb6c41c3fb61158fb62d97b9dd61";
    public static final String AUTH_TOKEN = "fb4ec68748aa1bfc0641bba480372552";
    public AddMaterielForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new materiel");

        TextField tfNom = new TextField("", "Materiel name");
        TextField tfMarque = new TextField("", "Address");
        TextField tfprix = new TextField("", "prix");
        TextField tfDesc = new TextField("", "Description");
        Button addMaterielButton = new Button("Add Materiel");

        addMaterielButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().isEmpty() || tfMarque.getText().isEmpty() || tfprix.getText().isEmpty() || tfDesc.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Materiel materiel = new Materiel(tfNom.getText(), tfMarque.getText(), tfprix.getText(), tfDesc.getText());
                        if (ServiceMateriel.getInstance().addMateriel(materiel)) {
                            Dialog.show("Success", "Materiel added", new Command[]{});
//                                                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+21692997134"),
//                new com.twilio.type.PhoneNumber("+16318697050"),
//                "le materiel est ajoute avec succe")
//            .create();

//        System.out.println(message.getSid());
                        } else {
                            Dialog.show("Error", "Server error", new Command[]{});
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                }
             
            }
        });

        addAll(tfNom, tfMarque, tfprix, tfDesc, addMaterielButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }

    public void show() {
        super.show();
    }
}

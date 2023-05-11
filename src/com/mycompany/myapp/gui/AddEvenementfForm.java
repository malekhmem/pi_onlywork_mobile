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
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class AddEvenementfForm extends Form {
   private Form previous;
     // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC2c842ef210ca7d30d22758e94eeb8034";
    public static final String AUTH_TOKEN = "305e8edd9a05028eec4a54f76271cb1a";


    public AddEvenementfForm(Form previous) {
        super("Newsfeed", BoxLayout.y());
        this.previous = previous;
        setTitle("Add new evenement");

        TextField tfTitre = new TextField("", " Titre Evenement");
        TextField tfDescription = new TextField("", "Description");
        TextField tfNomss = new TextField("", "Nomss");
        
        Button addEvenementButton = new Button("Add Evenement");

        addEvenementButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfTitre.getText().isEmpty() || tfDescription.getText().isEmpty() ||
                    tfNomss.getText().isEmpty()) {
                    Dialog.show("Error", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Evenement evenement = new Evenement(tfTitre.getText(), tfDescription.getText(),
                            tfNomss.getText());
                        if (ServiceEvenement.getInstance().addEvenement(evenement)) {
                            Dialog.show("Success", "Evenement added", new Command("OK"));
                             
                            //men hnee sms
                            
//                            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message message = Message.creator(
//                new com.twilio.type.PhoneNumber("+21654318456"),
//                new com.twilio.type.PhoneNumber("+13203218826"),
//                "l evenement  "+tfTitre.getText()+"  est ajoute avec succe")
//            .create();
//
//        System.out.println(message.getSid());
        
        //youfa sms
                        } else {
                            Dialog.show("Error", "Server error", new Command("OK"));
                        }
                    } catch (Exception e) {
                        Dialog.show("Error", "An error occurred: " + e.getMessage(), new Command("OK"));
                    }
                }
            }
        });

        addAll(tfTitre, tfDescription, tfNomss,addEvenementButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }}
      

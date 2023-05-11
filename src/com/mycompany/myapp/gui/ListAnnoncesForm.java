package com.mycompany.myapp.gui;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entites.Annonces;
import com.mycompany.myapp.services.ServiceAnnonces;
import static java.awt.Event.F1;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ListAnnoncesForm extends Form {

    private Form previous;

    public ListAnnoncesForm(Form previous) {
        this.previous = previous;
        setTitle("Liste des Annonces");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SORT_BY_ALPHA, e -> trierParNom());
TextField searchField = new TextField("", "Rechercher par nom", 20, TextField.ANY);
searchField.addDataChangeListener((type, index) -> {
    String text = searchField.getText();
    if (text.length() == 0) {
        refreshList(ServiceAnnonces.getInstance().getAllAnnonces());
    } else {
        refreshList(ServiceAnnonces.getInstance().searchAnnoncesByName(text));
    }
});
getToolbar().addComponentToSideMenu(searchField);

        ArrayList<Annonces> annonces = ServiceAnnonces.getInstance().getAllAnnonces();

        for (Annonces annonce : annonces) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

            Label nomLabel = new Label("Nom : " + annonce.getNoms());
            Label emailLabel = new Label("email : " + annonce.getEmails());
            Label numeroLabel = new Label("numero : " + annonce.getNumeros());
            Label adresseLabel = new Label("adresse : " + annonce.getAdresses());

            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
                    if (ServiceAnnonces.getInstance().deleteAnnonces(annonce.getIds())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'annonce", "Ok", null);
                    }
                }
            });

            ///////////////////////////////////////////////////////////
            /*
            
            
            
            ArrayList<com.mycompany.myapp.entites.Annonces> annoncesss = ServiceAnnonces.getInstance().getAllAnnonces(); 
        Container list = new Container(BoxLayout.y());
        list.setScrollableY(true);
        for (com.mycompany.myapp.entites.Annonces annoncess : annonces) {
            System.out.println(annoncess.getNoms());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(50, 50, 0xffff0000), true);
            Image i = URLImage.createToStorage(placeholder, annoncess.getNoms(), "http://127.0.0.1/ComputerWizards_Handiny_Symfony_master/public/uploads/" + annoncesss.get(CENTER));
            MultiButton sp = new MultiButton(annoncess.getNoms());
            sp.setIcon(i.fill(200, 200));
           sp.setTextLine1("Nom: " + annoncess.getNoms());
            sp.setTextLine2("Email:" + annoncess.getEmails());
            sp.setTextLine4("Numero:" + annoncess.getNumeros());
             sp.setTextLine3("Adresses:"+annoncess.getAdresses());
            list.add(sp);

            Button validerButton = new Button("afficher");
            Button QRcode= new Button("QRcode"); 
 
    
            
            QRcode.addActionListener(e -> {
            
            ServiceAnnonces.getInstance().Qrcode(annoncess.getIds());
            
            });
            
            container.addAll(validerButton,QRcode);

        }

        //SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceVoyage.getInstance().affichageVoyage().toString());
        //this.add(list);
        
            //container.add(list);
        
        
        
            
            */
            /////////////////////////////////////////////////////////////
            
            
            Button buttonModifier = new Button("Modifier");
            buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
            buttonModifier.addActionListener(e -> new ModifierAnnoncesForm(this, annonce).show());

            container.getStyle().setPadding(10, 10, 10, 10);
            container.getStyle().setBorder(Border.createLineBorder(2));
            container.getStyle().setBgTransparency(255);
            container.getStyle().setBgColor(0xffffff);
            container.add(nomLabel);
            container.add(emailLabel);
            container.add(numeroLabel);
            container.add(adresseLabel);
            container.add(buttonSupprimer);
            container.add(buttonModifier);
            
 
            add(container);
            

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new AddAnnoncesForm(this).show());
    }
private void refreshList(ArrayList<Annonces> annonces) {
    removeAll();
    for (Annonces annonce : annonces) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); 
       Label nomLabel = new Label("Nom : " + annonce.getNoms());
            Label emailLabel = new Label("email : " + annonce.getEmails());
            Label numeroLabel = new Label("numero : " + annonce.getNumeros());
            Label adresseLabel = new Label("adresse : " + annonce.getAdresses());

        Button buttonSupprimer = new Button("Supprimer");
        buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
        buttonSupprimer.addActionListener(e -> {
            if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
                if (ServiceAnnonces.getInstance().deleteAnnonces(annonce.getIds())) {
                    container.remove();
                    refreshTheme();
                } else {
                    Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'annonce", "Ok", null);
                }
            }
        });

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
        buttonModifier.addActionListener(e -> new ModifierAnnoncesForm(this, annonce).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
       container.add(nomLabel);
            container.add(emailLabel);
            container.add(numeroLabel);
            container.add(adresseLabel);
            container.add(buttonSupprimer);
            container.add(buttonModifier);

        add(container);
    }
    refreshTheme();
}


    private void trierParNom() {
        ArrayList<Annonces> annonces = ServiceAnnonces.getInstance().getAllAnnonces();
        Collections.sort(annonces, new Comparator<Annonces>() {
            @Override
            public int compare(Annonces annonce1, Annonces annonce2) {
                return annonce1.getNoms().compareTo(annonce2.getNoms());
}
});
removeAll();
for (Annonces annonce : annonces) {
Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); 
        Label nomLabel = new Label("Nom : " + annonce.getNoms());
        Label emailLabel = new Label("Email : " + annonce.getEmails());
        Label numeroLabel = new Label("numero : " + annonce.getNumeros());
        Label adresseLabel = new Label("adresse : " + annonce.getAdresses());

        Button buttonSupprimer = new Button("Supprimer");
        buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
        buttonSupprimer.addActionListener(e -> {
            if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
                if (ServiceAnnonces.getInstance().deleteAnnonces(annonce.getIds())) {
                    container.remove();
                    refreshTheme();
                } else {
                    Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'annonce", "Ok", null);
                }
            }
        });

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
        buttonModifier.addActionListener(e -> new ModifierAnnoncesForm(this, annonce).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
    container.add(nomLabel);
            container.add(emailLabel);
            container.add(numeroLabel);
            container.add(adresseLabel);
            container.add(buttonSupprimer);
            container.add(buttonModifier);

        add(container);
    }
    refreshTheme();
}

/*
public void sendMail(Resources res) {
        try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
             
            Session session = Session.getInstance(props,null); // aleh 9rahach 5ater mazlna masabinach javax.mail .jar
            
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("Societe ajouter <onlyyworkk2@gmail.com>"));
            msg.setRecipients(javax.mail.Message.RecipientType.TO, emails.getText().toString());
            msg.setSubject("Application nom  : Confirmation du ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           String noms = ServiceAnnonces.getInstance().getEmails(emails.getText().toString(), res);//mp taw narj3lo
           String txt = "Bienvenue sur AppNom : Tapez ce mot de passe : "+noms+" dans le champs requis et appuiez sur confirmer";
           
           
           msg.setText(txt);
           
          SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"malek.hmem@esprit.tn","201JFT2912");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());

        }catch(Exception e ) {
            e.printStackTrace();
        }
    }*/

}
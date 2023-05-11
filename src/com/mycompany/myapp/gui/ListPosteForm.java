/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.entites.Poste;
import com.mycompany.myapp.services.ServicePoste;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author zeineb
 */

public class ListPosteForm extends Form {
private Form previous;
 ArrayList<Poste> postes = ServicePoste.getInstance().getAllPostes();
public ListPosteForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des Postes");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
   
    Container buttonContainer = new Container(new FlowLayout(Component.CENTER));
        Button trierBtn = new Button("Trier par Domaine");
        buttonContainer.add(trierBtn);
        add(buttonContainer);

          trierBtn.addActionListener(e -> {
              trierParDomaine();
          });
    for (Poste poste : postes) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label nomLabel = new Label("Nom : " + poste.getNomp());
        Label domaineLabel = new Label("domaine : " + poste.getDomaine());
        Label emailLabel = new Label("Email : " + poste.getEmailp());
        Label descriptionLabel = new Label("Description : " + poste.getDescp());

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette poste ?", "Oui", "Non")) {
        if (ServicePoste.getInstance().deletePoste(poste.getIdp())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression du poste", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierPosteForm(this, poste).show());
Button pdf=new Button("obtenir fiche pdf");
            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, pdf.getUnselectedStyle()));
           pdf.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C:/xampp/pdff/poste" + poste.getIdp() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        
        //        // Ajouter le logo  C:\xampp\htdocs
//       String logoPath = "file:///C:/xampp/htdocs/CodenameOne/logo.jpg"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        
    

 DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        String currentDate = dateFormat.format(new Date());

        
//        //        // Ajouter le logo
//       String logoPath = "file:///C:/xampp/htdocs/logo.png"; // Remplace le chemin par le chemin réel de ton logo
//        Image logo = Image.getInstance(new URL(logoPath));
//        logo.scaleAbsolute(70,70);
//        document.add(logo);
        


        document.open();
        document.add(new Paragraph("Date : " + currentDate));
        document.add(new Paragraph("Fiche de poste"));
        document.add(new Paragraph("Nom :" + poste.getNomp()));
        document.add(new Paragraph("Type :" + poste.getDomaine()));
        document.add(new Paragraph("Description :" + poste.getDescp()));
        document.add(new Paragraph("Nombre de participants :" + poste.getEmailp()));
        
        
        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});
        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(nomLabel);
        container.add(domaineLabel);
        container.add(emailLabel);
        container.add(descriptionLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);
        container.add(pdf);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddPosteForm(this).show());
}
private void trierParDomaine() {


              
        Collections.sort(postes, (o1, o2) -> o1.getDomaine().compareToIgnoreCase(o2.getDomaine()));
        // Remove all the previous components from the form
        removeAll();
        // Create a container to hold the description and set its layout to center alignment
       for (Poste poste : postes) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

       
        Label nomLabel = new Label("Nom : " + poste.getNomp());
        Label domaineLabel = new Label("domaine : " + poste.getDomaine());
        Label emailLabel = new Label("Email : " + poste.getEmailp());
        Label descriptionLabel = new Label("Description : " + poste.getDescp());

       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette poste ?", "Oui", "Non")) {
        if (ServicePoste.getInstance().deletePoste(poste.getIdp())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression du poste", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierPosteForm(this, poste).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(nomLabel);
        container.add(domaineLabel);
        container.add(emailLabel);
        container.add(descriptionLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);

        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddPosteForm(this).show());
        revalidate();
    }
}
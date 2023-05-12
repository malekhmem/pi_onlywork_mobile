/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;

import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entites.reclamation;
import com.mycompany.myapp.services.Servicereclamation;
import java.util.ArrayList;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;
import com.codename1.l10n.DateFormat;


/**
 *
 * @author imtinen
 */
public class ListreclamationForm extends Form {
private Form previous;
public ListreclamationForm(Form previous) {
    this.previous = previous;
    setTitle("Liste des reclamation");
    setLayout(new BoxLayout(BoxLayout.Y_AXIS));
    
    ArrayList<reclamation> reclamations = Servicereclamation.getInstance().getAllreclamation();
    
      for (reclamation reclamation : reclamations) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

        Label descriptionLabel = new Label("Description : " + reclamation.getDescr());       
        Label nomLabel = new Label("Nom : " + reclamation.getNomr());
        Label emailLabel = new Label("Email : " + reclamation.getEmailr());
        Label adresseLabel = new Label("type : " + reclamation.getType());


       Button buttonSupprimer = new Button("Supprimer");
buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
buttonSupprimer.addActionListener(e -> {
    if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
        if (Servicereclamation.getInstance().deleteAnnoncef(reclamation.getIdr())) {
            container.remove();
            refreshTheme();
        } else {
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de la reclamation", "Ok", null);
        }
    }
});

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierreclamationForm(this, reclamation).show());
Button pdfr=new Button("obtenir fiche pdf");
            pdfr.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_STANDBY, pdfr.getUnselectedStyle()));
           pdfr.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C:/xampp/pdff/reclamation" + reclamation.getIdr() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        
        
    

 DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        String currentDate = dateFormat.format(new Date());



        document.open();
        document.add(new Paragraph("Date : " + currentDate));
        document.add(new Paragraph("Fiche de reclamation"));
        document.add(new Paragraph("Nom :" + reclamation.getNomr()));
        document.add(new Paragraph("Type :" + reclamation.getType()));
        document.add(new Paragraph("Description :" + reclamation.getDescr()));
        document.add(new Paragraph("email :" + reclamation.getEmailr()));
        
        
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
        container.add(adresseLabel);
        container.add(emailLabel);
        container.add(descriptionLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);
                container.add(pdfr);


        add(container);
    }
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddreclamationForm(this).show());
}

}

package com.mycompany.myapp.gui;

import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.entites.Materiel;
import com.mycompany.myapp.services.ServiceMateriel;
import java.util.ArrayList;
import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.itextpdf.text.Image;
//import java.net.URL;
import java.util.Date;

public class ListMaterielForm extends Form {

    private Form previous;

    public ListMaterielForm(Form previous) {
        this.previous = previous;
        setTitle("Liste des matériels");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        ArrayList<Materiel> materiels = ServiceMateriel.getInstance().getAllMateriels();

        for (Materiel materiel : materiels) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

            Label nomLabel = new Label("Nom : " + materiel.getNomm());
            Label marqueLabel = new Label("Marque : " + materiel.getMarque());
            Label prixLabel = new Label("Prix : " + materiel.getPrix());
            Label descriptionLabel = new Label("Description : " + materiel.getDescm());

            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cette materiel ?", "Oui", "Non")) {
                    if (ServiceMateriel.getInstance().deleteMateriel(materiel.getIdm())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'materiel", "Ok", null);
                    }
                }
            });
        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
       buttonModifier.addActionListener(e -> new ModifierMaterielForm(this, materiel).show());

        container.getStyle().setPadding(10, 10, 10, 10);
        container.getStyle().setBorder(Border.createLineBorder(2));
        container.getStyle().setBgTransparency(255);
        container.getStyle().setBgColor(0xffffff);
        container.add(nomLabel);
        container.add(marqueLabel);
        container.add(prixLabel);
        container.add(descriptionLabel);
        container.add(buttonSupprimer);
        container.add(buttonModifier);
//        
// Button pdf = new Button("PDF");
//
//            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, pdf.getUnselectedStyle()));
//            pdf.addActionListener(e -> new ModifierMaterielForm(this, materiel).show());
//
//            pdf.addActionListener(m -> {
//    try {
//        Document document = new Document();
//        String outputPath = "file:///C:/xampp/pdff/marque" + materiel.getIdm() + ".pdf";
//        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));
//
//        document.open();
//
//        document.add(new Paragraph("Fiche de evenement"));
//        document.add(new Paragraph("nom :" + materiel.getNomm()));
//        document.add(new Paragraph("Marque :" + materiel.getMarque()));
//        document.add(new Paragraph("Prix :" + materiel.getPrix()));
//        document.add(new Paragraph("Materiel :" + materiel.getDescm()));
//
//
//        document.close();
//        Dialog.show("Enregistré", "", "", "OK");
//
//        Log.p("PDF file successfully created!");
//    } catch (Exception e) {
//        Log.e(e);
//    }
//});


 Button pdf = new Button("PDF");

            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, pdf.getUnselectedStyle()));

            pdf.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C:/xampp/pdfff/FicheDeMateriel" + materiel.getIdm() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));
        
        

        document.open();

      
 // Ajouter la date locale
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        document.add(new Paragraph("Date : " + formattedDate));
        
        document.add(new Paragraph("Fiche de materiel"));
        document.add(new Paragraph("nom :" + materiel.getNomm()));
        document.add(new Paragraph("Marque :" + materiel.getMarque()));
        document.add(new Paragraph("Prix :" + materiel.getPrix()));
        document.add(new Paragraph("Description :" + materiel.getDescm()));


        document.close();
        Dialog.show("Enregistré", "", "", "OK");

        Log.p("PDF file successfully created!");
    } catch (Exception e) {
        Log.e(e);
    }
});
            
            
            
        add(container);
                add(pdf);

        
    }
        
    
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    getToolbar().addCommandToSideMenu("Home", null, ev -> new AddMaterielForm(this).show());
}
}
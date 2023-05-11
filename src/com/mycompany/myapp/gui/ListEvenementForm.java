package com.mycompany.myapp.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.io.Log.p;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import static com.codename1.ui.TextArea.URL;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.services.ServiceEvenement;
//import java.net.URL;
import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import java.util.Date;

public class ListEvenementForm extends Form {
    private Form previous;

    public ListEvenementForm(Form previous) {
                super("Newsfeed", BoxLayout.y());

            this.previous = previous;

        setTitle("List Evenement");
            setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        setLayout(BoxLayout.y());

        ArrayList<Evenement> evenementList = ServiceEvenement.getInstance().getAllEvenements();

        for (Evenement evt : evenementList) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

            Label titreLabel = new Label("Titre : " + evt.getTitre());
            Label descriptionLabel = new Label("Description : " + evt.getDescription());
            Label nomssLabel = new Label("Nomss : " + evt.getNomss());

            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cette evenement ?", "Oui", "Non")) {
                    if (ServiceEvenement.getInstance().deleteEvenement(evt.getIde())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'evenement", "Ok", null);
                    }
                }
            });

            Button buttonModifier = new Button("Modifier");
            buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
            buttonModifier.addActionListener(e -> new ModifierEvenementForm(this, evt).show());

            container.getStyle().setPadding(10, 10, 10, 10);
            container.getStyle().setBorder(Border.createLineBorder(2));
            container.getStyle().setBgTransparency(255);
            container.getStyle().setBgColor(0xffffff);
            container.add(titreLabel);
            container.add(descriptionLabel);
            container.add(nomssLabel);
            container.add(buttonSupprimer);
            container.add(buttonModifier);
            Button pdf = new Button("PDF");

            pdf.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_COMMENT, pdf.getUnselectedStyle()));

            pdf.addActionListener(m -> {
    try {
        Document document = new Document();
        String outputPath = "file:///C:/xampp/pdfs/evenement" + evt.getIde() + ".pdf";
        PdfWriter.getInstance(document, FileSystemStorage.getInstance().openOutputStream(outputPath));

        
        
        
        document.open();
        
        // Ajouter un cadre de design
        Rectangle rectangle = new Rectangle(10, 10, document.getPageSize().getWidth() - 20, document.getPageSize().getHeight() - 20);
        rectangle.setBorder(Rectangle.BOX);
        rectangle.setBorderWidth(2);
        rectangle.setBorderColor(BaseColor.GRAY);
        document.add(rectangle);
        
        // Ajouter la date locale
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = sdf.format(currentDate);
        document.add(new Paragraph("Date : " + formattedDate));
        
        
        // Modifier le style du paragraphe
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 40, BaseColor.BLACK); // Augmenter la taille de la police
        
        // ...

        // Modifier le style du titre
        Paragraph titleParagraph = new Paragraph("Fiche de evenement", titleFont); // Modifier le texte ici
        document.add(titleParagraph);

            // Ajouter le logo
    /*   String logoPath = "file:///C:/Users/chino/Desktop/CodenameOne/logo.jpg"; // Remplace le chemin par le chemin réel de ton logo
        Image logo = Image.getInstance(new URL(logoPath));
        logo.scaleAbsolute(120,120);
        document.add(logo);*/
        
        

//        document.add(new Paragraph("Fiche de evenement"));

// 
//        // Modifier le style du paragraphe
//        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 40, BaseColor.BLACK); // Augmenter la taille de la police
//        
//        // ...
//
//        // Modifier le style du titre
//        Paragraph titleParagraph = new Paragraph("Fiche de evenement", titleFont); // Modifier le texte ici
//        document.add(titleParagraph);

        document.add(new Paragraph("Titre :" + evt.getTitre()));
        document.add(new Paragraph("Description :" + evt.getDescription()));
        document.add(new Paragraph("Nom de societe :" + evt.getNomss()));

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
        getToolbar().addCommandToSideMenu("Home", null, ev -> new HomeForm().show());
    }
}

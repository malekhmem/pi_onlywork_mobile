package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entites.Annoncef;
import com.mycompany.myapp.services.ServiceAnnoncef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListAnnoncefForm extends Form {

    private Form previous;

    public ListAnnoncefForm(Form previous) {
        this.previous = previous;
        setTitle("Liste des Annonces");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SORT_BY_ALPHA, e -> trierParNom());
TextField searchField = new TextField("", "Rechercher par nom", 20, TextField.ANY);
searchField.addDataChangeListener((type, index) -> {
    String text = searchField.getText();
    if (text.length() == 0) {
        refreshList(ServiceAnnoncef.getInstance().getAllAnnonces());
    } else {
        refreshList(ServiceAnnoncef.getInstance().searchAnnoncesByName(text));
    }
});
getToolbar().addComponentToSideMenu(searchField);

        ArrayList<Annoncef> annonces = ServiceAnnoncef.getInstance().getAllAnnonces();

        for (Annoncef annonce : annonces) {
            Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); // définition du conteneur

            Label nomLabel = new Label("Nom : " + annonce.getNomf());
            Label adresseLabel = new Label("Adresse : " + annonce.getAdresse());
            Label emailLabel = new Label("Email : " + annonce.getEmailf());
            Label descriptionLabel = new Label("Description : " + annonce.getDescf());

            Button buttonSupprimer = new Button("Supprimer");
            buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
            buttonSupprimer.addActionListener(e -> {
                if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
                    if (ServiceAnnoncef.getInstance().deleteAnnoncef(annonce.getIdf())) {
                        container.remove();
                        refreshTheme();
                    } else {
                        Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'annonce", "Ok", null);
                    }
                }
            });

            Button buttonModifier = new Button("Modifier");
            buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
            buttonModifier.addActionListener(e -> new ModifierAnnoncefForm(this, annonce).show());

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

            add(container);

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToSideMenu("Home", null, ev -> new AddAnnoncefForm(this).show());
    }
private void refreshList(ArrayList<Annoncef> annonces) {
    removeAll();
    for (Annoncef annonce : annonces) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); 
        Label nomLabel = new Label("Nom : " + annonce.getNomf());
        Label adresseLabel = new Label("Adresse : " + annonce.getAdresse());
        Label emailLabel = new Label("Email : " + annonce.getEmailf());
        Label descriptionLabel = new Label("Description : " + annonce.getDescf());

        Button buttonSupprimer = new Button("Supprimer");
        buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
        buttonSupprimer.addActionListener(e -> {
            if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
                if (ServiceAnnoncef.getInstance().deleteAnnoncef(annonce.getIdf())) {
                    container.remove();
                    refreshTheme();
                } else {
                    Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'annonce", "Ok", null);
                }
            }
        });

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
        buttonModifier.addActionListener(e -> new ModifierAnnoncefForm(this, annonce).show());

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

        add(container);
    }
    refreshTheme();
}


    private void trierParNom() {
        ArrayList<Annoncef> annonces = ServiceAnnoncef.getInstance().getAllAnnonces();
        Collections.sort(annonces, new Comparator<Annoncef>() {
            @Override
            public int compare(Annoncef annonce1, Annoncef annonce2) {
                return annonce1.getNomf().compareTo(annonce2.getNomf());
}
});
removeAll();
for (Annoncef annonce : annonces) {
Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS)); 
        Label nomLabel = new Label("Nom : " + annonce.getNomf());
        Label adresseLabel = new Label("Adresse : " + annonce.getAdresse());
        Label emailLabel = new Label("Email : " + annonce.getEmailf());
        Label descriptionLabel = new Label("Description : " + annonce.getDescf());

        Button buttonSupprimer = new Button("Supprimer");
        buttonSupprimer.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, buttonSupprimer.getUnselectedStyle()));
        buttonSupprimer.addActionListener(e -> {
            if (Dialog.show("Confirmation", "Voulez-vous supprimer cette annonce ?", "Oui", "Non")) {
                if (ServiceAnnoncef.getInstance().deleteAnnoncef(annonce.getIdf())) {
                    container.remove();
                    refreshTheme();
                } else {
                    Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de l'annonce", "Ok", null);
                }
            }
        });

        Button buttonModifier = new Button("Modifier");
        buttonModifier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, buttonModifier.getUnselectedStyle()));
        buttonModifier.addActionListener(e -> new ModifierAnnoncefForm(this, annonce).show());

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

        add(container);
    }
    refreshTheme();
}
}

/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
import java.util.ArrayList;

public class ProfileForm extends Form {
 
    Form current;
     private Form previous;
    private Resources theme;
    private Button btnAnnuler;
      private Button btnModifier;
     
    public ProfileForm() {
        
          this.previous = previous;
        setTitle("Mon Pofile");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       // super("Newsfeed", BoxLayout.y());
     
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
      
        getContentPane().setScrollVisible(false);

        //  super.addSideMenu(theme);
        tb.addSearchCommand(e -> {
        });

        /*  Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", theme.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", theme.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(theme.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));
         */
        TextField username = new TextField(SessionManager.getUserName());
       
          TextField nom = new TextField(SessionManager.getNom());
        username.setUIID("TextFieldBlack");
        addStringValue("Nom", nom);

        TextField prenom = new TextField(SessionManager.getPrenom());
        username.setUIID("TextFieldBlack");
        addStringValue("Prenom", prenom);


        TextField email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        username.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
         TextField role = new TextField(SessionManager.getRole());
        username.setUIID("TextFieldBlack");
        addStringValue("Role", role);


        //  CheckBox cb1 = CheckBox.createToggle(theme.getImage("on-off-off.png"));
        //  cb1.setUIID("Label");
        //   cb1.setPressedIcon(theme.getImage("on-off-on.png"));
        //    CheckBox cb2 = CheckBox.createToggle(theme.getImage("on-off-off.png"));
        //    cb2.setUIID("Label");
        //    cb2.setPressedIcon(theme.getImage("on-off-on.png"));
        //  addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        //   addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
           btnAnnuler = new Button("Back to list");
        btnAnnuler.addActionListener(e -> new HomeForm().show());
        addAll(btnAnnuler);
          btnModifier = new Button("Modifier");
        Utilisateur utilisateur = new Utilisateur();
        btnModifier.addActionListener(e -> new ModifierProfileForm(this, utilisateur).show());
        addAll(btnModifier);
        
        
    }

    ProfileForm(Form previous) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
      //  add(createLineSeparator(0xeeeeee));
      
    }

   
}

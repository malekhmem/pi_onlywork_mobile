/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;

/**
 *
 * @author bouchrit
 */
public class OwForm extends Form {
    Resources res;
    Form current;

 
    public OwForm(){
        current=this;
           if (MyApplication.theme == null) {
    System.out.println("MyApplication.theme est null");
} else if (MyApplication.theme.getImage("1.jpg") == null) {
    System.out.println("MyApplication.theme.getImage(\"1.jpg\") est null");
} else {
    getUnselectedStyle().setBgImage(MyApplication.theme.getImage("1.jpg"));
}

        setTitle("Mon application");
        setLayout(BoxLayout.y());
       
       
       setTitle("OnlyWork");
       setLayout(BoxLayout.y());
       
       add(new Label("choisir une option"));
       Button login = new Button("SignIn");
      
         login.addActionListener(e-> new SignInForm().show());
        
          addAll(login);

    }

 
   
}

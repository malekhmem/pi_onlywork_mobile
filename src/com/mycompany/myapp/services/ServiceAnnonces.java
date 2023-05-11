/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Annonces;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import java.util.Properties;


/**
 *
 * @author chino
 */
public class ServiceAnnonces {
        TextField emails;

    private static ServiceAnnonces instance;
    private ConnectionRequest req;
    
    private ServiceAnnonces() {
        req = new ConnectionRequest();
    }
    
    public static ServiceAnnonces getInstance() {
        if (instance == null) {
            instance = new ServiceAnnonces();
        }
        return instance;
    }
        
    public boolean addAnnonces(Annonces l) {
    
        String url = Statics.BASE_URL + "/annonces/AddjsonnnAnnonce?noms=" + l.getNoms() + "&emails=" + l.getEmails() + "&numeros=" + l.getNumeros() + "&adresses=" + l.getAdresses();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("ajouté");
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Annonces> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Annonces> annonces = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Annonces a = new Annonces();
            int ids = (int) Float.parseFloat(obj.get("ids").toString());
            a.setIds(ids);
            a.setNoms(obj.get("noms").toString());
            a.setEmails(obj.get("emails").toString());
            int num = (int)Float.parseFloat(obj.get("numeros").toString());

            a.setNumeros(num);

           

            a.setAdresses(obj.get("adresses").toString());

            
            annonces.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(annonces);
    return annonces;
}
public ArrayList<Annonces> getAllAnnonces() {
    String url = Statics.BASE_URL + "/annonces/malek";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parseAnnonces(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deleteAnnonces(int ids) {
    String url = Statics.BASE_URL + "/annonces/deleteannoncesJSON/" + ids;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierAnnonces(Annonces l){
     
String url = Statics.BASE_URL + "/annonces/updateannoncesJSON/" + l.getIds() + "?noms=" + l.getNoms() + "&emails=" + l.getEmails() + "&numeros=" + l.getNumeros() + "&adresses=" + l.getAdresses();
       // req.setUrl(url);
    req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            private boolean resultOK;
            

            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
        return resultOK;
    }

    public String getEmails(String toString, Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*public void sendMail(Resources res) {
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
    public ArrayList<Annonces> searchAnnoncesByName(String name) {
    ArrayList<Annonces> annonces = getAllAnnonces(); // récupérer toutes les annonces depuis le serveur
    ArrayList<Annonces> result = new ArrayList<>();
    for (Annonces annonce : annonces) {
        if (annonce.getNoms().toLowerCase().contains(name.toLowerCase())) {
            result.add(annonce);
        }
    }
    return result;
}
}

package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Annoncef;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceAnnoncef {
    private static ServiceAnnoncef instance;
    private ConnectionRequest req;
    
    private ServiceAnnoncef() {
        req = new ConnectionRequest();
    }
    
    public static ServiceAnnoncef getInstance() {
        if (instance == null) {
            instance = new ServiceAnnoncef();
        }
        return instance;
    }
        
    public boolean addAnnoncef(Annoncef l) {
        String url = Statics.BASE_URL + "/annoncef/Addjson?nomf=" + l.getNomf() + "&adresse=" + l.getAdresse() + "&emailf=" + l.getEmailf() + "&descf=" + l.getDescf();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Annoncef> parseAnnonces(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Annoncef> annonces = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Annoncef a = new Annoncef();
            // int idf = (int) Integer.parseInt(obj.get("idf").toString());
            int idf = (int) Float.parseFloat(obj.get("idf").toString());
            a.setIdf(idf);
            a.setNomf(obj.get("nomf").toString());
            a.setAdresse(obj.get("adresse").toString());
            a.setEmailf(obj.get("emailf").toString());
            a.setDescf(obj.get("descf").toString());
            
            annonces.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(annonces);
    return annonces;
}
public ArrayList<Annoncef> getAllAnnonces() {
    String url = Statics.BASE_URL + "/annoncef/Allannoncef";
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


public boolean deleteAnnoncef(int idf) {
    String url = Statics.BASE_URL + "/annoncef/deleteAnnonceJSON/" + idf;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierAnnoncef(Annoncef l){
     
String url = Statics.BASE_URL + "/annoncef/updateAnnonceJSON/" + l.getIdf() + "?nomf=" + l.getNomf() + "&adresse=" + l.getAdresse() + "&emailf=" + l.getEmailf() + "&descf=" + l.getDescf();
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
}
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Servicereclamation {
    private static Servicereclamation instance;
    private ConnectionRequest req;
    
    private Servicereclamation() {
        req = new ConnectionRequest();
    }
    
    public static Servicereclamation getInstance() {
        if (instance == null) {
            instance = new Servicereclamation();
        }
        return instance;
    }
        
     public boolean addreclamation(reclamation r) {
        String url = Statics.BASE_URL + "/frontreclamation/Addjsonr/new?descr=" + r.getDescr() + "&nomr=" + r.getNomr() + "&emailr=" + r.getEmailr() + "&type=" + r.getType();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<reclamation> parseReclamations(String jsonText) {
      System.out.println("Début parsing");
    ArrayList<reclamation> reclamations = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        
        for (Map<String, Object> obj : list) {
            reclamation r = new reclamation();
            int idr = (int) Float.parseFloat(obj.get("idr").toString());
    r.setIdr(idr);

                r.setDescr(obj.get("descr").toString());
                r.setNomr(obj.get("nomr").toString());
                r.setEmailr(obj.get("emailr").toString());
               r.setType(obj.get("type").toString());
                reclamations.add(r);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(reclamations);
    return reclamations;
    }

    public ArrayList<reclamation> getAllreclamation() {
       String url = Statics.BASE_URL + "/frontreclamation/Allreclamationn";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
  
        return parseReclamations(response); // Correction ici
   
    }


public boolean deleteAnnoncef(int idr) {
    String url = Statics.BASE_URL + "/frontreclamation/deletereclamation/" + idr;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierAnnoncef(reclamation r){
     
        String url = Statics.BASE_URL + "/frontreclamation/updatereclamation/" + r.getIdr() + "?descr=" + r.getDescr() +"&nomr=" + r.getNomr() + "&emailr=" + r.getEmailr() + "&type=" + r.getType();
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
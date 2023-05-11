package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Evenement;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceEvenement {
    private static ServiceEvenement instance;
    private ConnectionRequest req;
    
    private ServiceEvenement() {
        req = new ConnectionRequest();
    }
    
    public static ServiceEvenement getInstance() {
        if (instance == null) {
            instance = new ServiceEvenement();
        }
        return instance;
    }
        
    public boolean addEvenement(Evenement l) {
        String url = Statics.BASE_URL + "/evenement/Addjsonevent?titre=" + l.getTitre() + "&description=" + l.getDescription() + "&nomss=" + l.getNomss() ;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
    public ArrayList<Evenement> parseEvenements(String jsonText) throws ParseException {
        System.out.println("Début parsing");
        ArrayList<Evenement> evenements = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> evenementsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) evenementsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Evenement a = new Evenement();
                int ide = (int) Float.parseFloat(obj.get("ide").toString());
                a.setIde(ide);
                a.setTitre(obj.get("titre").toString());
                a.setDescription(obj.get("description").toString());
                a.setNomss(obj.get("nomss").toString());
                
                evenements.add(a);
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(evenements);
        return evenements;
    }
    
    public ArrayList<Evenement> getAllEvenements() {
        String url = Statics.BASE_URL + "/evenement/AllEvenement";
        System.out.println(url);
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        String response = new String(req.getResponseData());
        try {
            return parseEvenements(response);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean deleteEvenement(int ide) {
        String url = Statics.BASE_URL + "/evenement/deleteEventJSON/" + ide;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueueAndWait(request);
        return request.getResponseCode() == 200;
    }
    
    


    

public boolean modifierEvenement(Evenement l){
     
String url = Statics.BASE_URL + "/evenement/updateEventJSONnn/" + l.getIde() + "?titre=" + l.getTitre() + "&description=" + l.getDescription() + "&nomss=" + l.getNomss() ;
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
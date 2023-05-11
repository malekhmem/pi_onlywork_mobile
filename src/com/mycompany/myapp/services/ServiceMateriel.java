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
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Materiel;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author imtinen
 */
public class ServiceMateriel {
        private static ServiceMateriel instance;
    private ConnectionRequest req;
    
    private ServiceMateriel() {
        req = new ConnectionRequest();
    }
    
    public static ServiceMateriel getInstance() {
        if (instance == null) {
            instance = new ServiceMateriel();
        }
        return instance;
    }
        
    public boolean addMateriel(Materiel l) {
        String url = Statics.BASE_URL + "/materiel/Addjson?nomm=" + l.getNomm() + "&marque=" + l.getMarque() + "&prix=" + l.getPrix() + "&descm=" + l.getDescm();
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Materiel> parseMateriels(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Materiel> materiels = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> MaterielsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) MaterielsListJson.get("root");
        for (Map<String, Object> obj : list) {
            Materiel a = new Materiel();
            // int idm = (int) Integer.parseInt(obj.get("idm").toString());
            int idm = (int) Float.parseFloat(obj.get("idm").toString());
            a.setIdm(idm);
            a.setNomm(obj.get("nomm").toString());
            a.setMarque(obj.get("marque").toString());
            a.setPrix(obj.get("prix").toString());
            a.setDescm(obj.get("descm").toString());
            
            materiels.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(materiels);
    return materiels;
}
public ArrayList<Materiel> getAllMateriels() {
    String url = Statics.BASE_URL + "/materiel/Allmateriel";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parseMateriels(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deleteMateriel(int idm) {
    String url = Statics.BASE_URL + "/materiel/deleteMaterielJSON/" + idm;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierMateriel(Materiel l){
     
String url = Statics.BASE_URL + "/materiel/updateMaterielJSON/" + l.getIdm() + "?nomm=" + l.getNomm() + "&marque=" + l.getMarque() + "&prix=" + l.getPrix() + "&descm=" + l.getDescm();
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

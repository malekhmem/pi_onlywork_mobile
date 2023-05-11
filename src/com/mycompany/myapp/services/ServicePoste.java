package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Poste;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServicePoste {
    private static ServicePoste instance;
    private ConnectionRequest req;
    
    private ServicePoste() {
        req = new ConnectionRequest();
    }
    
    public static ServicePoste getInstance() {
        if (instance == null) {
            instance = new ServicePoste();
        }
        return instance;
    }
        
    public boolean addPoste(Poste l) {
        String url = Statics.BASE_URL + "/poste/AddjsonP?nomp=" + l.getNomp() + "&domaine=" + l.getDomaine() + "&descp=" + l.getDescp() + "&emailp=" + l.getEmailp() ;
        req.setUrl(url);
        req.setPost(false);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return req.getResponseCode() == 200;
    }
 
public ArrayList<Poste> parsePostes(String jsonText) throws ParseException {
    System.out.println("Début parsing");
    ArrayList<Poste> postes = new ArrayList<>();
    try {
        JSONParser j = new JSONParser();
        Map<String, Object> annoncesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Poste a = new Poste();
          
            int idp= (int) Float.parseFloat(obj.get("idp").toString());
            a.setIdp(idp);
            a.setNomp(obj.get("nomp").toString());
            a.setDomaine(obj.get("domaine").toString());
            a.setEmailp(obj.get("emailp").toString());
            a.setDescp(obj.get("descp").toString());
            
            postes.add(a);
        }
        
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println(postes);
    return postes;
}
public ArrayList<Poste> getAllPostes() {
    String url = Statics.BASE_URL + "/poste/teste";
    System.out.println(url);
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);
    NetworkManager.getInstance().addToQueueAndWait(req);
    String response = new String(req.getResponseData());
    try {
        return parsePostes(response); // Correction ici
    } catch (ParseException ex) {
        System.out.println(ex.getMessage());
        return new ArrayList<>();
    }
}


public boolean deletePoste(int idp) {
    String url = Statics.BASE_URL + "/poste/deletePosteJSON/" + idp;
    ConnectionRequest request = new ConnectionRequest();
    request.setUrl(url);
    request.setHttpMethod("DELETE");
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200;
}
public boolean modifierPoste(Poste l){
     
String url = Statics.BASE_URL + "/poste/updatePosteJSON/" + l.getIdp() + "?nomp=" + l.getNomp() + "&domaine=" + l.getDomaine() + "&descp=" + l.getDescp() + "&emailp=" + l.getEmailp();
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
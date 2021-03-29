/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Vostro Placas
 */
public class CredencialesVO {
    private String ID; 
    private String apikey;
    private String idsesion;
    private String token;
    private String idUsuarios_Movil;

    public CredencialesVO(String ID, String apikey, String idsesion, String token, String idUsuarios_Movil) {
        this.ID = ID;
        this.apikey = apikey;
        this.idsesion = idsesion;
        this.token = token;
        this.idUsuarios_Movil = idUsuarios_Movil;
    }

    public CredencialesVO(String idsesion, String token, String idUsuarios_Movil, String apikey) {
        this.idsesion = idsesion;
        this.token = token;
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.apikey = apikey;
    }

    public CredencialesVO(String apikey,String idsesion, String token) {
        this.idsesion = idsesion;
        this.token = token;
        this.apikey = apikey;
    }

    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

   

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
    

    public String getIdsesion() {
        return idsesion;
    }

    public void setIdsesion(String idsesion) {
        this.idsesion = idsesion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdUsuarios_Movil() {
        return idUsuarios_Movil;
    }

    public void setIdUsuarios_Movil(String idUsuarios_Movil) {
        this.idUsuarios_Movil = idUsuarios_Movil;
    }
    
    
    
}

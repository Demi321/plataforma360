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
public class ProyectoVO {

    private String id;
    private String apikey;
    private String heroku;
    private String FireBaseAuthorization;

    public ProyectoVO(String id, String apikey, String heroku, String FireBaseAuthorization) {
        this.id = id;
        this.apikey = apikey;
        this.heroku = heroku;
        this.FireBaseAuthorization = FireBaseAuthorization;
    }

    public ProyectoVO(String id, String apikey, String heroku) {
        this.id = id;
        this.apikey = apikey;
        this.heroku = heroku;
    }

    public String getFireBaseAuthorization() {
        return FireBaseAuthorization;
    }

    public void setFireBaseAuthorization(String FireBaseAuthorization) {
        this.FireBaseAuthorization = FireBaseAuthorization;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getHeroku() {
        return heroku;
    }

    public void setHeroku(String heroku) {
        this.heroku = heroku;
    }
    

}

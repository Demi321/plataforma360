/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Moises Juarez
 */
public class RegistroRutaVO {
    private String id;
    private String cp;
    private String idUsuarios_Movil;
    private String fecha;
    private String hora;
    private String latitud;
    private String longitud;
    private String ruta;
    private String rango;
    private String estadoNot;

    public RegistroRutaVO(String idUsuarios_Movil) {
        this.idUsuarios_Movil = idUsuarios_Movil;
    }

    
    public RegistroRutaVO(String idUsuarios_Movil, String fecha, String ruta) {
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.fecha = fecha;
        this.ruta = ruta;
    }

    public RegistroRutaVO(String idUsuarios_Movil, String fecha, String hora, String latitud, String longitud) {
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.fecha = fecha;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public RegistroRutaVO(String id, String idUsuarios_Movil, String fecha, String hora, String latitud, String longitud) {
        this.id = id;
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.fecha = fecha;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public RegistroRutaVO(String id, String cp, String idUsuarios_Movil, String fecha, String hora, String latitud, String longitud, String ruta, String estadoNot) {
        this.id = id;
        this.cp = cp;
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.fecha = fecha;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ruta = ruta;
        this.estadoNot = estadoNot;
    }
    
    
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    
    public String getEstadoNot() {
        return estadoNot;
    }

    public void setEstadoNot(String estadoNot) {
        this.estadoNot = estadoNot;
    }

    
    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public String getIdUsuarios_Movil() {
        return idUsuarios_Movil;
    }

    public void setIdUsuarios_Movil(String idUsuarios_Movil) {
        this.idUsuarios_Movil = idUsuarios_Movil;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Vostro Placas
 */
public class RegistroLlamadasVO implements Serializable {

    private String idRegistro_Llamadas;
    private String fecha;
    private String hora;
    private String latitud;
    private String longitud;
    private String altitud;
    private String ruta_generada;
    private String ruta_video;
    private String ruta_chat;
    private String reporte_llamada;
    private String idTipo_Emergencia;
    private String idModo_Llamada;
    private String idModo_Llamada_Finalizada;
    private String idUsuario_Sys;
    private String idUsuarios_Movil;
    private String idPrioridad_Llamada;
    private String chat;
    private String direccion;
    private String Folio;
    private String folioIncidentes;

//    public RegistroLlamadasVO(String idRegistro_Llamadas, String fecha, String hora, String idModo_Llamada, String idUsuarios_Movil) {
//        this.idRegistro_Llamadas = idRegistro_Llamadas;
//        this.fecha = fecha;
//        this.hora = hora;
//        this.idModo_Llamada = idModo_Llamada;
//        this.idModo_Llamada_Finalizada = idModo_Llamada_Finalizada;
//        this.idUsuarios_Movil = idUsuarios_Movil;
//    }

    public RegistroLlamadasVO(String idRegistro_Llamadas, String fecha, String hora, String latitud, String longitud, String idModo_Llamada, String idUsuarios_Movil) {
        this.idRegistro_Llamadas = idRegistro_Llamadas;
        this.fecha = fecha;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idModo_Llamada = idModo_Llamada;
        this.idUsuarios_Movil = idUsuarios_Movil;
    }

    public RegistroLlamadasVO(String idRegistro_Llamadas, String fecha, String hora, String latitud, String longitud, String altitud, String ruta_generada, String ruta_video, String ruta_chat, String reporte_llamada, String idTipo_Emergencia, String idModo_Llamada, String idModo_Llamada_Finalizada, String idUsuario_Sys, String idUsuarios_Movil, String idPrioridad_Llamada) {
        this.idRegistro_Llamadas = idRegistro_Llamadas;
        this.fecha = fecha;
        this.hora = hora;
        this.latitud = latitud;
        this.longitud = longitud;
        this.altitud = altitud;
        this.ruta_generada = ruta_generada;
        this.ruta_video = ruta_video;
        this.ruta_chat = ruta_chat;
        this.reporte_llamada = reporte_llamada;

        this.idTipo_Emergencia = idTipo_Emergencia;
        this.idModo_Llamada = idModo_Llamada;
        this.idModo_Llamada_Finalizada = idModo_Llamada_Finalizada;
        this.idUsuario_Sys = idUsuario_Sys;
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.idPrioridad_Llamada = idPrioridad_Llamada;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public String getFolioIncidentes() {
        return folioIncidentes;
    }

    public void setFolioIncidentes(String folioIncidentes) {
        this.folioIncidentes = folioIncidentes;
    }
    
    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getIdRegistro_Llamadas() {
        return idRegistro_Llamadas;
    }

    public void setIdRegistro_Llamadas(String idRegistro_Llamadas) {
        this.idRegistro_Llamadas = idRegistro_Llamadas;
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

    public String getAltitud() {
        return altitud;
    }

    public void setAltitud(String altitud) {
        this.altitud = altitud;
    }

    public String getRuta_generada() {
        return ruta_generada;
    }

    public void setRuta_generada(String ruta_generada) {
        this.ruta_generada = ruta_generada;
    }

    public String getRuta_video() {
        return ruta_video;
    }

    public void setRuta_video(String ruta_video) {
        this.ruta_video = ruta_video;
    }

    public String getRuta_chat() {
        return ruta_chat;
    }

    public void setRuta_chat(String ruta_chat) {
        this.ruta_chat = ruta_chat;
    }

    public String getReporte_llamada() {
        return reporte_llamada;
    }

    public void setReporte_llamada(String reporte_llamada) {
        this.reporte_llamada = reporte_llamada;
    }

    public String getIdTipo_Emergencia() {
        return idTipo_Emergencia;
    }

    public void setIdTipo_Emergencia(String idTipo_Emergencia) {
        this.idTipo_Emergencia = idTipo_Emergencia;
    }

    public String getIdModo_Llamada() {
        return idModo_Llamada;
    }

    public void setIdModo_Llamada(String idModo_Llamada) {
        this.idModo_Llamada = idModo_Llamada;
    }
    
    public String getIdModo_Llamada_Finalizada() {
        return idModo_Llamada_Finalizada;
    }

    public void setIdModo_Llamada_Finalizada(String idModo_Llamada_Finalizada) {
        this.idModo_Llamada_Finalizada = idModo_Llamada_Finalizada;
    }

    public String getIdUsuario_Sys() {
        return idUsuario_Sys;
    }

    public void setIdUsuario_Sys(String idUsuario_Sys) {
        this.idUsuario_Sys = idUsuario_Sys;
    }

    public String getIdUsuarios_Movil() {
        return idUsuarios_Movil;
    }

    public void setIdUsuarios_Movil(String idUsuarios_Movil) {
        this.idUsuarios_Movil = idUsuarios_Movil;
    }

    public String getIdPrioridad_Llamada() {
        return idPrioridad_Llamada;
    }

    public void setIdPrioridad_Llamada(String idPrioridad_Llamada) {
        this.idPrioridad_Llamada = idPrioridad_Llamada;
    }

}

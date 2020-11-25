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
public class UsuarioSysVO implements Serializable{

    private String idUsuario_Sys;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;
    private String tipo;
    private String estatus;
    private String sesion;
    private String disponibilidad;

    public UsuarioSysVO(String idUsuario_Sys, String nombre, String apellido, String correo, String contrasenia, String tipo, String estatus, String sesion, String disponibilidad) {
        this.idUsuario_Sys = idUsuario_Sys;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
        this.estatus = estatus;
        this.sesion = sesion;
        this.disponibilidad = disponibilidad;
    }

    UsuarioSysVO(String idUsuario_Sys) {
        this.idUsuario_Sys = idUsuario_Sys;
        this.nombre = null;
        this.apellido = null;
        this.correo = null;
        this.contrasenia = null;
        this.tipo = null;
        this.estatus = null;
        this.sesion = null;
        this.disponibilidad = null;
        
    }
    
    

    public String getIdUsuario_Sys() {
        return idUsuario_Sys;
    }

    public void setIdUsuario_Sys(String idUsuario_Sys) {
        this.idUsuario_Sys = idUsuario_Sys;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
     public String toJSON(){
        return "{"
                + "\"idUsuario_Sys\":\""+this.idUsuario_Sys+"\","
                + "\"nombre\":\""+this.nombre+"\","
                + "\"apellido\":\""+this.apellido+"\","
                + "\"tipo\":\""+this.tipo+"\""
                + "}";
    }
}

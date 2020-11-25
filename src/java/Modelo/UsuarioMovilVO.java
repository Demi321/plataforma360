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
public class UsuarioMovilVO implements Serializable {

    private String idUsuarios_Movil;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String fecha_nacimiento;
    private String correo;
    private String telefono;
    private String genero;
    private String rh;
    private String alergias;
    private String condicion_medica;
    private String direccion;
    private String cp;
    private String contacto_nombre;
    private String contacto_telefono;
    private String Img;
    private String modo;
    private String latitud;
    private String longitud;
    private String FireBaseKey;
    private String idStream;
    private String icon;

    public UsuarioMovilVO(String idUsuarios_Movil, String nombre, String apellido_paterno, String apellido_materno, String fecha_nacimiento, String correo, String telefono, String genero, String rh, String alergias, String condicion_medica, String direccion, String cp, String contacto_nombre, String contacto_telefono, String URLImg, String icon) {
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.rh = rh;
        this.alergias = alergias;
        this.condicion_medica = condicion_medica;
        this.direccion = direccion;
        this.cp = cp;
        this.contacto_nombre = contacto_nombre;
        this.contacto_telefono = contacto_telefono;
        this.Img = URLImg;
        this.icon=icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    

    public String getIdStream() {
        return idStream;
    }

    public void setIdStream(String idStream) {
        this.idStream = idStream;
    }

    
    public UsuarioMovilVO(String idUsuarios_Movil, String FireBaseKey) {
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.FireBaseKey = FireBaseKey;
    }

    public UsuarioMovilVO(String idUsuarios_Movil, String latitud, String longitud) {
        this.idUsuarios_Movil = idUsuarios_Movil;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getFireBaseKey() {
        return FireBaseKey;
    }

    public void setFireBaseKey(String FireBaseKey) {
        this.FireBaseKey = FireBaseKey;
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

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getIdUsuarios_Movil() {
        return idUsuarios_Movil;
    }

    public void setIdUsuarios_Movil(String idUsuarios_Movil) {
        this.idUsuarios_Movil = idUsuarios_Movil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getCondicion_medica() {
        return condicion_medica;
    }

    public void setCondicion_medica(String condicion_medica) {
        this.condicion_medica = condicion_medica;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getContacto_nombre() {
        return contacto_nombre;
    }

    public void setContacto_nombre(String contacto_nombre) {
        this.contacto_nombre = contacto_nombre;
    }

    public String getContacto_telefono() {
        return contacto_telefono;
    }

    public void setContacto_telefono(String contacto_telefono) {
        this.contacto_telefono = contacto_telefono;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

}

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
public class ReporteVO {
    private String idRporte;
    private String idLlamada;
    private String tipoLugar;
    private String numPiso;
    private String descripcionLugar;
    private String temergencia;
    private int cp;
    private float superior;
    private float inferior;
    private float derecha;
    private float izquierda;
    private float altura;
    private String reporte;
    private String razonamiento;
    private String folioexterno;
    

    public ReporteVO(String tipoLugar, String numPiso, String descripcionLugar, float superior, float inferior, float derecha, float izquierda, float altura) {
        this.tipoLugar = tipoLugar;
        this.numPiso = numPiso;
        this.descripcionLugar = descripcionLugar;
        this.superior = superior;
        this.inferior = inferior;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.altura = altura;
    }

    public ReporteVO() {
    }

    public ReporteVO(String idLlamada, String tipoLugar, String numPiso, String descripcionLugar, float superior, float inferior, float derecha, float izquierda, float altura, String reporte) {
        this.idLlamada = idLlamada;
        this.tipoLugar = tipoLugar;
        this.numPiso = numPiso;
        this.descripcionLugar = descripcionLugar;
        this.superior = superior;
        this.inferior = inferior;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.altura = altura;
        this.reporte = reporte;
    }

    public String getRazonamiento() {
        return razonamiento;
    }

    public void setRazonamiento(String razonamiento) {
        this.razonamiento = razonamiento;
    }

    public String getFolioexterno() {
        return folioexterno;
    }

    public void setFolioexterno(String folioexterno) {
        this.folioexterno = folioexterno;
    }


    
    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getTemergencia() {
        return temergencia;
    }

    public void setTemergencia(String temergencia) {
        this.temergencia = temergencia;
    }

    
    public String getIdRporte() {
        return idRporte;
    }

    public void setIdRporte(String idRporte) {
        this.idRporte = idRporte;
    }

    public String getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(String idLlamada) {
        this.idLlamada = idLlamada;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public String getNumPiso() {
        return numPiso;
    }

    public void setNumPiso(String numPiso) {
        this.numPiso = numPiso;
    }

    public String getDescripcionLugar() {
        return descripcionLugar;
    }

    public void setDescripcionLugar(String descripcionLugar) {
        this.descripcionLugar = descripcionLugar;
    }

    public float getSuperior() {
        return superior;
    }

    public void setSuperior(float superior) {
        this.superior = superior;
    }

    public float getInferior() {
        return inferior;
    }

    public void setInferior(float inferior) {
        this.inferior = inferior;
    }

    public float getDerecha() {
        return derecha;
    }

    public void setDerecha(float derecha) {
        this.derecha = derecha;
    }

    public float getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(float izquierda) {
        this.izquierda = izquierda;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String getReporte() {
        return reporte;
    }

    public void setReporte(String reporte) {
        this.reporte = reporte;
    }

   
}

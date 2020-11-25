<%-- 
    Document   : plantilla
    Created on : 02 Ene 2020, 16:25:53
    Author     : Moises Juárez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>    
    <link href="${pathRecursos}/css/operador.css" rel="stylesheet" />
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div id="divTamBackup" style="display: none;">
            <div id="test"></div>
            <label  id="TamBackup"></label>
        </div>
        <div class="row col-12 m-0 p-2" id="toggle">Reportes</div>
        <div id="sidebar">
            <div class="h-100 col-12 m-0 p-0"  id="grupos" style="overflow-y: scroll;">
                <input type="hidden" name="IdAdministrador" id="IdAdministrador" value="" >
                <span id="span" style="color: lightblue; display: block; font-size: 12px; "  >${Alerta}</span>
                <%-- Aqui se iran insertando los botones de los reportes --%>
            </div>

        </div>
    </aside>
    <section>
        <div class="input-group" id="typeUser"><!--  col-12 m-0 p-0 pl-1 pr-1 -->
            <label class="label">Tipo de Usuario:</label>
        </div>
        
        <label>nombre</label>
        <input type="text" id="nombre">
        <label>nivel de atencion</label>
        <input type="text" id="nivel">
        <label>direccion</label>
        <input type="text" id="direccion">
        <input type="button" value="buscar" id="buscarDir">
        <label>telefono</label>
        <input type="text" id="telefono">
        <div id="map" class="w-50 h-50">
            
        </div>
        <input type="text" id="lat">
        <input type="text" id="lng">
        
        <input type="button" value="Agregar" id="agregarInstitucion">
        
        <input type="text" id="telefono1">
        <input type="text" id="telefono1_porcentaje">
        <input type="text" id="telefono2">
        <input type="text" id="telefono2_porcentaje">
        
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead_registro.jsp" %>

    <script src="${pathRecursos}/js/SuperAdmin/agregaServicio.js" ></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap">
    </script>

</body>


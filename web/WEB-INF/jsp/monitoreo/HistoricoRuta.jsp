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
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Registro de Rutas</div>
        <div id="sidebar">

            <div class="separadorAzul">INFORMACIÓN GENERAL</div>
            <%@include file="../plantilla/Perfil.jsp" %>


            <div class="separadorAzul">Historial de Ruta</div>
            <div class="row card-body2 col-12 m-0 p-0">


                <div class="row col-12 p-0 m-0" style="background: white; border-radius: 5px; width: 100%; min-width: 160px;">
                    <input  type="text"  id="dateformated"  style="
                            display: none;
                            width: 100%; 
                            height: 30px; 
                            border: none; 
                            border-radius: 3px; 
                            margin-left:  10px;
                            background: none;" disabled="true">
                    <button id="button__api-open-close"  class="pull-right" style="
                            display: none;
                            background: url(${calendarPNG});
                            background-repeat: no-repeat;
                            background-position:center;
                            background-size: cover;
                            -moz-background-size: cover;
                            -webkit-background-size: cover;
                            -o-background-size: cover;
                            width: 25px;
                            height: 25px;
                            border: none;
                            vertical-align: middle;
                            position: sticky;
                            left:100%;
                            cursor:pointer;"></button>
                </div>

                <input type="hidden" id="AllUsrs" value="" >
                <input type="hidden" name="NameAdministrador" id="NameAdministrador" value="" style="width: 90%; text-align: center; margin-bottom: 15px; font-family:monospace " readonly>
                <input type="hidden" name="IdAdministrador" id="IdAdministrador" value="" >
                <input type="hidden" name="icon" id="icon" value="" >
                <input type="hidden" name="idUsuario_Movil" id="idUsuario_Movil" value="${idUsuario_Movil}" >
                <br>

                <span id="span" style="color: lightblue; display: block; font-size: 12px; "  >${Alerta}</span>


            </div>

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-0" id="contenidoSection">
            <div class="row" id="scroll-window" style="width: 96.5%; margin: 0; display: none;">
                    <div class="col-12" style="    padding: 0;">
                        <div class="col-12" style="  background-color: white; border-radius: 5px; height: 35px; margin-bottom: 5px;">
                            <h2 id="fecha" style="color: #000000;
                                font: 16px arial;
                                height: 100%;
                                width: 100%;
                                display: flex;
                                align-items: center;
                                margin-left: 20px;"></h2>
                        </div>
                    </div>
                </div>
                <!-- *************** MAPA **************** -->
                <div class="col-12 m-0 p-0 h-100" id="map" style="position: relative;">

                </div>   

        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/Empresa/appRuta.js" ></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap2&callback=initMap">
    </script>
</body>
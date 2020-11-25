<%-- 
    Document   : plantilla
    Created on : 31 Dic 19, 16:25:53
    Author     : Moises Juárez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>    
    <spring:url value="${pathRecursos}/css/administrador.css" var="AdCSS" />
    <link href="${AdCSS}" rel="stylesheet" />
</head>

<body>

    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Llamadas</div>
        <div id="sidebar">
            <div id="HistoricoLlamadas" style="overflow-y: hidden;">
                <!--espacio para los historicos -->
            </div>
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div class="card-header text-center" style=" background-color:#ff8200; display: none;"><strong style="font: 16px Arial; color: white; font-weight: bolder;">Llamadas Recibidas</strong></div>
            <div class="row col-12 p-0 " id="rejillamada">
                <div id="card" style="display: none;"></div>

                <%-- Aqui se iran insertando las tarjetas correspondientes a las llamadas --%>

            </div>
        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/sos/Despacho/appAd.js"></script>

</body>





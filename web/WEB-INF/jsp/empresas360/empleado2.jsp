<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
    Author     : Moises Juárez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <spring:url value="${pathRecursos}/css/operador.css" var="operadorCSS" />
    <link href="${operadorCSS}" rel="stylesheet" />
    <spring:url value="${pathRecursos}/css/Empresa/home_empleado.css" var="home_empleadoCSS" />
    <%-- Javascript Personalizados --%>    
    <spring:url value="${pathRecursos}/js/Empresa/home_empleado.js" var="home_empleadoJS" />
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">
            <div><i class="fas fa-ellipsis-v"></i></div><span>Plataforma 360</span></div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    
    <section>
        <div class="h-100 row col-12 m-0 p-0" id="contenidoSection">

        </div>
        <%@include file="../plantilla/callhead.jsp" %>
        <script src="${sdk_awsJS}" ></script>


        <link href="${home_empleadoCSS}" rel="stylesheet" />
        <script src="${home_empleadoJS}" ></script>
    </section>
    
    <%@include file="../empresas360/modulo_perfil.jsp" %>
    <%@include file="../empresas360/modulo_reporte.jsp" %>
    <%@include file="../empresas360/modulo_reporte_ss.jsp" %>
    <%@include file="../empresas360/modulo_entrada_salida.jsp" %>
    <%@include file="../empresas360/modulo_comunicacion.jsp" %>
    <%--<%@include file="../empresas360/modulo_videowall.jsp" %>--%>


    <div class="d-none" id="guardando_logo">
        <div class="mensaje_guardando_logo">Guardando Logo ...</div>
    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMaps&callback=initMaps&libraries=places&v=weekly">
    </script>

</body>
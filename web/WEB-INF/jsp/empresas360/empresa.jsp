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
    <spring:url value="${pathRecursos}/css/Empresa/home_empresa.css" var="home_empresaCSS" />

    <%-- Javascript Personalizados --%>    
    <spring:url value="${pathRecursos}/js/Empresa/home_empresas.js" var="home_empresaJS" />
    <spring:url value="${pathRecursos}/js/Empresa/functions_empresas.js" var="functions_empresasJS" />

</head>

<body>
    
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>

    <div id="video_drag" class="d-none" style="position: fixed; background-color: rgb(241, 241, 241); border: 2px solid rgb(211, 211, 211); text-align: center; top: 75px; left:calc(100% - 200px) ; z-index: 1000;">
        <!-- Include a header DIV with the same name as the draggable DIV, followed by "header" -->
        <div id="video_drag_header" style="padding: 10px; cursor: move; z-index: 10; background-color: #495057; color: #fff;"><h7 class="nombre_completo"></h7></div>
        <div id="conectado_jornada_laboral" style="min-height: 150px; min-width: 150px; width: 100%; height: 100%; overflow: hidden;" >
        </div>
    </div>

    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">
            <div><i class="fas fa-ellipsis-v"></i></div><span>Plataforma 360</span></div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${sdk_awsJS}" ></script>
    <script src="${functions_empresasJS}" ></script>


    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">

        </div>
        
    
        <link href="${home_empresaCSS}" rel="stylesheet" />
        <script src="${home_empresaJS}" ></script>
    
        <%--<%@include file="../empresas360/modulos_empresa/modulo_perfil.jsp" %>--%>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_conmutador.jsp" %>--%>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_reporte.jsp" %>--%>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_seguridad_sanitaria.jsp" %>--%>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_registrar_activar.jsp" %>--%>
        <%@include file="../empresas360/modulos_empresa/modulo_mi_empresa.jsp" %>
        <%@include file="../empresas360/modulos_empresa/modulo_registrar_sucursal.jsp" %>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_registrar_mi_sucursal.jsp" %>--%>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_registrar_mi_institucion.jsp" %>--%>
        <%@include file="../empresas360/modulos_empresa/modulo_mis_sucursales.jsp" %>
        <%@include file="../empresas360/modulos_empresa/modulo_areas_de_trabajo.jsp" %>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_institucion_academica.jsp" %>--%>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_ajustes_privacidad.jsp" %>--%>
        <%@include file="../empresas360/modulos_empresa/modulo_plantillas_laborales.jsp" %>
        <%@include file="../empresas360/modulos_empresa/modulo_jornadas_laborales.jsp" %>
        <%--<%@include file="../empresas360/modulos_empresa/modulo_monitoreo_personal.jsp" %>--%>
        <%@include file="../empresas360/modulo_comunicacion.jsp" %>
        <%@include file="../empresas360/modulo_videowall.jsp" %>

    </section>


    <div class="d-none" id="guardando_logo">
        <div class="mensaje_guardando_logo">Guardando Logo ...</div>
    </div>

    <%@include file="../plantilla/footer.jsp" %>
    
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMaps&callback=initMaps&libraries=places&v=weekly">
    </script>

</body>
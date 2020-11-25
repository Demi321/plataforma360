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
    <%-- Javascript Personalizados --%>    
    
</head>

<body>
    <section id="page" style="overflow: hidden">
        <%@include file="../plantilla/header.jsp" %>
        <nav3 id="sidebar">
            <%@include file="../plantilla/toggle.jsp" %>
            <div class="contenidomenus"  >
                <h4 class="mx-auto mt-0 mb-2 title_sidebar">Título</h4>

                <!--ESPACIO SIDEBAR IZQ-->
                <!--ESPACIO SIDEBAR IZQ-->
                <!--ESPACIO SIDEBAR IZQ-->
                <!--ESPACIO SIDEBAR IZQ-->

            </div>
        </nav3>
        <main>
            <%@include file="../plantilla/modal_menu.jsp" %>
            <div class="align-items-center" id="contenidor" style="overflow-x: hidden;">
                
                ${url}
                ${nombreP}
                ${nombreM}
                ${mensaje}
                <!-- ESPACIO PRINCIPAL -->
                <!-- ESPACIO PRINCIPAL -->
                <!-- ESPACIO PRINCIPAL -->
                <!-- ESPACIO PRINCIPAL -->
            </div>


        </main>
            <%@include file="../plantilla/footer.jsp" %>
    </section>

            <%@include file="../plantilla/callhead.jsp" %>
    
</body>
<%-- 
    Document   : preloading
    Created on : 25 jul 2019, 9:26:27
    Author     : moises
--%>



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
    <link href="${pathRecursos}/css/preloading.css" rel="stylesheet" />
    <title>Verificación de IP</title>
</head>

<body>

    <section id="page" style="overflow: hidden">
        <%@include file="../plantilla/header.jsp" %>
        <nav3 id="sidebar" style="display: none;">
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

            <div class="align-items-center" id="contenidor" style="overflow-x: hidden; position: absolute;width: 100%;left: 0;margin: 0; padding: 0;height: fit-content;max-height: calc( 100% - 60px);">
                <div class="container">

                    <svg class="loader" viewBox="0 0 340 340">
                    <circle class="animatedCircle" cx="170" cy="170" r="160" stroke="#AEAEAE"/>
                    <circle class="animatedCircle" cx="170" cy="170" r="135" stroke="#006400"/>
                    <circle class="animatedCircle" cx="170" cy="170" r="110" stroke="#AEAEAE"/>
                    <circle class="animatedCircle" cx="170" cy="170" r="85" stroke="#2E8B57"/>
                    </svg>

                </div>


                <p id="ip_publica" style="display: none;">${ip_publica}</p>
                <input id="vista" type="hidden" value="${vista}">
                <div class="title">
                    <p id="title"></p>
                </div>
            </div>


        </main>
        <%@include file="../plantilla/footer.jsp" %>
    </section>
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/preloading/preloading.js" ></script>
</body>




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
    <spring:url value="${pathRecursos}/plantilla/empresa.css" var="homeCSS" />
    <link href="${homeCSS}" rel="stylesheet" />
    <%-- Javascript Personalizados --%>    
    <spring:url value="${pathRecursos}/plantilla/empresa.js" var="homeJS" />
    <spring:url value="${pathRecursos}/plantilla/empresa2.js" var="home2JS" />
    <script 
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&libraries=places&v=weekly">
    </script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<!-- Include a header DIV with the same name as the draggable DIV, followed by "header" -->
<!--<div id="video_drag_header" style="padding: 10px; cursor: move; z-index: 10; background-color: #495057; color: #fff;"><h7 class="nombre_completo"></h7></div>-->
<!--<div id="conectado_jornada_laboral" style="min-height: 150px; min-width: 150px; width: 100%; height: 100%; overflow: hidden;" >-->
</div>
</div>
<body>

    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <div class="Paneblock d-flex align-items-center justify-content-center" id="blockpage"></div>
    <script>
            //colocar lottie de carga de pagina 
            console.log("colocar lottie de carga de pagina");
            var lottieLoader_blockpage = document.createElement("div");
            lottieLoader_blockpage.style = "width: 100px;height: 100px;";
            lottieLoader_blockpage.id = "lottie_blockpage";
            document.getElementById("blockpage").appendChild(lottieLoader_blockpage);

            var lottieAnimation_blockpage = bodymovin.loadAnimation({
                container: lottieLoader_blockpage, // ID del div
                path: "https://empresas.claro360.com/p360_v4_dev_moises/json/Rayas rojo.json", // Ruta fichero .json de la animación
                renderer: 'svg', // Requerido
                loop: true, // Opcional
                autoplay: true, // Opcional
//                name: "Hello World" // Opcional
            });
            console.log(document.getElementById("blockpage"));
            console.log("termino colocar lottie de carga de pagina");

    </script>
    <aside>
        <div class="" id="toggle">
            <div><i class="fas fa-ellipsis-v"></i></div><span>Plataforma 360</span></div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <%@include file="../plantilla/callhead.jsp" %>
    <%--<%@include file="../plantilla/callhead_sinlogin.jsp" %>--%>

    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
        </div>

        <script src="${homeJS}" ></script>
        
    </section>
    <script>

            var perfil_usuario = ${perfil_usuario}
            var empresa_usuario = ${empresa_usuario}
            var sucursales_usuario = ${sucursales_usuario}
            var directorio_usuario = ${directorio_usuario}
            var jornada_usuario = ${jornada_usuario}
            var reportes_usuario = ${reportes_usuario}
            var BucketName = "lineamientos";
            var bucketRegion = "us-east-1";
            var IdentityPoolId = "us-east-1:a8460f87-8d3f-4452-935a-b95a4fcc83ed";
            AWS.config.update({
                region: bucketRegion,
                credentials: new AWS.CognitoIdentityCredentials({
                    IdentityPoolId: IdentityPoolId
                })
            });
            var s3 = new AWS.S3({
                apiVersion: "2006-03-01",
                params: {Bucket: BucketName}
            });
            console.log(directorio_usuario);
    </script>
    ${modulos}
    <div class="d-none" id="guardando_logo">
        <div class="mensaje_guardando_logo">Guardando Logo ...</div>
    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <script src="${home2JS}" ></script>
    <script>
    </script>
</body>
<%-- 
    Document   : pruebas_aws
    Created on : 2/06/2020, 01:24:13 PM
    Author     : web
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
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <!--            <form id="upload" method='post' enctype="multipart/form-data">
                            <input type="file" capture="true" class="w-100" id="upFile">
                            <input type="submit" class="btn btn-secondary w-50" value="Subir al bucket" style="height: 3%;">
                        </form>-->
            <form id="upload" method='post' enctype="multipart/form-data">
                <input type="file" id="upFile" name="files[]" multiple />
                <output id="list"></output>
                <input type="submit" class="btn btn-secondary w-50" value="Subir al bucket" style="height: 3%;">
            </form>
        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/aws/aws-sdk-2.685.0.min.js"></script>
    <script src="${pathRecursos}/js/aws/aws_config.js"></script>
    <script src="${pathRecursos}/js/aws/pruebas_aws.js"></script>



</body>

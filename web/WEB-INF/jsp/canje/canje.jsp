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
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Plataforma 360</div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">

        </div>
</div>


</section>

<%@include file="../plantilla/footer.jsp" %>
<%@include file="../plantilla/callhead.jsp" %>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMaps&callback=initMaps&libraries=places&v=weekly">
</script>

<style>
</style>
<script>
</script>

</body>
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
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">
            <%@include file="../plantilla/menu_alumno.jsp" %>
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <%@include file="../escuela_alumno/perfil.jsp" %>
        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead_login.jsp" %>
    <script>
        console.log("bingo");
        RequestGET("/API/registro_institucion").then((response)=>{
            console.log(response); 
        });
        RequestPOST("/API/registro_institucion",{
            nombre:"Alejandro",
            id:5
        }).then((response)=>{
            console.log(response);
        });
    </script>

</body>
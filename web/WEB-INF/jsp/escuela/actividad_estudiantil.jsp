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
        <div class="row col-12 m-0 p-0" id="toggle">Alumno</div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">

        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
    </script>
    <style>
        
        .menu_sidebar{
            width: 100%;
            height: 50px;
            border: solid 1px #495057;
            border-radius: 3px;
            margin: 5px 0;
            cursor: pointer;
            text-align: center;
            background: #343a40;
            align-items: center;
            padding: 0 20px;
            font: bold 1.12rem Arial;
        }
        .menu_sidebar:hover, .menu_selected{
            background: #0097a9;
        }
        .modulo_seccion{
            width: 100%;
            height: 100%;
            color: #343a40;
        }
        section{
            background: #f5f5f5;
        }
        #toggle{
            background: #f5f5f5;
        }
        footer{
            background-color: #40474f;
        }
    </style>
    <script>
        agregar_menu("Mi Grupo");
        agregar_menu("Perfil");
        agregar_menu("Materias");
        agregar_menu("Horario");
        agregar_menu("Clases en linea");
        agregar_menu("Tareas");
        agregar_menu("Cuestionarios");
        agregar_menu("Calificaciones");
        function agregar_menu(nombre) {
            let div = document.createElement("div");
            div.className = "menu_sidebar d-flex";
            div.innerHTML = nombre;
            div.id = "menu_seccion_" + nombre.replace(/\s/g, "");
            $("#sidebar").append(div);

            let div2 = document.createElement("div");
            div2.className = "modulo_seccion d-none";
            div2.id = "modulo_seccion_" + nombre.replace(/\s/g, "");//quitale los espacios si llegara a tener 
            div2.innerHTML = nombre;

            $("#contenidoSection").append(div2);

            div.addEventListener("click", function () {
                let modulos = $(".modulo_seccion");
                modulos.addClass("d-none");
                let menus = $(".menu_sidebar");
                menus.removeClass("menu_selected");
                $("#modulo_seccion_" + nombre.replace(/\s/g, "")).removeClass("d-none");
                $("#menu_seccion_" + nombre.replace(/\s/g, "")).addClass("menu_selected");
            });
        }
    </script>

</body>
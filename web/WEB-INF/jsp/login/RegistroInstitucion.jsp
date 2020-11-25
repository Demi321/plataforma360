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
    <%--<%@include file="../plantilla/header.jsp" %>--%>
    <%--<%@include file="../plantilla/modal_menu.jsp" %>--%>
    <input type="hidden" id="config" value="${config}">
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Reporte Hospital</div>
        <div id="sidebar">


        </div>
    </aside>
    <section>

        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <!--Contenido-->
            <div class="row">
                <div class="col-12">
                    <h1>Registro de Centro de Trabajo</h1>
                    <hr>
                    <label>Ingrese los datos relacionados a su institución médica,así como los insumos existentes relacionados a la emergencia sanitaria.</label>
                </div>
            </div>
            <form class="formRegistroInstitucion">
                <div class="form-row">
                    <div class="form-group col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4" id="typeUser">
                        <label for="institucion">Institución</label>
                    </div>
                    <div class="form-group col-12 col-sm-12 col-md-4 col-lg-4 col-xl-4">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control inputForm" id="nombre" name="nombre" placeholder="Escriba nombre" required="true">
                    </div>
                    <div class="form-group col-12 col-sm-12 col-md-2 col-lg-2 col-xl-2" id="Medica">
                        <input type="checkbox" class="form-check-input" id="checkMedica" style="right: 85%;top: 25%;">
                        <label class="form-check-label" for="exampleCheck1" style="position: absolute;right: 0%;top: 30%;">
                            ¿Tu institución es medica?
                        </label>
                    </div>
                    <div class="form-group col-12 col-sm-12 col-md-2 col-lg-2 col-xl-2 d-none" id="Niveles">
                        <label for="nivel_atencion">Nivel de Atención</label>
                        <select class="form-control inputForm" name="nivel_atencion" id="nivel">
                            <option value="0">Seleccione un nivel</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                        <!--<h1>Direccion</h1>-->
                        <label for="direccion" class="col-12 mb-2 p-0">Dirección</label>
                        <input type="text" class="form-control inputForm" id="direccion" name="direccion"  placeholder="Indique dirección" required="true">
                        <input type="button" class="btn btn-info" id="buscarDir" value="Buscar">
                    </div>
                    <div class="form-group col-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                        <label for="telefono">Teléfono</label>
                        <input type="text" class="form-control inputForm" id="telefono" name="telefono" placeholder="Escriba 10 dígitos">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-12">
                        <h1>Mapa para ubicar instalación</h1>
                    </div>
                    <div class="form-group w-100">
                        <div class="" id="contenidoMapa">
                            <!--Contenido-->
                            <div id="map"></div>
                        </div>
                    </div>
                </div>
                <div class="form-group col-12 text-center">
                    <button type="submit" id="agregarInstitucion" class="btn btn-red">Agregar</button>
                </div>
                <input type="hidden" id="lat" value="">
                <input type="hidden" id="lng" value="">
            </form>

        </div>
    </section>
    <%--<%@include file="../plantilla/footer.jsp" %>--%>
    <%--<@include file="../plantilla/callhead_registro.jsp" %>--%>
    <%@include file="../plantilla/callhead_sinlogin.jsp" %>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
    </script>

    <link href="${pathRecursos}/css/registroinstitucion.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/Empresa/registroinstitucion.js"></script>

</body>
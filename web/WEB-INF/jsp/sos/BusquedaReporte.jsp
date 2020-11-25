<%-- 
    Document   : plantilla
    Created on : 02 jEne 2020, 16:25:53
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

<body id="page">
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside class="toggleactive">
        <div class="row col-12 m-0 p-0" id="toggle" >Buscar Reportes</div>
        <div id="sidebar">
            <div id="accordion">
                <div class="card" style="border-top: none; border-right: none; border-left: none; border-image: initial; border-bottom: 2px solid white;">
                    <div class="card-header m-0 p-0 row col-12" id="headingfolio" style="background: rgb(0, 151, 169); border: none;">
                        <div class="m-0 p-0 col-12">
                            <button class="m-0 p-0 btn  collapsed" type="buttom" data-toggle="collapse" data-target="#collapsefolio" aria-expanded="false" aria-controls="collapsefolio" style="width: 100%; text-align: left;">
                                <h4 class="p-2 m-0" style="color: white; font: bold 12px arial;">Búsqueda por folio externo</h4>
                            </button>
                        </div>
                    </div>
                    <div id="collapsefolio" class="collapse show" aria-labelledby="headingfolio" data-parent="#accordion" style="">
                        <div class="card-body p-0 m-0" id="" style="background: rgb(64, 71, 79);">
                            <div id="ContainerBusquedaFolioExterno" style="padding: 20px 10px 20px 10px;">
                                <div class="input-group">
                                    <input class="form-control mr-sm-2" type="search" placeholder="Insertar Folio Externo" aria-label="Search" style="border-radius: 3px;" id="FolioExterno">
                                    <span class="input-group-btn" style="margin:auto;">
                                        <button class="btn btn-sm ml-1 btn-secondary " type="button"  id="BusquedaFolioExterno" style="overflow-x: hidden;">Buscar</button>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>                       

                </div>
                <div class="card" style="border-top: none; border-right: none; border-left: none; border-image: initial; border-bottom: 2px solid white;">
                    <div class="card-header m-0 p-0 row col-12" id="headingfecha" style="background: rgb(0, 151, 169); border: none;">
                        <div class="m-0 p-0 col-12">
                            <button class="m-0 p-0 btn  collapsed" type="buttom" data-toggle="collapse" data-target="#collapsefecha" aria-expanded="false" aria-controls="collapsefecha" style="width: 100%; text-align: left;">
                                <h4 class="p-2 m-0" style="color: white; font: bold 12px arial;">Búsqueda por fecha</h4>
                            </button>
                        </div>
                    </div>

                    <div id="collapsefecha" class="collapse show" aria-labelledby="headingfecha" data-parent="#accordion" style="">
                        <div class="card-body p-0 m-0" id="" style="background: rgb(64, 71, 79);">

                            <nav class="navbar-dark bg-dark" style="width: 100%">

                                <div id="ContainerBusquedaFecha p-0 pb-3" class="text-white">

                                    <div class="input-group pb-3" id="group_fecha">
                                        <input  class="form-control mr-sm-2" type="text"  id="dateformated"  style=" display: none;" disabled="true">

                                        <button id="button__api-open-close"  class="pull-right" style="background: url(${calendarPNG});
                                                background-repeat: no-repeat;
                                                background-position:center;
                                                background-size: cover;
                                                -moz-background-size: cover;
                                                -webkit-background-size: cover;
                                                -o-background-size: cover;
                                                width: 25px;
                                                height: 25px;
                                                border: none;
                                                cursor:pointer;
                                                display: none;"></button>


                                    </div>


                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i1">
                                            <label for="i1" class="custom-control-label" id="l1">Mis llamadas atendidas</label>
                                        </div>
                                    </div>
                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i2">
                                            <label for="i2" class="custom-control-label" id="l2">Todas las llamadas atendidas</label>
                                        </div>
                                    </div>
                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i3">
                                            <label for="i3" class="custom-control-label" id="l3">Todas las llamadas perdidas</label>
                                        </div>
                                    </div>
                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i4">
                                            <label for="i4" class="custom-control-label" id="l4">Llamadas en modo sigiloso</label>
                                        </div>
                                    </div>
                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i5">
                                            <label for="i5" class="custom-control-label" id="l5">Video llamadas</label>
                                        </div>
                                    </div>
                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i6">
                                            <label for="i6" class="custom-control-label" id="l6">Llamadas de voz</label>
                                        </div>
                                    </div>
                                    <div class="row col-12 pl-2 m-0">
                                        <div class="custom-control custom-switch">
                                            <input type="checkbox" class="custom-control-input" id="i7">
                                            <label for="i7" class="custom-control-label" id="l7">Comunicacion por chat</label>
                                        </div>
                                    </div>
                                </div>
                            </nav>
                        </div>
                    </div>   
                </div>                                
            </div> 
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-0" id="contenidoSection">
            <div class="card text-white w-100" style="height:100%;background: transparent;border:none;" >
                <div class="card-header text-center" style=" background-color:#ff8200; border-radius: 0;"><strong style="font: 16px Arial; color: white; font-weight: bolder;" id="RepEncontrados">Reportes encontrados</strong></div>
                <div class="card-body"  style=" max-height: calc(100% - 38px); padding: 0;">
                    <div class="row col-12 p-0 m-0 h-100" id="ContenidoLlamadas">

                        <div class="col-12 p-0" id="llamadas" style="height: -webkit-fill-available; overflow-y: scroll;">
                            <div id="accordionReporte">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <div id="myModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content" id="modal-content">
            <span class="close" id="CloseFrame" style="width: 30px;">&times;</span>
            <iframe id="FrameReporte" style="height: 100%; display:block;" src=""></iframe>
        </div>
    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk">
    </script>
    <script src="${pathRecursos}/js/sos/BusquedaReporte/BusquedaReporte.js" ></script>
</body>
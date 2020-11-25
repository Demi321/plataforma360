<%-- 
    Document   : Operador
    Created on : 7 Ene 2020, 16:25:53
    Author     : Moises Juárez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>   
    <link href="${pathRecursos}/css/operador.css" rel="stylesheet" />
    
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar" >
            <%@include file="../plantilla/Perfil.jsp" %>
            <div class=" col-sm-12 col-md-12 col-lg-12 col-xl-12 form-group p-0">


                <div class="card-headermenus" >
                    <a style="margin:5px;font-size: 11px">GENERAR REPORTE</a>

                </div> 



                <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 p-0" id="accordion">


                    <div class="card" >
                        <div class="titulomenu" id="headingZero">

                            <a id="aheadingZero" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseZero" aria-expanded="false" aria-controls="collapseZero">
                                Incidentes Cercanos
                            </a>

                        </div>

                        <div id="collapseZero" class="collapse show" aria-labelledby="headingZero" data-parent="#accordion">



                            <div class="row col-12 m-0 p-0" >

                            </div> 



                        </div>
                    </div>


                    <div class="card" style="display:none;">
                        <div class="titulomenu" id="headingOne">

                            <a id="aheadingOne" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                Establecer Incidente
                            </a>

                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">


                        </div>
                    </div>

                    <div class="card" style="display: none;">
                        <div class="titulomenu" id="headingTwo">
                            <h5 class="mb-0">
                                <a  id="aheadingOTwo" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                    Establecer Folio
                                </a>
                            </h5>
                        </div>

                        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">



                            <div class="row col-12 m-0 p-0"  id="nivelAtencion" >
                                <div class="col-12 text-center">
                                    <button  class=" btn btn-danger"  id="EstablecerFolio" value="Guardar" style="color:white; border-radius:1rem;width: 50%; padding: 2px; margin: 15px;">Establecer</button>
                                </div>
                            </div> 
                        </div>
                    </div>

                    <div class="card">
                        <div class="titulomenu" id="headingThree">

                            <a id="aheadingThree" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Detallar Reporte
                            </a>

                        </div>
                        <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">







                            <div style="height: auto;width: 100%; ">




                                <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                    <div class="col-8">
                                        <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Descripción de Lugar:</label>
                                    </div>
                                    <div class="col-12">
                                        <textarea type="text" placeholder="Descripción del lugar" style="font: 12px Arial; width: 100%; border-radius: 3px; " class="form-control" id="DescripcionLugar" name="descripcion" rows="3" cols="80" required="required"></textarea>
                                    </div>
                                </div> 

                                <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                    <div class="col-8">
                                        <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; " id="label">Reporte:</label>
                                    </div>
                                    <div class="col-12">
                                        <textarea class="form-control" id="AreaReporte" style="font: 12px Arial; margin-top: 5px; border-radius: 3px;" name="reporte" rows="5" cols="80" placeholder="Escribe tu reporte" required="required"></textarea>
                                    </div>
                                </div> 



                                <div class="row col-12 m-0 p-0 mt-2" >
                                    <div class="col-3">
                                        <label style="text-align: left; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; " class="label-default">Incidente:</label>
                                    </div>
                                    <div class="col-12 m-0 p-0 pl-1 pr-1" id="cniapp">


                                        <multiselect 
                                            placeholder="Selecciona un Incidente"
                                            v-model="value" 
                                            :options="options"
                                            track-by="Incidente"
                                            :custom-label="customLabel" 
                                            :select-label="''" 
                                            :selected-Label="''"
                                            :deselect-Label="'Remover'"
                                            @input="onChange" 
                                            @close="onTouch" 
                                            @select="onSelect">

                                        </multiselect>


                                    </div>
                                </div> 

                                <div class="row col-12 m-0 p-0 mt-2"  id="nivelAtencion" >
                                    <div class="col-3">
                                        <label style="text-align: left; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; " class="label-default">Prioridad:</label>
                                    </div>
                                    <div class="col-9">
                                        <input  class="readonly ClearInput" style="font: 12px Arial;" id="nivelemergencia" name="nivelemergencia" required="required"  disabled="true">
                                    </div>
                                </div>  


                                <div class="row col-12 m-0 p-0"  id="nivelAtencion" >
                                    <div class="col-12 text-center">
                                        <button  class=" btn btn-danger"  id="EstablecerIncidente" value="" style="color:white; border-radius:1rem;width: 50%; padding: 2px; margin: 15px;">Establecer y Notificar</button>
                                    </div>
                                </div>


                                <form id="reporte">


                                    <div class="row col-12 m-0 p-0 mt-2" >
                                        <div class="col-3">
                                            <label class="label-default"
                                                   style="
                                                   text-align: left;
                                                   height: 15px; 
                                                   margin-top: 10px; 
                                                   font: 12px Arial;    
                                                   color: #ff8200; " >Folio Interno</label>
                                        </div>
                                        <div class="col-9">
                                            <input placeholder="Ingresar folio"  class="ClearInput" style="font: 12px Arial;" id="folio" >
                                        </div>
                                    </div> 

                                    <div class="row col-12 m-0 p-0" >
                                        <div class="col-3">
                                            <label style="text-align: left; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; " class="label-default">Folio Incidentes</label>
                                        </div>
                                        <div class="col-9">
                                            <input  class="ClearInput" style="font: 12px Arial;" id="FolioIncidentes" disabled="true">
                                        </div>
                                    </div> 

                                    <div class="row col-12 m-0 p-0 mt-2"  id="container_folioexterno">
                                        <div class="col-3">
                                            <label class="label-default"
                                                   style="
                                                   text-align: left;
                                                   height: 15px; 
                                                   margin-top: 10px; 
                                                   font: 12px Arial;    
                                                   color: #ff8200; " >Folio Externo</label>
                                        </div>
                                        <div class="col-9">
                                            <input  class="form-control" style="font: 12px Arial;" id="folioExterno" >
                                        </div>
                                    </div> 


                                    <div class="row col-12 mx-auto mt-1" style="padding: 0;" id="container_razonamiento">
                                        <div class="col-8">
                                            <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; " id="label">Razonamiento:</label>
                                        </div>
                                        <div class="col-12">
                                            <textarea class="form-control" id="razonamiento" style="font: 12px Arial; margin-top: 5px; border-radius: 3px;" name="razonamiento" rows="5" cols="80" placeholder="Escribe tu razonamiento" ></textarea>
                                        </div>
                                    </div> 

                                    <div class="row col-12 mx-auto mt-2" style="padding: 0;">
                                        <%--div class="col-1">
                                            <input class="checkbox" type="checkbox"  id="registrarLugar" checked="true">

                                                    </div>
                                                    <div class="col-7">
                                                        <label class="label-default" style="height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; ">Añadir mejora de sitio</label>                                                            
                                                    </div--%>
                                        <div class="col-12 text-center">
                                            <button  class=" btn btn-danger" type="submit"  id="btn-reporte" value="Guardar" style="color:white; border-radius:1rem;width: 50%; padding: 2px; margin: 15px;" >Guardar</button>
                                        </div>
                                    </div> 

                                </form>



                            </div>
                        </div>
                    </div>

                    <div class="card" style="display:none;">

                        <div class="titulomenu" id="headingFour">

                            <a id="aheadingFour" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                Generar Reporte
                            </a>

                        </div>
                        <div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordion">



                        </div>

                    </div>

                    <div class="card" id="divmenudependencia">

                        <div class="titulomenu" id="headingFive">

                            <a type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                                Notificación de Dependencias
                            </a>

                        </div>
                        <div id="collapseFive" class="collapse show" aria-labelledby="headingFive" data-parent="#accordion">
                            <div class="menudependencia" id="menudependencia">

                                <div class="mt-4" id="divnotificarDependencias" style="display: block;">
                                    <form id="notificarDependencias" action="#" method="POST" >
                                        <div class="row col-12 mx-auto mt-2" style="padding: 0;"> 
                                            <table>
                                                ${Dependencias}
                                            </table>
                                        </div>      
                                        <div class="row col-12 mx-auto mt-2" style="padding: 0;"> 
                                            <div class="col-1">
                                            </div>
                                            <div class="col-1 col-sm-2 col-md-3 col-lg-4 col-xl-6">                                                          
                                            </div>
                                            <div class="col-9 col-sm-8 col-md-7 col-lg-6 col-xl-4">
                                                <button class=" btn btn-primary" type="submit" value="Notificar" style="color: white; width: 100%;">Notificar</button></td>
                                            </div>

                                        </div>      
                                    </form>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div class="row col-12 m-0 p-0" style="height: 100%; top: 0;">

                <div class="col p-0" id="side1">
                    <div class="col-12 p-0 pl-1 side1Map">
                        <div class="card-headertitle text-center" >
                            MAPA
                        </div> 
                        <%-- Ubicacion Mapa --%>
                        <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" style="    height: calc(100% - 3.3vh); color: black;">



                            <div id="enviarNotificacion" class="btnNotificacion" title="Notificar a los elementos dentro del rango"  style="display: none;"></div>
                            <div class="rangecontainer" id="ragecontainer" style="display: none;">
                                <div style="position: relative;width: 100%;height: 100%;">
                                    <input id="range" type="text" data-slider-min="0" data-slider-max="2500" data-slider-step="1" data-slider-value="500" data-slider-orientation="vertical"/>  
                                </div>
                            </div>

                            <div class="embed-responsive embed-responsive-4by3">
                                <div class="embed-responsive-item" id="map" >

                                </div>                            
                            </div>
                        </div>
                    </div>
                    <div class="col-12 p-0 pl-1 pt-0 side1Chat">

                        <form id="chat" class="h-100">
                            <div class="card" id="textchat" >
                                <div class="card-headertitle text-center" >
                                    CHAT
                                </div>
                                <div class="card-body" style="background-color: #f4f4f4;">

                                    <div class="row" id="history" style="width: 100%; margin: 0;">

                                    </div>




                                    <div class="inputTextChat" style=" background: none;border: none;">
                                        <input class="form-control " type="text" placeholder="Comienza un chat aqui" id="msgTxt" disabled="true" autocomplete="off">
                                    </div>

                                </div>

                            </div>
                        </form>

                    </div>
                </div>
                <div class="col" id="side2">
                    <div id="videos">
                        <div class=" p-0 row GRIDcontainerOp"  id="GRID">

                            <button id="myBtn" style="display: none;" title="Reporte PDF"></button>
                        </div>
                        <div id="publishers" class="row col-12 m-0 p-0"></div>
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
            <div id="demoCarrusel" class="carousel slide" data-ride="carousel" style="
                 max-width: 100%;
                 width: 100%;
                 display: none;
                 ">
                <ul class="carousel-indicators" id="carousel-indicators">
                </ul>
                <div class="carousel-inner" id="carousel-inner">

                </div>
                <a class="carousel-control-prev" href="#demoCarrusel" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#demoCarrusel" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>

        </div>

    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>


    <style>
        /* Make the image fully responsive */
        .carousel-inner img {
            width: 100%;
            height: 100%;
        }
        body {font-family: Arial, Helvetica, sans-serif;}

        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 100; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            /*width: 45%;*/width: 80%;
            height: 80%;
            /**/
            /*top: 20%;*/
        }

        /*Frame*/
        .frame{
            margin: auto;
            padding: 5px;
            border: 1px solid #888;
            width: -webkit-calc(100% - 40px);
            width:    -moz-calc(100% - 40px);
            width:         calc(100% - 40px);
            height: -webkit-calc(100% - 80px);
            height:    -moz-calc(100% - 80px);
            height:         calc(100% - 80px);
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
    </script>
    <script src="${pathRecursos}/js/sos/Operador/app.js"></script>
    <script src="${pathRecursos}/js/sos/Operador/map.js"></script>
</body>


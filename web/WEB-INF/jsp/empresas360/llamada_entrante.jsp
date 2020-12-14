<%-- 
    Document   : plantilla
    Created on : 02 Ene 2020, 16:25:53
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
            <div class="row col-12 m-0 p-0" id="toggle">
                  <div><i class="fas fa-ellipsis-v"></i></div>
                  <span id="titulomenu"> Participantes </span>
                  <input type="button" id="directorio" class="AddNewGroup der">
            </div>
            <div id="sidebar">
                  <div class="row m-0 p-0 h-50 overflow-auto" >
                        <div class="col-12 p-0 m-0" style="position: relative;display: block;">
                              <div id="participantes" class="containerCardsParticipantes">

                              </div>
                        </div>
                  </div>
                  <div class="row m-0 p-0 h-50" style="position: relative;">
                        <div class="card-headerseparador" >
                              Reporte
                        </div>
                        <div style="height: calc(100% - 30px); position: absolute; width: 100%; overflow: scroll; top:30px;">
                              <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                    <div class="col-8">
                                          <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Folio:</label>
                                    </div>
                                    <div class="col-12">
                                          <input type="text" placeholder="Ingresa un folio para la llamada"  class="ClearInput" id="folio" name="folio" 
                                                 style="background: white; border-radius: 3px;text-align: left; padding-left: 10px;     color: #40474e; font: 12px arial;" >
                                    </div>
                              </div> 
                              <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                    <div class="col-8">
                                          <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Motivo:</label>
                                    </div>
                                    <div class="col-12">
                                          <textarea type="text" placeholder="Escribe el motivo de la llamada" style="font: 12px Arial; width: 100%; border-radius: 3px; " class="form-control" id="motivo" name="motivo" rows="3" cols="80" required="required"></textarea>
                                    </div>
                              </div> 
                              <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                    <div class="col-8">
                                          <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Reporte:</label>
                                    </div>
                                    <div class="col-12">
                                          <textarea type="text" placeholder="Redacta tu reporte" 
                                                    style="font: 12px Arial; width: 100%; border-radius: 3px; " 
                                                    class="form-control" 
                                                    id="reporte" 
                                                    name="reporte" 
                                                    rows="8" 
                                                    cols="80" ></textarea>
                                    </div>
                              </div> 
                              <div class="col-12 text-center">
                                    <button  class=" btn btn-danger" type="submit"  id="btn-reporte" value="Guardar" style="color:white; border-radius:1rem;width: 50%; padding: 2px; margin: 15px;" >Guardar</button>
                              </div>

                        </div>
                  </div>
                  <div class="row justify-content-center" style="width: auto;display:none;">
                        <div class=" col-sm-12 col-md-12 col-lg-12 col-xl-12 form-group">

                              <div class=" col-sm-4 col-md-8 col-lg-9 col-xl-9 justify-content-center" style="padding-left:  0px;">
                                    <div style=" height: auto;width: auto; ">

                                    </div>

                              </div>
                              <div class="card-headermenus" >
                                    <a style="margin:5px;font-size: 11px"> </a>
                              </div> 
                              <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 p-0" id="accordion">


                                    <div class="card" >
                                          <div class="titulomenu" id="headingZero">

                                                <a id="aheadingZero" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseZero" aria-expanded="false" aria-controls="collapseZero">
                                                      Contactos
                                                </a>

                                          </div>

                                          <div id="collapseZero" class="collapse show" aria-labelledby="headingZero" data-parent="#accordion" style="    max-height: 500px;   overflow-y: scroll;">



                                          </div>
                                    </div>






                                    <div class="card">
                                          <div class="titulomenu" id="headingThree">

                                                <a id="aheadingThree" type="button" class="btn btn-secondary btn-lg btn-block linkbut collapsed coll" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                                      Detallar Reporte
                                                </a>

                                          </div>
                                          <div id="collapseThree" class="collapse show" aria-labelledby="headingThree" data-parent="#accordion">


                                                <div style="height: auto;width: 100%; ">
                                                      <form id="reporte">

                                                            <div class="row col-12 mx-auto mt-1" style="padding: 0;">
                                                                  <div class="col-8">
                                                                        <label class="label-default" style="margin: 0; height: 15px; margin-top: 10px; font: 12px Arial;    color: #ff8200; "id="label">Historial de Párticipantes:</label>
                                                                  </div>
                                                                  <div class="col-12">
                                                                        <textarea placeholder="Historial de párticipantes" style="font: 12px Arial; width: 100%; border-radius: 3px; " class="form-control" id="hitorialparticipantes" name="hitorialparticipantes" rows="3" cols="80" required="required"></textarea>
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

                                                      <button id="myBtn" style="display:none;" title="Reporte PDF"></button>

                                                </div>
                                          </div>
                                    </div>


                              </div>


                        </div>

                        <!------------------------------------------------------------fin del codigo implementado para vistas-->

                  </div>
            </div>
      </aside>
      <section>
            <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
                  <div class="row col-12 m-0 p-0 "  style="height: 100%; top: 0;">


                        <div class="col  p-0" id="side1">
                              <div class="col-12 p-0 pl-1 side1Map d-none">
                                    <div class="card-headertitle text-center" >
                                          MAPA
                                    </div> 
                                    <%-- Ubicacion Mapa --%>
                                    <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" style="    height: calc(100% - 3.3vh); color: black;">


                                          <div class="embed-responsive embed-responsive-4by3">
                                                <div class="embed-responsive-item" id="map" >

                                                </div>                            
                                          </div>
                                    </div>
                              </div>
                              <div class="col-12 p-0 pl-1 pt-0 side1Chat h-100">

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
                                    <div class=" p-0 row GRIDcontainer"  id="GRID">


                                    </div>
                                    <div id="publisher"></div>
                              </div>

                        </div>


                  </div>
            </div>
            <%-- Información del Perfil - seccion izquierda--%>
            <div class="row col-sm-12 col-md-12 col-lg-12 col-xl-12" style="display: none;">
                  <!-- Información General -->


                  <div class="card col-sm-6 col-md-6 col-lg-6 col-xl-6  " style="padding: 5px; padding-top: 0;    height: -webkit-fill-available;">


                        <br>





                  </div>

                  <div class="card col-sm-6 col-md-6 col-lg-6 col-xl-6 " style="padding: 5px; padding-top: 0; ">
                        <!--div class="card-headertitle text-center">
                            <h3>Llamada</h3>
                        </div-->
                        <div class="card-body">
                              <div class="row col-12 mx-auto mt-1" style="padding: 0; height: 100%; max-height: 800px;">
                                    <div class="col-9">
                                          <div class="col-sm-12 col-md-9 col-lg-9 col-xl-9 embed-responsive embed-responsive-1by1">
                                                <div class="embed-responsive-item" id="videos">

                                                      <div id="subscriber" class="big" style="background:#929799; border-right: solid 1px white;">
                                                            <!-- Trigger/Open The Modal -->

                                                      </div>
                                                      <div id="publisher" name="publisher">

                                                      </div> 
                                                      <div id="botonera">

                                                      </div>


                                                </div>  

                                          </div>
                                    </div>
                                    <div class="col-3" id="waitingbar" style="border-left: solid 1px; padding: 0; max-height: 100%; overflow-y: scroll;">


                                    </div>
                              </div>
                        </div>
                  </div>


            </div>



            <%-- Información  - seccion derecha --%>
            <div class="col-sm-12 col-md-12 col-lg-9 col-xl-9">

                  <div class="row">


                  </div>
                  <%-- Chat --%>
                  <%-- Chat --%>

            </div>
            <span id="span" style="color: lightblue; display: block; font-size: 12px; "  >${Alerta}</span>
      </section>

      <style>
            /* Make the image fully responsive */
            .carousel-inner img {
                  width: 100%;
                  height: 100%;
            }
            body {font-family: Arial, Helvetica, sans-serif;}

            /* The Modal (background) */
            .modal {
                  display:none; /* Hidden by default */
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

      <div id="myModal" class="modal">

            <!-- Modal content -->
            <div class="modal-content" id="modal-content">
                  <span class="close" id="CloseFrame" style="width: 30px;">&times;</span>

                  <iframe id="FrameReporte" style="height: 100%; display:block;" src=""></iframe>
                  <div id="demoCarrusel" class="carousel slide" data-ride="carousel" style="
                       max-width: 100%;
                       width: 100%;
                       display:none;
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
      <script src="${pathRecursos}/js/Empresa/llamada_entrante.js" ></script>
      <script async defer
              src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
      </script>

</body>





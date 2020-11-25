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
            <div class="row col-12 m-0 p-0" id="toggle">Notificaciones COVID19</div>
            <div id="sidebar">

                  <div class="row col-12 m-0 p-0" style="
                       text-align: center;
                       background: #252525;
                       border-bottom: solid 2px #484848;
                       ">
                        <div class="col-4 p-3  custom-control custom-checkbox">
                              <input type="checkbox" class="custom-control-input" id="CBbajo" checked="true">
                              <label class="custom-control-label" for="CBbajo">Nivel Bajo</label>
                        </div>
                        <div class="col-4 p-3  custom-control custom-checkbox">
                              <input type="checkbox" class="custom-control-input" id="CBmedio" checked="true"> 
                              <label class="custom-control-label" for="CBmedio">Nivel Medio</label>
                        </div>
                        <div class="col-4 p-3  custom-control custom-checkbox">
                              <input type="checkbox" class="custom-control-input" id="CBalto" checked="true">
                              <label class="custom-control-label" for="CBalto">Nivel Alto</label>
                        </div>
                  </div>



            </div>
      </aside>
      <section>
            <div style="display:none;" id="test"></div>
            <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
                  <!--Datos Personales-->
                  <div class="row col-12 m-0" style="height: 100%; display: block;">
                        <div class="headsec" style="
                             height: 60px;
                             width: 100%;
                             background: black;
                             border-top-left-radius: 10px;
                             border-top-right-radius: 10px;
                             margin-top: 15px;
                             display: flex;
                             align-items: center;
                             ">
                              <label style="
                                     margin: 0;
                                     font: bold 1.3em Arial;
                                     margin-left: 2%;
                                     ">Test COVID-19</label>
                        </div>
                        <div class="separadorsec" style="background: black;">
                              <span id="NivelRiesgo" style=" width: 50%; position: absolute; height: 100%; display: flex; align-items: center;">
                                    <label style="margin: 0; font: normal 1em Arial; margin-left: 2%; ">Nivel de Riesgo:</label>
                                    <label id="riesgo" style=" font: bold 1em Arial; margin-left: 2%;margin: 0;"></label>
                              </span> 
                              <span style="position: absolute; width: 50%; left: 50%;height: 100%; display: flex;  align-items: center;">
                                    <label style=" margin: 0; font: bold 1em Arial;margin-left: 2%; display: none ">Clinico:</label>
                                    <label id="clinico" style=" margin: 0; "></label>
                              </span>
                        </div>
                        <div class="row col-12 w-100 m-0 p-0 " style="background-color: #41474f">
                              <div class="col-sm-12 col-md-4 col-lg-3 col-xl-2 mt-3 mb-3 ml-3" id="foto"></div>
                              <div class="col mb-3">
                                    <input type="text" disabled="true" class="input1" id="nombre" value="">
                                    <input type="text" disabled="true" class="input2" id="f_nac" value="Fecha de nacimiento:">
                                    <input type="text" disabled="true" class="input2" id="edad" value="Edad:">
                                    <br>
                                    <input type="text" disabled="true" class="input2" id="direccion" value="Direccion:">
                                    <input type="text" disabled="true" class="input2" id="cp" value="CP:">
                                    <p>Datos de contacto</p>
                                    <input type="text" disabled="true" class="input2" id="correo"  value="Correo:">
                                    <input type="text" disabled="true" class="input2" id="telefono"  value="Telefono:">
                                    <p>Contactos de emergencia</p>
                                    <input type="text" disabled="true" class="input2" id="nombrece"  value="">
                                    <input type="text" disabled="true" class="input2" id="telefonoce" value="" >
                              </div>

                              <hr style="width:100%;margin:0;border-top: 2px solid rgb(165, 165, 165);height: 40%;">

                              <div class="row col-12 m-0 w-100" style=" height: 5%; ">
                                    <span id="FolioExterno" class="span1">
                                          <label style="color: #f68103;font: bold 1em Arial;">Folio externo:</label>
                                          <label style=" font: bold 1em Arial;margin-left: 5px;" id="LabelFolioExterno"></label>
                                    </span>
                                    <span id="HoraFecha" class="span1">
                                          <label style="color: #f68103;font: bold 1em Arial;">Hora y Fecha:</label>
                                          <label style=" font: bold 1em Arial;margin-left: 5px;" id="LabelHoraFecha"></label>
                                    </span>
                                    <span id="TipLlamada"  class="span1">
                                          <label style="color: #f68103;font: bold 1em Arial;">Tipo de llamada:</label>
                                          <label style=" font: bold 1em Arial;margin-left: 5px;" id="LabelTipoLlamada"></label>
                                    </span>
                              </div>
                              <!--Ubucacion y sintomas-->
                              <div class="row col-12 m-0 p-0 mt-3" style="border-top: 2px solid rgb(165, 165, 165);border-bottom: 2px solid rgb(165, 165, 165);">
                                    <div class="col" style="
                                         overflow-y: scroll;
                                         ">
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div1">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Has convivido con alguna persona que sea un caso confirmado de COVID-19 (Coronavirus)?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p1"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div2">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes fiebre? (Temperatura igual o mayor a 38ºC)</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p2"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div3">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes dolor de cabeza?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p3"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div4">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes tos?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p4"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div5">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes dolor en el pecho?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p5"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div6">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes dolor de garganta?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p6"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div7">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes dificultad para respirar?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p7"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div8">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes escurrimiento nasal?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p8"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div9">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes dolor en el cuerpo?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p9"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div10">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes conjuntivitis?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p10"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div11">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Hace cuántos días iniciaron tus síntomas? </label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p11"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div12">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes alguna de las siguientes condiciones? (diabetes, hipertensión, obesidad, problemas cardiacos, asma, EPOC, VIH, cáncer)</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p12"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div13">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Estás embarazada?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p13"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div14">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes 6 o más meses de embarazo?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p14"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div15">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Sientes dolor al respirar?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p15"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div16">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Sientes falta de aire al hablar o caminar algunos pasos?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p16"></label>
                                          </div>
                                          <div class="col-12 w-100 m-0" style="
                                               height: 11%;
                                               display: flex;
                                               align-items: center;
                                               border-bottom: 2px solid rgb(165, 165, 165);
                                               " id="div17">
                                                <label style="
                                                       width: 80%;
                                                       color: #f68103;
                                                       font: bold 1em Arial;
                                                       ">¿Tienes coloración azul o morada en labios, dedos o uñas?</label>
                                                <label style="
                                                       width: 20%;
                                                       font: bold 1em Arial;
                                                       " id="p17"></label>
                                          </div>
                                          
                                          <input type="hidden" disabled="true" class="input1" id="serie" value="">
                                          <input type="hidden" disabled="true" class="input1" id="firebasek" value="">
                                          
                                    </div>
                                    <div class="col-1 m-0 p-0" style="max-width: .9%;border-left: 2px solid rgb(165, 165, 165);"></div>
                                    <div class="col-sm-12 col-md-5 col-lg-4 col-xl-3 mr-3" id="map"></div>
                              </div>
                              <div class="row col-sm-12 col-md-10 col-xl-8 m-0 pl-5 mb-3">
                                    <label style=" color: #f68103; font: bold 1em Arial;">Escriba su razonamiento:</label>
                                    <!--<input type="text" disabled="true" class="input1" value="Razonamiento: ">-->
                                    <textarea type="" class="textarea1" id="reporte" value="Razonamiento: " style="background: white;
                                              color: black;
                                              font: 1.2em Arial;
                                              border-top-left-radius: 15px;
                                              border-top-right-radius: 15px;
                                              border-bottom-left-radius: 15px;
                                              border-bottom-right-radius: 15px;
                                              resize: none;
                                              padding: 8px 15px 8px 15px;" rows="5" maxlength="240"></textarea>

                                    <div class="col-12 text-right p-0 ">
                                          <button class=" btn btn-danger" id="Guardar" value="" style="color:white;border-radius:1rem;width: 200px;padding: 2px;margin: 0;margin-top: 10px;background-color: #da291c;
                                                  border-color: #da291c;">Guardar y Notificar</button>
                                    </div>

                              </div>
                        </div>

                  </div>

            </div>
      </section>
      <%@include file="../plantilla/footer.jsp" %>
      <%@include file="../plantilla/callhead.jsp" %>
      <script async defer
              src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
      </script>
      <script>
      </script>

      <style>

      </style>

      <link href="${pathRecursos}/css/covid.css" rel="stylesheet" />
      <script src="${pathRecursos}/js/Empresa/covid.js"></script>

</body>
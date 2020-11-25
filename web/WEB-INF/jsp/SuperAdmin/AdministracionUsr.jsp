<%-- 
    Document   : AdministracionUsr
    Created on : 28/01/2020, 10:39:19 AM
    Author     : web
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
      <%@include file="../plantilla/head.jsp" %>
      <%-- Estilos Personalizados --%>
      <link href="${pathRecursos}/css/administracionUsr.css" rel="stylesheet" />
      <%-- Javascript Personalizados --%>    

</head>

<body>
      <%@include file="../plantilla/header.jsp" %>
      <%@include file="../plantilla/modal_menu.jsp" %>

      <aside>
            <div class="row col-12 m-0 p-0" id="toggle">Administración de Usuarios</div>
            <div id="sidebar">
                  <div id="accordionAdministrador">
                        <div class="card ">
                            <div class="card-header" style="cursor: pointer;" id="cardBajaUsr" data-toggle="collapse" data-target="#collapseBajaUsr" aria-expanded="true" aria-controls="collapseBajaUsr">
                                <h5 class="mb-0 text-white text-center">
                                      Baja de Usuario movil de la plataforma
                                </h5>
                            </div>
                              <div class="card-header" style="cursor: pointer;" id="cardModulo" data-toggle="collapse" data-target="#collapseUsuarios360" aria-expanded="true" aria-controls="collapseModulo">
                                <h5 class="mb-0 text-white text-center">
                                    Modulos Usuarios 360
                                </h5>
                              </div>
                              <div id="collapseBajaUsr" class="collapse show" aria-labelledby="cardBajaUsr" data-parent="#accordionAdministrador">
                                    <div class="card-body p-0">
                                          <label class="sweetalrt titulo" style="">
                                                Selecciona un usuario
                                          </label>
                                          <div  id="agregarTels">
                                                <multiselect 
                                                      placeholder=""
                                                      v-model="value" 
                                                      :options="options"
                                                      track-by="id"
                                                      :custom-label="customLabel" 
                                                      :select-label="''" 
                                                      :selected-Label="''"
                                                      :deselect-Label="'Remover'"
                                                      @input="onChange" 
                                                      @close="onTouch" 
                                                      @select="onSelect">
                                                </multiselect>
                                          </div>
                                          <div class="botones centrar">
                                                <button class="btn btn-secondary " id="buscarIntegrante">Buscar</button>
                                          </div>
                                    </div>
                              </div>
                            <div id="collapseUsuarios360" class="collapse hide" aria-labelledby="cardBajaUsr" data-parent="#accordionAdministrador">
                                <div class="card-body p-0">
                                          <label class="sweetalrt titulo" style="">
                                                Selecciona un usuario
                                          </label>
                                          <div  id="agregarUsuarios360">
                                                <multiselect 
                                                      placeholder=""
                                                      v-model="value" 
                                                      :options="options"
                                                      track-by="id"
                                                      :custom-label="customLabel" 
                                                      :select-label="''" 
                                                      :selected-Label="''"
                                                      :deselect-Label="'Remover'"
                                                      @input="onChange" 
                                                      @close="onTouch" 
                                                      @select="onSelect">
                                                </multiselect>
                                          </div>
                                          <div class="botones centrar">
                                                <button class="btn btn-secondary " id="buscarUsuarios360">Buscar</button>
                                          </div>
                                    </div>
                            </div>
                        </div>
                  </div>
            </div>
      </aside>
      <section>
            <div class="h-100 row m-0 p-2" id="contenidoSection">
                  <div class="row col-sd-12 col-md-6 col-xl-4 p-0 pl-2 m-0 h-100 pt-4" style="margin-bottom: 5px; padding-bottom: 5px;">


                        <div class="col-2 divImgPerfil" style="background-image: url('${perfilIMG}')">
                        </div>

                        <div class="col-10 divContainerDatosPerfil"  style="">
                              <div class="row col-12 divContainerDatosPerfil2" >
                                    <label id="label" class="labelPerfil" >ID:</label>
                                    <input type="text" id="id" value="${id}" class="inputPerfil">
                              </div>
                              <div class="row col-12 divContainerDatosPerfil2" >
                                    <label id="label" class="labelPerfil" >Nombre:</label>
                                    <input type="text" id="NombrePerfil" value="${NombrePerfil}" class="inputPerfil">
                              </div>


                        </div>

                        <div class="row col-12 m-0 divContainerDatosPerfil3">
                              <div class="col-12 p-0 CDatos">
                                    <label class="labelPerfil"  id="label">Dirección:</label>
                                    <input class="inputPerfil" id="DireccionPerfil" value="${DireccionPerfil}">
                              </div>

                        </div>
                        <div class="row col-12 divContainerDatosPerfil3 m-0">
                              <div class="col-12 p-0 CDatos">
                                    <label  class="labelPerfil"   id="label">Fecha de Nacimiento: </label>
                                    <input type="text"  class="inputPerfil" id="Fecha_nacimiento" value="${Fecha_nacimiento}">   
                              </div>
                        </div>
                        <div class="row col-12 divContainerDatosPerfil3 m-0">
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label">Teléfono:</label>
                                    <input type="text" class="inputPerfil" id="TelPerfil" value="${TelPerfil}">
                              </div>
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label">E-mail:</label>
                                    <input type="text" class="inputPerfil" id="CorreoPerfil" value="${CorreoPerfil}">
                              </div>
                        </div>
                        <div class="row col-12 divContainerDatosPerfil3 m-0">
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label">Género:</label>
                                    <input type="text" class="inputPerfil"  id="GenPerfil" value="${GenPerfil}">
                              </div>
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label">Rh:</label>
                                    <input type="text" class="inputPerfil" id="RhPerfil" value="${RhPerfil}">
                              </div>
                        </div>

                        <div class="row col-12 divContainerDatosPerfil3 m-0" style=" height: auto;width: 100%; ">
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label"> Condición Médica:</label>
                                    <input type="text" class="inputPerfil" id="CondicionMedica" value="${CondicionMedica}">
                              </div>
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label">Alergias:</label>
                                    <input type="text" class="inputPerfil" id="AlergiasPerfil" value="${AlergiasPerfil}">
                              </div>

                        </div>
                        <div  id="tituloContacto" class="row col-12 ">
                              <a>CONTACTOS DE EMERGENCIA</a>
                        </div>
                        <div class="row col-12 divContainerDatosPerfil3 m-0" style=" height: max-content;width: 100%; ">
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label"> Nombre de Contacto:</label>
                                    <input type="text" class="inputPerfil" id="ContactoNombre" value="${ContactoNombre}">
                              </div>
                              <div class="col-6 p-0 CDatos">
                                    <label class="labelPerfil" id="label">Teléfono de Contacto:</label>
                                    <input type="text" class="inputPerfil" id="ContactoNumero" value="${ContactoNumero}">
                              </div>

                        </div>
                        <!--    <div class="row justify-content-center" style=" height: auto;width: 100%;margin:   0px;">
                                <input class="col-sm-5 col-md-5  col-lg-5 col-xl-5 "type="text"  style="padding: 0px;   background: transparent;  font: 12px Arial; border: none;   color: white;   height:25px;" id="ContactoNombre" value="${ContactoNombre}"> 
                                <input class="col-sm-5 col-md-5  col-lg-5 col-xl-5 " type="text"  style="padding: 0px;   background: transparent;  font: 12px Arial; border: none;   color: white;    height:25px;" id="ContactoNumero" value="${ContactoNumero}">
                            </div>-->

                        <div class="col-12 centrar w-100" style="height: 15%">
                              <input type="button" class="btn botonBaja mr-4" value="Dar de Baja" id="bajaUsr"/>
                              <input type="button" class="btn btn-secondary" value="Cancelar" id="cancelar" />
                        </div>

                  </div>     
            </div>
      </section>
      <%@include file="../plantilla/footer.jsp" %>
      <%@include file="../plantilla/callhead.jsp" %>
      <script src="${pathRecursos}/js/sos/Administracion/administracionUsr.js"></script>
</body>

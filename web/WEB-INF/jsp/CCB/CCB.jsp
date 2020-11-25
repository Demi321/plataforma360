<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
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
    <%@include file="../plantilla/baner_superior.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Referencia de Pacientes COVID-19</div>

        <div id="sidebar">
            <div id="accordion">
                <div class="card" style="border-top: none; border-right: none; border-left: none; border-image: initial; border-bottom: 2px solid white;">
                    <div class="card-header m-0 p-0 row col-12" id="headingfolio" style="background: rgb(0, 151, 169); border: none;">
                        <div class="m-0 p-0 col-12">
                            <button class="m-0 p-0 btn  collapsed" type="buttom" data-toggle="collapse" data-target="#collapsefolio" aria-expanded="false" aria-controls="collapsefolio" style="width: 100%; text-align: left;">
                                <h4 class="p-2 m-0" style="color: white; font: bold 12px arial;">Búsqueda por nombre o número de referencia</h4>
                            </button>
                        </div>
                    </div>
                    <div id="collapsefolio" class="collapse" aria-labelledby="headingfolio" data-parent="#accordion" style="">
                        <div class="card-body p-0 m-0" id="" style="background: rgb(64, 71, 79);">

                            <div class="col-12 m-0 p-0" id="directorio_pacientesCCB">
                                <!--                <label class="typo__label">Select with search</label>-->
                                <multiselect 
                                    v-model="value" 
                                    :options="options" 
                                    :custom-label="label_function" 
                                    placeholder="Buscar" 
                                    label="name" 
                                    track-by="name"
                                    :select-label="''" 
                                    :selected-Label="''"
                                    :deselect-Label="'Remover'"
                                    @select="onSelect"
                                    @open="onTouch" 
                                    ></multiselect>
                                <pre class="language-json d-none"><code>{{ value  }}</code></pre>
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

                    <div id="collapsefecha" class="collapse" aria-labelledby="headingfecha" data-parent="#accordion" style="">
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
                                </div>
                            </nav>
                        </div>
                    </div>   
                </div>                                
            </div> 

            <!--  <br>
                <input class="btn btn-danger" type="button" value="Buscar" id="buscar">
              <hr>
              <h1>Buscar por fecha</h1>
              <hr>-->


            <div class="row col-12 m-0 p-2">
                <input class="form-control filtro col-4" type="button" id="todas" value="Todas">
                <input class="form-control filtro col-4" type="button" id="aceptadas" value="Aceptadas">
                <input class="form-control filtro col-4" type="button" id="pendientes" value="Pendientes">
                <input class="form-control filtro col-4" type="button" id="proceso" value="En Proceso">
                <input class="form-control filtro col-4" type="button" id="ruta" value="En ruta">
                <input class="form-control filtro col-4" type="button" id="ingresados" value="Ingresados">
            </div>
            <hr>
            <div id="sidebar2"></div>

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div class="row col-12 m-0 p-0 conteo">
                <div class="col-3">
                    <div class="row stats" id="total_solicitudes">0</div>
                    <div class="row stats_label">Total de Solicitudes</div>
                </div>
                <div class="col border">
                    <div class="row stats" id="total_solicitudes_pendientes">0</div>
                    <div class="row stats_label">Pendientes</div>
                </div>
                <div class="col border">
                    <div class="row stats" id="total_solicitudes_proceso">0</div>
                    <div class="row stats_label">Proceso </div>
                </div>
                <div class="col border">
                    <div class="row stats" id="total_solicitudes_ruta">0</div>
                    <div class="row stats_label">Traslado </div>
                </div>
                <div class="col border">
                    <div class="row stats" id="total_solicitudes_rechazado">0</div>
                    <div class="row stats_label">No Aceptadas </div>
                </div>
            </div>
            <div class="row col-12 m-0 p-0 mt-1 informacion">
                <div class="row col-12 barra">
                    <div class="col-4" ><input type="text" id="id" style="
                                               padding: 8px;
                                               background: no-repeat;
                                               border: none;
                                               color: white;
                                               font: bold 1.2rem Arial;
                                               overflow: hidden;
                                               margin: 0;
                                               white-space: nowrap;
                                               width: 100%;
                                               "></div>
                    <div class="col-4" ><input type="text" id="codigo" style="
                                               padding: 8px;
                                               background: no-repeat;
                                               border: none;
                                               color: white;
                                               font: bold 1.2rem Arial;
                                               overflow: hidden;
                                               margin: 0;
                                               white-space: nowrap;
                                               width: 100%;
                                               "></div>
                    <div class="col-4" ><input type="text" id="fecha" style="
                                               padding: 8px;
                                               background: no-repeat;
                                               border: none;
                                               color: white;
                                               font: bold 1.2rem Arial;
                                               overflow: hidden;
                                               margin: 0;
                                               white-space: nowrap;
                                               text-overflow: ellipsis;
                                               "></div>
                    <div class="col-4"><input type="text" id="titulo_covid" style="
                                              padding: 8px;
                                              background: no-repeat;
                                              border: none;
                                              color: white;
                                              font: bold 1.1rem Arial;
                                              width: 100%;
                                              overflow: hidden;
                                              margin: 0;
                                              white-space: nowrap;
                                              text-overflow: ellipsis;
                                              " value="Referencia de Paciente a Unidad Temporal COVID-19"></div>
                    <div class="col-4" ><input type="text" id="codigo_alta" style="
                                               padding: 8px;
                                               background: no-repeat;
                                               border: none;
                                               color: white;
                                               font: bold 1.2rem Arial;
                                               overflow: hidden;
                                               margin: 0;
                                               white-space: nowrap;
                                               width: 100%
                                               "></div>
                </div>
                <div class="col-12 contenido_ficha">

                    <!--<h2>La información de tu paciente fue enviada para evaluación a la Unidad Temporal COVID-19</h2>-->
                    <div class="row col-12 ficha">
                        <h1 class="col-12 d-none" id="datos0">Datos de SUCRE</h1>
                        <div class="d-none row col-12" id="infoSUCRE">
                            <div class="col-6" style="display: flex; align-items: flex-end;">
                                <label id="unidadS"></label>
                            </div>
                            <div class="col-6" style="display: flex; align-items: flex-end;">
                                <label id="operadorS"></label>
                            </div>
                        </div>
                        <h1 class="col-12 d-none" id="datos1">Datos de C.R.U.M.</h1>
                        <div class="d-none row col-12" id="infoCRUM">
                            <div class="col-6" style="display: flex; align-items: flex-end;">
                                <label id="unidad"></label>
                            </div>
                            <div class="col-6" style="display: flex; align-items: flex-end;">
                                <label id="operador"></label>
                            </div>
                        </div>
                        <h1 class="col-12 d-none" id="datos2">Datos de la Unidad Temporal COVID-19</h1>
                        <div class="d-none row col-12" id="infoUTC">
                            <div class="col-6" style="display: flex; align-items: flex-end;">
                                <label id="cama"></label>
                            </div>
                            <div class="col-6" style="display: flex; align-items: flex-end;">
                                <label id="doc_responsable"></label>
                            </div>
                        </div>
                        

                        <h1 class="col-12">Ficha de identificación</h1>
                        <div class="col-4">
                            <label>Nombre del Paciente </label>
                            <input type="text" id="nombre">
                        </div>
                        <div class="col-4">
                            <label>Sexo </label>
                            <input type="text" id="genero">
                        </div>
                        <div class="col-4">
                            <label>Edad </label>
                            <input type="text" id="edad">
                        </div>
                        <div class="col-4">
                            <label>Fecha de nacimiento </label>
                            <input type="text" id="fecha_nacimiento" >
                        </div>
                        <div class="col-4">
                            <label>Hospital de envío </label>
                            <input type="text" id="institucion_refiere">
                        </div>
                        <div class="col-4">
                            <label>Médico que refiere </label>
                            <input type="text" id="medico_refiere">
                        </div>
                        <div class="col-4">
                            <label>Comorbilidades y medicamentos de uso regular </label>
                            <input type="text" id="comorbilidades">
                        </div>
                        <div class="col-4">
                            <label>Familiar responsable </label>
                            <input type="text" id="nombre_responsable">
                        </div>
                        <div class="col-4">
                            <label>Contacto de familiar responsable </label>
                            <input type="text" id="telefono_contacto">
                        </div>

                        <hr>

                        <h1 class="col-12">Padecimiento actual y evolución</h1>
                        <div class="col-4">
                            <label>Fecha de inicio de síntomas </label>
                            <input type="text" id="fecha_sintomas">
                        </div>
                        <div class="col-4">
                            <label>Fecha de ingreso hospitalario </label>
                            <input type="text" id="fecha_ingreso">
                        </div>
                        <div class="col-4">
                            <label>Diagnóstico de COVID-19 </label>
                            <input type="text" id="prueba_covid">
                        </div>
                        <div class="col-4">
                            <label for="area_cama">Área y cama de hospitalización</label> 
                            <input type="text" class="" id="area_cama" name="area_cama">
                        </div>
                        <div class="col-12">
                            <label>Tratamiento que ha recibido hasta este momento </label>
                            <textarea class="w-100" type="text" id="tratamiento" rows="10"></textarea>
                        </div>
                        <div class="col-12">
                            <label>Evolución y estado actual </label>
                            <textarea class="w-100" type="text" id="evaluacion" rows="25"></textarea>
                        </div>
                        <div class="col-4">
                            <label>Motivo de envío </label>
                            <input type="text" id="motivo_envio">
                        </div>


                        <hr class="col-12">

                        <h1 class="col-12">Datos de contacto </h1>
                        <div class="col-12 formulario">
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <label for="nombre">Nombre</label>
                                    <input type="text"  id="nombre_institucion" name="nombre" disabled="true">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <label for="telefono">Teléfono</label>
                                    <input type="number" id="telefono_contacto_institucion" name="telefono" disabled="true">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <label>Correo</label>
                                    <input type="email" id="correo_contacto_institucion" name="correo" disabled="true">
                                </div>
                            </div>
                            <br>
                        </div>
                        <br>
                        <hr>
                        <h1>Laboratorios de ingreso</h1>
                        <div class="col-12 formulario">
                            <div class="form-row">
                                <div class="col-6">
                                    <h4>Biometría hemática con diferencial</h4>
                                    <br>
                                </div>
                                <div class="col-6">
                                    <h4>Cantidad</h4>
                                    <br>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="eritrocitos">Eritrocitos </label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="eritrocitos" name="eritrocitos" min="2.0" max="10.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>millones/uL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="hemoglobina">Hemoglobina</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="hemoglobina" name="hemoglobina" min="5.0" max="30.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>g/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="hematocrito">Hematocrito</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="hematocrito"  min="20.0" max="65" step="0.1" name="hemglobina" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="hemoglobina_corp" class="checkbox-inline">Hemoglobina Corp Media</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number" id="hemoglobina_corp" name="hemoglobina_corp"  min="10.0" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>pg</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="concentracion_hemoglobina">Concentración Media Hemoglobina</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="concentracion_hemoglobina" name="concentracion_hemoglobina"  min="10.0" max="70.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>g/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="distribucion_erotrocitos">Ancho distribución de Eritrocitos </label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="distribucion_erotrocitos" name="distribucion_erotrocitos"  min="7.0" max="20.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="plaquetas">Plaquetas </label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="plaquetas" name="plaquetas"  min="10.0" max="999.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>miles/uL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="leucocitos" >Leucocitos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="leucocitos" name="leucocitos"  min="2.0" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>miles/uL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="neutrofilos" >Neutrofilos</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="neutrofilos" name="neutrofilos"  min="0" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="linfocitos" >Linfocitos</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="linfocitos" name="linfocitos"  min="0" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="monocitos"> Monocitos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="monocitos" name="monocitos" min="0" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="eosinofilos">Eosinofilos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id= "eosinofilos" name="eosinofilos" min="0" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="basofilos">Basofilos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="basofilos" name="basofilos" min="0" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="neutrofilos" >Neutrofilos</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="neutrofilos_ul" name="neutrofilos"  min="0.1" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>x103/μL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="linfocitos" >Linfocitos</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="linfocitos_ul" name="linfocitos"  min="0.1" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>x103/μL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="monocitos"> Monocitos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="monocitos_ul" name="monocitos" min="0.1" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>x103/μL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="eosinofilos">Eosinofilos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="eosinofilos_ul" name="eosinofilos" min="0.1" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>x103/μL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="basofilos">Basofilos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="basofilos_ul" name="basofilos" min="0.1" max="50.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>x103/μL</label>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-6">
                                    <h4>Química sanguínea</h4>
                                    <br>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="glucosa">Glucosa </label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="glucosa" name="glucosa" min="45" max="500" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="urea">Urea </label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="urea" name="urea" min="10" max="300" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="glucosa">Creatinina</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number" id="creatinina" name="creatinina" min="0.3" max="20.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="acido_urico">Ácido Úrico</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="acido_urico" name="acido_urico" min="2.0" max="10.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="colesterol">Colesterol</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="colesterol" name="colesterol" min="20" max="500" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="trigliceridos">Trigliceridos</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="trigliceridos" name="trigliceridos" min="20.0" max="3000" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="ferritina">Ferritina</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="ferritina" name="ferritina" min="10" max="3000" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mcg/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="dimero_d">Dímero D</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="dimero_d" name="dimero_d" value="0" min="10" max="3000" step="0.1" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>ng/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="troponina_i">Troponina I</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="troponina_i" name="troponina_i"  min="0" max="20" step="0.1"value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>ng/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="ck_mb">CK MB</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="ck_mb" name="ck_mb" min="10" max="500" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>UI/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="ck">CK</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="ck" name="ck" min="10" max="500" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>UI/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="dhl">DHL</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="dhl" name="dhl" min="10" max="3000" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>UI/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="ck_mb">BNP</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="bnp" name="bnp" min="10" max="3000" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>pg/mL</label>
                                </div>
                            </div>
                            <br>
                            <hr>
                            <div class="form-row">
                                <div class="col-6">
                                    <h4>Electrolitos séricos</h4>
                                    <br>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="sodio">Sodio</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="sodio" name="sodio" min="110" max="170" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mEq/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="potasio">Potasio</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="potasio" name="potasio" min="2.5" max="7" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mEq/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="cloro">Cloro</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="cloro" name="cloro" min="70" max="130" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mEq/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="calcio">Calcio total</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="calcio" name="calcio" min="6.5" max="14" step="0.1"value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="magnesio">Magnesio</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number" id="magnesio" name="magnesio" min="1.0" max="4.9" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mEq/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="fosforo">Fósforo</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="fosforo" name="fosforo" min="1.1" max="5" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mEq/L</label>
                                </div>
                            </div>
                            <br>
                            <hr>
                            <div class="form-row">
                                <div class="col-6">
                                    <h4>Pruebas de función hepática</h4>
                                    <br>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="bilirrubina_total">Bilirrubina total</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="bilirrubina_total" name="bilirrubina_total" min="0.0" max="15.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="bilirrubina_directa">Bilirrubina directa</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="bilirrubina_directa" name="bilirrubina_directa" min="0.0" max="15.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="bilirrubina_indirecta">Bilirrubina indirecta</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="bilirrubina_indirecta" name="bilirrubina_indirecta"  min="0.0" max="15.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mg/dL</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="tgo">TGO</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number" id="tgo" name="tgo"  min="10.0" max="5000.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>UI/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="tgp">TGP</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="tgp" name="tgp" min="10.0" max="5000.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>UI/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="fosfatasa_alcalina">Fosfatasa Alcalina</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="fosfatasa_alcalina" name="fosfatasa_alcalina" min="10.0" max="5000.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>UI/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="albumina">Albumina</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="albumina" name="albumina" min="1.0" max="10.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>gd/L</label>
                                </div>
                            </div>
                            <br>

                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="globulinas">Globulinas</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="globulinas" name="globulinas" min="1.0" max="30.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>gd/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="relacion_ag">Relación A/G</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="relacion_ag" name="relacion_ag" min="0.0" max="5.0" step="0.1" value="0" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="proteinas_totales">Proteínas Totales</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="proteinas_totales" name="proteinas_totales" min="1.0" max="30.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>gd/L</label>
                                </div>
                            </div>
                            <br>
                            <hr>
                            <div class="form-row">
                                <div class="col-6">
                                    <h4>Tiempos de coagulación</h4>
                                    <br>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="tp">TP</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="tp" name="tp" min="0" max="30.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>seg</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="tp">TPT</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="tpt" name="tpt" min="0" max="100.0" step="0.1" value="0"  required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>seg</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="tt">TT</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="tt" name="tt" min="0" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>seg</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="inr">INR</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="inr" name="inr" min="0" max="5.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>sin unidades</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="tiempo_sangrado">Tiempo sangrado IVI</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="tiempo_sangrado" name="tiempo_sangrado" min="0" max="30.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>min</label>
                                </div>
                            </div>
                            <br>
                            <hr>
                            <div class="form-row">
                                <div class="col-6">
                                    <h4> Gasometría arterial</h4>
                                    <br>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="ph">pH</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="ph" name="ph" min="6.9" max="7.7" step="0.1" value="0" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="pao2">PaO2</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="pao2" name="pao2" min="40" max="100" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mmHg</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="pco2">pCO2</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="pco2" name="pco2" min="20" max="75" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mmHg</label>
                                </div>
                            </div>
                            <br>   
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="sato2">SatO2</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="sato2" name="sato2" min="40" max="100" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="eb">EB</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="eb" name="eb" value="0" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="hco3">HCO3</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="hco3" name="hco3" min="10" max="40.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mEq/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="lactato">Lactato</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="lactato" min="0.0" max="10.0" step="0.1" name="lactato" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>mmol/L</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-6">
                                    <label for="fio2">FiO2</label> 
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-4">
                                    <input type="number"  id="fio2" name="fio2" min="21" max="100.0" step="0.1" value="0" required>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-2">
                                    <label>%</label>
                                </div>
                            </div>
                            <hr>
                            <div class="form-row">
                                <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                    <label>Descripción u observaciones:</label> 
                                </div>
                                <div class="col-12 col-md-12 col-lg-12 col-xl-12 pn-2">
                                    <textarea type="text" class="w-100" id="observaciones" name="observaciones" rows="10" disabled="true"></textarea>
                                </div>
                            </div>
                            <hr>
                            <div class="form-row">
                                <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                    <h4>Estudios de imágen y descripción</h4> 
                                </div>
                                <div class="col-12 col-md-12 col-lg-12 col-xl-12 pb-2">
                                    <textarea id="estudios_imagen" class="w-100"rows="20" disabled="true"></textarea>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div id="botones" class="row col-12 m-0 p-0 botones">

                    </div>
                </div>
            </div>

        </div>
    </section>

    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>

    <link href="${pathRecursos}/css/ccb.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/ccb/ccb.js"></script>
    

</body>
<%-- 
    Document   : modulo_jornadas_laborales
    Created on : 24/11/2020, 10:24:53 AM
    Author     : global
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- 
========================================
MóDULO DE REPORTING 
========================================
-->
<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_ReporteJornadasLaborales">
    <h3 style="display: block; width: 100%; text-align: left">Reporte de jornadas laborales</h3>
    
    <div id="tabs_jornadas_laborales" class="col-12">
        <ul class="opciones_tabs_jornadas_laborales">
            <li id="tab_en_jornada" class="active"><a href="#tabs_1_jornadas_laborales">Empleados en jornada laboral</a></li>
            <li id="tab_reporte_jornada"><a href="#tabs_2_jornadas_laborales">Generar reporte de jornadas laborales</a></li>
        </ul>
        <div id="tabs_1_jornadas_laborales">
            <div id="inicio-reporte-jornadas-laborales" class="w-100">
                <div class="text-center" style="position: relative">
                    <h6 style="font-size: 2rem; color: #343a40">Empleados en jornada laboral</h6>

                    <button id="btn-refrescar-jornadas" class="btn btn-outline-dark btn-lg" style="position: absolute; top: 0; right: 20px; ">
                        <i class="fas fa-sync-alt"></i>
                    </button>

                </div>

                <div class="row mt-4 mb-5 text-center">
                    <div class="col-md-6 col-sm-12">
                        <p style="font-size: 1.4rem">Entrada</p>
                        <button type="button" class="btn btn-success mr-2">
                            En tiempo <span class="badge badge-light" id="contadorEnTiempo"></span>
                        </button>
                        <button type="button" class="btn btn-warning mr-2">
                            Retardo <span class="badge badge-light" id="contadorRetardo"></span>
                        </button>
                        <button type="button" class="btn btn-danger">
                            Tarde <span class="badge badge-light" id="contadorTarde"></span>
                        </button>
                    </div>
                    <div class="col-md-6 col-sm-12">
                        <p style="font-size: 1.4rem">Salida</p>
                        <button type="button" class="btn btn-info mr-2">
                            Aún en jornada <span class="badge badge-light" id="contadorAunEnJornada"></span>
                        </button>
                        <button type="button" class="btn btn-success mr-2">
                            En tiempo <span class="badge badge-light" id="contadorEnTiempoSalida"></span>
                        </button>
                        <button type="button" class="btn btn-warning mr-2">
                            Antes <span class="badge badge-light" id="contadorRetardoSalida"></span>
                        </button>
                        <button type="button" class="btn btn-danger">
                            Muy antes <span class="badge badge-light" id="contadorTardeSalida"></span>
                        </button>
                    </div>
                </div>

                <div class="empleados-en-jornada-laboral">

                    <div id="sin-empleados-en-jornada" class="d-none">
                        <div class="alert alert-info" role="alert">Aún no se encuentra ningún empleado en jornada laboral!</div>
                    </div>

                    <div style="max-height: 550px; overflow-y: auto" id="con-empleados-en-jornada" class="d-none">

                        <table id="tabla-empleados-en-jornada" class="table table-hover">
                            <thead class="thead-light">
                                <tr class="text-center">
                                    <th>Empleado</th>
                                    <th>Sucursal</th>
                                    <th>Área</th>
                                    <th>Hora de Entrada</th>
                                    <th>Hora Inicio Jornada</th>
                                    <th>Hora Última Desconexión</th>
                                    <th>Hora de Salida</th>
                                    <th>Hora Salida Jornada</th>
                                    <th>Cantidad Desconexiones</th>
                                    <th>Enviar Mensaje</th>
                                    <th>Iniciar Llamada</th>
                                </tr>
                            </thead>
                            <tbody id="cuerpo-tabla-empleados-en-jornada">
                            </tbody>
                        </table>

                    </div>

                </div>
            </div>
        </div>
        <div id="tabs_2_jornadas_laborales">
            <div class="col-12 text-center">
                
                <h6 class="mb-5" style="font-size: 2rem; color: #343a40">Reporte de jornadas laborales</h6>
                
                <form id="form_historia_jornadas" class="row mb-4">

                    <div class="col-md-2 col-sm-12 p-0">
                        <div class="form-group">
                            <label for="fecha_inicio_reporte" class="mr-2">Fecha de inicio</label>
                            <input type="date" class="form-control" id="fecha_inicio_reporte">
                        </div>
                    </div>

                    <div class="col-md-2 col-sm-12 p-0">
                        <div id="contenedor_fecha_final" class="form-group mx-sm-3">
                        <label for="fecha_fin_reporte" class="mr-2">Fecha de fin</label>
                        <input type="date" class="form-control" id="fecha_fin_reporte">
                      </div>
                    </div>

                    <div class="col-md-3 col-sm-12">
                        <div class="form-group">
                            <label class="mr-2" for="tipo_busqueda">Tipo de búsqueda</label>
                            <select class="form-control-plaintext input p-2 text-dark m-0 mb-1" name="tipo_busqueda" id="tipo_busqueda">
                                <option selected disabled>Seleccionar...</option>
                                <option value="SUCURSAL">Por sucursal</option>
                                <option value="AREA">Por área</option>
                                <option value="EMPLEADO">Por empleado</option>
                                <option value="TODOS">Todos</option>
                            </select>
                        </div>
                    </div>

                    <!-- SELECT DE SUCURSALES -->
                    <div id="contenedor-select-sucursales" class="col-md-3 col-sm-12 d-none">
                        <div class="form-group">
                            <label class="mr-2" for="sucursal_jornadas">Seleccionar sucursal</label>
                            <select name="sucursal_jornadas" id="sucursal_jornadas" class="form-control-plaintext input p-2 text-dark m-0 mb-1">
                                <option selected disabled>Seleccionar...</option>
                            </select>
                        </div>
                    </div>

                    <!-- SELECT DE AREAS -->
                    <div id="contenedor-select-areas" class="col-md-3 col-sm-12 d-none">
                        <div class="form-group">
                            <label class="mr-2" for="area_jornadas">Seleccionar área</label>
                            <select name="area_jornadas" id="area_jornadas" class="form-control-plaintext input p-2 text-dark m-0 mb-1">
                                <option selected disabled>Seleccionar...</option>
                            </select>
                        </div>
                    </div>

                    <!-- SELECT DE EMPLEADOS -->
                    <div id="contenedor-select-empleados" class="col-md-3 col-sm-12 d-none">
                        <div class="form-group">
                            <label class="mr-2" for="empleado_jornadas">Seleccionar empleado</label>
                            <select name="empleado_jornadas" id="empleado_jornadas" class="form-control-plaintext input p-2 text-dark m-0 mb-1">
                                <option selected disabled>Seleccionar...</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-md-1 p-3 d-flex">
                        <button class="btn btn-dark btn-block"><i class="fas fa-search"></i></button>
                    </div>

                    <div class="col-md-1 p-3 d-flex">
                        <button type="button" style="background-color: darkgreen; border-color: darkgreen" id="botonDescargaReporteJornada" class="btn btn-dark btn-block d-none"><i class="fas fa-file-excel"></i></button>
                    </div>

                </form>
            </div>
            
            <div id="inicio_jornadas_laborales" class="jumbotron">
                <h1 class="display-4">Generar reporte de jornadas laborales!</h1>
                <p class="lead mt-3">Elige los parámetros de búsqueda para generar el reporte.</p>
                <hr class="my-4">
                <p>Pasos para generarlo.</p>
                
                <ul>
                    <li>Elige un rango de búsqueda </li>
                    <li>Selecciona un tipo de búsqueda</li>
                    <li style="list-style: none;">
                        <ul>
                            <li>Por sucursal</li>
                            <li>Por área</li>
                            <li>Por empleado</li>
                            <li>Todos (filtrará todos los empleados de la empresa)</li>
                        </ul>
                    </li>
                </ul>
                
                <p style="font-style: italic;">El tiempo de generación dependerá del rango de búsqueda y de la cantidad de empleados que se seleccione</p>
                
            </div>
            
            <div id="resultado-busqueda-jornadas" class="d-none col-12">

                <div id="tablas_resultados" class="accordion">

                </div>

                <div class="d-none" id="resultados-exportar-excel">

                </div>

            </div>
        </div>
    </div>

</div>
<!-- 
========================================
FINAL MóDULO DE REPORTING
========================================
-->

<spring:url value="${pathRecursos}/css/Empresa/modulo_jornadas_laborales.css" var="modulo_jornadas_laboralesCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_jornadas_laborales.js" var="modulo_jornadas_laboralesJS" />
<link href="${modulo_jornadas_laboralesCSS}" rel="stylesheet"/>
<script src="${modulo_jornadas_laboralesJS}" ></script>
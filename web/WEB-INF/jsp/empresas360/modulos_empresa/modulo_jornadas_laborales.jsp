<%-- 
    Document   : modulo_jornadas_laborales
    Created on : 24/11/2020, 10:24:53 AM
    Author     : global
--%>

<!-- 
========================================
M�DULO DE REPORTING 
========================================
-->
<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_ReporteJornadasLaborales">
    <h3>Reporte de jornadas laborales</h3>


    <div class="col-12 text-center">
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
                    <label class="mr-2" for="tipo_busqueda">Tipo de b�squeda</label>
                    <select class="form-control-plaintext input p-2 text-dark m-0 mb-1" name="tipo_busqueda" id="tipo_busqueda">
                        <option selected disabled>Seleccionar...</option>
                        <option value="SUCURSAL">Por sucursal</option>
                        <option value="AREA">Por �rea</option>
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
                    <label class="mr-2" for="area_jornadas">Seleccionar �rea</label>
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
    
    <div id="inicio-reporte-jornadas-laborales" class="w-100">
        <div class="text-center">
            <h5 style="font-size: 2rem;">Empleados en jornada laboral</h5>
        </div>
        <div class="empleados-en-jornada-laboral">
                
            <div class="sin-empleados-en-jornada d-none">
                <div class="alert alert-info" role="alert">A�n no se encuentra ning�n empleado en jornada laboral!</div>
            </div>

            <div class="con-empleados-en-jornada d-none">

                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Empleado</th>
                            <th>Sucursal</th>
                            <th>�rea</th>
                            <th>Hora de entrada</th>
                            <th>Enviar mensaje</th>
                        </tr>
                    </thead>
                    <tbody id="cuerpo-tabla-empleados-en-jornada">
                    </tbody>
                </table>

            </div>

        </div>
    </div>

    <div id="resultado-busqueda-jornadas" class="d-none col-12">

        <div id="tablas_resultados" class="accordion">

        </div>

        <div class="d-none" id="resultados-exportar-excel">

        </div>

    </div>

</div>
<!-- 
========================================
FINAL M�DULO DE REPORTING
========================================
-->

<spring:url value="${pathRecursos}/css/Empresa/modulo_jornadas_laborales.css" var="modulo_jornadas_laboralesCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_jornadas_laborales.js" var="modulo_jornadas_laboralesJS" />
<link href="${modulo_jornadas_laboralesCSS}" rel="stylesheet"/>
<script src="${modulo_jornadas_laboralesJS}" ></script>
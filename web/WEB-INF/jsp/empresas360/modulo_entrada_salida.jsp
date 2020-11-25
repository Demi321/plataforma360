<%-- 
    Document   : modulo_comunicacion
    Created on : 6/11/2020, 06:02:00 PM
    Author     : moises
--%>

<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_EntradaySalida">
    <h3>Registro Historico Check</h3>

    <div class="row col-12 m-0 p-2 pt-3">

        <div class="col-12 col-sm-12 col-md-12 p-0">
            <h5 class="nombre_completo"></h5>
            <input class="btn btn-danger my-3" type="button" value="Iniciar Jornada Laboral" id="iniciar_jornada_laboral">
            <div class="row m-0 col-12 m-0 p-2">
                <label class="col-2 p-0">Horario de ingreso: </label> <input type="text" disabled="true" id="ing" class="col-8" />

                <hr class="w-100" />
                <label class="col-2 p-0">Reporte de actividades: </label>
                <textarea disabled class="w-100 my-3" cols="100" rows="1" id="rep"> </textarea>

                <div style="text-align: right;">
                    <input class="btn btn-danger d-none" type="button" value="Guardar Reporte y Finalizar Jornada" style="width: fit-content;" id="guardarreporte" />
                </div>
            </div>


            <!-- AREA DE REPORTE E HISTORIAL -->
            <div class="row m-0 p-2 p2-2">

                <div class="col-12">
                    <h4 style="color: black; text-align: left"> Consultar historial de jornadas laborales </h4>
                </div>

                <div class="col-12 text-center">
                    <form id="form_historia_jornadas" class="form-inline mt-4 justify-content-center">

                        <div class="form-group mb-2">
                            <label for="fecha_inicio_reporte" class="mr-3">Fecha de inicio</label>
                            <input type="date" class="form-control" id="fecha_inicio_reporte">
                        </div>
                        <div id="contenedor_fecha_final" class="form-group mx-sm-3 mb-2">
                            <label for="fecha_fin_reporte" class="mr-3">Fecha de fin</label>
                            <input type="date" class="form-control" id="fecha_fin_reporte">
                        </div>

                        <div class="form-group mb-2">
                            <button class="btn btn-primary ml-3"><i class="fas fa-search"></i></button>
                            <button id="botonDescargaReporteJornada" class="btn btn-dark ml-3 d-none"><i class="fas fa-file-excel"></i></button>
                        </div>

                    </form>
                </div>

            </div>

            <div id="resultado-busqueda-jornadas" class="d-none mt-4">

                <div id="con-resultados-jornadas" class="table-responsive d-none">

                    <table class="table table-hover table-striped>"
                           <thead class="thead-dark"">
                        <tr>
                            <th>Día</th>
                            <th>Fecha</th>
                            <th>Hora entrada</th>
                            <th>Hora salida</th>
                        </tr>
                        </thead>
                        <tbody id="cuerpo-tabla-jornadas"></tbody>
                    </table>

                </div>

                <div id="sin-resultados-jornadas" class="d-none">
                    <div style="font-size: 1.5rem;" class="alert alert-dark text-center" role="alert">No se encontraron resultados en este periodo</div>
                </div>

                <table class="d-none" id="resultados-exportar-excel">

                </table>

            </div>
            <!-- FINAL AREA DE REPORTE E HISTORIAL -->

        </div>
    </div>
</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_entrada_salida.css" var="modulo_entrada_salidaCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_entrada_salida.js" var="modulo_entrada_salidaJS" />
<link href="${modulo_entrada_salidaCSS}" rel="stylesheet"/>
<script src="${modulo_entrada_salidaJS}" ></script>


<div id="video_drag" class="d-none" style="position: fixed; background-color: rgb(241, 241, 241); border: 2px solid rgb(211, 211, 211); text-align: center; top: 75px; left:calc(100% - 200px) ; z-index: 1000;">
    <!-- Include a header DIV with the same name as the draggable DIV, followed by "header" -->
    <div id="video_drag_header" style="padding: 10px; cursor: move; z-index: 10; background-color: #495057; color: #fff; max-width: 350px"><h7 class="nombre_completo" style="font: bold 1.1rem Arial;
                                                                                                                                               overflow: hidden;
                                                                                                                                               text-overflow: ellipsis;
                                                                                                                                               white-space: nowrap;
                                                                                                                                               display: block;"></h7></div>
    <div id="conectado_jornada_laboral" style="min-height: 150px; min-width: 150px; width: 100%; height: 100%; overflow: hidden;" ></div>
    <div id="video_drag_footer" style="padding: 5px; z-index: 10; background-color: #495057; color: #fff;" class="row col-12 m-0"></div>
</div>
<div class="d-none" id="mensaje-cargando-proceso">
    <div class="mensaje_guardando_logo">Iniciando jornada ....</div>
</div>
<div id="tooltip" role="tooltip" style="    
     margin: 0px;
     width: 150px;
     padding: 10px 15px;">
    Manten presionado el boton del microfono para enviar audio
    <div id="arrow" data-popper-arrow></div>
</div>
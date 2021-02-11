<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 entrada_salida" id="base_modulo_${id_menu}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Mi Jornada Laboral 
                    </span>
                </div>

                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>



                        <div class="col-5 p-3 d-flex align-items-center justify-content-center">
                            <div class="p-3 marco">
                                <div class="h-100 w-100" id="entrada_salida_empleado_img"></div>
                            </div>
                        </div>
                        <div class="col-7 p-1">

                            <div class="h3 font-weight-bold text-danger">Bienvenido</div>
                            <div class="h3 entrada_salida_nombre_empleado"></div>
                            <div class="py-2"><strong>Puesto de trabajo: </strong><span id="entrada_salida_puesto"> - </span></div>
                            <div class="py-2"><strong>Número de empleado </strong><span id="entrada_salida_numero_empleado"> - </span></div>

                            <div class="py-2"><i class="fas fa-map-marker-alt"></i><strong id="entrada_salida_municipio"> - </strong><span id="entrada_salida_estado"> - </span></div>

                        </div>
                        <div class="col-10 mx-auto text-center">
                            <div class="entrada_salida_logo">
                                <div id="entrada_salida_logo"></div>
                            </div>
                            <div id="entrada_salida_clock"></div>

                            <div class="d-flex align-items-center justify-content-center tiempo_jornada_clock">
                                <i class="fas fa-tv"></i><div id="tiempo_jornada_clock"></div>
                            </div>
                            <div class="d-flex align-items-center justify-content-center tiempo_jornada_clock mb-3">
                                <div class="d-block text-center">
                                    <p>Tiempo de jornada</p>
                                </div>
                            </div>
                            <button type="button" class="btn btn-primary iniciar_jornada d-none" id="entrada_salida_iniciar_jornada"> <i class="fas fa-play"></i> Iniciar jornada</button>
                            <button type="button" class="btn btn-warning pausar_jornada d-none" id="entrada_salida_pausar_jornada"> <i class="fas fa-pause"></i> Pausar Jornada (Descanso)</button>
                            <button type="button" class="btn btn-warning reanudar_jornada d-none" id="entrada_salida_reanudar_jornada"> <i class="fas fa-play"></i> Reanudar Jornada (Descanso)</button>
                            <button type="button" class="btn btn-danger finalizar_jornada d-none"  id="entrada_salida_finalizar_jornada"> <i class="fas fa-sign-out-alt"></i> Finalizar Jornada</button>

                            <div class="row col-12 m-0 p-0 mt-3">
                                <div class="col-4 p-0">
                                    <div class="progress">
                                        <span class="state1"><i class="fas fa-circle"></i></span>
                                        <div class="d-none state_walking_1"><i class="fas fa-walking"></i></i></div>
                                        <div class="progress-bar bg-success progress-bar1" role="progressbar"></div>
                                    </div>
                                </div>
                                <div class="col-4 p-0">
                                    <div class="progress">
                                        <span class="state2"><i class="fas fa-circle"></i></span>
                                        <div class="d-none state_walking_2"><i class="fas fa-walking"></i></i></div>
                                        <div class="progress-bar bg-success progress-bar2" role="progressbar"></div>
                                    </div>
                                </div>
                                <div class="col-4 p-0">
                                    <div class="progress">
                                        <span class="state3"><i class="fas fa-circle"></i></span>
                                        <div class="d-none state_walking_3"><i class="fas fa-walking"></i></i></div>
                                        <div class="progress-bar bg-success progress-bar3" role="progressbar"></div>
                                        <span class="state4"><i class="fas fa-circle"></i></span>
                                        <div class="d-none state_walking_4"><i class="fas fa-walking"></i></i></div>
                                    </div>
                                </div>


                            </div>
                        </div>

                    </div>
                    <div class="noticias row col-12 p-3 m-0 "></div>

                    <!-- AREA DE REPORTE E HISTORIAL -->
                    <div class="row m-0 p-2 p2-2 d-none">

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
    </div>

</div>
    
    
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
<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresa_empleado/entrada_salida/entrada_salida.css" var="modulo_entrada_salidaCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/entrada_salida/entrada_salida.js" var="modulo_entrada_salidaJS" />
<link href="${modulo_entrada_salidaCSS}" rel="stylesheet"/>
<script src="${modulo_entrada_salidaJS}" ></script>
<script>
    init_entrada_salida("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>


<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="${pathRecursos}/js/agenda/core/main.css" var="core_mainCSS" />
<spring:url value="${pathRecursos}/js/agenda/daygrid/main.css" var="daygrid_mainCSS" />
<spring:url value="${pathRecursos}/js/agenda/list/main.css" var="list_mainCSS" />
<spring:url value="${pathRecursos}/js/agenda/timegrid/main.css" var="timegrid_mainCSS" />
<link href="${core_mainCSS}" rel="stylesheet"/>
<link href="${daygrid_mainCSS}" rel="stylesheet"/>
<link href="${list_mainCSS}" rel="stylesheet"/>
<link href="${timegrid_mainCSS}" rel="stylesheet"/>

<spring:url value="${pathRecursos}/js/agenda/core/main.js" var="core_mainJS" />
<spring:url value="${pathRecursos}/js/agenda/interaction/main.js" var="interaction_mainJS" />
<spring:url value="${pathRecursos}/js/agenda/daygrid/main.js" var="daygrid_mainJS" />
<spring:url value="${pathRecursos}/js/agenda/list/main.js" var="list_mainJS" />
<spring:url value="${pathRecursos}/js/agenda/timegrid/main.js" var="timegrid_mainJS" />

<script src="${core_mainJS}" ></script>
<script src="${interaction_mainJS}" ></script>
<script src="${daygrid_mainJS}" ></script>
<script src="${list_mainJS}" ></script>
<script src="${timegrid_mainJS}" ></script>

<div class="row col-12 m-0 p-0 h-100 agenda" id="base_modulo_${id}">
    <div class="container py-4">
        <div class="row">
            <div class="col-12 col-md-10 bg-light">
                <!-- CALENDARIO -->

                <div id='calendar' style="max-width: 900px;margin: 40px auto;"></div>
            </div>
        </div>
    </div>


    <!-- MODALS -->

    <!-- MODAL PARA MOSTRAR FORMULARIO PARA NUEVO EVENTO -->
    <div class="modal fade" id="nuevoModal" tabindex="-1" role="dialog" aria-labelledby="nuevoEventoModalLabel" aria-hidden="true">
            <!-- Pantalla lg -->
        <div class="modal-dialog modal-lg" role="document">
            <!-- CONTENIDO DEL MODAL -->
            <div class="modal-content">
                    <!-- HEADER -->
                    <div class="modal-header">
                    <h5 class="modal-title" id="nuevoEventoModalLabel">Nuevo Evento</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                            </button>
                    </div>
                    <!-- FORMULARIO CON ID "nuevoEvento" -->
                    <form id="nuevoEvento">
                            <!-- BODY DEL MODAL CON LOS INPUTS DEL EVENTO -->
                            <div class="modal-body">
                                    <!-- CONTAINER  -->
                                    <div class="container">
                                            <!-- ROW -->
                                            <div class="row">
                                                    <!-- TITULO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="titulo">Titulo</label>
                                                            <input class="form-control" type="text" id="titulo" required="">
                                                    </div>
                                                    <!-- DESCRIPCION -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="descripcion">Descripción</label>
                                                            <input class="form-control" type="text" id="descripcion">
                                                    </div>
                                                    <!-- DIRECCION -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="direccion">Dirección</label>
                                                            <input class="form-control" type="text" id="direccion">
                                                    </div>
                                                    <!-- LATITUD -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="latitud">Latitud</label>
                                                            <input class="form-control" type="text" id="latitud">
                                                    </div>
                                                    <!-- LONGITUD -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="longitud">Longitud</label>
                                                            <input class="form-control" type="text" id="longitud">
                                                    </div>
                                                    <!-- TIPO DE EVENTO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="tipoevento">Tipo de evento</label>
                                                            <input class="form-control" type="text" id="tipoevento">
                                                    </div>
                                                    <!-- FECHA DE REGISTRO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fecharegistro">Fecha Registro</label>
                                                            <input class="form-control" type="date" required="" id="fecharegistro">
                                                    </div>
                                                    <!-- FECHA DE INICIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fechainicio">Fecha de Inicio</label>
                                                            <input class="form-control" type="date" required="" id="fechainicio">
                                                    </div>
                                                    <!-- HORA DE INICIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="horainicio">Hora de Inicio</label>
                                                            <input class="form-control" type="time" id="horainicio">
                                                    </div>
                                                    <!-- FECHA FIN -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fechafin">Fecha Fin</label>
                                                            <input class="form-control" type="date" required="" id="fechafin">
                                                    </div>
                                                    <!-- HORA FIN -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="horafin">Hora Fin</label>
                                                            <input class="form-control" type="time" id="horafin">
                                                    </div>
                                                    <!-- FECHA RECORDATORIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fecharecordatorio">Fecha Recordatorio</label>
                                                            <input class="form-control" type="date" id="fecharecordatorio">
                                                    </div>
                                                    <!-- HORA RECORDATORIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="horarecordatorio">Hora Recordatorio</label>
                                                            <input class="form-control" type="time" id="horarecordatorio">
                                                    </div>
                                                    <!-- TEMPORIZADOR -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="temporizador">Temporizador</label>
                                                            <input class="form-control" type="time" id="temporizador">
                                                    </div>
                                                    <!-- RECURRENTE -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="recurrente">Recurrente</label>
                                                            <input class="form-control" type="text" id="recurrente">
                                                    </div>
                                                    <!-- PERIODO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="periodo">Periodo</label>
                                                            <input class="form-control" type="text" id="periodo">
                                                    </div>
                                                    <!-- URL -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="url">URL</label>
                                                            <input class="form-control" type="url" id="url">
                                                    </div>
                                            </div>
                                    </div>
                                </div>
                                <!-- FOOTER PARA BOTONES -->
                            <div class="modal-footer">
                                    <!-- BOTON CANSELAR/QUITAR MODAL -->
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                    <!-- ENVIAR FORMULARIO -->
                                    <button type="submit" class="btn btn-primary">Guardar cambios</button>
                            </div>
                    </form>
            </div>
        </div>
    </div>

    <!-- MODAL DE EVENTOS DEL DíA -->
    <div class="modal fade" id="eventosHoyModal" tabindex="-1" role="dialog" aria-labelledby="hoyEventoModalLabel" aria-hidden="true">
            <!-- MODAL LG -->
        <div class="modal-dialog modal-lg">
            <!-- CONTENIDO DEL MODAL -->
            <div class="modal-content">
                    <!-- MODAL HEADER -->
                    <div class="modal-header">
                    <h5 class="modal-title" id="hoyEventoModalLabel">Eventos Hoy</h5>
                    <!-- BOTON CLOSE -->
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                            </button>
                    </div>
                    <!-- BODY DEL MODAL QUE SE LLENARA A TRAVES DEL AJAX -->
                    <div class="modal-body" id="bodyEventosDia">
                            <div class="mx-auto justify-content-center">
                                    <h1>Eventos del día:</h1>
                            </div>
                    </div>
                    <!-- FOOTER -->
                    <div class="modal-footer">
                            <!-- BOTON CERRAR MODAL -->
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
            </div>
        </div>
    </div>

    <!-- MOSTRAR EVENTO -->
    <div class="modal fade" id="showEventoModal" tabindex="-1" role="dialog" aria-labelledby="showEventoModalLabel" aria-hidden="true">
            <!-- MODAL lg -->
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                    <!-- HEADER -->
                    <div class="modal-header">
                    <h5 class="modal-title" id="showEventoModalLabel">Evento</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                            </button>
                    </div>
                    <!-- BODY DEL MODAL QUE SE LLENARA CON EL AJAX -->
                    <div class="modal-body" id="bodyEvento"></div>
                    <!-- FOOTER DEL MODAL -->
                    <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
            </div>
        </div>
    </div>


    <!-- MODAL PARA ACTUALIZAR UN EVENTO -->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateEventoModalLabel" aria-hidden="true">
            <!-- MODAL XL -->
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                    <!-- ACTUALIZAR -->
                    <div class="modal-header">
                    <h5 class="modal-title" id="updateEventoModalLabel">Actualizar Evento</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                            </button>
                    </div>
                    <!-- FORM CON ID updateEvento -->
                    <form id="updateEvento">
                            <!-- BODY CON LOS INPUTS DEL EVENTO -->
                            <div class="modal-body">
                                    <!-- CONTAINER -->
                                    <div class="container">
                                            <!-- ROW -->
                                            <div class="row">
                                                    <!-- TITULO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="titulo">Titulo</label>
                                                            <input class="form-control" type="text" id="update_titulo" required="">
                                                            <!-- ID DEL EVENTO
                                                             -->
                                                            <input type="hidden" id="evento_id" required="">
                                                    </div>
                                                    <!-- DESCRIPCION -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="descripcion">Descripción</label>
                                                            <input class="form-control" type="text" id="update_descripcion">
                                                    </div>
                                                    <!-- DIRECCION -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="direccion">Dirección</label>
                                                            <input class="form-control" type="text" id="update_direccion">
                                                    </div>
                                                    <!-- LATITUD -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="latitud">Latitud</label>
                                                            <input class="form-control" type="text" id="update_latitud">
                                                    </div>
                                                    <!-- LONGITUD -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="longitud">Longitud</label>
                                                            <input class="form-control" type="text" id="update_longitud">
                                                    </div>
                                                    <!-- TIPO EVENTO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="tipoevento">Tipo de evento</label>
                                                            <input class="form-control" type="text" id="update_tipoevento">
                                                    </div>
                                                    <!-- FECHA DE REGISTRO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fecharegistro">Fecha Registro</label>
                                                            <input class="form-control" type="date" id="update_fecharegistro">
                                                    </div>
                                                    <!-- FECHA DE INICIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fechainicio">Fecha de Inicio</label>
                                                            <input class="form-control" type="date" id="update_fechainicio">
                                                    </div>
                                                    <!-- HORA DE INICIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="horainicio">Hora de Inicio</label>
                                                            <input class="form-control" type="time" id="update_horainicio">
                                                    </div>
                                                    <!-- FECHA FIN -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fechafin">Fecha Fin</label>
                                                            <input class="form-control" type="date" id="update_fechafin">
                                                    </div>
                                                    <!-- HORA FIN -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="horafin">Hora Fin</label>
                                                            <input class="form-control" type="time" id="update_horafin">
                                                    </div>
                                                    <!-- FECHA RECORDATORIO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="fecharecordatorio">Fecha Recordatorio</label>
                                                            <input class="form-control" type="date" id="update_fecharecordatorio">
                                                    </div>
                                                    <!-- Hora recordatorio -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="horarecordatorio">Hora Recordatorio</label>
                                                            <input class="form-control" type="time" id="update_horarecordatorio">
                                                    </div>
                                                    <!-- TEMPORIZADOR -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="temporizador">Temporizador</label>
                                                            <input class="form-control" type="time" id="update_temporizador">
                                                    </div>
                                                    <!-- RECU RRENTE-->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="recurrente">Recurrente</label>
                                                            <input class="form-control" type="text" id="update_recurrente">
                                                    </div>
                                                    <!-- PERIODO -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="periodo">Periodo</label>
                                                            <input class="form-control" type="text" id="update_periodo">
                                                    </div>
                                                    <!-- URL -->
                                                    <div class="col-12 col-md-4 mt-5">
                                                            <label for="url">URL</label>
                                                            <input class="form-control" type="url" id="update_url">
                                                    </div>
                                            </div>
                                    </div>
                                </div>
                                <!-- FOOTER -->
                            <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                            </div>
                    </form>
            </div>
        </div>
    </div>

    <!-- ELIMINAR EVENTO -->
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteEventoModalLabel" aria-hidden="true">
            <!-- MODAL POR DEFAULT -->
        <div class="modal-dialog">
            <!-- CONTENT MODAL -->
            <div class="modal-content">
                <!-- HEADER -->
                <div class="modal-header">
                <h5 class="modal-title" id="deleteEventoModalLabel">Eliminar Evento</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                        </button>
                </div>
                <!-- FORMULARIO CON ID "deleteEvento" -->
                <form id="deleteEvento">
                    <!-- BODY  -->
                    <div class="modal-body">
                            <!-- CONTAINER -->
                            <div class="container">
                                    <!-- INPUT HIDDEN CON EL EVENTO_ID -->
                                    <input type="hidden" id="evento" required="">
                                    <!-- ROW CON ALERTA PARA NOTIFICAR QUE NO SE PODRA RECUPERAR -->
                                    <div class="row">
                                            Eliminar registro (ya no lo podrás recuperar)
                                    </div>
                            </div>
                        </div>
                        <!-- FOOTER PARA BOTONERAS -->
                    <div class="modal-footer">
                        <!-- BOTON CERRAR -->
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                        <!-- BOTON ENVIAR FORMULARIO -->
                        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
agregar_menu(${json});

</script>

<spring:url value="${pathRecursos}/empresas360/agenda/agenda.css" var="modulo_agendaCSS" />
<spring:url value="${pathRecursos}/empresas360/agenda/agenda.js" var="modulo_agendaJS" />
<link href="${modulo_agendaCSS}" rel="stylesheet"/>
<script src="${modulo_agendaJS}" ></script>
<script>
//    init_agenda("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_agenda(${json});
</script>
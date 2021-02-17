
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
            <div class="col-12 col-md-2 d-flex flex-column justify-content-center">
                <!-- BOTONES -->
                <button type="button" class="btn btn-success mt-3 p-auto" data-bs-toggle="modal" data-bs-target="#nuevoModal">Nuevo evento</button>
                <button type="button" class="btn btn-info mt-3 p-auto"  data-bs-toggle="modal" data-bs-target="#eventosHoyModal">Eventos de hoy</button>
            </div>
            <div class="col-12 col-md-10 bg-light">
                <!-- CALENDARIO -->

                <div id='calendar' style="max-width: 900px;margin: 40px auto;"></div>
            </div>
        </div>
    </div>


    <!-- Modals -->

    <!-- Nuevo Evento -->
    <div class="modal fade" id="nuevoModal" tabindex="-1" aria-labelledby="nuevoEventoModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-fullscreen">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="nuevoEventoModalLabel">Nuevo Evento</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="nuevoEvento">
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="titulo">Titulo</label>
                                    <input class="form-control" type="text" id="titulo" required="">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="descripcion">Descripción</label>
                                    <input class="form-control" type="text" id="descripcion">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="direccion">Dirección</label>
                                    <input class="form-control" type="text" id="direccion">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="latitud">Latitud</label>
                                    <input class="form-control" type="text" id="latitud">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="longitud">Longitud</label>
                                    <input class="form-control" type="text" id="longitud">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="tipoevento">Tipo de evento</label>
                                    <input class="form-control" type="text" id="tipoevento">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fecharegistro">Fecha Registro</label>
                                    <input class="form-control" type="date" required="" id="fecharegistro">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fechainicio">Fecha de Inicio</label>
                                    <input class="form-control" type="date" required="" id="fechainicio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="horainicio">Hora de Inicio</label>
                                    <input class="form-control" type="time" id="horainicio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fechafin">Fecha Fin</label>
                                    <input class="form-control" type="date" required="" id="fechafin">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="horafin">Hora Fin</label>
                                    <input class="form-control" type="time" id="horafin">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fecharecordatorio">Fecha Recordatorio</label>
                                    <input class="form-control" type="date" id="fecharecordatorio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="horarecordatorio">Hora Recordatorio</label>
                                    <input class="form-control" type="time" id="horarecordatorio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="temporizador">Temporizador</label>
                                    <input class="form-control" type="time" id="temporizador">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="recurrente">Recurrente</label>
                                    <input class="form-control" type="text" id="recurrente">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="periodo">Periodo</label>
                                    <input class="form-control" type="text" id="periodo">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="url">URL</label>
                                    <input class="form-control" type="url" id="url">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer" style="

                         height: 100px;
                         bottom: 0;
                         width: 100%;
                         ">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Eventos de hoy -->
    <div class="modal fade" id="eventosHoyModal" tabindex="-1" aria-labelledby="hoyEventoModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-fullscreen">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="hoyEventoModalLabel">Eventos Hoy</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="bodyEventosDia">
                    Eventos del día

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- MOSTRAR EVENTO -->
    <div class="modal fade" id="showEventoModal" tabindex="-1" aria-labelledby="showEventoModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="showEventoModalLabel">Evento</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="bodyEvento">


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Actualizar Evento -->
    <div class="modal fade" id="updateModal" tabindex="-1" aria-labelledby="updateEventoModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="updateEventoModalLabel">Actualizar Evento</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="updateEvento">
                    <div class="modal-body">
                        <div class="container">
                            <div class="row">
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="titulo">Titulo</label>
                                    <input class="form-control" type="text" id="update_titulo" required="">
                                    <input type="hidden" id="evento_id" required="">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="descripcion">Descripción</label>
                                    <input class="form-control" type="text" id="update_descripcion">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="direccion">Dirección</label>
                                    <input class="form-control" type="text" id="update_direccion">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="latitud">Latitud</label>
                                    <input class="form-control" type="text" id="update_latitud">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="longitud">Longitud</label>
                                    <input class="form-control" type="text" id="update_longitud">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="tipoevento">Tipo de evento</label>
                                    <input class="form-control" type="text" id="update_tipoevento">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fecharegistro">Fecha Registro</label>
                                    <input class="form-control" type="date" id="update_fecharegistro">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fechainicio">Fecha de Inicio</label>
                                    <input class="form-control" type="date" id="update_fechainicio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="horainicio">Hora de Inicio</label>
                                    <input class="form-control" type="time" id="update_horainicio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fechafin">Fecha Fin</label>
                                    <input class="form-control" type="date" id="update_fechafin">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="horafin">Hora Fin</label>
                                    <input class="form-control" type="time" id="update_horafin">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="fecharecordatorio">Fecha Recordatorio</label>
                                    <input class="form-control" type="date" id="update_fecharecordatorio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="horarecordatorio">Hora Recordatorio</label>
                                    <input class="form-control" type="time" id="update_horarecordatorio">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="temporizador">Temporizador</label>
                                    <input class="form-control" type="time" id="update_temporizador">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="recurrente">Recurrente</label>
                                    <input class="form-control" type="text" id="update_recurrente">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="periodo">Periodo</label>
                                    <input class="form-control" type="text" id="update_periodo">
                                </div>
                                <div class="col-12 col-md-4 mt-5">
                                    <label for="url">URL</label>
                                    <input class="form-control" type="url" id="update_url">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- ELIMINAR EVENTO -->
    <div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteEventoModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteEventoModalLabel">Eliminar Evento</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="deleteEvento">
                    <div class="modal-body">
                        <div class="container">
                            <input type="hidden" id="evento" required="">
                            <div class="row">
                                Eliminar registro (ya no lo podrás recuperar)
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
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
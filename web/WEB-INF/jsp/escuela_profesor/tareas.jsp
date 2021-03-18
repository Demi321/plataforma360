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

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria" id="base_modulo_${id}">
   <div class="container py-4">
        <div class="row">
            <div class="col-12 col-md-10 bg-light">
                <!-- CALENDARIO -->

                <div id='calendar' style="max-width: 900px;margin: 40px auto;"></div>
            </div>
        </div>
    </div>
     <!-- MODALS-->    
    <!-- MODAL PARA MOSTRAR FORMULARIO PARA INFORMACION DE TAREA -->
    <div class="modal fade" id="entregaModal" tabindex="-1" role="dialog" aria-labelledby="nuevoEventoModalLabel" aria-hidden="true">
            <!-- Pantalla lg -->
        <div class="modal-dialog modal-lg" role="document">
            <!-- CONTENIDO DEL MODAL -->
            <div class="modal-content">
                <!-- HEADER -->
                <div class="modal-header">
                <h5 class="modal-title" id="nuevoEventoModalLabel">Informacion de Tarea</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                        </button>
                </div>
                <!-- FORMULARIO CON ID "nuevoEvento" -->
                <!-- BODY DEL MODAL CON LOS INPUTS DEL EVENTO -->
                <form id="emailEvento">
                    <div class="modal-body">
                        <!-- CONTAINER  -->
                        <div class="container">
                            <!-- ROW -->
                            <div class="row">
                                <!-- SUPERVISOR_ID -->
                                <!-- <div class="col-12 col-md-4 mt-5">
                                        <label for="supervisor_id">supervisor_id</label>
                                        <input class="form-control" type="number" id="supervisor_id">
                                </div> -->

                                <!-- TITULO -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label for="titulo">Titulo</label>
                                        <input class="form-control" type="text" id="titulo" required="" value="Tarea n">
                                </div>
                                <!-- DESCRIPCION -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label for="descripcion">Descripción</label>
                                        <input class="form-control" type="text" id="descripcion" value="Dexcripcion">
                                </div>
                                <!-- FECHA -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label for="direccion">Fecha de Entrega</label>
                                        <input class="form-control" type="date" required="" id="fechaentrega">
                                </div>
                                <!-- Materia -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label for="direccion">Materia</label>
                                        <select class="form-control" name="tarea" id="tarea" placeholder="Seleccione un valor" required="">
                                            <option disabled="true" selected="true" value="">Seleccione un valor</option>
                                            <option value="1">Materia1</option>
                                            <option value="2">Materia2</option>
                                        </select>
                                </div>
                                <!-- Grupo -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label for="direccion">Grupo</label>
                                        <select class="form-control" name="tarea" id="tarea" placeholder="Seleccione un valor" required="">
                                            <option disabled="true" selected="true" value="">Seleccione un valor</option>
                                            <option value="1">Grupo1</option>
                                            <option value="2">Grupo2</option>
                                        </select>
                                </div>
                                <!-- ARCHIVO APOYO -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label>Seleccione el archivo de apoyo:</label>
                                        <input type="file" id="tarea" />
                                </div>
                                
                            </div>
                        </div>
                    </div>
                        <!-- FOOTER PARA BOTONES -->
                    <div class="modal-footer">
                            <!-- BOTON CANSELAR/QUITAR MODAL -->
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            <!-- ENVIAR FORMULARIO -->
                            <button type="submit" class="btn btn-primary">Enviar</button>
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

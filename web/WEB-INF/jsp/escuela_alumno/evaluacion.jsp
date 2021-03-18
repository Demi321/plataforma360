<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Evaluación
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <form id="reporte_seguridad_form" class="p-1">

                            <div class="row col-12 m-0 p-1">
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Materias:</strong>
                                    </div>
                                    <div class="col-12 col-md-8 col-lg-3">
                                        <select class="form-control" name="materia" id="materia" placeholder="Seleccione un valor" required="">
                                            <option disabled="true" selected="true" value="">Seleccione un valor</option>
                                            <option value="1">Materia1</option>
                                            <option value="2">Materia2</option>
                                        </select>
                                    </div>
                                </div>
                               
                            </div>
                            
                            <div class="row col-12 m-0 p-1">
                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>Tareas</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <table style="width:50%">
                                        <tr>
                                          <th>Tareas</th>
                                          <th>Acciones</th> 
                                        </tr>
                                        <tr>
                                          <td>Tarea n</td>
                                          <td>                                           
                                            <button type="button" class="btn btn-info">Ver</button>
                                          </td>
                                        </tr>
                                    </table>                                    
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>Examenes</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <table style="width:50%">
                                        <tr>
                                          <th>Examen</th>
                                          <th>Acciones</th> 
                                        </tr>
                                        <tr>
                                          <td>Tarea n</td>
                                          <td>                                           
                                            <button type="button" class="btn btn-info">Ver</button>
                                          </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>            
        </div>
    </div>
    <!-- MODALS-->
    <!-- MODAL PARA MOSTRAR FORMULARIO PARA INFORMACION DE EVALUACION -->
    <div class="modal fade" id="evaluacionModal" tabindex="-1" role="dialog" aria-labelledby="nuevoEventoModalLabel" aria-hidden="true">
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
                                     <!-- CALIFICACION -->
                                    <div class="col-12 col-md-4 mt-5">
                                        <label for="direccion">Calificaión</label>
                                        <input class="form-control" type="text" id="calificacion" value="calif">
                                    </div>
                            </div>
                        </div>
                    </div>
                        <!-- FOOTER PARA BOTONES -->
                    <div class="modal-footer">
                            <!-- BOTON CANSELAR/QUITAR MODAL -->
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                    </div>
            </div>
        </div>
    </div>
    
</div>


<!--script>
    //id menu 
    //nombre a mostrar alias
    //icono
    //categoria
    //url externa 
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
    agregar_menu(${json});
</script -->

<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.css" var="modulo_misreportesCSS" />
<spring:url value="${pathRecursos}/empresas360/misreportes/misreportes.js" var="modulo_misreportesJS" />
<link href="${modulo_misreportesCSS}" rel="stylesheet"/>
<script src="${modulo_misreportesJS}" ></script>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria text-dark" id="base_modulo_${id}">
    <div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_RegistrarSucursal">
        <div class="registro_institucion row m-0 p-2">
            <div class="col-12 content text-dark" id="formulario_institucion">
                <div class="caja row m-0 p-0 col-12">
                    <div class="col-12"><h3 class="text-dark p-3 m-0">Tareas</h3></div>
                    <div class="col-12 row m-0 p-2">
                        <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                            <div class="row m-0 col-12">
                                <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                    <label class="" for="registro_patronal">Grupo:</label>
                                    <select class="form-control" name="sector" id="Materia_tipo_sector" placeholder="Seleccione uno" required="">
                                        <option disabled="" selected="" value="">Selecciona una opción</option>

                                        <option value="1">Grupo 1</option>
                                        <option value="2">Grupo 2</option>
                                        <option value="3">Grupo 3</option>
                                    </select>
                                </div>
                                <div class="col-12 col-md-6 col-lg-6 col-xl-6 mt-2">
                                    <label class="" for="razon_social">Materia:</label>
                                    <select class="form-control" name="sector" id="Materia_tipo_sector" placeholder="Seleccione uno" required="">
                                        <option disabled="" selected="" value="">Selecciona una opción</option>

                                        <option value="1">Materia 1</option>
                                        <option value="2">Materia 2</option>
                                        <option value="3">Materia 3</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Tareas Entregadas</h3>
                <table style="width:100%" class="table table-hover">
                   <thead>
                       <tr>
                         <th scope="col">Tarea</th>
                         <th scope="col">Acciones</th> 
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                         <td scope="row">Materia n</td>

                         <td scope="row">                                           
                           <button class="btn btn-secondary" title="ver" data-toggle="modal" data-target="#tareaModal"><span>Ver</span></button>
                           <button class="btn btn-success" title="Entregar" data-toggle="modal" data-target="#entregaModal"><span>Entregar</span></button>
                           <button class="btn btn-info" title="Contacto"><span>Contacto</span></button>
                         </td>
                       </tr>
                   </tbody>
               </table>   
            </div>
            <hr>
            <div class="col-12 content text-dark" id="formulario_institucion">
                <h3>Tareas por Entregar</h3>
                <table style="width:100%" class="table table-hover">
                   <thead>
                       <tr>
                         <th scope="col">Tarea</th>
                         <th scope="col">Acciones</th> 
                       </tr>
                   </thead>
                   <tbody>
                       <tr>
                         <td scope="row">Materia n</td>

                         <td scope="row">                                           
                           <button class="btn btn-secondary" title="ver" data-toggle="modal" data-target="#tareaModal"><span>Ver</span></button>
                           <button class="btn btn-success" title="Entregar" data-toggle="modal" data-target="#entregaModal"><span>Entregar</span></button>
                           <button class="btn btn-info" title="Contacto"><span>Contacto</span></button>
                         </td>
                       </tr>
                   </tbody>
               </table>   
            </div>
        </div>
    </div>
    
     <!-- MODALS-->
    <!-- MODAL PARA MOSTRAR FORMULARIO PARA INFORMACION DE TAREA -->
    <div class="modal fade" id="tareaModal" tabindex="-1" role="dialog" aria-labelledby="nuevoEventoModalLabel" aria-hidden="true">
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
                                <!-- ARCHIVO -->
                                <div class="col-12 col-md-4 mt-5">
                                        <label>Seleccione el archivo:</label>
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
<script>
    function modalInfo(){
        
    }
    function modalEntrega(){
        
    }
</script>

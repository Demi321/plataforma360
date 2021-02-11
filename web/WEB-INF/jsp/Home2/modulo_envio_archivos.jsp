<%-- 
    Document   : modulo_lineamientos_estatales
    Created on : 22-nov-2020, 21:24:02
    Author     : Eduardo
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0" id="base_modulo_EnviodeArchivos">
    <div class="row col-12 m-0 p-2" id="files_shared">
        <div class="col-12 p-3 mb-2 border rounded-pill text-center bg-dark text-white" id="enviar_nuevo_archivo">
            <i class="fas fa-plus"></i>
            Nuevo Envió
        </div>
        <div class="row col-12 m-0 p-2 d-none">
            <div class="col">Ver Todos</div>
            <div class="col">Por Proyecto</div>
            <div class="col">Por Remitente</div>
        </div>
        <div class="col-12 m-0 p-0">
            <div class="row col-12 m-0 p-0 p-1 border-bottom d-flex justify-content-center align-items-center">
                <a class="row m-0 p-0 col-10 text-dark d-flex justify-content-center align-items-center collapsed" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                    <div class="col-12 col-sm-12 col-md-6 p-0"><h5>Nombre del archivo</h5></div>
                    <div class="col-12 col-sm-12 col-md-2"><h6>Proyecto</h6></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Fecha</h7></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Hora</h7></div>
                </a>
                <div class="col">
                    <i class="fas fa-share"></i>
                </div>
                <div class="col">
                    <i class="fas fa-cloud-download-alt"></i>
                </div>
                <div class="row col-12 m-0 p-2 collapse" id="collapseExample" style="background: rgb(211, 211, 211);">
                    <div class="col-12 m-0 p-1">
                        <strong>Compartido con: </strong><span>Pedro Perez, </span>
                        <span>Daniela Gonzalez, </span>
                        <span>Michael Ween, </span><span>Ricardo Gutierrez. </span>
                    </div>
                    <div class="col-12 m-0 p-1"><strong>Proyecto: </strong><span>Desarrollo 1</span></div>
                    <div class="col-12 m-0 p-1"><strong>Título: </strong><span>Documentacion para empleo del protocolo AS23GF</span></div>
                    <div class="col-12 m-0 p-1"><strong>Desacripción: </strong><span>Se envia la documentacion en formato PDF para su implementacion en el proyecto de desarrollo 1</span></div>
                    <div class="col-12 m-0 p-0"></div>
                </div>
            </div>
        </div>

        <div class="col-12 m-0 p-0">
            <div class="row col-12 m-0 p-0 p-1 border-bottom d-flex justify-content-center align-items-center">
                <a class="row m-0 p-0 col-10 text-dark d-flex justify-content-center align-items-center collapsed" data-toggle="collapse" href="#collapseExample1" role="button" aria-expanded="false" aria-controls="collapseExample1">
                    <div class="col-12 col-sm-12 col-md-6 p-0"><h5>Nombre del archivo</h5></div>
                    <div class="col-12 col-sm-12 col-md-2"><h6>Proyecto</h6></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Fecha</h7></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Hora</h7></div>
                </a>
                <div class="col">
                    <i class="fas fa-share"></i>
                </div>
                <div class="col">
                    <i class="fas fa-cloud-download-alt"></i>
                </div>
                <div class="row col-12 m-0 p-2 collapse" id="collapseExample1" style="background: rgb(211, 211, 211);">
                    <div class="col-12 m-0 p-1">
                        <strong>Compartido con: </strong><span>Pedro Perez, </span>
                        <span>Daniela Gonzalez, </span>
                        <span>Michael Ween, </span><span>Ricardo Gutierrez. </span>
                    </div>
                    <div class="col-12 m-0 p-1"><strong>Proyecto: </strong><span>Desarrollo 1</span></div>
                    <div class="col-12 m-0 p-1"><strong>Título: </strong><span>Documentacion para empleo del protocolo AS23GF</span></div>
                    <div class="col-12 m-0 p-1"><strong>Desacripción: </strong><span>Se envia la documentacion en formato PDF para su implementacion en el proyecto de desarrollo 1</span></div>
                    <div class="col-12 m-0 p-0"></div>
                </div>
            </div>
        </div>

        <div class="col-12 m-0 p-0">
            <div class="row col-12 m-0 p-0 p-1 border-bottom d-flex justify-content-center align-items-center">
                <a class="row m-0 p-0 col-10 text-dark d-flex justify-content-center align-items-center collapsed" data-toggle="collapse" href="#collapseExample2" role="button" aria-expanded="false" aria-controls="collapseExample2">
                    <div class="col-12 col-sm-12 col-md-6 p-0"><h5>Nombre del archivo</h5></div>
                    <div class="col-12 col-sm-12 col-md-2"><h6>Proyecto</h6></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Fecha</h7></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Hora</h7></div>
                </a>
                <div class="col">
                    <i class="fas fa-share"></i>
                </div>
                <div class="col">
                    <i class="fas fa-cloud-download-alt"></i>
                </div>
                <div class="row col-12 m-0 p-2 collapse" id="collapseExample2" style="background: rgb(211, 211, 211);">
                    <div class="col-12 m-0 p-1">
                        <strong>Compartido con: </strong><span>Pedro Perez, </span>
                        <span>Daniela Gonzalez, </span>
                        <span>Michael Ween, </span><span>Ricardo Gutierrez. </span>
                    </div>
                    <div class="col-12 m-0 p-1"><strong>Proyecto: </strong><span>Desarrollo 1</span></div>
                    <div class="col-12 m-0 p-1"><strong>Título: </strong><span>Documentacion para empleo del protocolo AS23GF</span></div>
                    <div class="col-12 m-0 p-1"><strong>Desacripción: </strong><span>Se envia la documentacion en formato PDF para su implementacion en el proyecto de desarrollo 1</span></div>
                    <div class="col-12 m-0 p-0"></div>
                </div>
            </div>
        </div>

        <div class="col-12 m-0 p-0">
            <div class="row col-12 m-0 p-0 p-1 border-bottom d-flex justify-content-center align-items-center">
                <a class="row m-0 p-0 col-10 text-dark d-flex justify-content-center align-items-center collapsed" data-toggle="collapse" href="#collapseExample3" role="button" aria-expanded="false" aria-controls="collapseExample3">
                    <div class="col-12 col-sm-12 col-md-6 p-0"><h5>Nombre del archivo</h5></div>
                    <div class="col-12 col-sm-12 col-md-2"><h6>Proyecto</h6></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Fecha</h7></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Hora</h7></div>
                </a>
                <div class="col">
                    <i class="fas fa-share"></i>
                </div>
                <div class="col">
                    <i class="fas fa-cloud-download-alt"></i>
                </div>
                <div class="row col-12 m-0 p-2 collapse" id="collapseExample3" style="background: rgb(211, 211, 211);">
                    <div class="col-12 m-0 p-1">
                        <strong>Compartido con: </strong><span>Pedro Perez, </span>
                        <span>Daniela Gonzalez, </span>
                        <span>Michael Ween, </span><span>Ricardo Gutierrez. </span>
                    </div>
                    <div class="col-12 m-0 p-1"><strong>Proyecto: </strong><span>Desarrollo 1</span></div>
                    <div class="col-12 m-0 p-1"><strong>Título: </strong><span>Documentacion para empleo del protocolo AS23GF</span></div>
                    <div class="col-12 m-0 p-1"><strong>Desacripción: </strong><span>Se envia la documentacion en formato PDF para su implementacion en el proyecto de desarrollo 1</span></div>
                    <div class="col-12 m-0 p-0"></div>
                </div>
            </div>
        </div>

        <div class="col-12 m-0 p-0">
            <div class="row col-12 m-0 p-0 p-1 border-bottom d-flex justify-content-center align-items-center">
                <a class="row m-0 p-0 col-10 text-dark d-flex justify-content-center align-items-center collapsed" data-toggle="collapse" href="#collapseExample4" role="button" aria-expanded="false" aria-controls="collapseExample4">
                    <div class="col-12 col-sm-12 col-md-6 p-0"><h5>Nombre del archivo</h5></div>
                    <div class="col-12 col-sm-12 col-md-2"><h6>Proyecto</h6></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Fecha</h7></div>
                    <div class="col-12 col-sm-12 col-md-2"><h7>Hora</h7></div>
                </a>
                <div class="col">
                    <i class="fas fa-share"></i>
                </div>
                <div class="col">
                    <i class="fas fa-cloud-download-alt"></i>
                </div>
                <div class="row col-12 m-0 p-2 collapse" id="collapseExample4" style="background: rgb(211, 211, 211);">
                    <div class="col-12 m-0 p-1">
                        <strong>Compartido con: </strong><span>Pedro Perez, </span>
                        <span>Daniela Gonzalez, </span>
                        <span>Michael Ween, </span><span>Ricardo Gutierrez. </span>
                    </div>
                    <div class="col-12 m-0 p-1"><strong>Proyecto: </strong><span>Desarrollo 1</span></div>
                    <div class="col-12 m-0 p-1"><strong>Título: </strong><span>Documentacion para empleo del protocolo AS23GF</span></div>
                    <div class="col-12 m-0 p-1"><strong>Desacripción: </strong><span>Se envia la documentacion en formato PDF para su implementacion en el proyecto de desarrollo 1</span></div>
                    <div class="col-12 m-0 p-0"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row col-12 m-4 p-3 d-none" id="share_files" style="border-radius: 15px; border: solid 1px #d3d3d3;">
        <form class="p-3 col-12" id="send_files_form">
            <div class="form-group">
                <label for="formGroupExampleInput">Para: </label>
                <input type="text" class="form-control" id="formGroupExampleInput" placeholder="Example input placeholder" />
            </div>
            <div class="form-group">
                <label for="formGroupExampleInput2">Proyecto</label>
                <input list="listado_proyectos" class="form-control" name="listado_proyectos" id="list_proj" placeholder="Selecciona o escribe un proyecto" />
                <datalist id="listado_proyectos">
                    <option value="Desarrollo1"> </option>
                    <option value="Desarrollo2"> </option>
                    <option value="Desarrollo3"> </option>
                    <option value="Desarrollo4"> </option>
                    <option value="Desarrollo5"> </option>
                </datalist>
            </div>

            <div class="form-group">
                <label for="formGroupExampleInput2">Título: </label>
                <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Another input placeholder" />
            </div>

            <div class="form-group">
                <label for="formGroupExampleInput2">Descripción</label>
                <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Another input placeholder" />
            </div>
            <div class="col-12 m-0 p-0 mb-3" style="height: 250px;">
                <div class="wrapper">
                    <div class="drop" style="border: 3px dashed rgb(218, 223, 227); background: transparent;">
                        <div class="cont" style="color: rgb(142, 153, 165);">
                            <!-- <i class="fa fa-cloud-upload"></i> Font Awesome fontawesome.com -->
                            <div class="tit">
                                Drag &amp; Drop
                            </div>
                            <div class="desc">
                                your files to Assets, or
                            </div>
                            <div class="browse">
                                click here to browse
                            </div>
                        </div>
                        <output id="list_files"></output><input id="files" multiple="true" name="files[]" type="file" />
                    </div>
                </div>
            </div>

            <input type="reset" class="btn btn-secondary" value="Cancelar" style="min-width: 180px;" />
            <input type="submit" class="btn btn-danger" value="Enviar" style="min-width: 180px;" />
        </form>
    </div>

</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_envio_archivos.css" var="modulo_envio_archivosCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_envio_archivos.js" var="modulo_envio_archivosJS" />
<link href="${modulo_envio_archivosCSS}" rel="stylesheet"/>
<script src="${modulo_envio_archivosJS}" ></script>


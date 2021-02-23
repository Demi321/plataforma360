
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-3 h-100 archivo d-block bg-white" id="base_modulo_${id}">
    
    <div id="loaderArchivos" class="d-none"></div>
    
    <%-- BOTON PARA ENVIAR NUEVO ARCHIVO --%>
    <div class="row col-12 m-0 p-2">
        <div class="col-12 p-3 mb-2 rounded-pill text-center bg-dark text-white" id="enviar_nuevo_archivo"><i class="fas fa-plus mr-2"></i>Nuevo Envío</div>
    </div>
    
    <%-- FORMULARIO DE ENVIO DE ARCHIVOS --%>
    <div class="row col-12 m-4 p-3" id="nuevo_envio_archivo" style="border-radius: 15px; display: none; border: solid 1px #d3d3d3;">
        <form class="p-3 col-12" id="form-enviar-archivo">
            <div class="form-group">
                <label id="labelDestinatariosArchivos" for="formGroupExampleInput">Para: </label>
                <div class="col-12" id="destinatario_archivos">
                    <multiselect 
                        placeholder="Seleccionar destinatarios"
                        v-model="value" 
                        :options="options"
                        track-by="id360"
                        :multiple="true"
                        :taggable="false"
                        :close-on-select="false"
                        :custom-label="customLabel" 
                        :select-label="'Seleccionar'" 
                        :selected-Label="'Seleccionado'"
                        :deselect-Label="'Remover'"
                        :hide-selected="true"
                        @select="onSelect"
                        @Close="onClose"
                        @Remove="onRemove">
                    </multiselect>
                    <pre class="language-json" style="display:none"><code>{{ value  }}</code></pre>
                </div>
            </div>
            <div class="form-group">
                <label id="labelProyectoArchivos" for="formGroupExampleInput2">Proyecto</label>
                <input list="listado_proyectos" class="form-control" name="listado_proyectos" id="list_proj" placeholder="Selecciona o escribe un proyecto" />
                <datalist id="listado_proyectos"></datalist>
            </div>

            <div class="form-group">
                <label id="labelTituloArchivos" for="formGroupExampleInput2">Asunto </label>
                <input type="text" class="form-control" id="tituloArchivo" placeholder="Asunto del envío" />
            </div>

            <div class="form-group">
                <label id="labelDescripcionArchivos" for="formGroupExampleInput2">Cuerpo del envío</label>
                <textarea id="descripcionArchivo" rows="15" class="form-control"></textarea>
            </div>
            
            <div class="form-group">
                <div class="file-loading">
                    <input id="archivos_envio" name="archivos_envio[]" type="file">
                </div>
            </div>

            <input type="button" id="btnCancelarEnvio" class="btn btn-secondary" value="Cancelar" style="min-width: 180px;" />
            <input type="button" id="btnEnviarArchivo" class="btn btn-danger" value="Enviar" style="min-width: 180px;" />
        </form>
    </div>
    
    <%-- VISTA DE CORREO --%>
    <div id="padreArchivosVistaCorreo">
        <div id="archivosVistaCorreo" class="row w-100 m-0 p-0">
        
            <div class="listadoDeProyectos">
                <h5 class="text-center">Seleccionar proyecto</h5>
                <form class="formulario-lista">
                    <div class="radio-proyectos">
                        <!-- CARGA DINÁMICA -->
                        <%--<div class="form-group">
                            <input value="0" type="radio" name="proyectoSeleccionado" id="todosLosProyectos">
                            <label for="todosLosProyectos">Todos los proyectos</label>
                        </div>--%>
                    </div>
                </form>
            </div>

            <div class="listadoDeArchivos">
                <h5 class="text-center">Lista de archvios</h5>
                <div class="listadoArchivosVistaCorreo">

                </div>
            </div>

            <div class="detalleArchivo p-3"></div>

        </div>
    </div>
    
    <%-- DESPLIEGUE DE PRODUCTOS --%>
    <div id="padreArchivosVistaArchivos">
        <div id="archivosVistaArchivos" class="row w-100 m-0 p-0">
        
            <%-- SECCION DE FILTROS PARA BUSQUEDA DE ARCHIVOS --%>
            <div class="col-12 m-0 p-0 mb-4">

                <div class="row m-0 p-0 filtrosArchivos">

                    <div class="col-3 form-group">
                        <label for="remitente">Por origen: </label>
                        <select id="origenArchivo" name="origen" class="custom-select">
                            <option value="0">Todos</option>
                            <option value="1">Enviados por mi</option>
                            <option value="2">Enviados a mi</option>
                        </select>
                    </div>

                    <div id="selectDestinatarioArchivos" class="col-3 form-group d-none">
                        <label for="destinatarioArchivo">Seleccionar destinatario</label>
                        <select id="destinatarioArchivo" name="destinatario" class="custom-select"></select>
                    </div>

                    <div id="selectRemitenteArchivos" class="col-3 form-group d-none">
                        <label for="remitenteArchivo">Seleccionar Remitente</label>
                        <select id="remitenteArchivo" name="remitente" class="custom-select"></select>
                    </div>

                    <div id="selectRemitenteArchivos" class="col-3 form-group d-none">
                        <label for="remitenteArchivo">Seleccionar Remitente</label>
                        <select id="remitenteArchivo" name="remitente" class="custom-select"></select>
                    </div>

                    <button class="btn btn-danger" id="eliminarArchivos"><i class="fas fa-trash"></i></button>
                    <button class="btn btn-secondary" id="refrescarArchivos"><i class="fas fa-sync-alt"></i></button>

                </div>

            </div>

            <%-- TABLA DE ARCHIVOS --%>
            <div id="contentArchivos" class="col-12 m-0 p-0 d-none">
                <table class="text-center" id="tablaArchivos"></table>
            </div>

            <%-- SIN RESULTADOS ENCONTRADOS --%>
            <div id="sinArchivos" class="col-12 m-0 p-0 d-none">
                <div class="jumbotron">
                    <h1 class="display-4">Sin archivos encontrados!</h1>
                    <p class="lead">Prueba cambiando los filtros para encontrar los archivos deseados.</p>
                </div>
            </div>

        </div>
    </div>
    
</div>


<script>
    agregar_menu(${json});
</script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/2.0.943/pdf.min.js"></script>
<spring:url value="${pathRecursos}/empresas360/archivo/archivo.css" var="modulo_archivoCSS" />
<spring:url value="${pathRecursos}/empresas360/archivo/archivo.js" var="modulo_archivoJS" />
<link href="${modulo_archivoCSS}" rel="stylesheet"/>
<script src="${modulo_archivoJS}" ></script>
<script>
//    init_archivo("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_archivo(${json});
</script>
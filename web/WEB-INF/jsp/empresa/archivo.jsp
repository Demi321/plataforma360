
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-3 h-100 archivo d-block bg-white" id="base_modulo_${id}">
    
    <%-- LOADER DEL MODULO --%>
    <div id="loaderArchivos" class="d-none"></div>
    
    <%-- TITULO DE MODULO --%>
    <div class="row col-12 m-0 p-2 mb-3">
        <h3 class="tituloModuloArchivos">Gestión de archivos</h3>
        <%-- TOGGLE DE VISTA --%>
        <div class="ml-3 contenedorTiposVista">
            <div class="form-check form-check-inline">
                <input class="form-check-input" checked name="tVista" type="radio" id="vCorreo" value="0">
                <label class="form-check-label" for="vCorreo">Vista de Correos</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" name="tVista" type="radio" id="vArchivos" value="1">
                <label class="form-check-label" for="vArchivos">Vista de Archivos</label>
            </div>
        </div>
    </div>
    
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
                    <input id="archivos_envio" name="archivos_envio[]" type="file" multiple="true">
                </div>
            </div>

            <input type="button" id="btnCancelarEnvio" class="btn btn-secondary" value="Cancelar" style="min-width: 180px;" />
            <input type="button" id="btnEnviarArchivo" class="btn btn-danger" value="Enviar" style="min-width: 180px;" />
        </form>
    </div>
    
    <%-- VISTA DE CORREO --%>
    <div id="padreArchivosVistaCorreo" class="h-100">
        <div id="archivosVistaCorreo" class="row w-100 m-0 p-0 h-100">
        
            <div class="listadoDeProyectos h-100">
                <h5 class="text-center">Seleccionar filtros</h5>
                
                <h6 class="mt-3">Seleccionar origen</h6>
                <form class="formulario-lista">
                    <div class="radio-origen">
                        <div class="form-group">
                            <input value="0" type="radio" name="origenSeleccionado" id="origenTodos" checked="checked">
                            <label for="origenTodos">Todos</label>
                        </div>
                        <div class="form-group">
                            <input value="1" type="radio" name="origenSeleccionado" id="origenAMi">
                            <label for="origenAMi">Recibidos</label>
                        </div>
                        <div class="form-group">
                            <input value="2" type="radio" name="origenSeleccionado" id="origenDeMi">
                            <label for="origenDeMi">Enviados</label>
                        </div>
                    </div>
                </form>
                
                <div id="remitenteVistaCorreos" class="w-100 d-none">
                    <h6 class="mt-3">Seleccionar remitente</h6>
                    <select name="remitenteVistaCorreos" id="remitenteVistaCorreosValor" class="custom-select select2"></select>
                </div>
                
                <div id="destinatarioVistaCorreos" class="w-100 d-none">
                    <h6 class="mt-3">Seleccionar destinatario</h6>
                    <select name="destinatarioVistaCorreos" id="destinatarioVistaCorreosValor" class="custom-select select2"></select>
                </div>
                
                <h6 class="mt-3">Seleccionar proyecto</h6>
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

            <div class="listadoDeArchivos h-100">
                <h5 class="text-center">Lista de archvios</h5>
                <div class="listadoArchivosVistaCorreo">

                </div>
            </div>

            <div class="detalleArchivo p-3 h-100"></div>

        </div>
    </div>
    
    <%-- VISTA DE ARCHIVOS --%>
    <div id="padreArchivosVistaArchivos" class="h-100">
        <div id="archivosVistaArchivos" class="row w-100 m-0 p-0 h-100 pb-5 overflow-auto">
        
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
            <div id="contentArchivos" class="col-12 m-0 p-0 d-none h-100">
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
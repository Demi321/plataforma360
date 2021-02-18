
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-3 h-100 archivo d-block bg-white" id="base_modulo_${id}">
    
    <div class="row col-12 m-0 p-2">
        <div class="col-12 p-3 mb-2 rounded-pill text-center bg-dark text-white" id="enviar_nuevo_archivo"><i class="fas fa-plus mr-2"></i>Nuevo Envío</div>
    </div>
    
    <div class="row col-12 m-4 p-3" id="nuevo_envio_archivo" style="border-radius: 15px; display: none; border: solid 1px #d3d3d3;">
        <form class="p-3 col-12" id="form-enviar-archivo">
            <div class="form-group">
                <label for="formGroupExampleInput">Para: </label>
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
                <input type="text" class="form-control" id="tituloArchivo" placeholder="Another input placeholder" />
            </div>

            <div class="form-group">
                <label for="formGroupExampleInput2">Descripción</label>
                <input type="text" class="form-control" id="descripcionArchivo" placeholder="Another input placeholder" />
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
    
    <div id="contenedorDespliegueDeArchivos" class="row w-100 m-0 p-0">
        <div id="contentArchivos" class="col-12 m-0 p-0">
            <table class="text-center" id="tablaArchivos"></table>
        </div>
    </div>
    
</div>


<script>
    agregar_menu(${json});
</script>

<spring:url value="${pathRecursos}/empresas360/archivo/archivo.css" var="modulo_archivoCSS" />
<spring:url value="${pathRecursos}/empresas360/archivo/archivo.js" var="modulo_archivoJS" />
<link href="${modulo_archivoCSS}" rel="stylesheet"/>
<script src="${modulo_archivoJS}" ></script>
<script>
//    init_archivo("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_archivo(${json});
</script>
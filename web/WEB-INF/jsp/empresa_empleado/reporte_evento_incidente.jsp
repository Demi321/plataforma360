<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 reporte_evento_incidente" id="base_modulo_${id_menu}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Reporte de Evento o Incidente
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <div class="text_small col-12 p-1">¿Necesitas reportar algún incidente? Repórtalo de inmediato.</div>

                        <form id="reporte_evento_incidente_form" class="p-1">

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_evento_incidente_anonimo">Anónimo</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_evento_incidente_anonimo" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_evento_incidente_anonimo" name="reporte_evento_incidente_anonimo" />
                                            <label class="custom-control-label" for="reporte_evento_incidente_anonimo"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Fecha:</strong>
                                    </div>
                                    <div class="row col-12 m-0 p-1">
                                        <input type="date" class="form-control" name="reporte_evento_incidente_fecha" id="reporte_evento_incidente_fecha" required="true">
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Hora:</strong>
                                    </div>
                                    <div class="row col-12 m-0 p-1">
                                        <input type="time" class="form-control" name="reporte_evento_incidente_hora" id="reporte_evento_incidente_hora" required="true">
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Selecciona el motivo de tu reporte</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <div class="col-12 p-1 d-flex align-items-center justify-content-center" id="directorio_catalogo_incidentes">

                                    <multiselect
                                        v-model="value"
                                        placeholder="Selecciona una opción"
                                        label="title"
                                        track-by="idRIncidente"
                                        select-label=""
                                        :options="options"
                                        :close-on-select="true"
                                        :custom-label="customLabel"
                                        :show-labels="true"
                                        :hide-selected="false"
                                        @close="onClosed"
                                        @tag="onTag"
                                        @remove="onRemove"
                                        @input="onInput"
                                        @open="onOpen"
                                        placeholder="Buscar contacto"
                                        >
                                    </multiselect>
                                    <input type="hidden" id="reporte_evento_incidente_categoria">

                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Describe el lugar donde ocurrió</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <input type="text" class="form-control" name="reporte_evento_incidente_asunto" id="reporte_evento_incidente_asunto" placeholder="Escriba una breve descripción" required="">
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿En dónde ocurrió?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <input type="text" class="form-control" name="reporte_evento_incidente_direccion" id="reporte_evento_incidente_direccion" placeholder="Escriba una breve descripción" required="">
                            </div>

                            <hr class="mb-1">
                            <div class="row col-12 m-0 p-1 text_small">
                                <div class="py-2"><i class="fas fa-map-marker-alt"></i><strong id="reporte_evento_incidente_municipio"> - </strong><span id="reporte_evento_incidente_estado">, - </span></div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <div id="map_reporte_evento_incidente" class="w-100" style="height: 350px; border-radius: 15px;"></div>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Descripción:</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <input type="text" class="form-control" name="reporte_evento_incidente_reporte" id="reporte_evento_incidente_reporte" placeholder="Escriba una breve descripción" required="">
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Subir evidencia fotografica:</strong>
                            </div> 
                            <div class="row col-12 m-0 p-1">
                                <input type="hidden" id="reporte_evento_incidente_evidencia">
                                <div class="row col-4 mx-auto my-0 p-0 p-1 bg-secondary color-white rounded" style="height: 130px;cursor: pointer;
                                     " id="upload_img_reporte_incidente"><i class="fas fa-camera w-100 h-100 text-white p-4"></i>
                                </div>
                                <div class="custom-file d-none">
                                    <input type="file" class="custom-file-input" id="reporte_event_incidente_customFileLang" lang="es" accept="image/*">
                                    <label class="custom-file-label" for="customFileLang">Subir evidencia fotográfica</label>
                                </div>
                            </div>

                            <div class="d-flex mt-3">
                                <div class="mx-auto justify-content-center">
                                    <input type="submit" class="btn btn-outline-danger btn_claro mx-auto" value="Guardar" />
                                </div>
                                <div class="mx-auto justify-content-center">
                                    <input type="reset" class="btn btn-outline-secondary btn_claro mx-auto" value="Cancelar" style="border: 2px solid #4d4949;" />
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
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
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");

</script>

<spring:url value="${pathRecursos}/empresa_empleado/reporte_incidente/reporte_incidente.css" var="modulo_reporte_incidenteCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/reporte_incidente/reporte_incidente.js" var="modulo_reporte_incidenteJS" />
<link href="${modulo_reporte_incidenteCSS}" rel="stylesheet"/>
<script src="${modulo_reporte_incidenteJS}" ></script>
<script>
    init_reporte_incidentes("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 reporte_seguridad_sanitaria" id="base_modulo_${id_menu}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Reporte de Seguridad Sanitaria
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <div class="text_small col-12 p-1">El cuidado de la salud está en manos de todos, envía un reporte a tu administrador, si hay algo que puede mejorarse. </div>

                        <form id="reporte_seguridad_form" class="p-1">

                            <div class="row col-12 m-0 p-1">
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Fecha:</strong>
                                    </div>
                                    <div class="row col-12 m-0 p-1">
                                        <input type="date" class="form-control" name="reporte_seguridad_fecha" id="reporte_seguridad_fecha" required="true">
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 m-0 p-0">
                                    <div class="row col-12 m-0 p-1 text_small mt-2">
                                        <strong>Hora:</strong>
                                    </div>
                                    <div class="row col-12 m-0 p-1">
                                        <input type="time" class="form-control" name="reporte_seguridad_hora" id="reporte_seguridad_hora" required="true">
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Selecciona el motivo de tu reporte</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <div class="col-12 p-1 d-flex align-items-center justify-content-center" id="reporte_seguridad_catalogo">

                                    <multiselect
                                        v-model="value"
                                        placeholder="Selecciona una opción"
                                        label="title"
                                        track-by="id"
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
                                    <input type="hidden" id="reporte_seguridad_categoria">

                                </div>

                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>¿En dónde ocurrió?</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <input type="text" class="form-control" name="reporte_seguridad_descripcion_lugar" id="reporte_seguridad_descripcion_lugar" placeholder="Escriba una breve descripción" required="">
                                </div>
                                <hr class="mb-1">
                                <div class="row col-12 m-0 p-1 text_small">
                                    <div class="py-2"><i class="fas fa-map-marker-alt"></i><strong id="reporte_seguridad_sanitaria_municipio"> - </strong><span id="reporte_seguridad_sanitaria_estado">, - </span></div>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <div id="map_seguridad_sanitaria" class="w-100" style="height: 350px; border-radius: 15px;"></div>
                                </div>

                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>Descripción:</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <input type="text" class="form-control" name="reporte_seguridad_descripcion" id="reporte_seguridad_descripcion" placeholder="Escriba una breve descripción" required="">
                                </div>


                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>Describe la situación:</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <textarea class="form-control" name="reporte_seguridad_observaciones" id="reporte_seguridad_observaciones" placeholder="Describe la situación" required=""></textarea>
                                </div>

                                <div class="row col-12 m-0 p-1 text_small mt-2">
                                    <strong>Subir evidencia fotografica:</strong>
                                </div>
                                <div class="row col-12 m-0 p-1">
                                    <input type="file" class="custom-file-input" id="reporte_seguridad_customFileLang" lang="es">
                                    <div class="row col-4 mx-auto my-0 p-0 p-1 bg-secondary color-white rounded" style="height: 130px;cursor: pointer;
                                         " id="upload_img_reporte_seguridad"><i class="fas fa-camera w-100 h-100 text-white p-4"></i>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="reporte_evento_seguridad_customFileLang" lang="es" accept="image/*">
                                        <input type="hidden" id="reporte_seguridad_evidencia">
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

<spring:url value="${pathRecursos}/empresa_empleado/reporte_seguridad_sanitaria/reporte_seguridad_sanitaria.css" var="modulo_reporte_seguridad_sanitariaCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/reporte_seguridad_sanitaria/reporte_seguridad_sanitaria.js" var="modulo_reporte_seguridad_sanitariaJS" />
<link href="${modulo_reporte_seguridad_sanitariaCSS}" rel="stylesheet"/>
<script src="${modulo_reporte_seguridad_sanitariaJS}" ></script>
<script>
    init_reporte_seguridad_sanitaria("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>
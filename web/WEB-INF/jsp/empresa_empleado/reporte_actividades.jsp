<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 reporte_actividades" id="base_modulo_${id_menu}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Reporte de Trabajo
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <div class="text_small col-12 p-1">Antes de concluir tu jornada laboral, agrega tu reporte de actividades de tu día. </div>
                        <div class="col-12 clock_container">
                            <div class="p-1 text-center mt-3"><strong id="today_reporte_actividades"></strong></div>
                            <div id="clock_reporte_actividades"></div>
                        </div>
                        <form id="reporte_actividades_form" class="p-1">

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Hora de entrada:</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <input type="text" class="form-control" name="dificultad_respirar" id="reporte_actividades_hora_entrada" disabled="true">
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Hora de salida:</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <input type="text" class="form-control" name="reporte_actividades_hora_salida" id="reporte_actividades_hora_salida" disabled="true">
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Reporte de actividades:</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <textarea class="form-control" name="temperatura" id="reporte_actividades_reporte" placeholder="Seleccione una opción" required=""></textarea>
                            </div>
                            <div class="row col-12 m-0 p-1 d-none">
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" id="reporte_actividades_url_archivo" lang="es">
                                    <label class="custom-file-label" for="customFileLang">Seleccionar Archivo</label>
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

<spring:url value="${pathRecursos}/empresa_empleado/reporte_actividades/reporte_actividades.css" var="modulo_reporte_actividadesCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/reporte_actividades/reporte_actividades.js" var="modulo_reporte_actividadesJS" />
<link href="${modulo_reporte_actividadesCSS}" rel="stylesheet"/>
<script src="${modulo_reporte_actividadesJS}" ></script>
<script>
    init_reporte_actividades("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>
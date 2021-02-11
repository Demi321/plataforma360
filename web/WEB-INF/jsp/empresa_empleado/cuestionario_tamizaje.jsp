<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 cuestionario_sintomas" id="base_modulo_${id_menu}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Cuestionariode tamizaje para COVID-19
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <div class="text_small col-12 p-1">Responde este test para detectar factores de riesgo y prevenir contagios. ¡Su salud es primero! </div>
                        <form id="cuestionario_tamizaje_form" class="p-1">
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Cuántos años tienes?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" class="form-control" name="cuestionario_tamizaje_edad" id="cuestionario_tamizaje_edad" placeholder="Ingresa tu edad" required="">
                             </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Cuál es tu sexo?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_sexo" id="cuestionario_tamizaje_sexo" placeholder="Seleccione un valor" required="">
                                    <option disabled="true" selected="true" value="">Seleccione un valor</option>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Femenino">Femenino</option>
                                </select>
                            </div>
                            
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Cuál es tu código postal?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" class="form-control" name="cuestionario_tamizaje_cp" id="cuestionario_tamizaje_cp" placeholder="Número de piezas" required="">
                             </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>1.- ¿Has convivido con alguna persona que sea un caso confirmado de COVID-19 (Coronavirus)?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_convivido_persona_covid" id="cuestionario_tamizaje_convivido_persona_covid" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>2.- ¿Tienes fiebre?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_fiebre" id="cuestionario_tamizaje_fiebre" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>3.- ¿Tienes dolor de cabeza?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_dolor_cabeza" id="cuestionario_tamizaje_dolor_cabeza" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>4.- ¿Tienes tos?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_tos" id="cuestionario_tamizaje_tos" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>5.- ¿Tienes dolor de pecho?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_dolor_pecho" id="cuestionario_tamizaje_dolor_pecho" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>6.- ¿Tienes dolor de garganta?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_dolor_garganta" id="cuestionario_tamizaje_dolor_garganta" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>7.- ¿Tienes dificultad para respirar?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_dificultad_respirar" id="cuestionario_tamizaje_dificultad_respirar" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>8.- ¿Tienes escurrimiento nasal?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_escurrimiento_nasal" id="cuestionario_tamizaje_escurrimiento_nasal" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>9.- ¿Tienes dolor de cuerpo?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_dolor_cuerpo" id="cuestionario_tamizaje_dolor_cuerpo" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>10.- ¿Tienes conjuntivitis?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_conjuntivitis" id="cuestionario_tamizaje_conjuntivitis" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>11.- ¿Tienes alguna de las siguientes condiciones? (Diabetes, hipertensión, obesidad, problemas cardiacos, asma, EPOC, VIH, cáncer)</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_condiciones" id="cuestionario_tamizaje_condiciones" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>12.- ¿Estás embarazada?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_embarazada" id="cuestionario_tamizaje_embarazada" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>13.- ¿Tienes 6 o más meses de embarazo?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="cuestionario_tamizaje_tiempo_embarazo" id="cuestionario_tamizaje_tiempo_embarazo" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
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
   agregar_menu("${id_menu}","${icono_categoria}","${nombre}","${alias}",'${icono}', '${categoria}',"");
    
</script>

<spring:url value="${pathRecursos}/empresa_empleado/cuestionario_tamizaje/cuestionario_tamizaje.css" var="modulo_cuestionario_tamizajeCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/cuestionario_tamizaje/cuestionario_tamizaje.js" var="modulo_cuestionario_tamizajeJS" />
<link href="${modulo_cuestionario_tamizajeCSS}" rel="stylesheet"/>
<script src="${modulo_cuestionario_tamizajeJS}" ></script>
<script>
    init_cuestionario_tamizaje("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>

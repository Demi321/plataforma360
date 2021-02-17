<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 proteccion_personal" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Equipo de protección personal
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <div class="text_small col-12 p-1">1.- Equipo de protección personal</div>
                        <div class="row col-12 m-0 p-1 text_small mt-2">
                            <strong>Selecciona la protección personal que te proporcionó tu centro de trabajo para tus labores diarias.</strong>
                        </div>
                        <div class="row col-12 m-0 p-1 text_small mt-2">
                            <strong>Manten actualizada esta información diariamente.</strong>
                        </div>
                        <form id="reporte_proteccion_personal_form" class="p-1">
                            
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_cubrebocas">Cubrebocas o mascarilla</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_cubrebocas" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_cubrebocas"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_cubrebocas" id="reporte_proteccion_personal_piezas_cubrebocas" placeholder="Número de piezas" >
                            </div>
                            
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_careta">Careta o pantalla facial antiempañante</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_careta" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_careta"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                             <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_careta" id="reporte_proteccion_personal_piezas_careta" placeholder="Número de piezas" >
                             </div>
                            
                            
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_guantes">Guantes de nitrilo o vinilo</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_guantes" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_guantes"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_guantes" id="reporte_proteccion_personal_piezas_guantes" placeholder="Número de piezas" >
                             </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_gogles">Gogles o gafas de seguridad</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_gogles" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_gogles"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_gogles" id="reporte_proteccion_personal_piezas_gogles" placeholder="Número de piezas" >
                             </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_gorra">Gorra desechable</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_gorra" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_gorra"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_gorra" id="reporte_proteccion_personal_piezas_gorra" placeholder="Número de piezas" >
                             </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_traje">Traje desechable</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_traje" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_traje"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_traje" id="reporte_proteccion_personal_piezas_traje" placeholder="Número de piezas" >
                             </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_bata_desechable">Bata de manga larga impermeable desechable</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_bata_desechable" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_bata_desechable"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_bata_desechable" id="reporte_proteccion_personal_piezas_bata_desechable" placeholder="Número de piezas" >
                             </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_bata_algodon">Bata de manga larga de algodón</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_bata_algodon" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_bata_algodon"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_bata_algodon" id="reporte_proteccion_personal_piezas_bata_algodon" placeholder="Número de piezas" >
                             </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_proteccion_personal_proteccion_protector_calzado">Protector desechable de calzado</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_proteccion_personal_proteccion_protector_calzado" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="sintoma_debilidad" name="sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_proteccion_personal_proteccion_protector_calzado"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                 <input type="number" value="0" class="form-control d-none" name="reporte_proteccion_personal_piezas_protector_calzado" id="reporte_proteccion_personal_piezas_protector_calzado" placeholder="Número de piezas" >
                             </div>
                            
                            
                            
                            
                            
                            
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Observaciones</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <textarea class="form-control" name="reporte_proteccion_personal_observaciones" id="reporte_proteccion_personal_observaciones" placeholder="Observaciones" ></textarea>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>2.- Pruebas realizadas</strong>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Se le ha practicado la prueba de COVID-19?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="reporte_proteccion_personal_prueba_covid" id="reporte_proteccion_personal_prueba_covid" placeholder="Seleccione una respuesta" required >
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2 d-none" id="ultima_prueba_titulo">
                                <strong>¿Cuándo se realizó la prueba más reciente?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1 d-none" id="ultima_prueba">
                                <select class="form-control" name="reporte_proteccion_personal_prueba_reciente" id="reporte_proteccion_personal_prueba_reciente" placeholder="Seleccione una respuesta" >
                                    <option disabled="true" selected="true" value="0">Seleccione una respuesta</option>
                                    <option value="Hoy">Hoy</option>
                                    <option value="Hace una semana">Hace una semana</option>
                                    <option value="Hace 15 dias">Hace 15 dias</option>
                                    <option value="Hace 1 mes">Hace 1 mes</option>
                                    <option value="Hace mas de 1 mes">Hace mas de 1 mes</option>
                                </select>
                            </div>
                            
                            <div class="row col-12 m-0 p-1 text_small mt-2 d-none" id="resultado_prueba_titulo">
                                <strong>¿Cuál fue el resultado de la prueba COVID-19 realizada?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1 d-none" id="resultado_prueba">
                                <select class="form-control" name="reporte_proteccion_personal_resultado_prueba" id="reporte_proteccion_personal_resultado_prueba" placeholder="Seleccione una respuesta" >
                                    <option disabled="true" selected="true" value="0">Seleccione una respuesta</option>
                                    <option value="Positivo">Positivo</option>
                                    <option value="Negativo">Negativo</option>
                                </select>
                            </div>

                            
                            <div class="d-block mt-3">
                                <div class="mx-auto justify-content-center my-2 text-center">
                                    <input type="submit" class="btn btn-outline-danger btn_claro mx-auto" value="Guardar" />
                                </div>
                                <div class="mx-auto justify-content-center my-2 text-center">
                                    <input type="reset" class="btn btn-outline-secondary btn_claro mx-auto" value="Cancelar" style="/*border: 2px solid #4d4949;*/" />
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
//   agregar_menu("${id_menu}","${icono_categoria}","${nombre}", "${alias}", '${icono}', '${categoria}', "");
agregar_menu(${json});

</script>

<spring:url value="${pathRecursos}/empresa_empleado/reporte_proteccion_personal/reporte_proteccion_personal.css" var="modulo_reporte_proteccion_personalCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/reporte_proteccion_personal/reporte_proteccion_personal.js" var="modulo_reporte_proteccion_personalJS" />
<link href="${modulo_reporte_proteccion_personalCSS}" rel="stylesheet"/>
<script src="${modulo_reporte_proteccion_personalJS}" ></script>
<script>
//    init_reporte_proteccion_personal("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_reporte_proteccion_personal(${json});
</script>
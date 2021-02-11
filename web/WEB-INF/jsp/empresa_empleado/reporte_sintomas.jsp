<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-0 reporte_sintomas" id="base_modulo_${id_menu}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0" >
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Reporte de Síntomas
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <div class="text_small col-12 p-1">Te invitamnos a realizar tu reporte diario de síntomas:</div>
                        <form id="reporte_sintomas_form" class="p-1">
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Registra tu temperatura corporal</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="reporte_sintomas_temperatura" id="reporte_sintomas_temperatura" placeholder="Seleccione una opción" required="true">
                                    <option disabled="true" selected="true">Seleccione una opción</option>
                                    <option value="35">35</option>
                                    <option value="36">36</option>
                                    <option value="37">37</option>
                                    <option value="38">38</option>
                                    <option value="39">39</option>
                                    <option value="40">40 o más</option>
                                </select>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Oximetría</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="reporte_sintomas_oximetria" id="reporte_sintomas_oximetria" placeholder="Seleccione un valor" required="">
                                    <option disabled="true" selected="true">Seleccione un valor</option>
                                    <option value="60">60%</option>
                                    <option value="61">61%</option>
                                    <option value="62">62%</option>
                                    <option value="63">63%</option>
                                    <option value="64">64%</option>
                                    <option value="65">65%</option>
                                    <option value="66">66%</option>
                                    <option value="67">67%</option>
                                    <option value="68">68%</option>
                                    <option value="69">69%</option>
                                    <option value="70">70%</option>
                                    <option value="71">71%</option>
                                    <option value="72">72%</option>
                                    <option value="73">73%</option>
                                    <option value="74">74%</option>
                                    <option value="75">75%</option>
                                    <option value="76">76%</option>
                                    <option value="77">77%</option>
                                    <option value="78">78%</option>
                                    <option value="79">79%</option>
                                    <option value="80">80%</option>
                                    <option value="81">81%</option>
                                    <option value="82">82%</option>
                                    <option value="83">83%</option>
                                    <option value="84">84%</option>
                                    <option value="85">85%</option>
                                    <option value="86">86%</option>
                                    <option value="87">87%</option>
                                    <option value="88">88%</option>
                                    <option value="89">89%</option>
                                    <option value="90">90%</option>
                                    <option value="91">91%</option>
                                    <option value="92">92%</option>
                                    <option value="93">93%</option>
                                    <option value="94">94%</option>
                                    <option value="95">95%</option>
                                    <option value="96">96%</option>
                                    <option value="97">97%</option>
                                    <option value="98">98%</option>
                                    <option value="99">99%</option>
                                    <option value="100">100%</option>
                                </select>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Tienes dificultad para respirar?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="reporte_sintomas_dificultad_respirar" id="reporte_sintomas_dificultad_respirar" placeholder="Seleccione una respuesta" required="true">
                                    <option disabled="true" selected="true">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Has presentado alguno de estos síntomas en las últimas 24 horas?</strong>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_debilidad">Debilidad o cansancio</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_debilidad" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_debilidad" name="reporte_sintomas_sintoma_debilidad" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_debilidad"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_diarrea">Diarrea y/o vómito</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_diarrea" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_diarrea" name="reporte_sintomas_sintoma_diarrea" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_diarrea"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_dolor_cabeza">Dolor de cabeza</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_dolor_cabeza" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_dolor_cabeza" name="reporte_sintomas_sintoma_dolor_cabeza" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_dolor_cabeza"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_dolor_huesos">Dolor de huesos y/o articulaciones</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_dolor_huesos" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_dolor_huesos" name="reporte_sintomas_sintoma_dolor_huesos" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_dolor_huesos"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_escalofrio">Escalofrío</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_escalofrio" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_escalofrio" name="reporte_sintomas_sintoma_escalofrio" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_escalofrio"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_fiebre">Fiebre</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_fiebre" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_fiebre" name="reporte_sintomas_sintoma_fiebre" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_fiebre"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_secrecion">Secreción y goteo nasal</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_secrecion" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_secrecion" name="reporte_sintomas_sintoma_secrecion" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_secrecion"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_tos">Tos</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_tos" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_tos" name="reporte_sintomas_sintoma_tos" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_tos"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="reporte_sintomas_sintoma_ninguno">Ninguna</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="reporte_sintomas_sintoma_ninguno" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="reporte_sintomas_sintoma_ninguno" name="reporte_sintomas_sintoma_ninguno" />
                                            <label class="custom-control-label" for="reporte_sintomas_sintoma_ninguno"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Actualmente ¿Te encuentras?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="reporte_sintomas_estado_usuario" id="reporte_sintomas_estado_usuario" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true">Seleccione una respuesta</option>
                                    <option value="Home office">Trabajando en casa (Home office)</option>
                                    <option value="aislamiento">En aislamiento</option>
                                    <option value="cuarentena">En cuarentena</option>
                                    <option value="Otro">Otro</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>¿Por qué?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="reporte_sintomas_razon" id="reporte_sintomas_razon" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true">Seleccione una respuesta</option>
                                    <option value="Asisto a mi trabajo de forma normal ">Asisto a mi trabajo de forma normal</option>
                                    <option value="Asisto a mi trabajo de forma intermitente">Asisto a mi trabajo de forma intermitente</option>
                                    <option value="Cuarentena: sospecha / diagnóstico enfermedad muy contagiosa">Cuarentena: sospecha / diagnóstico enfermedad muy contagiosa</option>
                                    <option value="Trabajo en casa (Home Office): por política de la empresa">Trabajo en casa (Home Office): por política de la empresa</option>
                                    <option value="Aislamiento: síntomas de enfermedad respiratoria aguda ">Aislamiento: síntomas de enfermedad respiratoria aguda</option>
                                    <option value="Aislamiento: con diagnóstico enfermedad altamente contagiosa">Aislamiento: con diagnóstico enfermedad altamente contagiosa</option>
                                    <option value="Aislamiento: por detección en filtro sanitario">Aislamiento: por detección en filtro sanitario</option>
                                    <option value="Hospitalizado: sospecha confirmación enfermedad altamente contagiosa ">Hospitalizado: sospecha confirmación enfermedad altamente contagiosa </option>
                                    <option value="Cuarentena: por decisión personal">Cuarentena: por decisión personal</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>Fecha de inicio de aislamiento</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <input type="date" class="form-control" name="reporte_sintomas_inicio_aislamiento" id="reporte_sintomas_inicio_aislamiento" placeholder="Seleccione una fecha">
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

<spring:url value="${pathRecursos}/empresa_empleado/reporte_sintomas/reporte_sintomas.css" var="modulo_reporte_sintomasCSS" />
<spring:url value="${pathRecursos}/empresa_empleado/reporte_sintomas/reporte_sintomas.js" var="modulo_reporte_sintomasJS" />
<link href="${modulo_reporte_sintomasCSS}" rel="stylesheet"/>
<script src="${modulo_reporte_sintomasJS}" ></script>
<script>
    init_reporte_sintomas("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
</script>
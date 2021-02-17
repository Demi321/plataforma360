
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 usodecubrebocas" id="base_modulo_${id}">
    <div class="row col-12 m-0 px-2 pt-3 pb-0 parte1">
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Uso de cubrebocas cotidianamente
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <form id="uso_cubrebocas_form1" class="p-1">
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_fuera_casa">
                                <strong>1.- ¿Utilizas cubrebocas cuando sales de casa?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_fuera_casa" id="uso_cubrebocas_fuera_casa" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_lugares_cerrados">
                                <strong>2. ¿Utilizas cubrebocas cuando estás en lugares públicos cerrados? (Tiendas, supermercados, plazas comerciales, bancos, mercados, etcétera)</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_lugares_cerrados" id="uso_cubrebocas_lugares_cerrados" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_transporte_publico">
                                <strong>3. Si utilizas transporte público, ¿Usas el cubrebocas, durante el trayecto? (Si no viajas en transporte público, por favor selecciona la opción N/A) </strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_transporte_publico" id="uso_cubrebocas_transporte_publico" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="na">N/A</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_automovil_compartido">
                                <strong>4. Si te trasladas en un automóvil compartido, ¿Usas el cubrebocas durante el trayecto? (Si no realizas esa acción, por favor selecciona la opción N/A)</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_automovil_compartido" id="uso_cubrebocas_automovil_compartido" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="na">N/A</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_personas_conocidas">
                                <strong>5. ¿Utilizas cubrebocas cuando estás con personas conocidas que no comparten domicilio contigo? </strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_personas_conocidas" id="uso_cubrebocas_personas_conocidas" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="na">N/A</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_reunion_social">
                                <strong>6. ¿Cuando asistes a una reunión social, utilizas cubrebocas? (Si no has asistido a alguna reunión social en los últimos seis meses, por favor selecciona la opción N/A)</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_reunion_social" id="uso_cubrebocas_reunion_social" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="na">N/A</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_lugar_trabajo">
                                <strong>7. ¿Es obligatorio el uso de cubrebocas en tu lugar de trabajo?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_lugar_trabajo" id="uso_cubrebocas_lugar_trabajo" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_empleados">
                                <strong>8. ¿Tus empleados proveen los cubrebocas que utilizas?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_empleados" id="uso_cubrebocas_empleados" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_frecuencia_trabajo">
                                <strong>9. ¿Con qué frecuencia utilizas el cubrebocas en tu lugar de trabajo?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_frecuencia_trabajo" id="uso_cubrebocas_frecuencia_trabajo" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="a veces">A veces</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="casi nunca">Casi nunca</option>
                                    <option value="casi siempre">Casi siempre</option>
                                    <option value="siempre">Siempre</option>
                                    <option value="na">N/A - Trabajo desde casa</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_equipo_adicional">
                                <strong>10 ¿Utilizas algún otro elemento de protección personal contra la enfermedad causante de la pandemia actual? </strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_equipo_adicional" id="uso_cubrebocas_equipo_adicional" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>

                            <div class="d-block mt-3">
                                <div class="mx-auto justify-content-center my-2 text-center">
                                    <input type="submit" class="btn btn-outline-danger btn_claro mx-auto" value="Siguiente" />
                                </div>
                                <!--                                <div class="mx-auto justify-content-center my-2 text-center">
                                                                    <input type="reset" class="btn btn-outline-secondary btn_claro mx-auto" value="Cancelar" style="/*border: 2px solid #4d4949;*/" />
                                                                </div>-->
                            </div>
                        </form>

                    </div>
                </div>
            </div>            
        </div>
    </div>
    <div class="row col-12 m-0 px-2 pt-3 pb-0 parte2">
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Modo de uso de cubrebocas
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <form id="uso_cubrebocas_form2" class="p-1">
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>11. ¿Cuál de las siguientes imágenes, describen mejor el tipo de cubrebocas que utilizas regularmente? </strong>
                            </div>

                            <div class="form-group row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start mascara1" for="uso_cubrebocas_quirurgico1"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_quirurgico1" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_quirurgico1" name="uso_cubrebocas_quirurgico1" />
                                            <label class="custom-control-label" for="uso_cubrebocas_quirurgico1"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start mascara2" for="uso_cubrebocas_quirurgico2"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_quirurgico2" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_quirurgico2" name="uso_cubrebocas_quirurgico2" />
                                            <label class="custom-control-label" for="uso_cubrebocas_quirurgico2"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start mascara3" for="uso_cubrebocas_regular1"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_regular1" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_regular1" name="uso_cubrebocas_regular1" />
                                            <label class="custom-control-label" for="uso_cubrebocas_regular1"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start mascara4" for="uso_cubrebocas_regular2"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_regular2" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_regular2" name="uso_cubrebocas_regular2" />
                                            <label class="custom-control-label" for="uso_cubrebocas_regular2"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start mascara5" for="uso_cubrebocas_kn95"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_kn95" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_kn95" name="uso_cubrebocas_kn95" />
                                            <label class="custom-control-label" for="uso_cubrebocas_kn95"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start mascara6" for="uso_cubrebocas_alta_eficacia_valvula"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_alta_eficacia_valvula" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_alta_eficacia_valvula" name="uso_cubrebocas_alta_eficacia_valvula" />
                                            <label class="custom-control-label" for="uso_cubrebocas_alta_eficacia_valvula"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>12. ¿Cual de las siguientes imágenes, describe mejor la forma en que portas el cubrebocas? </strong>
                            </div>
                            
                            <div class="form-group row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start uso_mascara1" for="uso_cubrebocas_uso1"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_uso1" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_uso1" name="uso_cubrebocas_uso1" />
                                            <label class="custom-control-label" for="uso_cubrebocas_uso1"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start uso_mascara2" for="uso_cubrebocas_uso2"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_uso2" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_uso2" name="uso_cubrebocas_uso2" />
                                            <label class="custom-control-label" for="uso_cubrebocas_uso2"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start uso_mascara3" for="uso_cubrebocas_uso3"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_uso3" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_uso3" name="uso_cubrebocas_uso3" />
                                            <label class="custom-control-label" for="uso_cubrebocas_uso3"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start uso_mascara4" for="uso_cubrebocas_uso4"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_uso4" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_uso4" name="uso_cubrebocas_uso4" />
                                            <label class="custom-control-label" for="uso_cubrebocas_uso4"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row col-12 m-0 p-0 d-flex align-items-center justify-content-center">
                                <div class="col-9 col-sm-9 col-md-5 col-form-label d-flex align-items-center justify-content-start uso_mascara5" for="uso_cubrebocas_uso5"></div>
                                <div class="col-3 col-sm-3 col-md-1 d-flex align-items-center justify-content-center mb-3">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_uso5" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_uso5" name="uso_cubrebocas_uso5" />
                                            <label class="custom-control-label" for="uso_cubrebocas_uso5"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6 d-flex align-items-center justify-content-center"></div>
                                
                            </div>
                            
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_lavado_crubrebocas">
                                <strong>13. Si portas cubrebocas reusable, ¿Con qué frecuencia lo lavas?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_lavado_crubrebocas" id="uso_cubrebocas_lavado_crubrebocas" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="nunca">Nunca</option>
                                    <option value="una vez por semana">Una vez por semana</option>
                                    <option value="diario">Diario</option>
                                    <option value="cada 15 dias">Cada 15 días</option>
                                    <option value="cada tercer día">Cada tercer día</option>
                                    <option value="no utilizo">No utilizo</option>
                                </select>
                            </div>
                            <div class="d-block mt-3">
                                <div class="mx-auto justify-content-center my-2 text-center">
                                    <input type="submit" class="btn btn-outline-danger btn_claro mx-auto" value="Siguiente" />
                                </div>
                                <!--                                <div class="mx-auto justify-content-center my-2 text-center">
                                                                    <input type="reset" class="btn btn-outline-secondary btn_claro mx-auto" value="Cancelar" style="/*border: 2px solid #4d4949;*/" />
                                                                </div>-->
                            </div>
                        </form>

                    </div>
                </div>
            </div>            
        </div>
    </div>
    <div class="row col-12 m-0 px-2 pt-3 pb-0 parte3">
        <div class="col-12 col-sm-12 col-md-10 col-lg-8 col-xl-7 mx-auto container shadow p-3 mb-5 bg-white p-2">
            <div class="card">
                <div class="card-header pb-0">
                    <span class="title">
                        Creencias sobre el uso de cubrebocas
                    </span>
                </div>
                <div class="card-body pt-0">
                    <div class="row m-0 p-1 col-12">
                        <hr>
                        <form id="uso_cubrebocas_form3" class="p-1" >
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_proteccion_pandemia">
                                <strong>14. ¿Consideras que utilizar cubrebocas te protege de contraer la enfermedad causante de la pandemia actual?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_proteccion_pandemia" id="uso_cubrebocas_proteccion_pandemia" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="completamente en desacuerdo">Completamente en desacuerdo</option>
                                    <option value="me es indiferente">Me es indiferente</option>
                                    <option value="completamente deacuerdo">Completamente deacuerdo</option>
                                    <option value="en desacuerdo">En desacuerdo</option>
                                    <option value="de acuerdo">De acuerdo</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_reduce_transmision">
                                <strong>15. ¿Consideras que el uso de cubrebocas ayuda a reducir la transmisión del virus causante de la pandemia actual?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_reduce_transmision" id="uso_cubrebocas_reduce_transmision" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="completamente en desacuerdo">Completamente en desacuerdo</option>
                                    <option value="me es indiferente">Me es indiferente</option>
                                    <option value="completamente deacuerdo">Completamente deacuerdo</option>
                                    <option value="en desacuerdo">En desacuerdo</option>
                                    <option value="de acuerdo">De acuerdo</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_responsabilidad_personal">
                                <strong>16. ¿Crees que es tu responsabilidad usar el cubrebocas, aún cuando las autoridades laborales y/o sanitarias no lo exijan?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_responsabilidad_personal" id="uso_cubrebocas_responsabilidad_personal" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="completamente en desacuerdo">Completamente en desacuerdo</option>
                                    <option value="me es indiferente">Me es indiferente</option>
                                    <option value="completamente deacuerdo">Completamente deacuerdo</option>
                                    <option value="en desacuerdo">En desacuerdo</option>
                                    <option value="de acuerdo">De acuerdo</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_entre_conocidos">
                                <strong>17. ¿Es necesario usar cubrebocas aún conviviendo con personas conocidas que no sean positivas al virus?</strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_entre_conocidos" id="uso_cubrebocas_entre_conocidos" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="completamente en desacuerdo">Completamente en desacuerdo</option>
                                    <option value="me es indiferente">Me es indiferente</option>
                                    <option value="completamente deacuerdo">Completamente deacuerdo</option>
                                    <option value="en desacuerdo">En desacuerdo</option>
                                    <option value="de acuerdo">De acuerdo</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2" for="uso_cubrebocas_uso_asintomaticos">
                                <strong>18. ¿Consideras que deberías usar cubrebocas aún siendo asintomático? </strong>
                            </div>
                            <div class="row col-12 m-0 p-1">
                                <select class="form-control" name="uso_cubrebocas_uso_asintomaticos" id="uso_cubrebocas_uso_asintomaticos" placeholder="Seleccione una respuesta" required="">
                                    <option disabled="true" selected="true" value="">Seleccione una respuesta</option>
                                    <option value="1">Sí</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 p-1 text_small mt-2">
                                <strong>19. Señale la secuencia correcta de pasos a seguir al retirar el cubrebocas. </strong>
                            </div>

                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="uso_cubrebocas_secuencia1">1. Retira las cintas elásticas ubocadas detrás de las orejas, mientras mantienen la mascarilla alejada de tu cara y ropa. Deséchala o guárdala en un contenedor cerrado según sea el caso. Finaliza lavando tus manos.</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_secuencia1" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_secuencia" name="uso_cubrebocas_secuencia"  for="uso_cubrebocas_secuencia"/>
                                            <label class="custom-control-label" for="uso_cubrebocas_secuencia1"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="uso_cubrebocas_secuencia2">2. Retira las cintas elásticas ubocadas detrás de las orejas, mientras mantienen la mascarilla alejada de tu cara y ropa. Desecha la mascarilla en un contenedor cerrado.</label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_secuencia2" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_secuencia" name="uso_cubrebocas_secuencia" for="uso_cubrebocas_secuencia"/>
                                            <label class="custom-control-label" for="uso_cubrebocas_secuencia2"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row col-12 m-0 p-1 d-flex align-items-center justify-content-center">
                                <label class="col-md-9 col-form-label d-flex align-items-center justify-content-start" for="uso_cubrebocas_secuencia3">3. Lava tus manos, corta las cintas elásticas colocadas detrás de las orejas y guarda las mascarilla en su bolsa respectiva. </label>
                                <div class="col-md-3 d-flex align-items-center justify-content-center">
                                    <div style="display: flex; justify-content: center; align-items: center;">
                                        <div class="custom-control custom-switch">
                                            <input id="uso_cubrebocas_secuencia3" style="padding: 10px 25px !important; border-radius: 30px;" value="" class="form-control custom-control-input" type="checkbox" data-name="uso_cubrebocas_secuencia" name="uso_cubrebocas_secuencia"  for="uso_cubrebocas_secuencia"/>
                                            <label class="custom-control-label" for="uso_cubrebocas_secuencia3"></label>
                                        </div>
                                    </div>
                                </div>
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

<spring:url value="${pathRecursos}/empresas360/usodecubrebocas/usodecubrebocas.css" var="modulo_usodecubrebocasCSS" />
<spring:url value="${pathRecursos}/empresas360/usodecubrebocas/usodecubrebocas.js" var="modulo_usodecubrebocasJS" />
<link href="${modulo_usodecubrebocasCSS}" rel="stylesheet"/>
<script src="${modulo_usodecubrebocasJS}" ></script>
<script>
//    init_usodecubrebocas("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
    init_usodecubrebocas(${json});
</script>
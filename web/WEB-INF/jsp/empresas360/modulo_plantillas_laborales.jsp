<%-- 
    Document   : modulo_plantillas_laborales
    Created on : 11/11/2020, 01:01:08 AM
    Author     : moises
--%>

<!-- Cambio fernando -->
        <!--<div class="modulo_section" id="modulo_section_MisPlantillasLaborales">-->
        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_MisPlantillasLaborales">
            <div class="col-12"><h3 class="pt-3 m-0 p-3 text-left" id="MisPlantillasLaborales_nombre_empresa"></h3></div> <!-- Nombre Empresa -->
            <div class="row col-12 m-0" style="height: 110px;">
                <div class="row col-sm-12 col-md-6 col-lg-3 m-0 px-5" style="">
                    <div class="row col-12 m-0" style="border-radius: 10px; background-color: #9fc823;">
                        <p class="col-12 text-center d-flex justify-content-center align-items-end m-0" style=""><strong style="font: bold 1.5rem Arial;" id="num_sucursales"></strong></p>
                        <p class="col-12 text-center d-flex justify-content-center align-items-center m-0" style="font: 1.5rem Arial;">
                            <strong style="font: bold 1.5rem Arial;" id="MisPlantillasLaborales_num_emleados">0 </strong><em style="font: normal 1.5rem Arial;" class="pl-3 m-0"> Empleados</em>
                        </p>
                    </div>
                </div>
                <div class="row col-sm-12 col-md-6 col-lg-3 m-0 px-5">
                    <div class="row col-12 m-0" style="border-radius: 10px; background-color: #cccccc;">
                        <p class="col-12 text-center d-flex justify-content-center align-items-end m-0" style=""><strong style="font: bold 1.5rem Arial;">Perfil Completo</strong></p>
                        <p class="col-12 text-center d-flex justify-content-center align-items-center m-0" style="font: 1.5rem Arial;">
                            <strong style="font: bold 1.5rem Arial;" id="MisPlantillasLaborales_p_completo">0 </strong><em style="font: normal 1.5rem Arial;" class="pl-3 m-0"> Empleados</em>
                        </p>
                    </div>
                </div>
                <div class="row col-sm-12 col-md-6 col-lg-3 m-0 px-5">
                    <div class="row col-12 m-0" style="border-radius: 10px; background-color: #cccccc;">
                        <p class="col-12 text-center d-flex justify-content-center align-items-end m-0" style=""><strong style="font: bold 1.5rem Arial;">Perfil en proceso</strong></p>
                        <p class="col-12 text-center d-flex justify-content-center align-items-center m-0" style="font: 1.5rem Arial;">
                            <strong style="font: bold 1.5rem Arial;" id="MisPlantillasLaborales_p_proceso">0 </strong><em style="font: normal 1.5rem Arial;" class="pl-3 m-0"> Empleados</em>
                        </p>
                    </div>
                </div>
                <div class="row col-sm-12 col-md-6 col-lg-3 m-0 px-5">
                    <div class="row col-12 m-0" style="border-radius: 10px; background-color: #cccccc;">
                        <p class="col-12 text-center d-flex justify-content-center align-items-end m-0" style=""><strong style="font: bold 1.5rem Arial;">Perfil sin completar</strong></p>
                        <p class="col-12 text-center d-flex justify-content-center align-items-center m-0" style="font: 1.5rem Arial;">
                            <strong style="font: bold 1.5rem Arial;" id="MisPlantillasLaborales_p_scompletar">0 </strong><em style="font: normal 1.5rem Arial;" class="pl-3 m-0"> Empleados</em>
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-12 m-0 mb-5">
                <h3 class="pt-3 m-0 p-3 text-left">Mis plantillas laborales</h3>
                <div class="row col-12 m-0">
                    <h7 class="col-12 m-0 mb-3">Seleccione la sucursal que desea mostrar su plantilla laboral</h7>
                    <div class="col-12 m-0">
                        <select
                            class="form-control-plaintext input py-2 px-0 text-dark m-0 mb-1"
                            id="MisPlantillasLaborales_listado"
                            placeholder="Lista de sucursales registradas"
                            required=""
                            style="font: bold 1.4rem Arial; border: none; background: none; border-bottom: solid 2px #495057;">
                            <option disabled="" selected="" value="">Lista de sucursales registradas</option>
                            <!--                                <option value="3057">Rocket Inc. 1 </option>
                                                            <option value="3058">Raccoon Inc 2 </option>-->
                        </select>
                    </div>
                </div>
            </div>
            <div class="row col-12 m-0 px-5 mb-4" style="height: 210px;">
                <div class="col-8 m-0" style="border-radius: 20px; border-right-style: dotted; border-right-color: white; border-right-width: 4px; border-top: solid 10px #cccccc; border-bottom: solid 10px #cccccc; background-color: #cccccc;">
                    <div class="col-12 m-0" style="">
                        <strong style="color: #da2a1c; font-size: 1.7rem;">Registra tus sucursales a través de código de invitación</strong>
                    </div>
                    <div class="row col-12 m-0 p-3">
                        <div class="col-4 m-0 d-flex justify-content-center align-items-center">
                            <i class="fas fa-user-friends" style="height: 80px;width: 100px;"></i>
                        </div>
                        <div class="col-4 m-0 d-flex justify-content-center align-items-center">
                            <i class="fas fa-clipboard-check" style="height:80px;width:100px"></i>
                        </div>
                        <div class="col-4 m-0 d-flex justify-content-center align-items-center">
                            <i class="fas fa-building" style="height:80px; width:100px;"></i>
                        </div>
                    </div>
                    <div class="row col-12 m-0">
                        <div class="row col-4 m-0 d-flex justify-content-center align-items-center">
                            <div class="col-1 m-0 p-0">
                                <strong style="font-size: 4rem; color: #da2a1c;">1</strong>
                            </div>
                            <div class="col-11 m-0 p-0 pl-2">
                                <em style="font: normal 1.2rem Arial;">Comparte tú código con tu equipo de trabajo</em>
                            </div>
                        </div>
                        <div class="row col-4 m-0 d-flex justify-content-center align-items-center">
                            <div class="col-1 m-0 p-0">
                                <strong style="font-size: 4rem; color: #da2a1c;">2</strong>
                            </div>
                            <div class="col-11 m-0 p-0 pl-3">
                                <em style="font: normal 1.2rem Arial;">El encargado de la sucursal registra la información</em>
                            </div>
                        </div>
                        <div class="row col-4 m-0 d-flex justify-content-center align-items-center">
                            <div class="col-1 m-0 p-0">
                                <strong style="font-size: 4rem; color: #da2a1c;">3</strong>
                            </div>
                            <div class="col-11 m-0 p-0 pl-3">
                                <em style="font: normal 1.2rem Arial;">¡Listo! Se ha conectado a tu red de trabajo</em>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4 m-0" style="border-radius: 20px; border-left-style: dotted; border-left-color: white; border-left-width: 4px; border-top: solid 10px #da2a1c; border-bottom: solid 10px #da2a1c; background-color: #da2a1c;">
                    <div class="col-12 m-0 text-center" style="">
                        <strong style="font-size: 1.7rem; color: white;">CÓDIGO DE ACCESO</strong>
                    </div>
                    <div class="col-12 m-0 py-3">
                        <label style="height: 80px; border: solid 4px white; font: bold 3rem Arial; color: white;" class="m-0 w-100 d-flex justify-content-center align-items-center">12HS-9183-4526</label>
                    </div>
                    <div class="col-12 m-0 py-1 d-flex justify-content-center align-items-center">
                        <button class="btn row col-6 m-0 py-2" style="background-color: white; border-radius: 7px; width: 50%;">
                            <i class="fas fa-share-alt col-4" style="font-size: 1.8rem; color: #da2a1c;"></i>
                            <em class="col-8 m-0 p-0 text-left" style="font: normal 1.5rem Arial; color: #da2a1c;">Compartir</em>
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-12 m-0 px-5 mb-4"><input type="button" class="btn px-5" style="background-color: #da2a1c; color: white; font: normal 1.2rem Arial; border-radius: 10px;" value="Mostrar Avance de Registro" /></div>
            <div class="col-12 m-0 px-3 mb-5" style="height: 800px;">
                <div class="row col-12 m-0 p-0" style="border: solid 2px #cccccc; border-radius: 10px; max-height: 800px;">
                    <div class="row col-12 m-0 p-0 mb-5" style="">
                        <div class="col-8"><h3 class="pt-3 m-0 p-3 text-left">Lista de Empleados</h3></div>
                        <div class="col-4 d-flex justify-content-center align-items-center">
                            <input
                                type="text"
                                placeholder="Buscar Empleado o Folio de Empleado"
                                class="w-75 h-50 px-3"
                                style="border: solid 1px #cccccc; border-right: none; border-top-left-radius: 7px; border-bottom-left-radius: 7px; font: normal 1.3rem Arial;"
                                />
                            <i class="fas fa-search" style='font-size: 2.1rem;background-color: white;border: solid 1px #cccccc;border-left: none;padding: 3px;border-top-right-radius: 7px;border-bottom-right-radius: 7px;'></i>
                        </div>
                    </div>
                    <div class="row col-12 m-0 p-0" style="overflow-y: scroll; max-height: 700px;" id="listado_areas_sucursal">

                    </div>
                </div>
            </div>
            <div class="col-12 m-0 px-5 mb-4 text-center"><input type="button" class="btn px-5" style="background-color: #da2a1c; color: white; font: normal 1.2rem Arial; border-radius: 10px;" value="Actualizar Registro" /></div>
        </div>
        <!--</div>-->


<spring:url value="${pathRecursos}/css/Empresa/modulo_plantillas_laborales.css" var="modulo_plantillas_laboralesCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_plantillas_laborales.js" var="modulo_plantillas_laboralesJS" />
<link href="${modulo_plantillas_laboralesCSS}" rel="stylesheet"/>
<script src="${modulo_plantillas_laboralesJS}" ></script>


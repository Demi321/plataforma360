<%-- 
    Document   : modulo_registrar_mi_sucursal
    Created on : 24/11/2020, 10:37:41 AM
    Author     : global
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_RegistrarMiSucursal">
    <!--    <h3>Registra y activa una empresa</h3>-->
    <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            <div class="col-sm-12" id="registros_file"></div>
            <form id="form_registrar_institucion" class="form_registrar_institucion row col-12 m-0 p-0 h-100 w-100">
                <input type="hidden" id="id360" value="28">
                <input type="hidden" id="idModulo">
                <input type="hidden" id="id_institucion">

                <!--/***************Toda la vista***************/-->

                <div class="col-12" id="cont_subir_documento">
                    <div class="caja row m-0">
                        <div class="col-12 p-2"><h7 class="text-dark">Ingresa el código de la empresa a la que pertenece la sucursal</h7></div>
                        <div class="row m-0 col-12 p-2 ">
                            <input type="text" class="col-6 form-control text-center"><div class="col-1"></div>
                            <input type="submit" class=" col-1 btn btn-danger">
                        </div>
                        <div class="col-12 p-0">


                        </div>
                        <div class="col-sm-12 p-0">
                            <p>* Este código es proporcionado por el administrador de la empresa es necesario que te lo comparta para que puedas registrar una sucursal que pertenesca a la red de trabajo creada </p>
                        </div>
                    </div>
                </div>
                <hr class="w-100">

                <!--/******************************/-->

                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra tu sucursal de forma individual</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <input type="hidden" id="id_empresa" value="28">
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-3 col-lg-2">
                                <div class="row m-0 col-12">
                                    <div class="col-sm-12">
                                        <div class="logotipo_preview" id="logotipo_preview" ></div>
                                    </div>
                                    <div class="col-sm-12 mt-1">
                                        <label class="d-none" for="logotipo">Agregar logotipo</label>
                                        <!--<br>-->
                                        <label class="" for="logotipo" style="font-size: 10px;">Agregue un archivo jpg o png de un peso menor a 3 MB</label>
                                        <!--<br>-->
                                        <!--<form id="upload" method='post' enctype="multipart/form-data">-->
                                        <input type="button" class="btn btn-danger boton mx-auto" id="chose_file" value="Seleccionar Logo">
                                        <div class="d-none">
                                            <input type="text" id="logotipo" value="">
                                            <input type="file" id="upFile" name="files[]">
                                            <output id="list"></output>
                                        </div>
                                        <!--<input type="submit" class="btn btn-secondary w-50" value="Subir al bucket" style="height: 3%;">-->
                                        <!--</form>-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                <div class="row m-0 col-12">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="registro_patronal">Registro Patronal:</label>
                                        <input type="text" name="registro_patronal" class="form-control" id="registro_patronal" placeholder="Indique registro ante IMSS" required="">
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="razon_social">Razón Social:</label>
                                        <input type="text" name="razon_social" class="form-control" id="razon_social" placeholder="Razón Social" required="">
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="col-12" for="rfc">RFC:</label>
                                        <input type="text" name="rfc" class="form-control" id="rfc" placeholder="RFC" required="">
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="sector">Actividad Económica o Sector:</label>
                                        <select class="form-control" name="sector" id="tipo_sector" placeholder="Seleccione uno" required="">
                                            <option disabled="" selected="" value="">Selecciona una opción</option>
                                            <option value="19">- Otro Giro de Empresa</option>
                                            <option value="13">Bancos</option>
                                            <option value="25">Carnicería</option>
                                            <option value="12">Cine</option>
                                            <option value="4">Cruceros</option>
                                            <option value="26">Departamentales</option>
                                            <option value="22">Destinos de Sol y Playa</option>
                                            <option value="16">Empresa de Construcción</option>
                                            <option value="17">Empresa de Minería</option>
                                            <option value="20">Esteticas y Barberias</option>
                                            <option value="18">Fabricación de Transportes</option>
                                            <option value="11">Farmacia</option>
                                            <option value="28">Gasolineras y gas</option>
                                            <option value="5">Hoteles</option>
                                            <option value="15">Industria Esencial</option>
                                            <option value="2">Instituto Nacional de Migración</option>
                                            <option value="29">Lavandería y tintorería</option>
                                            <option value="7">Manufacturera</option>
                                            <option value="8">Minería</option>
                                            <option value="21">Museos</option>
                                            <option value="27">Oficina</option>
                                            <option value="23">Operadores de playas</option>
                                            <option value="24">Parques acuáticos</option>
                                            <option value="6">Restaurantes</option>
                                            <option value="14">Servicios a Domicilio</option>
                                            <option value="10">Supermercado</option>
                                            <option value="9">Teatros</option>
                                            <option value="30">Telecomunicaciones</option>
                                            <option value="1">Transporte Aéreo</option>
                                            <option value="3">Transporte Terrestre</option>
                                        </select>
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="" for="planta">Nombre del Edificio o Centro de Trabajo:</label>
                                        <input type="text" name="planta" class="form-control" id="nombre_edificio" placeholder="Indique tipo" required="">
                                    </div>
                                    <!--                        <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                                                                <label class="" for="planta">Planta:</label>
                                                                                <input type="text" name="planta" class="form-control" id="planta" placeholder="Indique tipo" required>
                                                                            </div>  -->
                                    <div class="col-6 col-md-3 col-lg-3 col-xl-3 d-flex justify-content-center align-items-end">
                                        <div class="col-12 rdo">
                                            <label class="checkbox-inline">
                                                Patrón Primario
                                                <input type="radio" name="1" value="patron_primario" id="radio_patron_primario" onchange="document.getElementById('patron_primario').value = $('#radio_patron_primario').is(':checked');document.getElementById('proveedor').value = $('#radio_proveedor').is(':checked');">
                                            </label>
                                        </div>
                                        <input type="hidden" name="patron_primario" id="patron_primario" value="false" required="">
                                    </div>
                                    <div class="col-6 col-md-3 col-lg-3 col-xl-3 d-flex justify-content-center align-items-end">
                                        <div class="col-12 rdo">
                                            <label class="checkbox-inline">
                                                Proveedor
                                                <input type="radio" name="1" value="proveedor" id="radio_proveedor" onchange="document.getElementById('proveedor').value = $('#radio_proveedor').is(':checked');document.getElementById('patron_primario').value = $('#radio_patron_primario').is(':checked');">
                                            </label>
                                        </div>
                                        <input type="hidden" name="proveedor" id="proveedor" value="true" required="">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 text-left mt-1"><h7 class="text-dark">Dirección</h7></div>

                        <div class="form-group row m-0 p-2 pt-4">
                            <div class="row m-0 col-sm-12 col-md-8 p-0">
                                <label class="col-sm-12 col-md-12 col-form-label d-flex align-items-center">Dirección:</label>
                                <div class="col-sm-12 col-md-12 d-flex align-items-center">
                                    <input type="text" class="form-control pac-target-input" id="d_autocompletar4" value="" placeholder="Ingresa una dirección." autocomplete="off" required="true">
                                </div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Número exterior:</label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                    <input type="text" class="form-control" id="n_exterior" placeholder="Número exterior" value="">
                                </div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Número interior: </label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                    <input type="text" class="form-control" id="n_interior" placeholder="Número interior" value="">
                                </div>
                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Calle:</label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                    <input type="text" class="form-control bg-secondary text-white" id="calle4" placeholder="Calle" disabled="true">
                                </div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Colonia: </label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                    <input type="text" class="form-control bg-secondary text-white" id="colonia4" placeholder="Colonia" value="" disabled="true">
                                </div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Ciudad o Municipio</label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                    <input type="text" class="form-control bg-secondary text-white" id="municipio4" placeholder="Ciudad o Municipio" disabled="true">
                                </div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">CP</label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2"><input type="text" class="form-control bg-secondary text-white" id="cp4" placeholder="Código Postal" disabled="true"></div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Estado:</label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                    <input type="text" class="form-control bg-secondary text-white" id="estado4" placeholder="Estado" value="" disabled="true">
                                </div>

                                <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Pais:</label>
                                <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2"><input type="text" class="form-control bg-secondary text-white" id="pais3" placeholder="Pais" disabled="true"></div>
                            </div>
                            <div class="col-sm-12 col-md-4 p-0" id="map4" style="min-height: 150px; position: relative; overflow: hidden;"></div>
                        </div>

                        <hr>
                        <div class="col-12 text-left mt-2"><h7 class="text-dark">Plantilla Laboral</h7></div>
                        <div class="col-12 row">
                            <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                <label class="col-12" for="numero_trabajadores">Número de trabajadores:</label>
                                <input type="number" name="numero_trabajadores" class="form-control" id="numero_trabajadores" placeholder="Indique un número" required="">
                                <input type="hidden" id="tipo_institucion">
                            </div>
                        </div>
                    </div>
                    <div class="caja row m-0">
                        <div class="col-12 text-left mt-2"><h7 class="text-dark">Datos de Contacto</h7></div>
                        <div class="col-12 row">
                            <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                <label class="" for="nombre">Nombre (s):</label>
                                <input type="text" name="nombre" class="form-control" id="nombre" placeholder="Nombre" required="">
                            </div>
                            <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                <label class="" for="apellido_p">Apellido Paterno:</label>
                                <input type="text" name="apellido_p" class="form-control" id="apellido_p" placeholder="Apellido Paterno" required="">
                            </div>
                            <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                <label class="" for="apellido_m">Apellido Materno:</label>
                                <input type="text" name="apellido_m" class="form-control" id="apellido_m" placeholder="Apellido Materno" required="">
                            </div>
                            <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                <label class="" for="telefono">Teléfono:</label>
                                <input type="number" name="telefono" class="form-control" id="telefono" placeholder="Indique en número" required="">
                            </div>
                            <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                <label class="col-12" for="extension">Extensión:</label>
                                <input type="number" name="extension" class="form-control" id="extension" placeholder="Indique en número">
                            </div>
                            <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                <label class="col-12" for="correo">Correo:</label>
                                <input type="text" name="correo" class="form-control" id="correo" placeholder="Indique su correo electrónico" required="">
                            </div>
                        </div>
                    </div>
                </div>
                <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar" id="boton_seleccionar_institucion">
            </form>
        </div>
    </div>


</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_registrar_mi_sucursal.css" var="modulo_registrar_mi_sucursalCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_registrar_mi_sucursal.js" var="modulo_registrar_mi_sucursalJS" />
<link href="${modulo_registrar_mi_sucursalCSS}" rel="stylesheet"/>
<script src="${modulo_registrar_mi_sucursalJS}" ></script>
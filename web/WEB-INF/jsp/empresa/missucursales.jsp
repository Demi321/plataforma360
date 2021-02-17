
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 missucursales" id="base_modulo_${id}">
<div class="listado_instituciones row m-0 p-2">
        <div class="row m-0 p-0 col-12 content">
            <div
                class="row col-12 m-0 p-0"
                style="
                width: 100%;
                height: 100%;
                left: 0;
                top: 0;
                color: #212224;
                justify-content: center;
                align-items: center;
                display: flex;
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
                overflow: hidden;
                "
                >
                <div class="col-sm-12 p-2" id="form_registro_empresa">
                    <form class="form-group row m-0 p-2 pt-4 text-dark" id="form_registro_de_empresa">
                        <div class="row col-12 col-sm-12 col-md-4 col-lg-3 m-0 p-2 px-5" style="min-width: 150px; display: flex; align-items: center;">
                            <div class="logotipo_preview" id="MisSucursales_logotipo_preview">
                                <svg class="svg-inline--fa fa-image fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="image" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
                                <path
                                    fill="currentColor"
                                    d="M464 448H48c-26.51 0-48-21.49-48-48V112c0-26.51 21.49-48 48-48h416c26.51 0 48 21.49 48 48v288c0 26.51-21.49 48-48 48zM112 120c-30.928 0-56 25.072-56 56s25.072 56 56 56 56-25.072 56-56-25.072-56-56-56zM64 384h384V272l-87.515-87.515c-4.686-4.686-12.284-4.686-16.971 0L208 320l-55.515-55.515c-4.686-4.686-12.284-4.686-16.971 0L64 336v48z"
                                    ></path>
                                </svg>
                                <!-- <i class="fas fa-image"></i> -->
                            </div>

                            <div class="d-none">
                                <input type="text" id="MisSucursales_logotipo" value="" />
                                <input type="file" id="MisSucursales" name="files[]" />
                                <output id="list"></output>
                            </div>
                        </div>
                        <div class="row col-12 col-sm-12 col-md-8 col-lg-9 m-0 p-2">
                            <div style="font-size: 1.4rem; align-items: center; display: flex; padding: 10px 5px;" class="col-12 p-0">
                                <select
                                    class="form-control-plaintext input p-2 text-dark m-0 mb-1"
                                    name="sector"
                                    id="MisSucursales_listado"
                                    placeholder="Seleccione uno"
                                    required=""
                                    style="font: bold 1.4rem Arial; border: none; background: none; border-bottom: solid 2px #495057;"
                                    >
                                    <option disabled="" selected="" value="">Selecciona una opción</option>

                                </select>
                            </div>
                            <div class="col-12 p-0 mb-3" style="font-size: 1rem; align-items: center; display: flex; padding: 10px 5px;">
                                Codigo de invitación: <strong style="font-size: 1rem; padding: 5px 20px;">ALG4-R482-SDF2-B254</strong>
                                <svg
                                    title="Compartir Código"
                                    class="svg-inline--fa fa-share-alt fa-w-14"
                                    aria-hidden="true"
                                    focusable="false"
                                    data-prefix="fas"
                                    data-icon="share-alt"
                                    role="img"
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 448 512"
                                    data-fa-i2svg=""
                                    style="font-size: 1.5rem; margin-left: 20px; color: #17a2b8; cursor: pointer;"
                                    aria-labelledby="svg-inline--fa-title-SG8cev6tXIPl"
                                    >
                                <title id="svg-inline--fa-title-SG8cev6tXIPl">Compartir Código</title>
                                <path
                                    fill="currentColor"
                                    d="M352 320c-22.608 0-43.387 7.819-59.79 20.895l-102.486-64.054a96.551 96.551 0 0 0 0-41.683l102.486-64.054C308.613 184.181 329.392 192 352 192c53.019 0 96-42.981 96-96S405.019 0 352 0s-96 42.981-96 96c0 7.158.79 14.13 2.276 20.841L155.79 180.895C139.387 167.819 118.608 160 96 160c-53.019 0-96 42.981-96 96s42.981 96 96 96c22.608 0 43.387-7.819 59.79-20.895l102.486 64.054A96.301 96.301 0 0 0 256 416c0 53.019 42.981 96 96 96s96-42.981 96-96-42.981-96-96-96z"
                                    ></path>
                                </svg>
                                <!-- <i class="fas fa-share-alt px-3"></i> -->
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Nombre de la Empresa: </label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border" id="MisSucursales_empresa" placeholder="Nombre de la Empresa" disabled="true" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Razón Social: </label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_Empresa_razon_social" placeholder="Razón Social" disabled="true" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">RFC: </label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border" id="MisSucursales_Empresa_rfc" placeholder="RFC" disabled="true" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Registro Patronal:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_Empresa_registro_patronal" placeholder="Registro Patronal" disabled="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Correo:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_Empresa_correo" placeholder="Correo" disabled="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Teléfono:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_Empresa_telefono" placeholder="Teléfono" disabled="true" />
                            </div>
                        </div>
                    </form>

                    <form class="form-group row m-0 p-2 pt-4 text-dark" id="MisSucursales_form_actualizar">
                        <div class="row col-12 col-sm-12 col-md-4 col-lg-3 m-0 p-2 px-5" style="min-width: 150px; display: flex; align-items: center;" id="map5"></div>
                        <div class="row col-12 col-sm-12 col-md-8 col-lg-9 m-0 p-2">
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center" for="sector">Actividad Económica o Sector:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <select class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" name="sector" id="MisSucursales_tipo_sector" placeholder="Seleccione uno" required="">
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
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center" for="planta">Nombre del Edificio o Centro de Trabajo:</label>

                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" name="planta" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_nombre_edificio" placeholder="Indique tipo" required="" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Razón Social:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_razon_social" placeholder="Razón Social" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Registro Patronal:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_registro_patronal" placeholder="Registro Patronal" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">RFC:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_rfc" placeholder="RFC" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center" for="planta"></label>
                            <div class="row m-0 col-sm-12 col-md-8 d-flex align-items-center">
                                <label class="col-6 p-2 m-0 checkbox-inline d-flex justify-content-center align-items-center">
                                    Patrón Primario
                                    <input
                                        type="radio"
                                        name="1"
                                        value="patron_primario"
                                        id="MisSucursales_radio_patron_primario"
                                        onchange="document.getElementById('MisSucursales_patron_primario').value = $('#MisSucursales_radio_patron_primario').is(':checked');document.getElementById('MisSucursales_proveedor').value = $('#MisSucursales_radio_proveedor').is(':checked');"
                                        style="margin-left: 20px;"
                                        />
                                </label>
                                <input type="hidden" name="MisSucursales_patron_primario" id="MisSucursales_patron_primario" value="false" required="">
                                <label class="col-6 p-2 m-0 checkbox-inline d-flex justify-content-center align-items-center">
                                    Proveedor
                                    <input
                                        type="radio"
                                        name="1"
                                        value="proveedor"
                                        id="MisSucursales_radio_proveedor"
                                        onchange="document.getElementById('MisSucursales_proveedor').value = $('#MisSucursales_radio_proveedor').is(':checked');document.getElementById('MisSucursales_patron_primario').value = $('#MisSucursales_radio_patron_primario').is(':checked');"
                                        style="margin-left: 20px;"
                                        />
                                </label>
                                <input type="hidden" name="MisSucursales_proveedor" id="MisSucursales_proveedor" value="false" required="">
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Número trabajadores:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="number" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_n_trabajadores" placeholder="Número trabajadores" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Dirección:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="d_autocompletar5" value="" placeholder="Ingresa una dirección." autocomplete="off" required="true" />
                            </div>
                            <input type="hidden" id="calle5" />
                            <input type="hidden" id="colonia5" />
                            <input type="hidden" id="cp5" />
                            <input type="hidden" id="estado5" />
                            <input type="hidden" id="municipio5" />
                            <input type="hidden" id="pais5" />
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Número Exterior:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_n_exterior" placeholder="Número Exterior" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Número Interior:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_n_interior" placeholder="Número Interior" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Datos de contacto</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center"></div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Nombre (s):</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_nombre" placeholder="Nombre"/>
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Apellido Paterno:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_apellido_p" placeholder="Apellido Paterno" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Apellido Materno:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_apellido_m" placeholder="Apellido Materno" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Correo:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_correo_contacto" placeholder="Correo" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Teléfono:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_telefono_contacto" placeholder="Teléfono" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Extensión:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark m-0 mb-1" id="MisSucursales_extension_contacto" placeholder="Extensión" />
                            </div>
                            <input type="hidden" id="MisSucursales_tipo_servicio">

                            <div class="col-sm-12 d-flex align-items-center justify-content-center mt-5">
                                <input type="button" id="baja_sucursal" class="btn btn-secondary m-0" value="Dar de baja Sucursal" style="font-size: 1rem; min-width: 160px; padding: 5px; border-radius: 15px; font: bold 1.1rem arial;">
                                <input type="submit" class="btn btn-danger m-0 ml-5" value="Actualizar Datos" style="font-size: 1rem; min-width: 160px; padding: 5px; border-radius: 15px; font: bold 1.1rem arial;" />
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="row m-0 p-0 col-12 content">
            <div class="p-0 py-2 col-12">
                <div class="card">
                    <div class="card-header text-dark text-left" style="background: none; font-size: 1.5rem;">
                        Da de alta tu plantilla laboral
                    </div>
                    <div class="card-body text-dark text-left px-4" style="background: no-repeat; border: none; font-size: 1.4rem;">
                        <div style="font-size: 1.4rem; align-items: center; display: flex; padding: 10px 5px;" class="d-none">
                            Codigo de invitación: <strong style="font-size: 1.4rem; padding: 5px 20px;">ALG4-R482-SDF2-B254</strong>
                            <svg
                                title="Compartir Código"
                                class="svg-inline--fa fa-share-alt fa-w-14"
                                aria-hidden="true"
                                focusable="false"
                                data-prefix="fas"
                                data-icon="share-alt"
                                role="img"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 448 512"
                                data-fa-i2svg=""
                                style="font-size: 2rem; margin-left: 20px; color: #17a2b8; cursor: pointer;"
                                aria-labelledby="svg-inline--fa-title-SG8cev6tXIPl"
                                >
                            <title id="svg-inline--fa-title-SG8cev6tXIPl">Compartir Código</title>
                            <path
                                fill="currentColor"
                                d="M352 320c-22.608 0-43.387 7.819-59.79 20.895l-102.486-64.054a96.551 96.551 0 0 0 0-41.683l102.486-64.054C308.613 184.181 329.392 192 352 192c53.019 0 96-42.981 96-96S405.019 0 352 0s-96 42.981-96 96c0 7.158.79 14.13 2.276 20.841L155.79 180.895C139.387 167.819 118.608 160 96 160c-53.019 0-96 42.981-96 96s42.981 96 96 96c22.608 0 43.387-7.819 59.79-20.895l102.486 64.054A96.301 96.301 0 0 0 256 416c0 53.019 42.981 96 96 96s96-42.981 96-96-42.981-96-96-96z"
                                ></path>
                            </svg>
                            <!-- <i class="fas fa-share-alt px-3"></i> -->
                        </div>
                        <p class="card-text">Da de alta tu plantilla laboral de forma masiva o individual e invitalos a formar parte de tu red de trabajo.</p>
                        <a href="#" class="btn btn-danger">Registrar Plantilla</a>
                    </div>
                </div>
            </div>

            <div class="p-0 py-2 col-12">
                <div class="card">
                    <div class="card-header text-dark text-left" style="background: none; font-size: 1.5rem;">Administra tus areas de trabajo</div>
                    <div class="card-body text-dark text-left px-4" style="background: no-repeat; border: none; font-size: 1.4rem;">
                        <p class="card-text">Selecciona las areas de trabajo que tiene tu sucursal</p>
                        <a href="#" class="btn btn-danger">Administrar áreas de trabajo</a>
                    </div>
                </div>
            </div>
            <div class="p-0 py-2 col-12">
                <div class="card">
                    <div class="card-header text-dark text-left" style="background: none; font-size: 1.5rem;">Protocolo de Seguridad Sanitaria</div>
                    <div class="card-body text-dark text-left px-4" style="background: no-repeat; border: none; font-size: 1.4rem;">
                        <div style="font-size: 1.4rem; align-items: center; display: flex; padding: 10px 5px;" class="d-none">
                            Codigo de invitación: <strong style="font-size: 1.4rem; padding: 5px 20px;">ALG4-R482-SDF2-B254</strong>
                            <svg
                                title="Compartir Código"
                                class="svg-inline--fa fa-share-alt fa-w-14"
                                aria-hidden="true"
                                focusable="false"
                                data-prefix="fas"
                                data-icon="share-alt"
                                role="img"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 448 512"
                                data-fa-i2svg=""
                                style="font-size: 2rem; margin-left: 20px; color: #17a2b8; cursor: pointer;"
                                aria-labelledby="svg-inline--fa-title-SG8cev6tXIPl"
                                >
                            <title id="svg-inline--fa-title-SG8cev6tXIPl">Compartir Código</title>
                            <path
                                fill="currentColor"
                                d="M352 320c-22.608 0-43.387 7.819-59.79 20.895l-102.486-64.054a96.551 96.551 0 0 0 0-41.683l102.486-64.054C308.613 184.181 329.392 192 352 192c53.019 0 96-42.981 96-96S405.019 0 352 0s-96 42.981-96 96c0 7.158.79 14.13 2.276 20.841L155.79 180.895C139.387 167.819 118.608 160 96 160c-53.019 0-96 42.981-96 96s42.981 96 96 96c22.608 0 43.387-7.819 59.79-20.895l102.486 64.054A96.301 96.301 0 0 0 256 416c0 53.019 42.981 96 96 96s96-42.981 96-96-42.981-96-96-96z"
                                ></path>
                            </svg>
                            <!-- <i class="fas fa-share-alt px-3"></i> -->
                        </div>
                        <p class="card-text">Registra tus evidencias correspondientes al protocolo de seguridad sanitaria de tu sucursal.</p>
                        <a href="#" class="btn btn-danger">Ver</a>
                    </div>
                </div>
            </div>

            <div class="p-0 py-2 col-12">
                <div class="card">
                    <div class="card-header text-dark text-left" style="background: none; font-size: 1.5rem;">Plataforma GIS</div>
                    <div class="card-body text-dark text-left px-4" style="background: no-repeat; border: none; font-size: 1.4rem;">
                        <div style="font-size: 1.4rem; align-items: center; display: flex; padding: 10px 5px;" class="d-none">
                            Codigo de invitación: <strong style="font-size: 1.4rem; padding: 5px 20px;">ALG4-R482-SDF2-B254</strong>
                            <svg
                                title="Compartir Código"
                                class="svg-inline--fa fa-share-alt fa-w-14"
                                aria-hidden="true"
                                focusable="false"
                                data-prefix="fas"
                                data-icon="share-alt"
                                role="img"
                                xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 448 512"
                                data-fa-i2svg=""
                                style="font-size: 2rem; margin-left: 20px; color: #17a2b8; cursor: pointer;"
                                aria-labelledby="svg-inline--fa-title-SG8cev6tXIPl"
                                >
                            <title id="svg-inline--fa-title-SG8cev6tXIPl">Compartir Código</title>
                            <path
                                fill="currentColor"
                                d="M352 320c-22.608 0-43.387 7.819-59.79 20.895l-102.486-64.054a96.551 96.551 0 0 0 0-41.683l102.486-64.054C308.613 184.181 329.392 192 352 192c53.019 0 96-42.981 96-96S405.019 0 352 0s-96 42.981-96 96c0 7.158.79 14.13 2.276 20.841L155.79 180.895C139.387 167.819 118.608 160 96 160c-53.019 0-96 42.981-96 96s42.981 96 96 96c22.608 0 43.387-7.819 59.79-20.895l102.486 64.054A96.301 96.301 0 0 0 256 416c0 53.019 42.981 96 96 96s96-42.981 96-96-42.981-96-96-96z"
                                ></path>
                            </svg>
                            <!-- <i class="fas fa-share-alt px-3"></i> -->
                        </div>
                        <p class="card-text">Visualiza los estadisticos en tiempo real</p>
                        <a href="#" class="btn btn-danger">Ver</a>
                    </div>
                </div>
            </div>
        </div>

        <div class="d-none row col-12 m-0 p-0 butons">
            <input type="button" class="m-2 btn btn-danger boton bg-secondary" style="border: none;" value="cancelar" id="" />
            <!--                <input type="button" class="m-2 btn btn-danger boton" style="border: none;" value="Continuar" id="boton_seleccionar_institucion" />-->
            <input type="button" class="m-2 btn btn-danger boton" value="Seleccionar" id="boton_seleccionar_empresa" />
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

<spring:url value="${pathRecursos}/empresas360/missucursales/missucursales.css" var="modulo_missucursalesCSS" />
<spring:url value="${pathRecursos}/empresas360/missucursales/missucursales.js" var="modulo_missucursalesJS" />
<link href="${modulo_missucursalesCSS}" rel="stylesheet"/>
<script src="${modulo_missucursalesJS}" ></script>
<script>
//    init_missucursales("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_missucursales(${json});
</script>
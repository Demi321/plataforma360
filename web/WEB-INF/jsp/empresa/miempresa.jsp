
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 h-100 miempresa" id="base_modulo_${id}">
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
                    <form class="form-group row m-0 p-2 pt-4 text-dark" id="MiEmpresa_form_registro_de_empresa">
                        <div class="row col-12 col-sm-12 col-md-4 col-lg-3 m-0 p-2 px-5" style="min-width: 150px; display: flex; align-items: center;">
                            <div class="logotipo_preview" id="upFile_MiEmpresa_logotipo_preview">
                                <svg class="svg-inline--fa fa-image fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="image" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
                                <path
                                    fill="currentColor"
                                    d="M464 448H48c-26.51 0-48-21.49-48-48V112c0-26.51 21.49-48 48-48h416c26.51 0 48 21.49 48 48v288c0 26.51-21.49 48-48 48zM112 120c-30.928 0-56 25.072-56 56s25.072 56 56 56 56-25.072 56-56-25.072-56-56-56zM64 384h384V272l-87.515-87.515c-4.686-4.686-12.284-4.686-16.971 0L208 320l-55.515-55.515c-4.686-4.686-12.284-4.686-16.971 0L64 336v48z"
                                    ></path>
                                </svg>
                                <!-- <i class="fas fa-image"></i> -->
                            </div>

                            <div class="d-none">
                                <input type="text" id="upFile_MiEmpresa_logotipo" value="" />
                                <input type="file" id="upFile_MiEmpresa" name="files[]" />
                                <output id="list"></output>
                            </div>
                        </div>
                        <div class="row col-12 col-sm-12 col-md-8 col-lg-9 m-0 p-2">
                            <div style="font-size: 1.4rem; align-items: center; display: flex; padding: 10px 5px;" class="col-12 p-0">
                                <h7 style="font-size: 2rem;">Nombre de la empresa</h7>
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
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border" id="MiEmpresa_empresa" placeholder="Nombre de la Empresa" required="true" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Razón Social: </label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="MiEmpresa_razon_social" placeholder="Razón Social" required="true" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">RFC: </label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border" id="MiEmpresa_rfc" placeholder="RFC" required="true" />
                            </div>

                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Registro Patronal:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="MiEmpresa_registro_patronal" placeholder="Registro Patronal" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Correo:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="MiEmpresa_correo" placeholder="Correo" required="true" />
                            </div>
                            <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center">Teléfono:</label>
                            <div class="col-sm-12 col-md-8 d-flex align-items-center">
                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="MiEmpresa_telefono" placeholder="Teléfono" required="true" />
                            </div>
                            <div class="col-sm-12 d-flex align-items-center justify-content-center mt-5">
                                <input type="submit" class="btn btn-danger m-0" value="Actualizar Datos" style="font-size: 1rem; min-width: 160px; padding: 5px; border-radius: 15px; font: bold 1.1rem arial;" />
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
                        Registra tus sucursales
                    </div>
                    <div class="card-body text-dark text-left px-4" style="background: no-repeat; border: none; font-size: 1.4rem;">
                        <div style="font-size: 1.4rem; align-items: center; display: flex; padding: 10px 5px;">
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
                        <p class="card-text">Ahora puedes ampliar tu red de trabajo registrando tus sucursales o compartiendo el código con el encargado de la sucursal para que realice este proceso.</p>
                        <a href="#" class="btn btn-danger" id="ver_registrarsucursal">Registrar sucursales</a>
                    </div>
                </div>
            </div>

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
                        <a href="#" class="btn btn-danger" id="ver_plantillalaboral">Registrar Plantilla</a>
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
                        <p class="card-text">Manten la organizacion de tus sucursales y monitorea las evidencias de seguridad sanitaria que deben cumplir.</p>
                        <a href="#" class="btn btn-danger" id="ver_lineamientos">Ver</a>
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
                        <a href="#" class="btn btn-danger" id="ver_gis">Ver</a>
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

<spring:url value="${pathRecursos}/empresas360/miempresa/miempresa.css" var="modulo_miempresaCSS" />
<spring:url value="${pathRecursos}/empresas360/miempresa/miempresa.js" var="modulo_miempresaJS" />
<link href="${modulo_miempresaCSS}" rel="stylesheet"/>
<script src="${modulo_miempresaJS}" ></script>
<script>
//    init_miempresa("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_miempresa(${json});
</script>
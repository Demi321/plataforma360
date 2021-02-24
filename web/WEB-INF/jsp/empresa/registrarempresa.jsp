
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row col-12 m-0 p-0 registrarempresa" id="base_modulo_${id}">
 <h3>Registra y activa una empresa</h3>
    <div class="listado_instituciones row m-0 p-2">
        <div class="row col-12 m-0 p-0">
            <div class="row col-12 m-0 p-4 body">
                <!--                <div class="col-12 title" style="font: bold 3rem Arial; text-align: center;">
                                    Registra y activa una empresa
                                </div>-->

                <div class="row m-0 col-12 content">
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
                        <div class="col-sm-12 p-2">
                            <form class="form-group row m-0 p-2 pt-4 text-dark" id="form_registro_nueva_empresa">
                                <div class="row col-12 col-sm-12 col-md-4 col-lg-3 m-0 p-2 px-5" style="min-width: 150px; display: flex; align-items: center;">
                                    <div class="logotipo_preview" id="upFile_logo_nueva_empresa_logotipo_preview">
                                        <svg class="svg-inline--fa fa-image fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="image" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
                                        <path
                                            fill="currentColor"
                                            d="M464 448H48c-26.51 0-48-21.49-48-48V112c0-26.51 21.49-48 48-48h416c26.51 0 48 21.49 48 48v288c0 26.51-21.49 48-48 48zM112 120c-30.928 0-56 25.072-56 56s25.072 56 56 56 56-25.072 56-56-25.072-56-56-56zM64 384h384V272l-87.515-87.515c-4.686-4.686-12.284-4.686-16.971 0L208 320l-55.515-55.515c-4.686-4.686-12.284-4.686-16.971 0L64 336v48z"
                                            ></path>
                                        </svg>
                                        <!-- <i class="fas fa-image"></i> -->
                                    </div>
                                    <div class="col-sm-12 d-flex align-items-center justify-content-center mt-5">
                                        <input type="button" id="subir_img" class="btn btn-danger m-0" value="Sube una imagen" style="font-size: 1rem; min-width: 160px; padding: 5px; border-radius: 15px; font: bold 1.1rem arial;">
                                    </div>
                                    <span style="font: normal 1rem Arial; text-align: center;">
                                        Agrega un archivo jpg o png de un peso menor a 3 MB
                                    </span>
                                    <div class="d-none">
                                        <input type="text" id="upFile_logo_nueva_empresa_logotipo" value="" />
                                        <input type="file" id="upFile_logo_nueva_empresa" name="files[]" />
                                        <output id="list"></output>
                                    </div>
                                </div>
                                <div class="row col-12 col-sm-12 col-md-8 col-lg-9 m-0 p-2">
                                    <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center mt-4">Nombre de la Empresa: </label>
                                    <div class="col-sm-12 col-md-8 d-flex align-items-center mt-4">
                                        <input type="text" class="form-control-plaintext input bg-light rounded p-2 border" id="empresa" placeholder="Nombre de la Empresa" required="true" />
                                    </div>

                                    <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center mt-4">Razón Social: </label>
                                    <div class="col-sm-12 col-md-8 d-flex align-items-center mt-4">
                                        <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="razon_social" placeholder="Razón Social" required="true" />
                                    </div>

                                    <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center mt-4">RFC: </label>
                                    <div class="col-sm-12 col-md-8 d-flex align-items-center mt-4">
                                        <input type="text" class="form-control-plaintext input bg-light rounded p-2 border" id="rfc" placeholder="RFC" required="true" />
                                    </div>

                                    <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center mt-4">Registro Patronal:</label>
                                    <div class="col-sm-12 col-md-8 d-flex align-items-center mt-4">
                                        <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="registro_patronal" placeholder="Registro Patronal" required="true" />
                                    </div>
                                    <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center mt-4">Correo:</label>
                                    <div class="col-sm-12 col-md-8 d-flex align-items-center mt-4">
                                        <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark correo_registrarempresa" id="correo" placeholder="Correo" required="true" />
                                    </div>
                                    <label class="col-sm-12 col-md-4 col-form-label d-flex align-items-center mt-4">Teléfono:</label>
                                    <div class="col-sm-12 col-md-8 d-flex align-items-center mt-4">
                                        <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="telefono" placeholder="Teléfono" required="true" />
                                    </div>
                                    <div class="col-sm-12 d-flex align-items-center justify-content-center mt-5">
                                        <input type="submit" class="btn btn-danger m-0" value="Registrar" style="font-size: 1rem; min-width: 160px; padding: 5px; border-radius: 15px; font: bold 1.1rem arial;" />
                                    </div>
                                </div>
                            </form>
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

<spring:url value="${pathRecursos}/empresas360/registrarempresa/registrarempresa.css" var="modulo_registrarempresaCSS" />
<spring:url value="${pathRecursos}/empresas360/registrarempresa/registrarempresa.js" var="modulo_registrarempresaJS" />
<link href="${modulo_registrarempresaCSS}" rel="stylesheet"/>
<script src="${modulo_registrarempresaJS}" ></script>
<script>
//    init_registrarempresa("${id_usuario}", "${tipo_usuario}", '${tipo_servicio}');
init_registrarempresa(${json});
</script>
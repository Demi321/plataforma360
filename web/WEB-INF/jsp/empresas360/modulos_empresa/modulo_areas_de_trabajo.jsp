<%-- 
    Document   : modulo_areas_de_trabajo
    Created on : 24/11/2020, 10:41:16 AM
    Author     : global
--%>

<div class="row col-12 m-0 p-2 pt-3" id="base_modulo_ÁreasdeTrabajo">
    <h3>Administra tus areas de trabajo</h3>
    <form id="registrar_area" class="row m-0 p-0 col-12">
        <input type="hidden" id="AreasdeTrabajo_tipo_usuario">
        <div style="font-size: 1rem;align-items: center;display: flex;padding: 10px 5px;" class="col-sm-12 col-md-7 p-2">
            <select class="form-control-plaintext input p-2 text-dark m-0 mb-1" id="AreasdeTrabajo_listado_sucursales" placeholder="Seleccione uno" required="" style="font: bold 1.4rem Arial; border: none; background: none; border-bottom: solid 2px #495057;">
                <option disabled="" selected="" value="">Selecciona una sucursal</option>
            </select>
        </div>
        <div class="row m-0 col-sm-12 col-md-5 p-2">
            <div class="col-sm-8">
                <input list="listado_Areas" name="AreasdeTrabajo_listado_Areas" id="AreasdeTrabajo_listado_Areas" placeholder="Establece una Área" class=" p-2 text-dark m-0 mb-1" style="
                       font: bold 1.4rem Arial;
                       border: none;
                       background: none;
                       border-bottom: solid 2px #495057;
                       " required="true" type="text">
                <datalist id="listado_Areas"></datalist>
            </div>
            <div class="col-sm-4 p-1"><input type="submit" class="btn btn-danger w-100" value="Agregar"></div>
        </div>
    </form>

    <div class="row m-0 p-0 col-12 content" id="areas_registradas">
        <!--                <div class="p-0 py-2 col-12">
                            <div class="card">
                                <div class="card-header text-dark text-left" style="background: none; font-size: 1.5rem;">
                                    <svg class="svg-inline--fa fa-minus-circle fa-w-16" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="minus-circle" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" data-fa-i2svg="">
                                    <path fill="currentColor" d="M256 8C119 8 8 119 8 256s111 248 248 248 248-111 248-248S393 8 256 8zM124 296c-6.6 0-12-5.4-12-12v-56c0-6.6 5.4-12 12-12h264c6.6 0 12 5.4 12 12v56c0 6.6-5.4 12-12 12H124z"></path>
                                    </svg>
                                     <i class="fas fa-minus-circle"></i> 
                                    Área 1
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
                                         <i class="fas fa-share-alt px-3"></i> 
                                    </div>
                                    <p class="card-text">Comparte el codigo de invitación a los encargados del area: <strong>Área 1</strong>.</p>
                                </div>
                            </div>
                        </div>-->

    </div>
</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_areas_de_trabajo.css" var="modulo_areas_de_trabajoCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_areas_de_trabajo.js" var="modulo_areas_de_trabajoJS" />
<link href="${modulo_areas_de_trabajoCSS}" rel="stylesheet"/>
<script src="${modulo_areas_de_trabajoJS}" ></script>

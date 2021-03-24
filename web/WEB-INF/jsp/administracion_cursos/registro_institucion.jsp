<%-- 
    Document   : registro_sucursal
    Created on : 22/03/2021, 10:19:18 AM
    Author     : XPS
--%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row col-12 m-0 p-2 pt-3 text-dark" id="base_modulo_RegistrarSucursal">
    <!--    <h3>Registra y activa una empresa</h3>-->
    <div class="registro_institucion row m-0 p-2">
        <div class="row col-12 m-0 p-0 h-100">
            <!--/******************************/-->
            <form id="form_RegistrarSucursal" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                <div class="col-12 content text-dark" id="formulario_institucion">
                    <div class="caja row m-0 p-0 col-12">
                        <div class="col-12"><h3 class="text-dark p-3 m-0">Registra tu Institución</h3></div>
                        <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                        <input type="hidden" id="id_empresa" value="" />
                        <div class="col-12 row m-0 p-2">
                            <div class="col-12 col-sm-12 col-md-3 col-lg-2">
                                <div class="row m-3 col-12">
                                    <div class="col-sm-12">
                                        <div
                                            class="logotipo_preview"
                                            id="upFile_RegistrarSucursal_logotipo_preview"

                                            ></div>
                                    </div>
                                    <div class="col-sm-12 mt-1">
                                        <label class="d-none" for="logotipo">Agregar logotipo</label>
                                        <!--<br>-->
                                        <label class="" for="logotipo" style="font-size: 10px;">Agregue un archivo jpg o png de un peso menor a 3 MB</label>
                                        <!--<br>-->
                                        <!--<form id="upload" method='post' enctype="multipart/form-data">-->
                                        <input type="button" class="btn btn-danger boton mx-auto" id="chose_file_RegistrarSucursal" value="Seleccionar Logo" />
                                        <div class="d-none">
                                            <input type="text" id="upFile_RegistrarSucursal_logotipo" value="" />
                                            <input type="file" id="upFile_RegistrarSucursal" name="files[]" />
                                            <output id="list"></output>
                                        </div>
                                        <!--<input type="submit" class="btn btn-secondary w-50" value="Subir al bucket" style="height: 3%;">-->
                                        <!--</form>-->
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="row m-3 col-12">
                                    <label class="col-6" for="nombre_institucion">Nombre de la Institución:</label>
                                    <input type="text" name="nombre_institucion" class="form-control col-6" id="Registrar_nombre_institucionl" placeholder="Nombre de Institución" required="" />
                                </div>
                                <div class="row m-3 col-12">                               
                                    <label class="col-6" for="razon_social">Razón Social:</label>
                                    <input type="text" name="razon_social" class="form-control col-6" id="Registrar_razon_social" placeholder="Razón Social" required="" />
                                </div>
                                <div class="row m-3 col-12">
                                    <label class="col-6" for="rfc">RFC:</label>
                                    <input type="text" name="rfc" class="form-control col-6" id="Registrar_rfc" placeholder="RFC" required="" />
                                </div>
                                 <div class="row m-3 col-12">
                                    <label class="col-6" for="registro_patronal">Registro Patronal:</label>
                                    <input type="text" name="registro_patronal" class="form-control col-6" id="Registrar_registro_patronal" placeholder="Indique registro ante IMSS" required="" />
                                </div>
                                <div class="row m-3 col-12">                               
                                    <label class="col-6" for="correo">Correo:</label>
                                    <input type="text" name="correo" class="form-control col-6" id="Registrar_correo" placeholder="Correo Electronico" required="" />
                                </div>
                                <div class="row m-3 col-12">
                                    <label class="col-6" for="telefono">Teléfono:</label>
                                    <input type="text" name="telefono" class="form-control col-6" id="Registrar_telefono" placeholder="Telefono" required="" />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>


</div>

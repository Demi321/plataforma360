<%-- 
    Document   : modulo_ajustes_privacidad
    Created on : 24/11/2020, 10:43:24 AM
    Author     : global
--%>

<div class="row col-12 m-0 p-0 h-100" id="base_modulo_AjustesdePrivacidad">
    <div class="row col-12 m-0 p-2 pt-3 h-100 d-flex justify-content-center align-items-center">
        <h3>Configuracion de privacidad y seguridad del centro de trabajo</h3>
        <div class="row col-12 m-0 p-3 h-75">
            <div class="col-12 col-sm-6 col-md-6 col-lg-4 p-3 text-center">
                <div class="row col-12 m-0 p-2">
                    <div class="col-12 px-5" class="titulo_ajuste">Bloquear la vinculacion a este centro de trabajo</div>
                    <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                        <div class="cuadrado_icon text-white">
                            <div data-current="" class="cuadrado_contenido parametro_modificable" id="vinculacion">
                                <i class="fas fa-lock-open"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_vinculacion">Desbloqueado</strong></div>
                </div>
            </div>

            <div class="col-12 col-sm-6 col-md-6 col-lg-4 p-3 text-center">
                <div class="row col-12 m-0 p-2">
                    <div class="col-12 px-5" class="titulo_ajuste">Habilitar vista en la App 360</div>
                    <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                        <div class="cuadrado_icon text-white">
                            <div data-current="" class="cuadrado_contenido parametro_modificable" id="visibilidad_app">
                                <i class="fas fa-eye"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_visibilidad_app">Visible</strong></div>
                </div>
            </div>

            <div class="col-12 col-sm-6 col-md-6 col-lg-4 p-3 text-center">
                <div class="row col-12 m-0 p-2">
                    <div class="col-12 px-5" class="titulo_ajuste">Agregar Token de seguridad para la vinculación</div>
                    <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                        <div class="cuadrado_icon text-white">
                            <div data-current="" class="cuadrado_contenido parametro_modificable" id="token_vinculacion">
                                <i class="fas fa-door-open"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_token_vinculacion">Sin Token</strong></div>
                    <div class="row col-12 m-0 p-0 d-none" id="info_token">
                        <div class="col-sm-12 col-md-12 px-4"><input type="text" class="form-control-plaintext input" id="valor_nuevo_token" placeholder="Ingresa Token" style="font: bold 1.1rem Roboto; text-align: center;" /></div>
                        <div class="col-sm-12 col-md-6 pl-4 pr-1"><input type="button" class="btn btn-danger w-100" id="generar_token_aleatorio" value="Generar Aleatorio" /></div>
                        <div class="col-sm-12 col-md-6 pl-1 pr-4"><input type="button" class="btn btn-danger w-100" id="establecer_nuevo_token" value="Establecer" /></div>
                    </div>
                </div>
            </div>

            <div class="col-12 col-sm-6 col-md-6 col-lg-4 p-3 text-center">
                <div class="row col-12 m-0 p-2">
                    <div class="col-12 px-5" class="titulo_ajuste">Restringir vinculación con lista blanca</div>
                    <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                        <div class="cuadrado_icon text-white">
                            <div data-current="" class="cuadrado_contenido parametro_modificable" id="lista_blanca">
                                <i class="fas fa-user-friends"></i>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 px-4" style="font: bold 1rem Arial;">Estado actual: <strong style="color: #ffa500; font: bold 1.2rem Arial;" id="estatus_lista_blanca">Desactivada</strong></div>
                </div>
            </div>

            <div id="componente_edicion_individual" class="col-12 col-sm-6 col-md-6 col-lg-4 p-3 text-center">
                <div class="row col-12 m-0 p-2">
                    <div class="col-12 px-5" class="titulo_ajuste">Edición individual de las sucursales</div>
                    <div class="col-12 p-4 d-flex justify-content-center align-items-center">
                        <div class="cuadrado_icon text-white">
                            <div class="cuadrado_contenido" id="edicion_individual">
                                <i class="fas fa-user-edit"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-sm-12" id="registros_file"></div>
</div>
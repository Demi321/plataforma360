<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
    Author     : Moises Juárez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <spring:url value="${pathRecursos}/css/operador.css" var="operadorCSS" />
    <link href="${operadorCSS}" rel="stylesheet" />
    <spring:url value="${pathRecursos}/css/Empresa/home_empresa.css" var="home_empresaCSS" />

    <%-- Javascript Personalizados --%>    
    <spring:url value="${pathRecursos}/js/Empresa/home_empresas.js" var="home_empresaJS" />

</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <div id="video_drag" class="d-none" style="position: fixed; background-color: rgb(241, 241, 241); border: 2px solid rgb(211, 211, 211); text-align: center; top: 75px; left:calc(100% - 200px) ; z-index: 1000;">
        <!-- Include a header DIV with the same name as the draggable DIV, followed by "header" -->
        <div id="video_drag_header" style="padding: 10px; cursor: move; z-index: 10; background-color: #495057; color: #fff;"><h7 class="nombre_completo"></h7></div>
        <div id="conectado_jornada_laboral" style="min-height: 150px; min-width: 150px; width: 100%; height: 100%; overflow: hidden;" >
        </div>
    </div>

    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Plataforma 360</div>

        <div id="sidebar" class="p-2">
            
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">



        </div>



        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_MiPerfil">
            <h3>Mi Perfil</h3>

            <div class="row col-12 m-0 p-2 pt-3">
                <div class="col-12 col-sm-12 col-md-4 p-0" style="
                     min-height: 300px;
                     ">
                    <div class="img_perfil" id="img"></div>
                </div>
                <div class="col-12 col-sm-12 col-md-8 p-0">
                    <h4>Bienvenid@</h4>
                    <h5 class="nombre_completo"></h5>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Empresa:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_empresa" placeholder="Empresa" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Sucursal:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_sucursal" placeholder="Sucursal" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Área:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_area" placeholder="Área" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Puesto:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="puesto" placeholder="Puesto" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Número de empleado:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="num_empleado" placeholder="Número de empleado" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Jornada:</label>
                        <label class="col-sm-2 col-form-label d-flex justify-content-center align-items-center">Entrada:</label>
                        <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="horario_entrada" placeholder="Entrada" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-center align-items-center">Salida</label>
                        <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="horario_salida" placeholder="Salida" /></div>
                    </div>

                </div>
                <div class="col-12 col-sm-12" style="
                     min-height: 30px;
                     ">
                </div>
            </div>
        </div>
        <div class="row col-12 m-0 p-0 h-100" id="base_modulo_Conmutador">

            <div class="row col-12 m-0 p-0 h-100">
                <div class="row m-0 col-12 col-sm-12 col-md-3 p-0 h-100">
                    <div class="row m-0 col-12 col-sm-12 p-0 h-50">
                        <h6>Participantes</h6>
                        <div class="col-6 col-sm-6 p-0 d-flex justify-content-center align-items-center" style="height: 30px;">
                            <input type="button" class="btn btn-info" value="Agregar participante" />
                        </div>
                        <div class="col-6 col-sm-6 p-0 d-flex justify-content-center align-items-center" style="height: 30px;">
                            <input type="button" class="btn btn-info" value="Enviar invitacion por correo" />
                        </div>
                        <div class="col-12 col-sm-12 p-0" id="listado_participantes" style="height: calc(100% - 80px); border: solid 1px #d3d3d3; border-radius: 15px;"></div>
                    </div>
                    <div class="row m-0 col-12 col-sm-12 p-0 h-50">
                        <div class="form-group row col-12 m-0 p-2" style="height: 70px;">
                            <label class="col-sm-12 col-form-label d-flex justify-content-start align-items-center" style="height: 30px;">Proyecto:</label>
                            <div class="col-sm-12 p-0" style="height: 30px;"><input type="text" class="form-control-plaintext input" id="asunto_proyecto" placeholder="Proyecto" /></div>
                        </div>

                        <div class="form-group row col-12 m-0 p-2" style="height: calc(100% - 70px);">
                            <label class="col-sm-12 col-form-label d-flex justify-content-start align-items-center" style="height: 30px;">Minuta:</label>

                            <div class="col-sm-12 p-0" style="height: calc(100% - 80px);"><textarea id="reporte" placeholder="Minuta de reunión" style="height: 100%;"></textarea></div>
                            <div class="col-sm-12 p-0 d-flex justify-content-center align-items-center" style="height: 30px;">
                                <input type="button" class="btn btn-danger w-100" value="Guardar" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row m-0 col-12 col-sm-12 col-md-9 p-0 pl-3">
                    <div id="videos">
                        <div class="p-0 row GRIDcontainer" id="GRID">
                            <input type="button" id="maximizarVideo" class="maximizarVideo" />
                        </div>

                        <div id="publisher"></div>
                        <div class="row col-12 m-0 p-0" id="menu_botones" style="background: rgb(52, 58, 64); position: absolute; bottom: 0px; left: calc(50% - 100px); width: 300px; border-top-left-radius: 50px; border-top-right-radius: 50px;">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12 col-sm-12" style="min-height: 30px;"></div>
        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_EntradaySalida">
            <h3>Registro Historico Check</h3>

            <div class="row col-12 m-0 p-2 pt-3">

                <div class="col-12 col-sm-12 col-md-12 p-0">
                    <h5 class="nombre_completo"></h5>
                    <input class="btn btn-danger my-3" type="button" value="Iniciar Jornada Laboral" id="iniciar_jornada_laboral">
                    <div class="row m-0 col-12 m-0 p-2">
                        <label class="col-2 p-0">Horario de ingreso: </label> <input type="text" disabled="true" id="ing" class="col-8" />

                        <hr class="w-100" />
                        <label class="col-2 p-0">Reporte de actividades: </label>
                        <textarea class="w-100 my-3" cols="100" rows="7" id="rep"> </textarea>

                        <div style="text-align: right;">
                            <input class="btn btn-danger d-none" type="button" value="Guardar Reporte y Finalizar Jornada" style="width: fit-content;" id="guardarreporte" />
                        </div>
                    </div>

                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Lunes:</label>
                        <div class="col-sm-10"><input type="date" class="form-control-plaintext input" id="jornada_fin" placeholder="Fecha" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Entrada:</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_inicio" placeholder="Entrada" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Salida</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_fin" placeholder="Salida" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Entrada:</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_inicio" placeholder="Entrada" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Salida</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_fin" placeholder="Salida" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Martes</label>
                        <div class="col-sm-10"><input type="date" class="form-control-plaintext input" id="jornada_fin" placeholder="Fecha" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Entrada:</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_inicio" placeholder="Entrada" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Salida</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_fin" placeholder="Salida" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Entrada:</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_inicio" placeholder="Entrada" /></div>
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Salida</label>
                        <div class="col-sm-4"><input type="time" class="form-control-plaintext input" id="jornada_fin" placeholder="Salida" /></div>
                    </div>

                </div>
                <div class="col-12 col-sm-12" style="
                     min-height: 30px;
                     ">
                </div>
            </div>
        </div>
        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_NuevoReporte">
            <h3>Reporte de Evento</h3>

            <div class="row col-12 m-0 p-2 pt-3">

                <div class="col-12 col-sm-12 col-md-8 p-0">
                    <h5 class="nombre_completo"></h5>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-3 col-form-label d-flex justify-content-center align-items-center">Fecha:</label>
                        <div class="col-sm-3"><input type="date" class="form-control-plaintext input" id="fecha_reporte" placeholder="Fecha" disabled="true"/></div>
                        <label class="col-sm-3 col-form-label d-flex justify-content-center align-items-center">Hora:</label>
                        <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="hora_reporte" placeholder="Hora" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Asunto</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="asunto_reporte" placeholder="Asunto" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Categoria:</label>
                        <div class="col-sm-10">
                            <select class="form-control"  id="categoria" placeholder="Seleccione uno" required="">
                                <option disabled="" selected="" value="">Selecciona una opción</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Reporte</label>
                        <div class="col-sm-10"><textarea  id="reporte_reporte_evento" placeholder="Redacta tu reporte"></textarea></div>
                    </div>

                    <div class="form-group row m-0 p-2 ">

                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Dirección:</label>
                        <div class="col-sm-10" >
                            <input type="text" class="form-control-plaintext input" id="d_autocompletar" value="" placeholder="Ingresa una dirección." autocomplete="off" required="true"/>
                        </div>

                    </div>
                </div>
                <div class="row m-0 col-12 col-sm-12 col-md-4 p-0" style="
                     min-height: 500px;
                     ">
                    <div class="row col-12 m-0 p-0 p-1 bg-secondary color-white rounded mb-3" style="cursor:pointer;" id="evidencia_evento">
                        <h5 class="text-white" style="
                            font-size: 1.5rem;
                            width: 100%;
                            align-items: center;
                            justify-content: center;
                            ">Agregar Evidencia Fotografica 
                        </h5>
                        <div class="col-12 m-0 h-75 d-flex justify-content-center align-items-center" id="cont_img_evento">
                            <i style="font-size: 8rem;color: white;" class="fas fa-image"></i>
                        </div>
                        <div class="col-12 m-0 h-25 d-none">
                            <input type="file" id="img_reporte_evento">
                        </div>
                    </div>
                    <div class="col-12 p-1" id="map"></div>
                </div>
                <div class="col-12 col-sm-12" style="
                     min-height: 30px;
                     ">
                    <input id="guardar_reporte_evento" type="button" class="btn btn-danger" value="Guardar Reporte"/>
                </div>
            </div>
        </div>
        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_ReporteSeguridadSanitaria">
            <h3>Reporte de Evento</h3>
            <div class="row col-12 m-0 p-2 pt-3">
                <div class="col-12 col-sm-12 col-md-8 p-0">
                    <h5 class="nombre_completo"></h5>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-3 col-form-label d-flex justify-content-center align-items-center">Fecha:</label>
                        <div class="col-sm-3"><input type="date" class="form-control-plaintext input" id="fecha_reporte_seguridad" placeholder="Fecha" disabled="true"/></div>
                        <label class="col-sm-3 col-form-label d-flex justify-content-center align-items-center">Hora:</label>
                        <div class="col-sm-3"><input type="time" class="form-control-plaintext input" id="hora_reporte_seguridad" placeholder="Hora" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Asunto</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="asunto_reporte_seguridad" placeholder="Asunto" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Categoria:</label>
                        <div class="col-sm-10">
                            <select class="form-control"  id="categoria_seguridad" placeholder="Seleccione uno" required="">
                                <option disabled="" selected="" value="">Selecciona una opción</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Reporte</label>
                        <div class="col-sm-10"><textarea  id="reporte_reporte_seguridad" placeholder="Redacta tu reporte"></textarea></div>
                    </div>
                    <div class="form-group row m-0 p-2 ">

                        <label class="col-sm-2 col-form-label d-flex justify-content-start align-items-center">Dirección:</label>
                        <div class="col-sm-10" >
                            <input type="text" class="form-control-plaintext input" id="d_autocompletar2" value="" placeholder="Ingresa una dirección." autocomplete="off" required="true"/>
                        </div>

                    </div>
                </div>
                <div class="row m-0 col-12 col-sm-12 col-md-4 p-0" style="
                     min-height: 500px;
                     ">
                    <div class="row col-12 m-0 p-0 p-1 bg-secondary color-white rounded mb-3" style="cursor: pointer;" id="evidencia_seguridad_sanitaria">
                        <h5 class="text-white" style="
                            font-size: 1.5rem;
                            width: 100%;
                            align-items: center;
                            justify-content: center;
                            ">Agregar Evidencia Fotografica 
                        </h5>
                        <div class="col-12 m-0 h-75 d-flex justify-content-center align-items-center" id="cont_img_seguridad">
                            <i style="font-size: 8rem;color: white;" class="fas fa-image"></i>
                        </div>
                        <div class="col-12 m-0 h-25 d-none">
                            <input type="file" id="img_reporte_seguridad">
                        </div>
                    </div>
                    <div class="col-12 p-1" id="map2"></div>
                </div>
                <div class="col-12 col-sm-12" style="
                     min-height: 30px;
                     ">
                    <input id="guardar_reporte_seguridad" type="button" class="btn btn-danger" value="Guardar Reporte"/>
                </div>
            </div>
        </div>

        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_RegistraryActivarEmpresa">
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
                                                <input type="text" class="form-control-plaintext input bg-light rounded p-2 border text-dark" id="correo" placeholder="Correo" required="true" />
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
        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_RegistrarSucursal">
            <!--    <h3>Registra y activa una empresa</h3>-->
            <div class="registro_institucion row m-0 p-2">
                <div class="row col-12 m-0 p-0 h-100">
                    <div class="col-sm-12" id="registros_file_RegistrarSucursal"></div>

                    <input type="hidden" id="id360" value="28" />
                    <input type="hidden" id="idModulo" />
                    <input type="hidden" id="id_institucion" />

                    <!--/***************Toda la vista***************/-->
                    <div class="col-12 text-center"><h3 class="pt-3 m-0 p-3">Registra tus sucursales de forma masiva</h3></div>
                    <div class="col-12" id="cont_subir_documento_RegistrarSucursal">
                        <div class="caja row m-0">
                            <div class="col-12 p-2"><h7 class="text-dark">Subir documento (Excel)</h7></div>
                            <div class="col-12">
                                <label>Seleccione el archivo:</label>
                                <input type="file" id="sucursales" />
                            </div>
                            <div class="col-sm-12">
                                <p>
                                    El documento debe ser un archivo con extensión csv ó xlsx y debe contener como minimo las columnas <strong>Registro Patronal, Razón Social, RFC, Nombre Sucursal, Numero Trabajadores, Nombre Personal de Contacto, Apellido Paterno Personal de Contacto, Apellido Materno Personal de Contacto, Teléfono Personal de Contacto, Extensión y Correo</strong> puedes descargar una plantilla
                                    <a target="_blank" href="https://lineamientos.s3.amazonaws.com/Plantilla_Registro_Sucursales.xlsx">aquí.</a>
                                </p>
                            </div>
                        </div>
                    </div>
                    <hr class="w-100" />

                    <!--/******************************/-->
                    <form id="form_RegistrarSucursal" class="form_registrar_institucion row col-12 m-0 p-0 w-100">
                        <div class="col-12 content text-dark" id="formulario_institucion">
                            <div class="caja row m-0 p-0 col-12">
                                <div class="col-12"><h3 class="text-dark p-3 m-0">Registra tu sucursal de forma individual</h3></div>
                                <div class="col-12 text-left p-2"><h7 class="text-dark" style="">Datos generales</h7></div>
                                <input type="hidden" id="id_empresa" value="" />
                                <div class="col-12 row m-0 p-2">
                                    <div class="col-12 col-sm-12 col-md-3 col-lg-2">
                                        <div class="row m-0 col-12">
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
                                    <div class="col-12 col-sm-12 col-md-9 col-lg-10">
                                        <div class="row m-0 col-12">
                                            <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                                <label class="" for="registro_patronal">Registro Patronal:</label>
                                                <input type="text" name="registro_patronal" class="form-control" id="RegistrarSucursal_registro_patronal" placeholder="Indique registro ante IMSS" required="" />
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                                <label class="" for="razon_social">Razón Social:</label>
                                                <input type="text" name="razon_social" class="form-control" id="RegistrarSucursal_razon_social" placeholder="Razón Social" required="" />
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                                <label class="col-12" for="rfc">RFC:</label>
                                                <input type="text" name="rfc" class="form-control" id="RegistrarSucursal_rfc" placeholder="RFC" required="" />
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                                <label class="" for="sector">Actividad Económica o Sector:</label>
                                                <select class="form-control" name="sector" id="RegistrarSucursal_tipo_sector" placeholder="Seleccione uno" required="">
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
                                                <input type="text" name="planta" class="form-control" id="RegistrarSucursal_nombre_edificio" placeholder="Indique tipo" required="" />
                                            </div>
                                            <!--                        <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                                                                        <label class="" for="planta">Planta:</label>
                                                                                        <input type="text" name="planta" class="form-control" id="planta" placeholder="Indique tipo" required>
                                                                                    </div>  -->
                                            <div class="col-6 col-md-3 col-lg-3 col-xl-3 d-flex justify-content-center align-items-end">
                                                <div class="col-12 rdo">
                                                    <label class="checkbox-inline">
                                                        Patrón Primario
                                                        <input
                                                            type="radio"
                                                            name="1"
                                                            value="patron_primario"
                                                            id="RegistrarSucursal_radio_patron_primario"
                                                            onchange="document.getElementById('RegistrarSucursal_patron_primario').value = $('#RegistrarSucursal_radio_patron_primario').is(':checked');document.getElementById('RegistrarSucursal_proveedor').value = $('#RegistrarSucursal_radio_proveedor').is(':checked');"
                                                            />
                                                    </label>
                                                </div>
                                                <input type="hidden" name="patron_primario" id="RegistrarSucursal_patron_primario" value="false" required="" />
                                            </div>
                                            <div class="col-6 col-md-3 col-lg-3 col-xl-3 d-flex justify-content-center align-items-end">
                                                <div class="col-12 rdo">
                                                    <label class="checkbox-inline">
                                                        Proveedor
                                                        <input
                                                            type="radio"
                                                            name="1"
                                                            value="proveedor"
                                                            id="RegistrarSucursal_radio_proveedor"
                                                            onchange="document.getElementById('RegistrarSucursal_proveedor').value = $('#RegistrarSucursal_radio_proveedor').is(':checked');document.getElementById('RegistrarSucursal_patron_primario').value = $('#RegistrarSucursal_radio_patron_primario').is(':checked');"
                                                            />
                                                    </label>
                                                </div>
                                                <input type="hidden" name="proveedor" id="RegistrarSucursal_proveedor" value="true" required="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 text-left mt-1"><h7 class="text-dark">Dirección</h7></div>

                                <div class="form-group row m-0 p-2 pt-4">
                                    <div class="row m-0 col-sm-12 col-md-8 p-0">
                                        <label class="col-sm-12 col-md-12 col-form-label d-flex align-items-center">Dirección:</label>
                                        <div class="col-sm-12 col-md-12 d-flex align-items-center">
                                            <input type="text" class="form-control pac-target-input" id="d_autocompletar3" value="" placeholder="Ingresa una dirección." autocomplete="off" required="true" />
                                        </div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Número exterior:</label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                            <input type="text" class="form-control" id="RegistrarSucursal_n_exterior" placeholder="Número exterior" value="" required="true"/>
                                        </div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Número interior: </label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                            <input type="text" class="form-control" id="RegistrarSucursal_n_interior" placeholder="Número interior" value="" required="true"/>
                                        </div>
                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Calle:</label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                            <input type="text" class="form-control bg-secondary text-white" id="calle3" placeholder="Calle" disabled="true" />
                                        </div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Colonia: </label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                            <input type="text" class="form-control bg-secondary text-white" id="colonia3" placeholder="Colonia" value="" disabled="true" />
                                        </div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Ciudad o Municipio</label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                            <input type="text" class="form-control bg-secondary text-white" id="municipio3" placeholder="Ciudad o Municipio" disabled="true" />
                                        </div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">CP</label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2"><input type="text" class="form-control bg-secondary text-white" id="cp3" placeholder="Código Postal" disabled="true" /></div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Estado:</label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2">
                                            <input type="text" class="form-control bg-secondary text-white" id="estado3" placeholder="Estado" value="" disabled="true" />
                                        </div>

                                        <label class="col-sm-12 col-md-3 col-form-label mt-2 d-flex align-items-center">Pais:</label>
                                        <div class="col-sm-12 col-md-3 d-flex align-items-center mt-2"><input type="text" class="form-control bg-secondary text-white" id="pais3" placeholder="Pais" disabled="true" /></div>
                                    </div>
                                    <div class="col-sm-12 col-md-4 p-0" id="map3" style="min-height: 150px; position: relative; overflow: hidden;">

                                    </div>
                                </div>

                                <hr />
                                <div class="col-12 text-left mt-2"><h7 class="text-dark">Plantilla Laboral</h7></div>
                                <div class="col-12 row">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-3 mt-2">
                                        <label class="col-12" for="numero_trabajadores">Número de trabajadores:</label>
                                        <input type="number" name="numero_trabajadores" class="form-control" id="RegistrarSucursal_numero_trabajadores" placeholder="Indique un número" required="" />
                                        <input type="hidden" id="tipo_institucion" />
                                    </div>
                                </div>
                            </div>
                            <div class="caja row m-0">
                                <div class="col-12 text-left mt-2"><h7 class="text-dark">Datos de Contacto</h7></div>
                                <div class="col-12 row">
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                        <label class="" for="nombre">Nombre (s):</label>
                                        <input type="text" name="nombre" class="form-control" id="RegistrarSucursal_nombre" placeholder="Nombre" required="" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                        <label class="" for="apellido_p">Apellido Paterno:</label>
                                        <input type="text" name="apellido_p" class="form-control" id="RegistrarSucursal_apellido_p" placeholder="Apellido Paterno" required="" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                        <label class="" for="apellido_m">Apellido Materno:</label>
                                        <input type="text" name="apellido_m" class="form-control" id="RegistrarSucursal_apellido_m" placeholder="Apellido Materno" required="" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                        <label class="" for="telefono">Teléfono:</label>
                                        <input type="number" name="telefono" class="form-control" id="RegistrarSucursal_telefono" placeholder="Indique en número" required="" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                        <label class="col-12" for="extension">Extensión:</label>
                                        <input type="number" name="extension" class="form-control" id="RegistrarSucursal_extension" placeholder="Indique en número" />
                                    </div>
                                    <div class="col-12 col-md-6 col-lg-6 col-xl-4 mt-2">
                                        <label class="col-12" for="correo">Correo:</label>
                                        <input type="text" name="correo" class="form-control" id="RegistrarSucursal_correo" placeholder="Indique su correo electrónico" required="" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="submit" class="btn btn-danger boton" style="border: none; margin: 15px auto;" value="continuar" id="boton_seleccionar_institucion" />
                    </form>
                </div>
            </div>


        </div>
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
        <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_RegistrarmiInstitución">
            <h3>Registrar mi Institucón</h3>
            <div class="col-12 p-0">
                <form id="form_registro_institucion">
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Nombre Institución:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_institucion" placeholder="Institución" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Director:</label>
                        <div class="col-sm-4"><input type="text" class="form-control-plaintext input" id="nombre_director" placeholder="Nombre" /></div>
                        <div class="col-sm-3"><input type="text" class="form-control-plaintext input" id="apellido_paterno_director" placeholder="Apellido Paterno" /></div>
                        <div class="col-sm-3"><input type="text" class="form-control-plaintext input" id="apellido_materno_director" placeholder="Apellido Materno" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Teléfono:</label>
                        <div class="col-sm-10"><input type="number" class="form-control-plaintext input" id="telefono_institucion" placeholder="Teléfono" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Correo Electrónico:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="correo_institucion" placeholder="Correo Electrónico" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Turno:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="turno" placeholder="Turno" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Zona:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="zona" placeholder="Zona" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Clave:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="clave" placeholder="Clave" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">CCT:</label>
                        <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="cct" placeholder="Clave de Centro de Trabajo" /></div>
                    </div>
                    <div class="form-group row m-0 p-2">
                        <label class="col-sm-2 col-form-label">Dirección:</label>
                        <div class="col-sm-10 col-md-8 col-lg-9"><input type="text" class="form-control-plaintext input" id="direccion_institucion" placeholder="Dirección" /></div>
                        <input type="button" class="col-sm-12 col-md-2 col-lg-1 btn btn-danger" value="Buscar" id="buscar_direccion">
                    </div>
                    <div class="form-group row m-0 p-2"></div>
                    <div class="col-sm-12 col-md-12 col-lg-6" style="height: 250px;">
                        <div class="col-12 w-100 h-100 rounded" id="map21"></div>
                    </div>
                    <input type="hidden" id="lat" />
                    <input type="hidden" id="lng" />
                </form>
            </div>
        </div>
        <div class="row col-12 m-0 p-2" id="base_modulo_MiEmpresa">
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
        <div class="row col-12 m-0 p-2" id="base_modulo_MisSucursales">
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

        <div class="row col-12 m-0 p-0 h-100" id="base_modulo_InstituciónAcadémica">
            <div class="col-sm-12 col-md-12 col-lg-6 d-flex align-items-center">
                <div class="datos_institucion">
                    <h2 id="InstituciónAcadémica_codigo">##-########</h2>
                    <h1 id="InstituciónAcadémica_nombre">Institución Demo 10</h1>
                    <h5 id="InstituciónAcadémica_direccion">Zurich 221 ampliacion granada</h5>
                    <hr />
                    <h4><strong>Director: </strong><span id="InstituciónAcadémica_nombre_director"></span></span></h4>
                    <h4><strong>Télefono: </strong><span id="InstituciónAcadémica_telefono"></span></h4>
                    <h4><strong>Correo electrónico: </strong><span id="InstituciónAcadémica_correo"></span></h4>
                    <h4><strong>Turno: </strong><span id="InstituciónAcadémica_turno"></span></h4>
                    <h4><strong>Zona: </strong><span id="InstituciónAcadémica_zona"></span></h4>
                    <h4><strong>Clave: </strong><span id="InstituciónAcadémica_clave"></span></h4>
                    <h4><strong>CCT: </strong><span id="InstituciónAcadémica_cct"></span></h4>
                </div>
            </div>
            <div class="col-sm-12 col-md-12 col-lg-6"><div class="col-12 w-100 h-100 rounded" id="map"></div></div>
        </div>

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


        <!--------------------->
        <%@include file="../plantilla/callhead.jsp" %>
        <script src="${sdk_awsJS}" ></script>
        <link href="${home_empresaCSS}" rel="stylesheet" />
        <script src="${home_empresaJS}" ></script>

    </section>

    <%@include file="../empresas360/modulo_videowall.jsp" %>

    <div class="d-none" id="guardando_logo">
        <div class="mensaje_guardando_logo">Guardando Logo ...</div>
    </div>

    <%@include file="../plantilla/footer.jsp" %>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMaps&callback=initMaps&libraries=places&v=weekly">
    </script>


</body>
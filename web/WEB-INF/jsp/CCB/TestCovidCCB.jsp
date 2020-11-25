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
    <%-- Javascript Personalizados --%>  
    <link href="${pathRecursos}/css/operador.css" rel="stylesheet" />
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">
            <div id="accordion">

                <div class="card" style="border-top: none; border-right: none; border-left: none; border-image: initial; border-bottom: 2px solid white;">
                    <div class="card-header m-0 p-0 row col-12" id="headingfecha" style="background: rgb(0, 151, 169); border: none;">
                        <div class="m-0 p-0 col-12">
                            <button class="m-0 p-0 btn  collapsed" type="buttom" data-toggle="collapse" data-target="#collapsefecha" aria-expanded="false" aria-controls="collapsefecha" style="width: 100%; text-align: left;">
                                <h4 class="p-2 m-0" style="color: white; font: bold 12px arial;">Búsqueda por fecha</h4>
                            </button>
                        </div>
                    </div>

                    <div id="collapsefecha" class="collapse show" aria-labelledby="headingfecha" data-parent="#accordion" style="">
                        <div class="card-body p-0 m-0" id="" style="background: rgb(64, 71, 79);">

                            <nav class="navbar-dark bg-dark" style="width: 100%">

                                <div id="ContainerBusquedaFecha p-0 pb-3" class="text-white">

                                    <div class="input-group pb-3" id="group_fecha">
                                        <input  class="form-control mr-sm-2" type="text"  id="dateformated"  style=" display: none;" disabled="true">

                                        <button id="button__api-open-close"  class="pull-right" style="background: url(${calendarPNG});
                                                background-repeat: no-repeat;
                                                background-position:center;
                                                background-size: cover;
                                                -moz-background-size: cover;
                                                -webkit-background-size: cover;
                                                -o-background-size: cover;
                                                width: 25px;
                                                height: 25px;
                                                border: none;
                                                cursor:pointer;
                                                display: none;"></button>

                                    </div>
                                </div>
                            </nav>
                        </div>
                    </div>   
                </div>                                
            </div> 


            <div id="sidebar2"></div>

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <details open="true" id="details" style="
                     padding: 5px;
                     background: #40474f;
                     width: 100%;
                     margin-bottom: 2px;
                     min-height: 25px;
                     display: none;
                     "><!-- display: none; -->
                <summary>Historialde envio de test</summary>
                <div id="calendar_basic"></div>
            </details>

            <div class="row col-12 m-0 p-0 sideSection"> <!-- style="height:calc(100% - 225px)" -->

                <div class="col side1" id="side1">

                    <div class="col-12 p-0 pl-1 pt-0 side1Chat d-none"id="side1Chat">

                        <form id="chat" class="h-100">
                            <div class="card" id="textchat" >
                                <div class="card-headertitle text-center" >
                                    CHAT
                                </div>
                                <div class="card-body" style="background-color: #f4f4f4;">

                                    <div class="row" id="history" style="width: 100%; margin: 0;">

                                    </div>
                                    <div class="inputTextChat" style=" background: none;border: none;">
                                        <input class="form-control " type="text" placeholder="Comienza un chat aqui" id="msgTxt"  autocomplete="off">
                                    </div>

                                </div>

                            </div>
                        </form>

                    </div>
                    <div id="informacion" class="row col-12 m-0 p-0 h-100">

                        <h3 class="col-12" style="height: 3%;">Información Personal</h3>
                        <div id="contInformacionPersonal" class="contInformacionPersonal">
                            <div class="row col-12 m-0 h-100">
                                <div class="col-4 ImgTestCovid">
                                </div>
                                <div class="col-8 infoPer">
                                    <div class="col-12">
                                        <label>Nombre</label>
                                        <input type="text" id="nombre" disabled="true">
                                    </div>
                                    <div class="col-12">
                                        <label>Teléfono </label>
                                        <input type="text" id="idUsuario" disabled="true">
                                    </div>
                                    <div class="col-12">
                                        <label>Fecha de Nacimiento </label>
                                        <input type="text" id="fecha_nacimiento" disabled="true">
                                    </div>
                                </div>

                                <div class="col-4">
                                    <label>Edad </label>
                                    <input type="text" id="edad" disabled="true">
                                </div>
                                <div class="col-4">
                                    <label>Genero </label>
                                    <input type="text" id="genero" disabled="true">
                                </div>
                                <div class="col-4">
                                    <label>Fecha y Hora del Test </label>
                                    <input type="text" id="fecha_hora_test" disabled="true">
                                </div>
                                <div class="col-12 divBtnLlamar">
                                    <input type="button" value="llamar" id="llamar_familiar" class="btn btn-danger boton d-none">
                                </div>
                            </div>
                            <!--<input type="button" value="llamar" id="llamar_familiar" class="btn btn-danger boton" style="height: 10%">-->

                        </div>

                        <h3 class="col-12 hTest">Test COVID</h3>
                        <div class="row col-12 m-0 contTest">
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pfiebre">¿Tiene Fiebre? (Temperatura igual o mayor a 37.6ºC)</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rfiebre"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="ptos">¿Tiene Tos de nuevo inicio?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rtos"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pdolor_cabeza">¿¿Tienes dolor de cabeza de nuevo inicio?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rdolor_cabeza"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pdolor_pecho">¿Tienes dolor en el pecho?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rdolor_pecho"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pdolor_garganta">¿Tienes dolor de garganta?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rdolor_garganta"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pdificultad_respirar">¿Tienes dificultad para respirar?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rdificultad_respirar"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pdolor_general">¿Tienes dolor en el cuerpo, como dolor muscular o articular?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rdolor_general"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pescalofrios">¿Tienes escalofríos?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rescalofrios"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pcondiciones_medicas">¿Tienes alguna de las siguientes condiciones: diabetes, hipertensión, obesidad, problemas cardiacos, asma, EPOC, VIH no controlado, cáncer en quimioterapia?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rcondiciones_medicas"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pembarazo">¿Estás embarazada? </label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rembarazo"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pdolor_respirar">¿Sientes dolor o dificultad al respirar?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rdolor_respirar"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pfalta_aire">¿Sientes falta de aire al hablar o caminar algunos pasos?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rfalta_aire"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pcoloracion_azul">¿Tienes coloración azul o morada en labios, dedos o uñas?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rcoloracion_azul"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pconvivido_sin_equipo">¿Has convivido sin equipo de protección con alguna persona que sea un caso confirmado de COVID-19 (Coronavirus) en los últimos 3 días?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rconvivido_sin_equipo"></label>
                            </div>
                            <div class="col-8 divPregunta">
                                <label class="pregunta" id="pcontaminado_retiro">¿Te has contaminado durante el uso o al retirar tu equipo de protección?</label>
                            </div>
                            <div class="col-4 divRespuesta">
                                <label class="respuesta" id="rcontaminado_retiro"></label>
                            </div>
                            <div class="col-12">
                                <label class="respuesta d-none" id="rmensaje"></label>
                            </div>
                        </div>
                        <h3 class="col-12 hDiagnostico">Diagnóstico</h3>
                        <div id="contDiagnostico" class="contDiagnostico w-100">
                            <textarea rows="4" cols="50" class="w-100 col-12 textareaDiag"></textarea>
                            <div class="row col-12 m-0 contIngresar">
                                <label class="col-8" id="labelIngresar">¿Podra ingresar a la UTC-19?</label>
                                <select id="ingresar" class="col-4" style="height: 60%;">
                                    <option value="-1">Selecciones una opción</option>
                                    <option value="1">Si</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0 contAgendarLlamada">
                                <label class="col-6" id="labelIngresar">Agendar nueva llamada</label>
                                <input type="date" id="fecha_nuevaLlamada" class="col-6">
                            </div>
                            <div class="col-12 divBtnLlamar mt-2">
                                <input type="button" value="Guardar Diagnóstico" id="guardarDiagnostico" class="btn btn-danger boton d-none" style="/* height: 61%; */">
                            </div>
                        </div>
                        <input type="hidden" id="id" value="">
                    </div>
                    <input type="button" class="btn btn-secondary d-none SwitchEstado" value="" id="btnSwitchChat">
                </div>
                <div class="col" id="side2">
                    <div id="videos">
                        <div class=" p-0 row GRIDcontainerOp"  id="GRID">

                            <button id="myBtn" style="display: none;" title="Reporte PDF"></button>
                        </div>
                        <div id="publishers" class="row col-12 m-0 p-0"></div>
                    </div>

                </div>
            </div>

        </div>
    </section>
    <div id="modal_llamada" class="d-none" style="
         position: absolute;
         width: 100%;
         height: 100%;
         background: #000000ab;
         z-index: 100;
         "> <div id="modal_aviso" style="
            width: 50%;
            height: 10%;
            margin: auto;
            top: 40%;
            position: absolute;
            left: 25%;
            font: bold 1.2rem Arial;
            align-items: center;
            display: flex;
            justify-content: center;
            background: white;
            border-radius: 15px;
            color: #40474f;
            padding: 20px;
            ">

        </div>

    </div>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>


    <link href="${pathRecursos}/css/TestCovidCCB.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/ccb/TestCovidCCB.js"></script>

    <script>
    </script>
    <style>
    </style>

</body>
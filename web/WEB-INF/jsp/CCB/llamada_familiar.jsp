<%-- 
    Document   : plantilla
    Created on : 26 jul 2019, 16:25:53
    Author     : web
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>  
    <%@include file="../plantilla/head.jsp" %>
    <%-- Estilos Personalizados --%>
    <%-- Javascript Personalizados --%>    
    <link href="${pathRecursos}/css/operador.css" rel="stylesheet" />

</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <%--<%@include file="../plantilla/baner_superior.jsp" %>--%>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Referencia de Pacientes COVID-19</div>
        <div style="height: 85px;">
            <h1>Búsqueda por número de referencia</h1>
            <div class="col-12 m-0 p-0 pl-1 pr-1" id="directorio_pacientesCCB">
                <!--                <label class="typo__label">Select with search</label>-->
                <multiselect 
                    v-model="value" 
                    :options="options" 
                    :custom-label="label_function" 
                    placeholder="Búsqueda" 
                    label="name" 
                    track-by="name"
                    :select-label="''" 
                    :selected-Label="''"
                    :deselect-Label="'Remover'"
                    @select="onSelect"
                    @open="onTouch" 
                    ></multiselect>
                <pre class="language-json d-none"><code>{{ value  }}</code></pre>
            </div>
        </div>
        <div id="sidebar">

            <!--  <br>
                <input class="btn btn-danger" type="button" value="Buscar" id="buscar">
              <hr>
              <h1>Buscar por fecha</h1>
              <hr>-->

            <div class="row col-12 m-0 p-0">

                <h3 class="col-12">Ficha de identificación</h3>
                <div class="col-12">
                    <label>Cama </label>
                    <input disabled="true"  type="text" id="cama">
                </div>
                <div class="col-12">
                    <label>Nombre del Paciente </label>
                    <input disabled="true"  type="text" id="nombre">
                </div>
                <div class="col-12">
                    <label>Género </label>
                    <input disabled="true"  type="text" id="genero">
                </div>
                <div class="col-12">
                    <label>Edad </label>
                    <input disabled="true"  type="text" id="edad">
                </div>
                <div class="col-12">
                    <label>Fecha de nacimiento </label>
                    <input disabled="true"  type="text" id="fecha_nacimiento" >
                </div>
                <div class="col-12">
                    <label>Hospital de envío </label>
                    <input disabled="true"  type="text" id="institucion_refiere">
                </div>
                <div class="col-12">
                    <label>Médico que refiere </label>
                    <input disabled="true"  type="text" id="medico_refiere">
                </div>
                <div class="col-12">
                    <label>Comorbilidades y medicamentos de uso regular </label>
                    <input disabled="true"  type="text" id="comorbilidades">
                </div>
                <div class="col-12">
                    <label>Familiar responsable </label>
                    <input disabled="true"  type="text" id="nombre_responsable">
                </div>
                <div class="col-12">
                    <label>Contacto de familiar responsable </label>
                    <input disabled="true"  type="text" id="telefono_contacto">
                </div>

                <hr>

                <h3 class="col-12">Padecimiento actual y evolución</h3>
                <div class="col-12">
                    <label>Fecha de inicio de síntomas </label>
                    <input disabled="true"  type="text" id="fecha_sintomas">
                </div>
                <div class="col-12">
                    <label>Fecha de ingreso hospitalario </label>
                    <input disabled="true"  type="text" id="fecha_ingreso">
                </div>
                <div class="col-12">
                    <label>Diagnóstico de COVID-19 </label>
                    <input disabled="true"  type="text" id="prueba_covid">
                </div>
                <div class="col-12">
                    <label>Motivo de envío </label>
                    <input disabled="true"  type="text" id="motivo_envio">
                </div>
            </div>

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">

            <div class="row col-12 m-0 p-0" style="height: 100%; top: 0;">

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

                    <h3 class="col-12">Solicitar llamada a familiar</h3>
                    <input type="button" value="Llamar" id="llamar_familiar" class="btn btn-danger boton">
                    <h3 class="col-12">Actualización de estado del paciente:</h3>
                    <form id="ActualizacionEstado"> 
                        <div class="col-12">
                            <label>Referencia: </label>
                            <input disabled="true"  type="text" id="id" required="true">
                        </div>
                        <div class="col-12 d-none">
                            <label>Nombre de doctor: </label>
                            <input type="text" id="nombre_doctor" >
                        </div>
                        <div class="col-12">
                            <label>Estado actual: </label>
                            <select class="form-control inputForm" name="estado_nuevo" id="estado_nuevo" required>
                                <option value="">Seleccione un estado</option>
                                <option value="Mejoría">Mejoría</option>
                                <option value="Estable">Estable</option>
                                <option value="Grave">Grave</option>
                            </select>
                        </div>
                        <div class="col-12">
                            <label>Diagnóstico: </label>
                            <textarea class="form-control" id="diagnostico_nuevo" name="diagnostico_nuevo" rows="20" required></textarea>
                        </div>
                        <input type="submit" value="Enviar" class="btn btn-danger boton" id="enviar_actualizacion_estado">
                    </form>
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
            "></div></div>
        <%@include file="../plantilla/footer.jsp" %>
        <%@include file="../plantilla/callhead.jsp" %>

    <link href="${pathRecursos}/css/VinculacionFamiliar.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/ccb/VinculacionFamiliar.js"></script>

    <script>
    </script>
    <style>
    </style>

</body>
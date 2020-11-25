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

</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">
            <div id="accordion">
                <!--                <div class="card" style="border-top: none; border-right: none; border-left: none; border-image: initial; border-bottom: 2px solid white;">
                                    <div class="card-header m-0 p-0 row col-12" id="headingfolio" style="background: rgb(0, 151, 169); border: none;">
                                        <div class="m-0 p-0 col-12">
                                            <button class="m-0 p-0 btn  collapsed" type="buttom" data-toggle="collapse" data-target="#collapsefolio" aria-expanded="false" aria-controls="collapsefolio" style="width: 100%; text-align: left;">
                                                <h4 class="p-2 m-0" style="color: white; font: bold 12px arial;">Búsqueda por nombre o número de referencia</h4>
                                            </button>
                                        </div>
                                    </div>
                                    <div id="collapsefolio" class="collapse" aria-labelledby="headingfolio" data-parent="#accordion" style="">
                                        <div class="card-body p-0 m-0" id="" style="background: rgb(64, 71, 79);">
                
                                            <div class="col-12 m-0 p-0" id="directorio_pacientesCCB">
                                                                <label class="typo__label">Select with search</label>
                                                <multiselect 
                                                    v-model="value" 
                                                    :options="options" 
                                                    :custom-label="label_function" 
                                                    placeholder="Buscar" 
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
                                    </div>                       
                
                                </div>-->
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
                    <div id="informacion" class="row col-12 m-0 p-0">
                        <h3 class="col-12">Información del paciente</h3>
                        <div class="row col-12 m-0">


                            <div class="col-4">
                                <label>Nombre del Paciente </label>
                                <input type="text" id="nombre">
                            </div>
                            <div class="col-4">
                                <label>Teléfono </label>
                                <input type="text" id="telefono_paciente">
                            </div>
                            <div class="col-4">
                                <label>Fecha de Nacimiento </label>
                                <input type="text" id="fecha_nacimiento">
                            </div>
                            <div class="col-4">
                                <label>Edad </label>
                                <input type="text" id="edad">
                            </div>
                            <div class="col-4">
                                <label>Genero </label>
                                <input type="text" id="genero">
                            </div>
                            <div class="col-4">
                                <label>Fecha y Hora de alta </label>
                                <input type="text" id="fecha_cambio">
                            </div>
                            <div class="col-4">
                                <label>Fecha de inicio de sintomas </label>
                                <input type="text" id="fecha_inicio_sintomas">
                            </div>
                            <div class="col-4">
                                <label>Fecha de hospitalización </label>
                                <input type="text" id="fecha_hospitalizacion">
                            </div>
                            <div class="col-4">
                                <label>Número de dias con los sintomas </label>
                                <input type="text" id="dias_sintomas">
                            </div>
                            <div class="col-4">
                                <label>Número de dias dado de alta </label>
                                <input type="text" id="dias_alta">
                            </div>
                            <div class="col-12">
                                <label>Comorbilidades </label>
                                <input type="text" id="comorbilidades">
                            </div>
                            <div class="col-8">
                                <label>Familiar Responsable </label>
                                <input type="text" id="nombre_responsable">
                            </div>
                            <div class="col-4">
                                <label>Telefono Responsable </label>
                                <input type="text" id="telefono_responsable">
                            </div>
                            <div class="col-12">
                                <label>Doctor Responsable </label>
                                <input type="text" id="doctor_responsable">
                            </div>
                        </div>

                        <input type="hidden" id="idUsuario" >
                        <input type="hidden" id="numero_llamada" >

                        <input type="button" value="llamar" id="llamar_familiar" class="btn btn-danger boton d-none">
                        <h3 class="col-12">Actualizacion de estado del paciente</h3>
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
                                <label>¿Cómo se encuentra hoy comparado con ayer? </label>
                                <select class="form-control inputForm" name="estado_nuevo" id="estado_nuevo" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="Mejor">Mejor</option>
                                    <option value="Igual">Igual</option>
                                    <option value="Peor">Peor</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label>¿Tiene falta de aire o sensación de aire empeorando? </label>
                                <select class="form-control inputForm" name="falta_aire" id="falta_aire" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="Si">Si</option>
                                    <option value="No">No</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label>¿Ha persistido la fiebre por más de 72 Horas? </label>
                                <select class="form-control inputForm" name="persistido_fiebre" id="persistido_fiebre" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="Si">Si</option>
                                    <option value="No">No</option>
                                </select>
                            </div>
                            <div class="row col-12 m-0">
                                <label>¿Cuanto marca la saturación de oxigeno? </label>
                                <input type="number" min="0" step="0.01" class="col-10" id="saturacion_oxigeno" required>
                                <label class="col-2">%</label>
                            </div>
                            <div class="col-12">
                                <label>¿Ha tenido cansancio que lo limita a realizar sus actividades? </label>
                                <select class="form-control inputForm" name="cansancio" id="cansancio" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="Si">Si</option>
                                    <option value="No">No</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label>¿Ha tenido dolor de cabeza? </label>
                                <select class="form-control inputForm" name="dolor_cabeza" id="dolor_cabeza" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="Si">Si</option>
                                    <option value="No">No</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label>¿Ha tenido sintomas gastrointestinales como diarrea y vomito? </label>
                                <select class="form-control inputForm" name="dolor_cabeza" id="gastrointestinales" required>
                                    <option value="">Seleccione un estado</option>
                                    <option value="Si">Si</option>
                                    <option value="No">No</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <label>¿Ha tenido otra molestia? </label>
                                <textarea class="form-control" id="otra_molestia" name="otra_molestia" rows="5" required></textarea>
                            </div>
                            <div class="col-12">
                                <label>¿Esta tomando otros medicamentos? ¿Cuales?</label>
                                <textarea class="form-control" id="otros_medicamentos" name="otros_medicamentos" rows="5" required></textarea>
                            </div>

                            <div class="col-12 d-none">
                                <label>Diagnostico </label>
                                <textarea class="form-control" id="diagnostico_nuevo" name="diagnostico_nuevo" rows="10"></textarea>
                            </div>
                            <input type="submit" value="Guardar" class="btn btn-danger boton d-none" id="enviar_actualizacion_estado">

                        </form>
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
    
    
    <link href="${pathRecursos}/css/MonitoreoRemoto.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/ccb/MonitoreoRemoto.js"></script>
    
    <script>
    </script>
    <style>
    </style>

</body>
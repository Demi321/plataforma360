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

</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%@include file="../plantilla/modal_menu.jsp" %>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Reporte Hospital</div>
        <div id="sidebar">


        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <!--Contenido-->
            <div class="col-12 titulo-instalaciones">
                <h1>Instalaciones</h1>
                <hr>
                <label>Seleccione los espacios existentes en su institución, indique número y disponibilidad según se solicite.</label>
            </div>
            <div class="col-12 contenedor-formulario">
                <div class="">
                    <form id="reporteHospitalario" >
                        <div class="col-12 formulario">
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="pacientes">Pacientes COVID-19</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="porcentaje">Porcentaje</label>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pacientes_totales">Pacientes Totales</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number"  min="0" class="form-control" id="pacientes_totales" name="pacientes_totales" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pacientes_totales_porcentaje" name="pacientes_totales_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="graves">Graves</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pacientes_graves" name="pacientes_graves" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pacientes_graves_porcentaje" name="pacientes_graves_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pacientes_nograves">No graves en aislamiento voluntario</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pacientes_nograves" name="pacientes_nograves" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pacientes_nograves_porcentaje" name="pacientes_nograves_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label>Recuperados</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="recuperados" name="recuperados" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="recuperados_porcentaje" name="recuperados_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label>Fallecidos</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="fallecidos" name="fallecidos" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="fallecidos_porcentaje" name="fallecidos_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>

                            <div class="form-row">
                                <div class="col-3 encabezados">
                                    <label for="capacidad">Capacidad hospitalaria</label>
                                </div>
                                <div class="col-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                                <div class="col-3 encabezados">
                                    <label for="ocupadas">Ocupadas</label>
                                </div>
                                <div class="col-3 encabezados">
                                    <label for="porcentaje">Porcentaje</label>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="col-3">
                                    <label for="camas_hospitalarias">Camas Hospitalarias</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_totales" name="camas_totales" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_ocupadas" name="camas_ocupadas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="camas_ocupadas_porcentaje" name="camas_ocupadas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="camas_hospitalarias">Camas Hospitalarias COVID-19</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_covid_totales" name="camas_covid_totales" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_covid_ocupadas" name="camas_covid_ocupadas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="camas_covid_ocupadas_porcentaje" name="camas_covid_ocupadas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="terapia_intensiva">Camas terapia intensiva</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_ti_totales" name="camas_ti_totales" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_ti_ocupadas" name="camas_ti_ocupadas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="camas_ti_ocupadas_porcentaje" name="camas_ti_ocupadas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>                       
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="camas_urgencias">Camas de urgencias</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_urgencias_totales" name="camas_urgencias_totales" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="camas_urgencias_ocupadas" name="camas_urgencias_ocupadas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="text" class="form-control" id="camas_urgencias_ocupadas_porcentaje" name="camas_urgencias_ocupadas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>

                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="instalaciones">Instalaciones Hospitalarios</label>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="terapia_intensiva">Unidad de terapia intensiva</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="uti" name="uti" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>  
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="aislamiento">Cuarto para aislamiento</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="cuartos_aislamiento" name="cuartos_aislamiento" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>  
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="positivos">Positivos no complicados</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="positivos_nograves" name="positivos_nograves" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>  
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="graves">Graves</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="positivos_graves" name="positivos_graves" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>                      
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="moviles">Instalaciones Móviles</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="capacidad">Capacidad de personas</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="campamentos">Campamentos</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="campamentos" name="campamentos" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_campamentos" name="capacidad_campamentos" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="casas_campana">Casas de campaña</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="casas_campana" name="casas_campana" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_casas_campana" name="capacidad_casas_campana" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="toldo">Toldo/Carpa</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="toldo" name="toldo" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_toldo" name="capacidad_toldo" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="catres">Catres individual</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="catre" name="catre" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="cocinas">Cocinas móviles</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="cocina_moviles" name="cocina_moviles" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>
                            <div class="form-row">
                                <div class="col-3col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="transporte">Medios de transporte</label>
                                </div>
                            </div>                       
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="pacientes">Traslado de pacientes</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="capacidad">Capacidad de personas</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="ambulancia_terapia">Ambulancia de terapia intensiva</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="ambulancia_ti" name="ambulancia_ti" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_ambulancia_ti" name="capacidad_ambulancia_ti" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="ambulancia_traslado">Ambulancia de traslado</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="ambulancia_traslado" name="ambulancia_traslado" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_ambulancia_traslado" name="capacidad_ambulancia_traslado" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>

                            <div class="form-row">
                                <div class="col-3 encabezados">
                                    <label for="insumos">Traslado de insumos</label>
                                </div>
                                <div class="col-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                                <div class="col-3 encabezados">
                                    <label for="capacidad">Capacidad de personas</label>
                                </div>
                                <div class="col-3 encabezados">
                                    <label for="toneladas">Carga(Ton)</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="carga">Vehículo de carga</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="vehiculo_carga" name="vehiculo_carga" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">

                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_vehiculo_carga" name="capacidad_vehiculo_carga" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="tractocamion">Tractocamión</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="tractocamion" name="tractocamion" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_tractocamion" name="capacidad_tractocamion" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="plataforma">Plataforma</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="plataforma" name="plataforma" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_plataforma" name="capacidad_plataforma" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="camabaja">Cama baja</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="cama_baja" name="cama_baja" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_cama_baja" name="capacidad_cama_baja" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="cajaseca">Caja seca</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="caja_seca" name="caja_seca" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_caja_seca" name="capacidad_caja_seca" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="carromudanza">Carro de mudanza</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="carro_mudanza" name="carro_mudanza" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_carro_mudanza" name="capacidad_carro_mudanza" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="pullman">Pullman</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="pullman" name="pullman" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_pullman" name="capacidad_pullman" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="alafija">Aeronave de ala fija</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="aeronave_alafija" name="aeronave_alafija" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_personas_aeronave_alafija" name="capacidad_personas_aeronave_alafija" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_aeronave_alafija" name="capacidad_aeronave_alafija" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-3">
                                    <label for="aeronave_alarotativa">Aeronave de ala rotativa</label>  
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="aeronave_alarotativa" name="aeronave_alarotativa" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_personas_aeronave_alarotativa" name="capacidad_personas_aeronave_alarotativa" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-3">
                                    <input type="number" min="0" class="form-control" id="capacidad_aeronave_alarotativa" name="capacidad_aeronave_alarotativa" placeholder="Indique capacidad Ton." value="0">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="col-12">
                            <h1>Personal y Equipos</h1>
                            <hr>
                            <label>Seleccione los equipos y personal existente en su institución, indique número y disponibilidad según se solicite.</label>
                        </div>
                        <div class="col-12 formulario"> 
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="equipos">Equipos Especializados</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="porcentaje">Porcentaje</label>
                                </div>
                            </div>


                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="ventiladores">Ventiladores mecánicos</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="ventiladores_mecanicos" name="ventiladores_mecanicos" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="monitores">Monitores</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="monitores" name="monitores" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="rx">Rayos X pórtatiles</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="rx_portatiles" name="rx_portatiles" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pulsioxímetros">Pulsioxímetros</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pulsioximetros" name="pulsioximetros" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="carro_rojo">Carros rojos</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="carro_rojo" name="carro_rojo" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="ultrasonidos_moviles">Ultrasonidos móviles</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="ultrasonidos_moviles" name="ultrasonidos_moviles" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pruebasdisponibles">Pruebas COVID-19 disponibles</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pruebas_covid_disponibles" name="pruebas_covid_disponibles" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pruebas_covid_disponibles_porcentaje" name="pruebas_covid_disponibles_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pruebasrealizadas">-Pruebas COVID-19 realizadas</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pruebas_covid_realizadas" name="pruebas_covid_realizadas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pruebas_covid_realizadas_porcentaje" name="pruebas_covid_realizadas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pruebaspositivas">-Pruebas COVID-19 positivas</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pruebas_covid_positivas" name="pruebas_covid_positivas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pruebas_covid_positivas_porcentaje" name="pruebas_covid_positivas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <label for="pruebasnegativas">-Pruebas COVID-19 negativas</label>  
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="pruebas_covid_negativas" name="pruebas_covid_negativas" placeholder="Indique en número" value="0">
                                </div>
                                <div class="col-4 col-md-3 col-lg-3 col-xl-3">
                                    <input type="text" class="form-control" id="pruebas_covid_negativas_porcentaje" name="pruebas_covid_negativas_porcentaje" placeholder="El sistema indica porcentaje" disabled>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="equipos">Equipos de Protección Personal</label>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="cubrebocas">Cubre bocas</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="cubrebocas" name="cubrebocas" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="caretas">Careta</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="caretas" name="caretas" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="guantes">Guantes</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="guantes" name="guantes" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="desechables">Trajes desechables</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="trajes_desechables" name="trajes_desechables" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="aislamiento">Trajes de aislamiento hospitalario</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="trajes_aislamiento" name="trajes_aislamiento" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>
                            <br>
                            <hr>

                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="rh">Recursos Humanos</label>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="personal_total">Personal Total</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="personal_total" name="personal_total" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-12 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="especializado">Personal Especializado COVID-19</label>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="medicos">Médicos</label>
                                </div>
                            </div>  
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label></label>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                            </div> 
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="intensiva">Terapia Intensiva</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="medicos_ti" name="medicos_ti" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="urgencias">Urgencias</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="medicos_urg" name="medicos_urg" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="medicina">Medicina interna</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="medicina_interna" name="medicina_interna" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="neumologia">Neumología</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="neumologia" name="neumologia" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="infectologia">Infectología</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="infectologia" name="infectologia" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="anesteciologia">Anesteciología</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="anesteciologia" name="anesteciologia" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="cirujano">Médico Cirujano</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="medico_cirujano" name="medico_cirujano" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="dentista">Cirujano Dentista</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="cirujano_dentista" name="cirujano_dentista" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="enfermeria">Enfermería</label>
                                </div>
                            </div> 
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label></label>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="efermero">Enfermero(a)s</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="enfermeros" name="enfermeros" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="efermeroterapia">Enfermero(a)s Terapia intensiva</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="enfermeros_ti" name="enfermeros_ti" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="efermeroinhaloterapia">Enfermero(a)s Inhaloterapia</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="enfermeros_inhaloterapia" name="enfermeros_inhaloterapia" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>

                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="personal">Personal</label>
                                </div>
                            </div>  
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label></label>
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 encabezados">
                                    <label for="cantidad">Cantidad</label>
                                </div>
                            </div> 
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="sanidad">Personal operativo en apoyo sanidad</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="personal_operativo_apoyo" name="personal_operativo_apoyo" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="oficialessanidad">Oficiales de sanidad</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="oficiales_sanidad" name="oficiales_sanidad" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <br>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <label for="tropassanidad">Tropas de sanidad</label>  
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                    <input type="number" min="0" class="form-control" id="tropas_sanidad" name="tropas_sanidad" placeholder="Indique en número" value="0">
                                </div>
                            </div>
                            <hr>
                            <div class="form-row">
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3">
                                </div>
                                <div class="col-6 col-md-3 col-lg-3 col-xl-3 botones">
                                    <button type="submit" class="btn btn-red">Guardar</button>
                                </div>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>


    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>


    <link href="${pathRecursos}/css/ReporteHospitales.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/Empresa/ReporteHospitales.js"></script>

</body>
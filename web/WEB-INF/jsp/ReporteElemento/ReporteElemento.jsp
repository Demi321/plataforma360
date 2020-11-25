<%-- 
    Document   : plantilla
    Created on : 02 Ene 2020, 16:25:53
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
        <div id="divTamBackup" style="display: none;">
            <div id="test"></div>
            <label  id="TamBackup"></label>
        </div>
        <div class="row col-12 m-0 p-2" id="toggle">Reportes</div>
        <div id="sidebar">
            <div class="h-100 col-12 m-0 p-0"  id="grupos" style="overflow-y: scroll;">
                <input type="hidden" name="IdAdministrador" id="IdAdministrador" value="" >
                <span id="span" style="color: lightblue; display: block; font-size: 12px; "  >${Alerta}</span>
                <%-- Aqui se iran insertando los botones de los reportes --%>
            </div>

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div class="row col-12">
                <div class="col-6" id="fomulario">
                    <!-- Campos para el reporte del elelemto!-->
                    <form id="rep" action="#">
                        <div id="dividUaurio">
                            <div class="form-group">
                                <input type="hidden" class="form-control" id="idUsuario" disabled="true">
                            </div>
                        </div>

                        <div id="dividRE">
                            <div class="form-group">
                                <label for="idREP" style="color:white">No. Reporte</label>
                                <input type="text" class="form-control" id="idRE" style="border-radius: 10px;" placeholder="No. Reporte" disabled="true">
                            </div>
                        </div>
                        <div id="divque">
                            <div class="form-group">
                                <label for="que" style="color:white">¿Qué?</label>
                                <textarea class="form-control" rows="3" id="que" style="resize: none;border-radius: 10px;" placeholder="¿Qué?" disabled="true"></textarea>
                            </div>
                        </div>

                        <div id="divquien">
                            <div class="form-group">
                                <label for="quien" style="color:white">¿Quien?</label>
                                <textarea class="form-control" rows="3" id="quien" style="resize: none;border-radius: 10px;" placeholder="¿Quien?" disabled="true"></textarea>
                            </div>
                        </div>

                        <div id="divcuando">
                            <div class="form-group">
                                <label for="cuando" style="color:white">¿Cuando?</label>
                                <input type="text" class="form-control" id="cuando" placeholder="¿Cuando?" style="border-radius: 10px;" disabled="true">
                                <!--textarea class="form-control" rows="4" id="cuando" style="resize: none;border-radius: 10px;" placeholder="¿Cuando?"></textarea-->
                            </div>
                        </div>

                        <div id="divdonde">
                            <div class="form-group">
                                <label for="done" style="color:white">¿Donde?</label>
                                <textarea class="form-control" rows="3" id="donde" style="resize: none;border-radius: 10px;" placeholder="¿Donde?" disabled="true"></textarea>
                            </div>
                        </div>

                        <div id="divlatlngE">
                            <div class="form-group">
                                <%--label for="latlngE" style="color:white">Posición del elemento</label--%>
                                <input type="hidden" class="form-control" id="latlngE" placeholder="Latitud,Longitud" style="border-radius: 10px;" disabled="true">
                            </div>
                        </div>

                        <div id="divlatlngI">
                            <div class="form-group">
                                <%--label for="latlngI" style="color:white">Posición del incidente</label--%>
                                <input type="hidden" class="form-control" id="latlngI" placeholder="Latitud,Longitud" style="border-radius: 10px;" disabled="true">
                            </div>
                        </div>

                        <div id="divinformacionadicional">
                            <div class="form-group">
                                <label for="informacionadicional" style="color:white">Información Adicional</label>
                                <textarea class="form-control" rows="3" id="informacionadicional" style="resize: none;border-radius: 10px;" placeholder="Información Adicional" disabled="true"></textarea>
                            </div>
                        </div>

                        <div id="divrazonamiento">
                            <div class="form-group">
                                <label for="razonamiento" style="color:white">Razonamiento</label>
                                <textarea class="form-control" rows="3" id="razonamiento" style="resize: none;border-radius: 10px;" placeholder="Razonamiento" disabled="true"></textarea>
                            </div>
                        </div>
                        <div id="divfolioexterno">
                            <div class="form-group">
                                <label for="folioexterno" style="color:white">Folio Externo</label>
                                <input type="text" class="form-control" id="folioexterno" placeholder="Ingrese un Folio para este reporte" style="border-radius: 10px;" disabled="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="button" class="btn btn-danger" id="boton" disabled="true">Finalizar</button>
                        </div>
                    </form>

                </div>


                <div class="col-6" id="mapa" style="padding-top:2rem">
                    <!-- Aqui se va a agregar el mapa !-->
                    <div class="alt-1 col-xl-12 col-lg-12 col-md-12 col-sm-12 " style="border: solid 3px white; border-radius: 18px; padding: 0; "> <%-- Ubicacion Mapa --%>
                        <div class="col-12 embed-responsive embed-responsive-16by9" style="height: 100%; ">
                            <div class=" embed-responsive-item" id="map" style="border-radius:14px;">

                            </div>                            
                        </div> 
                    </div>

                    <!--div id="encabezado" style="margin-top: 2rem" class="row col-12">
                        <div id="similares">

                        </div>
                    </div-->
                    <div class="menudependencia" id="menudependencia" style="margin-top:2rem">

                        <div class="mt-4" id="divnotificarDependencias" style="display: block;">
                            <form id="notificarDependencias" action="#" method="POST" >
                                <div class="row col-12 mx-auto mt-2" style="padding: 0;"> 
                                    <table>
                                        ${Dependencias}
                                    </table>
                                </div>      
                                <div class="row col-12 mx-auto mt-2" style="padding: 0;"> 
                                    <div class="col-1">
                                    </div>
                                    <div class="col-1 col-sm-2 col-md-3 col-lg-4 col-xl-6">                                                          
                                    </div>
                                    <div class="col-9 col-sm-8 col-md-7 col-lg-6 col-xl-4">
                                        <button class="btn btn-danger" type="button" value="Notificar" style="color: white; width: 100%;">Notificar</button></td>
                                    </div>

                                </div>      
                            </form>
                        </div>
                    </div> 
                </div>
            </div>
        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/IncidenteOperativo/appReporteElemento.js" ></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap">
    </script>

</body>


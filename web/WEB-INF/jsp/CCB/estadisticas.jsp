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
    <%@include file="../plantilla/baner_superior.jsp" %>
    <aside class="d-none">
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">

            <div class="row col-12 m-0 p-1 " id="fila1">
                <div class="col-3 p-1">
                    <div class="row col-12 m-0 p-1 h-100 c1">
                        <div class="col-4 p-1" id="n_ingresados_logo"></div>
                        <div class="col-8 p-1">
                            <div class="row col-12 m-0 p-1 h-25">
                                <h1>Número de Ingresados</h1>
                            </div>
                            <div class="row col-12 m-0 p-1 h-75">
                                <h2 id="n_ingresados">182</h2>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-9 p-1">
                    <div class="row col-12 m-0 p-1 h-100 c2">

                        <div class="col-3 p-0 h-100">
                            <div class="row col-12 m-0 p-0 h-100">
                                <div class="col-4 p-1" id="n_hospitalizados_logo">

                                </div>
                                <div class="col-8 p-1">
                                    <div class="row col-12 m-0 p-1 h-25">
                                        <h1>Hospitalizados en piso</h1>
                                    </div>
                                    <div class="row col-12 m-0 p-1 h-75">
                                        <h2 id="n_hospitalizados">111</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-3 p-0 h-100">
                            <div class="row col-12 m-0 p-0 h-100">
                                <div class="col-4 p-1" id="n_tera_logo">

                                </div>
                                <div class="col-8 p-1">
                                    <div class="row col-12 m-0 p-1 h-25">
                                        <h1>Hospitalizados en TERA</h1>
                                    </div>
                                    <div class="row col-12 m-0 p-1 h-75">
                                        <h2  id="n_tera">5</h2>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-3 p-0 h-100">
                            <div class="row col-12 m-0 p-0 h-100">
                                <div class="col-4 p-1"  id="n_contra_logo">

                                </div>
                                <div class="col-8 p-1">
                                    <div class="row col-12 m-0 p-1 h-25">
                                        <h1>Contra-Referencia</h1>
                                    </div>
                                    <div class="row col-12 m-0 p-1 h-75">
                                        <h2 id="n_contra">9</h2>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-3 p-0 h-100">
                            <div class="row col-12 m-0 p-0 h-100">
                                <div class="col-4 p-1"  id="n_altas_logo">

                                </div>
                                <div class="col-8 p-1">
                                    <div class="row col-12 m-0 p-1 h-25">
                                        <h1>Altas</h1>
                                    </div>
                                    <div class="row col-12 m-0 p-1 h-75">
                                        <h2 id="n_altas">57</h2>
                                    </div>
                                </div>
                            </div>

                        </div>


                        <div class="col-3 p-1"></div>
                    </div>
                </div>  
            </div>
            <div class="row col-12 m-0 p-1 grafica_title">
                <h1># Ingreso de Pacientes Diario</h1>
            </div>
            <div class="row col-12 m-0 p-1 h-100" id="fila2">
                <div class="col-12 p-1 h-100">
                    <div class="row col-12 m-0 p-1 h-100">
                        <div class="row col-12 m-0 p-1 h-100" id="grafica_pacientes_diarios"></div>   
                    </div>
                </div>

            </div>
            <div class="row col-12 m-0 p-1 grafica_title">
                <div class="col-4 p-1 grafica_title">
                    <h1># Pacientes ContraReferencia por Hospital de Contrarreferencia</h1>
                </div>  
                <div class="col-4 p-1 grafica_title">
                    <h1># Pacientes por Ambulancia</h1>
                </div>
                <div class="col-4 p-1 grafica_title">
                    <h1># Pacientes por Hospital de origen</h1>
                </div>  
            </div>
            <div class="row col-12 m-0 p-1 " id="fila3">
                <div class="col-4 p-1">
                    <div class="row col-12 m-0 p-1">
                        <div class="row col-12 m-0 p-1" id="grafica_contrareferencias"></div>
                    </div>
                </div>  
                <div class="col-4 p-1">
                    <div class="row col-12 m-0 p-1">
                        <div class="row col-12 m-0 p-1" id="grafica_pacientes_ambulancia"></div>
                    </div>
                </div>
                <div class="col-4 p-1">
                    <div class="row col-12 m-0 p-1">
                        <div class="row col-12 m-0 p-1" id="grafica_hospitaldeorigen"></div>
                    </div>
                </div>  
            </div>

        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link href="${pathRecursos}/css/graficas.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/ccb/graficas.js"></script>
    <script>

    </script>
    <style>

    </style>

</body>
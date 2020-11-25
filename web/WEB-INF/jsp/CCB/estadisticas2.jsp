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
    <aside class="">
        <div class="row col-12 m-0 p-0" id="toggle">Estadísticas</div>
        <div id="sidebar">
            <div class="row col-12 m-0 p-1">
                <div class="col-12 menu" id="menu_solicitudes">Solicitudes</div>
                <div class="col-12 menu" id="menu_traslados">Traslados</div>
            </div>
        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
            <div class="row col-12 m-0 p-0 h-100"  id="stats_solicitudes">
                <div class="col-sm-12 col-md-6 c4">
                    <div class="row col-12 m-0 p-1 h-100" id="grafica_hospitaldeorigen"></div>
                </div>
                <div class="col-sm-12 col-md-6 p-0 c4">
                    <div class="row col-12 m-0 p-0 h-100">
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-laptop-medical"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Total de solicitudes</div>    
                                <div class="col-12 p-0 num" id="total_solicitudes"></div>

                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-times-circle"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Solicitudes improcedentes</div>    
                                <div class="col-12 p-0 num" id="no_procedentes"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-hospital-alt"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Solicitudes procedentes</div>    
                                <div class="col-12 p-0 num" id="procedentes"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-user-check"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Solicitudes aceptadas</div>    
                                <div class="col-12 p-0 num" id="aceptadas"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-user"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Solicitudes no aceptadas</div>    
                                <div class="col-12 p-0 num" id="no_aceptadas"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-window-close"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Solicitudes canceladas</div>    
                                <div class="col-12 p-0 num" id="canceladas"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-eye"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Pendientes de revisión</div>    
                                <div class="col-12 p-0 num" id="revision"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-procedures"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Pacientes hospitalizados</div>    
                                <div class="col-12 p-0 num" id="ingresado"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-clipboard-check"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Pacientes dados de alta</div>    
                                <div class="col-12 p-0 num" id="alta"></div>
                            </div>
                        </div>                    
                    </div>
                </div>
                <div class="col-sm-12 col-md-12 c4">
                    <div class="row col-12 m-0 p-1 h-100" id="grafica_pacientes_diarios"></div>
                </div>
            </div>
            <div class="row col-12 m-0 p-0 h-100" id="stats_traslados">
                <div class="col-sm-12 col-md-6 c4">
                    <div class="row col-12 m-0 p-1 h-100" id="grafica_contrareferencias"></div>
                </div>
                <div class="col-sm-12 col-md-6 p-0 c4">
                    <div class="row col-12 m-0 p-0 h-100">
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-exchange-alt"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Contrarreferencias</div>    
                                <div class="col-12 p-0 num" id="contrarreferencia"></div>

                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-users"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Personal registrado UTC</div>    
                                <div class="col-12 p-0 num" id="personal_utc"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-ambulance"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Personal registrad SUCRE</div>    
                                <div class="col-12 p-0 num" id="personal_sucre"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-ambulance"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Personal registrado CRUM</div>    
                                <div class="col-12 p-0 num" id="personal_crum"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-bell"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Vinculaciones familiares</div>    
                                <div class="col-12 p-0 num" id="vinculaciones_familiares"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-file-contract"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Vinculaciones pacientes</div>    
                                <div class="col-12 p-0 num" id="vinculaciones_pacientes"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-calendar-alt"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Llamadas programadas</div>    
                                <div class="col-12 p-0 num" id="llamadas_programadas"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-procedures"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Test enviados por pacientes</div>    
                                <div class="col-12 p-0 num" id="test_pacientes"></div>
                            </div>
                        </div>
                        <div class="row m-0 col-4 c9">
                            <div class="col p-0 icon"><i class="fas fa-clipboard-check"></i></div>
                            <div class="row m-0 col-8 p-0">
                                <div class="col-12 p-0 tit">Test enviados por personal UTC</div>    
                                <div class="col-12 p-0 num" id="test_doctores"></div>
                            </div>
                        </div>                    
                    </div>
                </div>
                <div class="col-sm-12 col-md-6 c4">
                    <div class="row col-12 m-0 p-1 h-100" id="grafica_pacientes_ambulancia"></div>
                </div>
                <div class="col-sm-12 col-md-6 c4"></div>
            </div>

        </div>
    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead.jsp" %>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link href="${pathRecursos}/css/graficas2.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/ccb/graficas2.js"></script>
    <script>

    </script>
    <style>

    </style>

</body>
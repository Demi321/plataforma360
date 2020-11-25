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
    
    <link href="${pathRecursos}/css/operador.css" rel="stylesheet" />
    <title>Configuración de Proyectos</title>
</head>

<body>
    <section id="page" style="overflow: hidden">
        <%@include file="../plantilla/header.jsp" %>
        <nav3 id="sidebar">
            <%@include file="../plantilla/toggle.jsp" %>
            <div class="contenidomenus"  >
                <h4 class="mx-auto mt-0 mb-2 title_sidebar">Título</h4>

                <!--ESPACIO SIDEBAR IZQ-->
                <!--ESPACIO SIDEBAR IZQ-->
                <!--ESPACIO SIDEBAR IZQ-->
                <!--ESPACIO SIDEBAR IZQ-->

            </div>
        </nav3>
        <main>
            <%@include file="../plantilla/modal_menu.jsp" %>
            <div class="align-items-center" id="contenidor" style="overflow-x: hidden;">
                <!-- ESPACIO PRINCIPAL -->
                <!-- ESPACIO PRINCIPAL -->
                <!-- ESPACIO PRINCIPAL -->
                <!-- ESPACIO PRINCIPAL -->
                <div class= "container mt-4">
                    <div class="row" style="">
                        <div class="col-12 col-sm-12 col-md-12 col-lg-7 col-xl-7" style="min-width: 300px; width: 70%; height: 550px;">

                            <div class="card mx-auto" style="height: 100%;">
                                <div class="card-header">
                                    Dependencias asociadas
                                </div>
                                <div class="card-body" style="    max-height: 515px;">

                                    <div class="col-12" style="height: 75%; overflow-y: scroll" id="dependencias">

                                    </div>

                                    <div class="col-12" style="height: 25%; background: #dedfe0; border-radius: 5px; padding-left: 25px;  padding-right: 25px; padding-top: 5px;">

                                        <form id="agregarDependencia"  class="form-inline"  style="    display: contents;" action="AgregarDependencia" method="POST">

                                            <div class="row mt-2 mb-2" style="justify-content: center; background: wheat; height: 22px; color:black; border-radius: 5px;">
                                                <h5 style=" margin-top: 1px;" class="label label-default">Asocia una nueva dependencia</h5>
                                            </div>
                                            <div class="row  mt-1">
                                                <div class="col-10">
                                                    <div class="row">
                                                        <div class="col-6 col-sm-6 col-md-4 col-lg-4 col-xl-4" style="  padding: 0;">
                                                            <input required="true" class="form-control" id="DependenciaNombre" name="DependenciaNombre" type="text" placeholder="Nombre del proyecto"  style="width: 98%">
                                                        </div>
                                                        <div class="col-6 col-sm-6 col-md-4 col-lg-4 col-xl-4 mb-1" style="  padding: 0;">
                                                            <input required="true" class="form-control" id="DependenciaURL" name="DependenciaURL" type="text" placeholder="URL del proyecto" style="width: 98%" >
                                                        </div>
                                                        <div class="col-6 col-sm-6 col-md-4 col-lg-4 col-xl-4 mb-1" style="  padding: 0;">
                                                            <input required="true" class="form-control" id="DependenciaAlias" name="DependenciaAlias" type="text" placeholder="Alias" style="width: 98%">
                                                        </div>
                                                        <div class="col-6 col-sm-6 col-md-12 col-lg-12 col-xl-12 " style="  padding: 0;">
                                                            <input  class="form-control" id="DependenciaIcon" name="DependenciaIcon" type="text" placeholder="Icon (Opcional) - Ej. data:image/png;base64,..." style="width: 99%">
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="col-2">
                                                    <div class="row">
                                                        <br>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12" style="  padding: 0;">
                                                            <input required="true" type="submit" value="Añadir" class="btn btn-primary" style="max-width: 100%; width: 90%">
                                                        </div>
                                                    </div>
                                                </div>


                                            </div>
                                        </form>
                                    </div>


                                </div>
                            </div>



                        </div>
                        <div class="col-12 col-sm-12 col-md-12 col-lg-5 col-xl-5" style="min-width: 300px; height: 550px;">
                            <div class="card mx-auto" style="height: 100%;">
                                <div class="card-header">
                                    Datos del proyecto
                                </div>
                                <div class="card-body" >
                                    <div class="col-10 mx-auto">
                                        <form action="ActualizarProyecto" method="POST" id="proyecto">
                                            <div class="form-group " >
                                                <label class="" id="label">APY KEY</label>
                                                <input type="text" placeholder="${apikey}" value="" class="col-lg-8 form-control" id="apikey" name="apikey" value="${apikey}" >
                                            </div>
                                            <div class="form-group ">
                                                <label class="" id="label">URL Heroku:</label>
                                                <input type="text" placeholder="${heroku}" value="" class="col-lg-8 form-control" id="heroku" name="heroku" value="${heroku}" >
                                            </div>
                                            <div class="form-group ">
                                                <label class="" id="label">Firebase Authorization:</label>
                                                <input type="text" placeholder="${FireBaseAuthorization}" value="" class="col-lg-8 form-control" id="FireBaseAuthorization" name="FireBaseAuthorization" value="${FireBaseAuthorization}" >
                                            </div>
                                        </form>
                                        <span style="color: lightcyan;"  ></span>
                                        <button type="submit" class="btn btn-primary" id="btn-submit" form="proyecto" value="Submit">Actualizar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row" >
                        <div class="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto"  >

                            <div class="card mx-auto" >
                                <div class="card-body" style="padding: 0;border: none;     padding: 15px;   border: none;   margin-top: 30px;   border-radius: 5px;">
                                    <span style="color: lightcyan;  left: 2%; position: relative;"  >${AlertaDependencia} ${Alerta}</span>
                                </div>
                            </div>



                        </div>

                    </div>

                </div>
            </div>


        </main>
        <%@include file="../plantilla/footer.jsp" %>
    </section>

    <%@include file="../plantilla/callhead.jsp" %>
    <script src="${pathRecursos}/js/SuperAdmin/proyecto.js" ></script>
</body>







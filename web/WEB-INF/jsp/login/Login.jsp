<%-- 
    Document   : Login
    Created on : 2/10/2018, 05:02:03 PM
    Author     : Vostro Placas
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>


<head>

    <%@include file="../plantilla/head_login.jsp" %>  

    <title>Inicio de Sesión</title>
</head>

<body>



    <%@include file="../plantilla/header_vacio.jsp" %>

    <div class="formulario" style="border: none;">
        <!--h2>Sistema de Monitoreo de Emergencias</h2-->
        <h2></h2>
        <form class="form-group col-12 m-0" method="POST" id="Log-in">

            <label for="usr"  class="" style="display: table; margin-left: auto; margin-right: auto; font: 15px Arial;">Usuario:</label>
            <input type="text" name="usr" class="form-control" id="usuario" style="color: #495057; background-color: #fff;">
            <label for="pwd" class="" style="display: table; margin-left: auto; margin-right: auto; font: 15px Arial;">Contraseña:</label>
            <input type="password" name="pwd" class="form-control" id="contra" style="color: #495057; background-color: #fff;" autocomplete="on">
            <button type="submit" class="btn btn-default" name="enviar" style="border-radius: 20px; width: 150px; margin-left: auto;  margin-right: auto;">Entrar</button>
            <p class="text-center"><a href="/plataforma360/Registro">Registrarse</a></p>
            <span   style="font-size: 13px; margin-left: auto; margin-right: auto; display: table;">Las cookies y notificaciones deben de estar activadas en el navegador.</span>
        </form>
        <div style="    display: block;
             margin-top: -25px;
             position: absolute;
             z-index: 10000;
             height: 30px;
             width: 100%;">
            <span id="span" style="color: white;
                  background-color: #ff8200;
                  height: 30px;
                  justify-content: center;
                  font: 12px Arial;
                  padding-top: 8px;
                  border-bottom-right-radius: 10px;
                  border-bottom-left-radius: 10px;
                  display: none;"  >${Alerta}</span>
        </div>

    </div> 

    <div class="d-none vista_completa" id="seleccionar_institucion" >
        <div class="listado_instituciones row ">
            <div class="row col-12 m-0 p-0 h-100">
                <div class="row col-12 m-0 p-4 body">
                    <div class="col-12 title">Selecciona con que cuenta quieres continuar</div>
                    <hr class="division mb-2">
                    <div class="col-12 content"id="catalogo_instituciones"></div>
                </div>
                <div class="col-12 m-0 p-0 butons">
                    <input type="button" class="btn btn-danger boton m-0" style="border: none;" value="continuar" id="boton_seleccionar_institucion">
                </div>
            </div>
        </div>
    </div>
    <div class="d-none vista_completa" id="agregar_perfil" >
        <div class="listado_instituciones row ">
            <div class="row col-12 m-0 p-0 h-100">
                <div class="row col-12 m-0 p-4 body">
                    <div class="col-12 title">Selecciona el tipo de perfil</div>
                    <hr class="division mb-2">
                    <div class="col-12 content">
                        <div class="row col-12 m-0 p-0 h-100">
                            
                            <div class="row m-0 col-sm-12 col-md-4 p-3" id="nueva_institucion">
                                <div class="col-sm-12 p-2 d-flex justify-content-center align-items-center perfil">Registrar Nueva Institución Académica</div>    
                            </div>   
                            <div class="row m-0 col-sm-12 col-md-4 p-3" id="vinculacion_institucion">
                                <div class="col-sm-12 p-2 d-flex justify-content-center align-items-center perfil">Vincularse a una Institución Académica</div>    
                            </div>  
                            <div class="row m-0 col-sm-12 col-md-4 p-3" id="vinculacion_grupo">
                                <div class="col-sm-12 p-2 d-flex justify-content-center align-items-center perfil">Vincularse a un Grupo</div>    
                            </div>  
                        </div>
                    </div>
                </div>
                <div class="col-12 m-0 p-0 butons">
                    <input type="button" class="btn btn-danger boton m-0" style="border: none;" value="continuar" id="boton_seleccionar_institucion">
                </div>
            </div>
        </div>
    </div>
    <%@include file="../plantilla/footer.jsp" %>

    <%@include file="../plantilla/callhead_login.jsp" %>
    <link href="${pathRecursos}/css/form-index.css" rel="stylesheet" />

</body>

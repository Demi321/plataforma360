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
</head>

<body>
    <%@include file="../plantilla/header.jsp" %>
    <%--<%@include file="../plantilla/modal_menu.jsp" %>--%>
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Título</div>
        <div id="sidebar">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">
        </div>
    </section>
    <section id="sectionfull" class="hidetoogle">

        <div class="formulario">
            <h2>Nuevo Registro</h2>

            <form action="#" class="formulario" name="formulario_registro" method="post" autocomplete="new">
                <span style="color: lightcyan;"  >${Alerta}</span>
                <div>
                    <div class="input-group">
                        <label class="label" for="Usuario">Usuario:</label>
                        <input type="text" id="Usuario" name="Usuario">

                    </div>
                    <div class="input-group">
                        <label class="label" for="Nombre">Nombre:</label>
                        <input type="text" id="Nombre" name="Nombre">

                    </div>
                    <div class="input-group">
                        <label class="label" for="Apellidos">Apellidos:</label>
                        <input type="text" id="Apellidos" name="Apellidos">

                    </div>

                    <div class="input-group" id="typeUser"><!--  col-12 m-0 p-0 pl-1 pr-1 -->
                        <label class="label">Dependencia:</label>
                    </div>

                    <div class="input-group" id="servUser"><!--  col-12 m-0 p-0 pl-1 pr-1 -->
                        <label class="label">Institución:</label>
                    </div>

                    <div class="input-group" id="regServicio"><!--  col-12 m-0 p-0 pl-1 pr-1 -->
                        <label class="label"><a href="#" id="RegistraServicio">Si su institución no aparece, regístrela aquí.</a></label>
                    </div>

                    <div class="input-group">
                        <!--input type="text" name="prevent_autofill" id="prevent_autofill" value="" style="display:none;" /-->

                        <!--input type="email" id="fake_email" name="fake_email" style="display:none"-->
                        <label class="label" for="Correo">Correo Electrónico:</label>
                        <input type="email" id="Correo" name="Correo" autocomplete="new">


                    </div>

                    <div class="input-group">
                        <!--input type="text" name="prevent_autofill" id="prevent_autofill" value="" style="display:none;" /-->

                        <!--input type="password" name="password_fake" id="password_fake" value="" style="display:none;" /-->
                        <label class="label" for="Pass">Contraseña:</label>
                        <input type="password" id="Pass" name="Pass" autocomplete="new-password">


                    </div>
                    <div class="input-group">
                        <label class="label" for="Pass2">Repetir Contraseña:</label>
                        <input type="password" id="Pass2" name="Pass2" autocomplete="new-password">

                    </div>

                    <div class="input-group custom-control custom-switch d-none">
                        <input type="checkbox" class="custom-control-input" id="confirma">
                        <label for="confirma" class="custom-control-label">¿El usuario podrá registrar a más personas?</label>
                    </div>

                    <div class="input-group checkbox d-none">
                        <input type="checkbox" name="terminos" id="terminos" value="true">
                        <label for="terminos">Acepto los Términos y Condiciones</label>
                    </div>

                    <div class="row col-12 m-0">

                        <div class="col-6 m-0">
                            <a href="Login"> <input type="button" id="login" value="Iniciar Sesión" class="alogin" style="
                                                    color: white;
                                                    border-radius: 1rem;
                                                    width: 70%;
                                                    padding: 5px;
                                                    margin: 15px;
                                                    border: none;
                                                    background: no-repeat;
                                                    text-decoration: underline;
                                                    font: bold 1.2rem Arial;"></a>
                        </div>
                        <div class="col-6 m-0">
                            <input type="submit" id="btn-submit" value="Registrar" style="
                                   color: white;
                                   border-radius: 1rem;
                                   width: 70%;
                                   padding: 5px;
                                   margin: 15px;
                                   border: none;">
                        </div>
                    </div>


                </div>

            </form>

        </div>


    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead_registro.jsp" %>
    <script src="${pathRecursos}/js/SuperAdmin/formulario-registro.js" ></script>
    <link href="${pathRecursos}/css/formulario-registro.css" rel="stylesheet" />

</body>
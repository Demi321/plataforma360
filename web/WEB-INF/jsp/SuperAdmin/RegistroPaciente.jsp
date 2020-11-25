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
    <!--<input type="hidden" id="config" value="${config}">-->
    <!--    <aside>
            <div class="row col-12 m-0 p-0" id="toggle">Registro Paciente</div>
            <div id="sidebar">
    
    
            </div>
        </aside>-->
    <section>
        <div class="w-100 h-100 p-4 centrar">
            <form class="w-50 pt-5 pb-5 pl-2 pr-2 bg-dark" id="formPaciente">
                <div class="form-group row col-12 mb-3 mr-0 ml-0 mt-3 p-0">
                    <div class="col-4 m-0 pl-0">
                        <label for="nombre">Nombre:</label>
                        <input type="text" class="form-control" id="nombre" aria-describedby="nombre" placeholder="Ingresa el Nombre" required="true">
                    </div>
                    <div class="col-4 m-0">
                        <label for="apellido_paterno">Apellido Paterno:</label>
                        <input type="text" class="form-control" id="apellido_paterno" placeholder="Ingrese el Apellido Paterno" required="true">
                    </div>
                    <div class="col-4 m-0 pr-0">
                        <label for="apellido_materno">Apellido Materno:</label>
                        <input type="text" class="form-control" id="apellido_materno" placeholder="Ingrese el Apellido Materno" required="true">
                    </div>
                </div>
                <div class="form-group">
                    <label for="direccion">Dirección:</label>
                    <input type="text" class="form-control" id="direccion" placeholder="Ingrese la dirección del paciente" required="true">
                </div>
                <div class="form-group row col-12 mb-3 mr-0 ml-0 mt-0 p-0">
                    <div class="col-6 m-0 pl-0">
                        <label for="telefono">Telefono:</label>
                        <input type="number" class="form-control" id="telefono" placeholder="Ingrese el telefono" required="true">
                    </div>
                    <div class="col-6 m-0 pr-0">
                        <label for="edad">Edad:</label>
                        <input type="number" class="form-control" id="edad" placeholder="Ingrese la edad" required="true">
                    </div>
                </div>
                <div class="form-group d-none" id="divid">
                    <label for="idpaciente">Identificador del paciente:</label>
                    <input type="text" class="form-control" id="idpaciente" disabled="true">
                </div>
                <div class="row col-12 m-0 p-0">
                    <div class="col-6 m-0 p-0 centrar">
                        <button type="submit" class="btn btnRegistrar btn-primary" id="Registrar">Registrar</button>
                    </div>
                    <div class="col-6 m-0 p-0 centrar">
                        <button type="button" class="btn btnCancelar" id="Cancelar">Cancelar</button>
                    </div>
                </div>
                
            </form>
        </div>

    </section>
    <%@include file="../plantilla/footer.jsp" %>
    <%@include file="../plantilla/callhead_registro.jsp" %>

    <link href="${pathRecursos}/css/registropaciente.css" rel="stylesheet" />
    <script src="${pathRecursos}/js/SuperAdmin/registropaciente.js"></script>
</body>
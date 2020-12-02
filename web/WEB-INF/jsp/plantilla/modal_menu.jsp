<%-- Document : menu Created on : 30 Dic 2019, 11:14:42 Author : Moisés Juárez --%>
<div id="menuModalIcon" class="menuModalIcon segmento">
    <nav role="navigation" id="hamburgerMenu">
        <div id="menuToggle">
            <input type="checkbox" id="cbmenuToggle" />
            <span></span>
            <span></span>
            <span></span>
            <ul id="menu" class="p-0">
                <div id="menuContainer" class="d-none">
                    <nav class="navbar navbar-lg navbar-md navbar-sm p-0">
                        <div class="collapse navbar-collapse" id="navbarColor01" style="display: flex;">
                            <ul class="navbar-nav" style="width: 100%;">
                                <li class="nav-item pl-0" id="modulo201">
                                    <a class="amenu nav active" href="estadisticas">Resumen Estadístico</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo200">
                                    <a class="amenu nav active" href="Protocolo de seguridad sanitaria">Seguridad Sanitaria</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
                <div id="textologin" class="pt-2 d-none">
                    <p id="title_textologin">Contacto</p>
                    <div class="p-2 modalText">
                        <img id="imgModal" style="width: 30%; /*margin-top: 10rem*/"><br><br><br>
                        Lago Zurich No. 245<br>
                        Torre Presa Falcón, Piso 19, Plaza Carso,<br>
                        Ampliación Granada, Miguel Hidalgo,<br>
                        Ciudad de México, México<br><br><br><br>
                        Teléfono: 5590003902 ext.520<br>
                        Correo: contacto@claro360.com
                    </div>
                </div>
                <div class="position-relative h-100 w-100">
                    <!--Contenido-->
                    <address class="cerrarSesion w-100 h-100" id="ventanaSesion">
<!--                        <div class="titulo d-flex justify-content-center align-items-center">
                            <h5 class="titulo-seg">Bienvenido</h5>
                            <div style="position: absolute; left: 15px; font-size: 23px; cursor: pointer;" title="Cambiar cuenta" id="cambiar_cuenta">
                                <i class="fas fa-exchange-alt"></i>
                            </div>
                        </div>-->

                         <div class="perfil" id="menu_info_perfil">
                            <div class="foto">
                                <div class="foto-perfil d-flex justify-content-center align-content-center" id="img_perfil_user"><i class="fas fa-user-circle" style="font-size: 7rem; margin-top: 20px;"></i></div>
                            </div>
                            <div class="datos">
                                <div class="info">
                                    <h6 class="nombre" id="nombre_modal"></h6>
                                    <p class="correo" id="correo_modal"></p>
                                    <p class="ubicacion" id="direccion_modal"></p>
                                </div>
                                <div class="semaforo">
                                    <div class="contenido-semaforo">Semaforo</div>
                                    <div class="colorS" id="colorSemaforo" style="background-color: #e26800;border: 1px solid #e26800;"></div>
                                </div>
                            </div>
                            <div class="btn-perfil" id="btn_perfil360">
                                <!--                                <a class="boton-perfil" href="/plataforma360/perfil">Mi Perfil 360</a>-->
                            </div>
                        </div>
                        <div class="navegacion" id="menu_navegacion" style="">
                            <nav>
                                <ul class="menu-sesion text-left">
                                    <li id="link_registro"><a href="https://claro360.com/plataforma360/registro">Registrate</a></li>
                                    <li id="pcollapseServicios" class="serviciosMH" data-toggle="collapse" href="#collapseServicios" role="button" aria-expanded="false" aria-controls="collapseExample">
                                        Mis Servicios
                                    </li>
                                    <ul class="menu-servicios collapse" id="collapseServicios"></ul>
                                    <ul class="navbar-nav" style="width: 100%;">
                                        <li class="nav-item " id="modulo22">
                                            <a class="nav active" href="EstadisticosCCB">Resumen Estadístico</a>
                                        </li>
                                        <li class="nav-item " id="modulo10">
                                            <a class="nav active" href="CCB">Referencia de Pacientes COVID-19</a>
                                        </li>

                                        <li class="nav-item " id="modulo6">
                                            <a class="nav active" href="Institucion">Referencia de Pacientes COVID-19</a>
                                        </li>
                                        <li class="nav-item " id="modulo7">
                                            <a class="nav active" href="SolicitudTraslado" target="_blank">Solicitud a U.T.C-19</a>
                                        </li>
                                        <li class="nav-item " id="modulo26">
                                            <a class="nav active" href="referencia_contrareferencia">Solicitudes de referencia y contrareferencia.</a>
                                        </li>
                                        <li class="nav-item " id="modulo27">
                                            <a class="nav active" href="referencia_hospitalaria" target="_blank">Solicitud de referencia hospitalaria</a>
                                        </li>
                                        <li class="nav-item " id="modulo8">
                                            <a class="nav active" href="CRUM">Traslado de Referencias COVID-19</a>
                                        </li>
                                        <li class="nav-item " id="modulo9">
                                            <a class="nav active" href="SUCRE">Traslado de Referencias COVID-19</a>
                                        </li>
                                        <li class="nav-item " id="modulo5">
                                            <a class="nav active" href="ReporteHospital">Reporte Hospital</a>
                                        </li>
                                        <li class="nav-item " id="modulo11">
                                            <a class="nav active" href="vinculacion_familiar">Vinculación Familiar</a>
                                        </li>

                                        <li class="nav-item  d-none" id="modulo12">
                                            <a class="nav active" href="MonitoreoRemotoPersonalCCB">Monitoreo Personal CCB</a>
                                        </li>

                                        <li class="nav-item " id="modulo13">
                                            <a class="nav active" href="MonitoreoRemoto">Monitoreo Pacientes dados de Alta</a>
                                        </li>
                                        <li class="nav-item " id="modulo4">
                                            <a class="nav active" href="Administrador">Recepción de llamadas</a>
                                        </li>
                                        <li class="nav-item " id="modulo14">
                                            <a class="nav active" href="Notificaciones_TestCOVID19">Reportes COVID-19</a>
                                        </li>

                                        <li class="nav-item " id="modulo3">
                                            <a class="nav active" href="Empresa">Monitoreo de Unidades</a>
                                        </li>

                                        <li class="nav-item " id="modulo15">
                                            <a class="nav active" href="BuscarReporte">Búsqueda de Reporte</a>
                                        </li>

                                        <li class="nav-item " id="modulo16">
                                            <a class="nav active" href="#">PDF - Usuarios Registrados</a>
                                        </li>

                                        <li class="nav-item " id="modulo17">
                                            <a class="nav active" href="#">PDF - Usuarios Activos</a>
                                        </li>

                                        <li class="nav-item " id="modulo1">
                                            <a class="nav active" href="Registro">Registrar un Usuario</a>
                                        </li>

                                        <li class="nav-item " id="modulo18">
                                            <a class="nav active" href="ReporteElemento">
                                                Reporte de Incidente Operativo
                                                <label id="TamBackup" style="padding: 4px 5px 4px 5px; margin: 0; background: red; border-radius: 15px; font: bold 12px Arial; right: 5%; position: absolute; display: none;"></label>
                                            </a>
                                            <div id="divTamBackup">
                                                <div id="test"></div>
                                            </div>
                                        </li>

                                        <li class="nav-item " id="modulo2">
                                            <a class="nav active" href="Administracion">Administración de Usuarios</a>
                                        </li>
                                        <li class="nav-item " id="modulo19">
                                            <a class="nav active" href="TestCovidCCB">Test Covid Personal U.T.C.</a>
                                        </li>
                                        <li class="nav-item " id="modulo20">
                                            <a class="nav active" href="TestCovidPacientesCCB">Test Covid Pacientes U.T.C.</a>
                                        </li>
                                        <li class="nav-item " id="modulo21">
                                            <a class="nav active" href="RegistroPaciente">Registro de Pacientes</a>
                                        </li>
                                        <li class="nav-item " id="modulo24">
                                            <a class="nav active" href="RegistroPlantillaLaboral">Plantilla Laboral</a>
                                        </li>
                                        <li class="nav-item " id="modulo25">
                                            <a class="nav active" href="dashboard">Dashboard</a>
                                        </li>
                                        <li class="nav-item " id="modulo403">
                                            <a class="nav active" href="agregar_perfil">Nuevo Perfil</a>
                                        </li>
                                        <li class="nav-item " id="modulo400">
                                            <a class="nav active" href="administracion_directiva">Dirección</a>
                                        </li>
                                        <li class="nav-item " id="modulo401">
                                            <a class="nav active" href="planeacion_docente">Docente</a>
                                        </li>
                                        <li class="nav-item " id="modulo402">
                                            <a class="nav active" href="actividad_estudiantil">Alumno</a>
                                        </li>
                                    </ul>
                                    <li class="d-none segmentos" data-toggle="collapse" href="#collapseSegmentos" role="button" aria-expanded="false" aria-controls="collapseExample">360</li>
                                    <ul class="menu-segmentos collapse" id="collapseSegmentos">
                                        <li class="segmentos" id="persona"> <a href="#">Persona</a> </li>
                                        <li class="segmentos" id="empresa"> <a href="#">Empresa</a> </li>
                                        <li class="segmentos" id="corporativo"> <a href="#">Corporativo</a> </li>
                                        <li class="segmentos" id="gobierno"> <a href="#">Gobierno</a> </li>
                                    </ul>
                                    <li> <a target="_blank" href="https://store360.ml/app">Store 360</a> </li>
                                    <li class="d-none"> <a id="canje-invitacion-existente" target="_blank" href="javascript:void(0)">Tengo un codigo de canje</a> </li>
                                    <!--li> <a href="#">Ayuda</a> </li-->
                                </ul>
                                <div class="col-12">
                                    <div class="row segPerf" id="perfilP">
                                        <div class="col-2 logoPrfl" id="logoPrflP">
                                        </div>
                                        <div class="col-10 segTxt">
                                            <h5>Casa</h5>
                                            <p>Sesión Personal</p>
                                        </div>
                                    </div>
                                    <div class="row segPerf" id="perfilE">
                                        <div class="col-2 logoPrfl" id="logoPrflE">
                                        </div>
                                        <div class="col-10 segTxt">
                                            <h5 id="nomPrflE"></h5>
                                            <p>Sesión Empresa</p>
                                        </div>
                                    </div>
                                    <div class="row segPerf" id="perfilC">
                                        <div class="col-2 logoPrfl" id="logoPrflC">
                                        </div>
                                        <div class="col-10 segTxt">
                                            <h5 id="nomPrflC"></h5>
                                            <p>Sesión Corporativo</p>
                                        </div>
                                    </div>
                                    <div class="row segPerf" id="perfilG">
                                        <div class="col-2 logoPrfl" id="logoPrflG">
                                        </div>
                                        <div class="col-10 segTxt">
                                            <h5 id="nomPrflG"></h5>
                                            <p>Sesión Gobierno</p>
                                        </div>
                                    </div>
                                    <div class="row segInv" id="perfilInvitacionP"> 
                                        <div class="col-9 segTxt">
                                            <h5>Persona</h5>
                                            <hr>
                                            <p>Conviertete en un Experto 360.</p>
                                            <a class="" href="">Activar Invitación 360</a>
                                        </div>
                                    </div>
                                    <div class="row segInv" id="perfilInvitacionE"> 
                                        <div class="col-9 segTxt">
                                            <h5>Empresa</h5>
                                            <hr>
                                            <p>Tu negocio y grupo de trabajo, siempre en linea.</p>
                                            <a class="" href="">Activar Invitación 360</a>
                                        </div>
                                    </div>
                                    <div class="row segInv" id="perfilInvitacionC"> 
                                        <div class="col-9 segTxt">
                                            <h5>Corporativo</h5>
                                            <hr>
                                            <p>Organice las tareas diarias de su red de sucursales, unidades y personal.</p>
                                            <a class="" href="">Activar Invitación 360</a>
                                        </div>
                                    </div>
                                    <div class="row segInv" id="perfilInvitacionG"> 
                                        <div class="col-9 segTxt">
                                            <h5>Gobierno</h5>
                                            <hr>
                                            <p>El espacio único de comunicación, atención y cuidado de personas y espacios públicos.</p>
                                            <a class="" href="">Activar Invitación 360</a>
                                        </div>
                                    </div>
                                </div>
                            </nav>
                        </div>

                        <div id="menu_cerrar_sesion" class="btn-sesion d-none">
                            <a href="#" class="boton-sesion">Cerrar Sesión</a>
                        </div>
                    </address>
                </div>
            </ul>
        </div>
    </nav>
</div>

<div id="contenedor-canje-codigo-existente" class="contenedor-canje-codigo d-none"></div>

<spring:url value="${pathRecursos}/js/base/header.js" var="headerJS" />
<script src="${headerJS}"></script>
<spring:url value="${pathRecursos}/js/base/btns-toggle.js" var="btnstoggleJS" />
<script src="${btnstoggleJS}"></script>
<spring:url value="${pathRecursos}/js/base/onboarding.js" var="onboardingJS" />
<script src="${onboardingJS}"></script>

<script>
    $("#canje-invitacion-existente").click(function (e) {
        e.preventDefault();
        $.scrollify.disable();

        Swal.fire({
            icon: 'question',
            html:
                    '<form autocomplete="off" id="canjea-invitacion-existente">' +
                    '<div class="form-group">' +
                    '<label style="margin-bottom: 20px;" for="input-codigo-canje-nuevo">Ingresa tu código</label><br>' +
                    '<input type="text" class="form-control text-center" id="input-codigo-canje-nuevo">' +
                    '<div class="invalid-feedback">Formato incorrecto!</div>' +
                    '<div class="valid-feedback">Formato correcto!!</div>' +
                    '</div>' +
                    '<button type="submit" class="btn btn-block btn-primary">Canjear código</button>' +
                    '</form>',

            showConfirmButton: false,
            showCancelButton: true,
            cancelButtonText: 'Cancelar',
            willClose: () => {
                $.scrollify.enable();
            }
        }).then(() => {
            $.scrollify.enable();
        });


        initCodigoCanje("canjea-invitacion-existente", "contenedor-canje-codigo-existente", 1, false);

    });
</script>

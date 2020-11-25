<%-- 
    Document   : menu 
    Created on : 30 Dic 2019, 11:14:42
    Author     : Moisés Juárez
--%>
<div class="menuModalIcon segmento">
    <nav role="navigation" id="hamburgerMenu">
        <div id="menuToggle">
            <input type="checkbox" id="cbmenuToggle"/>
            <span></span>
            <span></span>
            <span></span>


            <ul id="menu">
                <div id="menuContainer">
                    <nav class="navbar navbar-lg navbar-md navbar-sm p-0">
                        <div class="collapse navbar-collapse" id="navbarColor01" style="display: flex;">

                            <ul class="navbar-nav" style="width: 100%;">


                                <li class="nav-item pl-0" id="modulo22">
                                    <a class="amenu nav active " href="EstadisticosCCB">Resumen Estadístico</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo10">
                                    <a class="amenu nav active " href="CCB">Referencia de Pacientes COVID-19</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo6">
                                    <a class="amenu nav active " href="Institucion" >Referencia de Pacientes COVID-19</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo7">
                                    <a class="amenu nav active " href="SolicitudTraslado" target="_blank">Solicitud a U.T.C-19</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo8">
                                    <a class="amenu nav active " href="CRUM" >Traslado de Referencias COVID-19</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo9">
                                    <a class="amenu nav active " href="SUCRE" >Traslado de Referencias COVID-19</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo5">
                                    <a class="amenu nav active " href="ReporteHospital" >Reporte Hospital</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo11">
                                    <a class="amenu nav active " href="vinculacion_familiar" >Vinculación Familiar</a>
                                </li>

                                <li class="nav-item pl-0 d-none" id="modulo12">
                                    <a class="amenu nav active " href="MonitoreoRemotoPersonalCCB">Monitoreo Personal CCB</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo13">
                                    <a class="amenu nav active " href="MonitoreoRemoto" >Monitoreo Pacientes dados de Alta</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo4">
                                    <a class="amenu nav active " href="Administrador" >Recepción de llamadas</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo14">
                                    <a class="amenu nav active " href="Notificaciones_TestCOVID19"  >Reportes COVID-19</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo3">
                                    <a class="amenu nav active " href="Empresa" >Monitoreo de Unidades</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo15">
                                    <a class="amenu nav active " href="BuscarReporte" >Búsqueda de Reporte</a>
                                </li>


                                <li class="nav-item pl-0" id="modulo16" >
                                    <a class="amenu nav active " href="#" >PDF - Usuarios Registrados</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo17" >
                                    <a class="amenu nav active " href="#" >PDF - Usuarios Activos</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo1">
                                    <a class="amenu nav active " href="Registro">Registrar un Usuario</a>
                                </li>

                                <li class="nav-item pl-0" id="modulo18">
                                    <a class="amenu nav active" href="ReporteElemento">
                                        Reporte de Incidente Operativo
                                        <label  id="TamBackup" style="padding: 4px 5px 4px 5px;margin: 0;background: red;border-radius: 15px;font: bold 12px Arial;right: 5%;position: absolute; display: none;"></label>
                                    </a>
                                    <div id="divTamBackup">
                                        <div id="test"></div>
                                    </div>
                                </li>

                                <li class="nav-item pl-0" id="modulo2">
                                    <a class="amenu nav active " href="Administracion">Administración de Usuarios</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo19">
                                    <a class="amenu nav active " href="TestCovidCCB">Test Covid Personal U.T.C.</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo20">
                                    <a class="amenu nav active " href="TestCovidPacientesCCB">Test Covid Pacientes U.T.C.</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo21">
                                    <a class="amenu nav active " href="RegistroPaciente">Registro de Pacientes</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo403">
                                    <a class="amenu nav active " href="agregar_perfil">Nuevo Perfil</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo400">
                                    <a class="amenu nav active " href="administracion_directiva">Dirección</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo401">
                                    <a class="amenu nav active " href="planeacion_docente">Docente</a>
                                </li>
                                <li class="nav-item pl-0" id="modulo402">
                                    <a class="amenu nav active " href="actividad_estudiantil">Alumno</a>
                                </li>

                            </ul>



                            <ul class="navbar-nav d-none" style="width: 100%;">


                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="CCB" id="menuCCB" >Referencia de Pacientes COVID-19</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="Institucion" id="menuInstitucion" >Referencia de Pacientes COVID-19</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="SolicitudTraslado" id="menuSolicitudTraslado" target="_blank">Solicitud a U.T.C-19</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="CRUM" id="menuCRUM" >Traslado de Referencias COVID-19</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="SUCRE" id="menuSUCRE" >Traslado de Referencias COVID-19</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="ReporteHospital" id="menuReporteHospital" >Reporte Hospital</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="vinculacion_familiar" id="menuvinculacion_familiar" >Vinculación Familiar</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="MonitoreoRemoto" id="menuvinculacion_pacientes" >Monitoreo Pacientes dados de Alta</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="Administrador" id="menuAdmin" >Recepción de llamadas</a>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="Notificaciones_TestCOVID19" id="menutestcovid" >Reportes COVID-19</a>
                                </li>

                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="Empresa" id="menuEmpresa" >Monitoreo de Unidades</a>
                                </li>


                                <li class="nav-item pl-0" style="display: none;">
                                    <a class="amenu nav active " href="BuscarReporte" id="menuBuscarReporte" >Búsqueda de Reporte</a>
                                </li>


                                <li class="nav-item pl-0" id="padronregistroLI">
                                    <a class="amenu nav active " href="#" id="padronregistro">PDF - Usuarios Registrados</a>
                                </li>

                                <li class="nav-item pl-0" id="usuariosActivosLI" >
                                    <a class="amenu nav active " href="#" id="usuariosActivos">PDF - Usuarios Activos</a>
                                </li>

                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="Registro" id="menuRegistro">Registrar un Usuario</a>
                                </li>

                                <li class="nav-item pl-0" style="display: none;">
                                    <a class="amenu nav active" href="ReporteElemento" id="menuRElemento">
                                        Reporte de Incidente Operativo
                                        <label  id="TamBackup" style="padding: 4px 5px 4px 5px;margin: 0;background: red;border-radius: 15px;font: bold 12px Arial;right: 5%;position: absolute; display: none;"></label>
                                    </a>
                                    <div id="divTamBackup" style="display:none;">
                                        <div id="test"></div>

                                    </div>
                                </li>
                                <li class="nav-item pl-0">
                                    <a class="amenu nav active " href="Administracion" id="adminusers">Administración de Usuarios</a>
                                </li>
                                <!--                                    <li class="nav-item pl-0">
                                                                        <a class="amenu nav active " href="RegistroPaciente" id="menuRegistroPaciente">Registrar Paciente</a>
                                                                    </li>-->
                            </ul>

                        </div>
                    </nav>
                </div>
                <div id="textologin" class="pt-2">
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
                <div id="closeSession">
                    <button type="button" id="botonsesion" > Cerrar Sesión</button>
                </div>    
            </ul>
        </div>
    </nav>
</div>
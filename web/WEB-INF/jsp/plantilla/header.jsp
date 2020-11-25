<%-- 
    Document   : header
    Created on : 30 Dic 2019, 11:14:42
    Author     : Moisés Juárez
--%>


<spring:url value="${pathRecursos}/css/base/global-icons.css" var="globaliconsCSS" />
<link href="${globaliconsCSS}" rel="stylesheet" />
<spring:url value="${pathRecursos}/css/base/layoutBase.css" var="layoutBaseCSS" />
<link href="${layoutBaseCSS}" rel="stylesheet" />
<spring:url value="${pathRecursos}/css/base/header.css" var="headerCSS" />
<link href="${headerCSS}" rel="stylesheet" />
<spring:url value="${pathRecursos}/css/base/onboarding.css" var="onboardingCSS" />
<link href="${onboardingCSS}" rel="stylesheet"/>

<!-- //////////////  HIDDEN VALUES   //////////// -->   
<input type="hidden" id="config" value="${config}">
<input type="hidden" id="AllUsrs" value="" >
<input type="hidden" class="NameAdministrador" name="NameAdministrador" id="NameAdministrador" value=""  onclick="window.location.href = 'Empresa'" readonly>
<input type="hidden" name="IdAdministrador" id="IdAdministrador" value="" >
<input type="hidden" id="data" value="${data}">
<input type="hidden" id="FireBaseAuthorization" value="${FireBaseAuthorization}" >
<input type="hidden" id="apikey" value="${apikey}">
<input type="hidden" id="sesion" value="${session}">
<input type="hidden" id="token" value="${token}">
<input type="hidden" id="llamada" value="${idLlamada}">
<input type="hidden" id="idStream" value="${idStream}">
<input type="hidden" id="origen" value="${origen}">
<input type="hidden" id="modo" value="${modo}">
<input type="hidden" id="modoLlamada" value="${modoLlamada}">
<input type="hidden" id="idelementos" value="${elementos}" >
<input type="hidden" id="elementos" value="" >
<input type="hidden" id="iconUsr" value="" >
<!-- ////////////////   HEADER   //////////////// -->
<div class="Paneblock" id="block"></div>
<header class="segmento">
    <div class="cabezera header1">
        <a href="/plataforma360/home" id="logo360"></a>
        <div class="col-12 row m-0 p-0 headEmpresa">
            <div class="col-6 txtlogo" id="txtlogoE">
                <h6 id="nomEmpresa"></h6>
            </div>
            <div class="col-6 imgEmpresa" id="logoEmpresa">
            </div>
            <div class="col-6 img360" id="logoN360">
            </div>
        </div>
    </div>
    <div class="cabezera header2">
        <!--button id="btn-menuSeg" class="btn-menuSeg">Menu</button-->
        <ul id="menuResponsivo" class="menu menuResponsivo">
            <!--li id="submenu" class="submenu d-none"><a href="#">Mis Servicios</a></li-->
            <li class="segmentosV" id="hdrinicio"><a href="#">360</a></li>
            <li class="segmentos" id="hdrpersona"> <a href="#">Persona</a></li>
            <li class="segmentos" id="hdrempresa"> <a href="#">Empresa</a></li>
            <li class="segmentos" id="hdrcorporativo"> <a href="#">Corporativo</a></li>
            <li class="segmentos" id="hdrgobierno"> <a href="#">Gobierno</a> </li>
            <li class="segmentosV" id="hdrstore"><a target="_blank" href="https://store360.ml/app">Store 360</a></li>
        </ul>
        <div>
            <div class=" m-0 proyect_titles" style="">
                <div class="proyect_title_top" id="T1Header"></div>
                <div class="proyect_title_bot" id="T2Header"></div>
            </div>
        </div>
    </div>
    <div class="col h-100 header3 unlogged">
        <div class="containerHeader4">
            <span class="fas fa-th iconServicios" id="iconServ"></span>
            <!--a class="btn btn-danger btn-registrate" id="registro">Registrate</a-->
            <a class="btn btn-danger btn-registrate" id="registro" href="/plataforma360/registro">Registrate</a>
            <!--a class="btn btn-danger btn-ingresa" id="ingreso">Ingresa</a-->
            <a class="btn btn-danger btn-ingresa" id="ingreso" href="/plataforma360/login">Ingresa</a>
            <span id="NombreDependencia" align="right"></span>
            <span id="user"></span>
            <span id="tipoSegmento"></span>
            <!--br-->
            <span id="t4" style="display: none;"></span>
        </div>
    </div>    
    <!--a class="btn btn-danger btn-ingresa" id="ingreso" href="/plataforma360/login">Ingresa</a-->
</header>
<div id="menuServicios" class="menuServicios segmento">
    <ul id="servicios" class="servicios d-none">
        <!--li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/persona/40/Hogar_Conectado-Persona_40X40.png"></div>
                <label for="">EJEMPLO DE SERVICIOS</label>
            </a>
        </li-->
    </ul>
    <ul id="ulhdrpersona" class="servicios d-none">
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/persona/40/Hogar_Conectado-Persona_40X40.png"></div>
                <label for="">Hogar Conectado</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/persona/40/Administracion-Persona_40X40.png"></div>
                <label for="">Administración</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/persona/40/Salud360-Persona_40X40.png"></div>
                <label for="">Salud 360</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/persona/40/SOS_CIudadano-Persona_40X40.png"></div>
                <label for="">SOS Ciudadano</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/persona/40/Expertos360-Persona_40X40.png"></div>
                <label for="">Expertos 360</label>
            </a>
        </li>
    </ul>
    <ul id="ulhdrempresa" class="servicios d-none">
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/empresa/40/Empresa360-Empresa_40X40.png"></div>
                <label for="">Empresa 360</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/empresa/40/Administracion-Empresa_40X40.png"></div>
                <label for="">Administración</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/empresa/40/Expertos360-Empresa_40X40.png"></div>
                <label for="">Expertos 360</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/empresa/40/SOS_CIudadano-Empresa_40X40.png"></div>
                <label for="">SOS Ciudadano</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/empresa/40/Videovigilancia-Empresa_40X40.png"></div>
                <label for="">Videovigilancia</label>
            </a>
        </li>
    </ul>
    <ul id="ulhdrcorporativo" class="servicios d-none">
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/corporativo/40/Empresa360-Corporativo_40X40.png"></div>
                <label for="">Empresa 360</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/corporativo/40/Administracion-Corporativo_40X40.png"></div>
                <label for="">Administracion</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/corporativo/40/Expertos360-Corporativo_40X40.png"></div>
                <label for="">Expertos 360</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div class="text-center"><img src="${pathRecursos}/images/iconosHeader/corporativo/40/SOS_CIudadano-Corporativo_40X40.png"></div>
                <label for="">SOS Ciudadano</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/corporativo/40/Videovigilancia-Corporativo_40X40.png"></div>
                <label for="">Videovigilancia</label>
            </a>
        </li>
    </ul>
    <ul id="ulhdrgobierno" class="servicios d-none">
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/gobierno/40/Videovigilancia-Gobierno_40X40.png"></div>
                <label for="">Videovigilancia</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/gobierno/40/Analitico_de_Video-Gobierno_40X40.png"></div>
                <label for="">Analítico de Video</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/gobierno/40/Consulta_Visualizacion-Datos-Gobierno_40X40.png"></div>
                <label for="">Consulta y Visualización de Datos</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/gobierno/40/Expertos360-Gobierno_40X40.png"></div>
                <label for="">Expertos 360</label>
            </a>
        </li>
        <li>
            <a href="#">
                <div><img src="${pathRecursos}/images/iconosHeader/gobierno/40/Monitoreo_Unidades-Gobierno_40X40.png"></div>
                <label for="">Monitoreo de Unidades y Elementos</label>
            </a>
        </li>
    </ul>
</div>
                
  


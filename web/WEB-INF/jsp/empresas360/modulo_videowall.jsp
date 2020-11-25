<%-- 
    Document   : modulo_comunicacion
    Created on : 6/11/2020, 06:02:00 PM
    Author     : moises
--%>

<div class="row col-12 m-0 p-0 h-100" id="base_modulo_VideoWallEmpleados">
    <div class="user_datails d-none">
        <p>
            <span class="user_details_name" id="user_details_name"></span>
            <i class="fas fa-window-close"></i>
        </p>
        <div class="user_details_img" id="user_details_img"></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Area: <span id="user_details_area">Backend</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Puesto: <span id="user_details_puesto">Desarrollador</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Horario: <span id="user_details_horario">9:00 - 19:00</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Entrada: <span id="user_details_entrada">8:55</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Promedio entrada: <span id="user_details_pentrada">8:45</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Promedio salida: <span id="user_details_psalida">20:55</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Desempe�o: <span id="user_details_desempeno">120%</span></div>
        <div class="user_details_list"><i class="fas fa-circle px-1"></i>Retardos: <span id="user_details_retardos">4</span></div>
    </div>


    <div class="empleados_stats">
        <div class="stats_box">
            <i class="fas fa-user-friends w-50"></i> 
            <div class="row col-6 m-0 p-0">
                <div class="col-12 m-0 p-0 details_title">Total de Empleados</div>
                <div class="col-12 m-0 p-0 details total_empleados"></div>
            </div>
        </div>
        <div class="stats_box">
            <i class="fas fa-user-friends w-50"></i> 
            <div class="row col-6 m-0 p-0">
                <div class="col-12 m-0 p-0 details_title">Empleados en Jornada Laboral</div>
                <div class="col-12 m-0 p-0 details empleados_laborando"></div>
            </div>
        </div>
        <div class="stats_box">
            <i class="fas fa-user-friends w-50"></i> 
            <div class="row col-6 m-0 p-0">
                <div class="col-12 m-0 p-0 details_title">Empleados Fuera de Jornada Laboral</div>
                <div class="col-12 m-0 p-0 details empleados_descansando"></div>
            </div>
        </div>
        <div class="stats_box">
            <i class="fas fa-user-friends w-50"></i> 
            <div class="row col-6 m-0 p-0">
                <div class="col-12 m-0 p-0 details_title">Empleados Conectados</div>
                <div class="col-12 m-0 p-0 details empleados_conectados"></div>
            </div>
        </div>

    </div>
    <div class="empleados_list">
<!--        <div class="user_box">
            <div class="details"></div>
            <div class="details_title">Usuarios Conectados</div>
        </div>-->
    </div>

    <div class="row m-0 col-12 p-0 GRIDcontainer" id="videos_empleados">

    </div>

</div>

<spring:url value="${pathRecursos}/css/Empresa/modulo_videowall.css" var="modulo_videowallCSS" />
<spring:url value="${pathRecursos}/js/Empresa/modulo_videowall.js" var="modulo_videowallJS" />
<link href="${modulo_videowallCSS}" rel="stylesheet"/>
<script src="${modulo_videowallJS}" ></script>

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
    <aside>
        <div class="row col-12 m-0 p-0" id="toggle">Administración Directiva</div>
        <div id="sidebar" class="p-2">

        </div>
    </aside>
    <section>
        <div class="h-100 row col-12 m-0 p-2" id="contenidoSection">



        </div>

        <div class="row col-12 m-0 p-2 pt-3 d-none" id="base_modulo_AsignacióndeGrupos">
            <h3>Crear Grupos</h3>

            <div class="col-12 p-0">
                <form id="form_crear_grupo">
                    <div class="form-group row m-0 p-2">
                        <label for="nombre" class="col-sm-2 col-md-1 col-form-label d-flex align-items-center">Grupo:</label>
                        <div class="col-sm-10 col-md-4">
                            <input type="text" class="form-control-plaintext input d-flex align-items-center" id="nombre_grupo" placeholder="Nombre del grupo" value="">
                        </div>
                        <div class="col-sm-12 col-md-5" id="listado_profesores">
                            <multiselect 
                                placeholder="Selecciona los profesores que pertenecen a este Grupo"
                                v-model="value" 
                                :options="options"
                                track-by="nombre"
                                :custom-label="customLabel" 
                                :select-label="''" 
                                :selected-Label="''"
                                :deselect-Label="'Remover'"
                                :multiple="true"
                                @input="onChange" 
                                @close="onTouch" 
                                @select="onSelect">

                            </multiselect>


                        </div
                    </div>
                    <div class="col-sm-4 col-md-2 d-flex justify-content-center">
                        <button type="submit" class="btn btn-danger mb-2">Registrar</button>
                    </div>
            </div>
            </form>
            <hr>
        </div>

        <div class="col-12 p-0">
            <h3>Asignacion de Grupos</h3>
            <div class="row m-0 col-12 p-0" id="grupos_escuela">
                <!--                <div class="col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card">
                                    <h5 class="card-header">1-A</h5>
                                    <div class="card-body">
                                        <h6>Código: 8-4DFG5XC</h6>
                                        <h5 class="card-title">Profesores Asignados:</h5>
                                        <ul>
                                            <li>Profesora Karla Mejia Gomez</li>
                                            <li>Profesor Juan Carlos Cabrera Lopez</li>
                                        </ul>
                
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card">
                                    <h5 class="card-header">1-A</h5>
                                    <div class="card-body">
                                        <h6>Código: 3-4DFG5XC</h6>
                                        <h5 class="card-title">Profesores Asignados:</h5>
                                        <ul>
                                            <li>Profesora Karla Mejia Gomez</li>
                                            <li>Profesor Juan Carlos Cabrera Lopez</li>
                                        </ul>
                
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card">
                                    <h5 class="card-header">1-A</h5>
                                    <div class="card-body">
                                        <h6>Código: 7-4DFG5XC</h6>
                                        <h5 class="card-title">Profesores Asignados:</h5>
                                        <ul>
                                            <li>Profesora Karla Mejia Gomez</li>
                                            <li>Profesor Juan Carlos Cabrera Lopez</li>
                                        </ul>
                
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card">
                                    <h5 class="card-header">1-A</h5>
                                    <div class="card-body">
                                        <h6>Código: 9-4DFG5XC</h6>
                                        <h5 class="card-title">Profesores Asignados:</h5>
                                        <ul>
                                            <li>Profesora Karla Mejia Gomez</li>
                                            <li>Profesor Juan Carlos Cabrera Lopez</li>
                                        </ul>
                
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card">
                                    <h5 class="card-header">1-A</h5>
                                    <div class="card-body">
                                        <h6>Código: 4-4DFG5XC</h6>
                                        <h5 class="card-title">Profesores Asignados:</h5>
                                        <ul>
                                            <li>Profesora Karla Mejia Gomez</li>
                                            <li>Profesor Juan Carlos Cabrera Lopez</li>
                                        </ul>
                
                                    </div>
                                </div>
                                <div class="col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card">
                                    <h5 class="card-header">1-A</h5>
                                    <div class="card-body">
                                        <h6>Código: 7-4DFG5XC</h6>
                                        <h5 class="card-title">Profesores Asignados:</h5>
                                        <ul>
                                            <li>Profesora Karla Mejia Gomez</li>
                                            <li>Profesor Juan Carlos Cabrera Lopez</li>
                                        </ul>
                
                                    </div>
                                </div>-->


            </div>
        </div>
    </div>

    <div class="row col-12 m-0 p-2 pt-3" id="base_modulo_RegistrarmiInstitución">
        <h3>Registrar mi Institucón</h3>
        <div class="col-12 p-0">
            <form id="form_registro_institucion">
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Nombre Institución:</label>
                    <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="nombre_institucion" placeholder="Institución" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Director:</label>
                    <div class="col-sm-4"><input type="text" class="form-control-plaintext input" id="nombre_director" placeholder="Nombre" /></div>
                    <div class="col-sm-3"><input type="text" class="form-control-plaintext input" id="apellido_paterno_director" placeholder="Apellido Paterno" /></div>
                    <div class="col-sm-3"><input type="text" class="form-control-plaintext input" id="apellido_materno_director" placeholder="Apellido Materno" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Teléfono:</label>
                    <div class="col-sm-10"><input type="number" class="form-control-plaintext input" id="telefono_institucion" placeholder="Teléfono" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Correo Electrónico:</label>
                    <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="correo_institucion" placeholder="Correo Electrónico" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Turno:</label>
                    <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="turno" placeholder="Turno" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Zona:</label>
                    <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="zona" placeholder="Zona" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Clave:</label>
                    <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="clave" placeholder="Clave" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">CCT:</label>
                    <div class="col-sm-10"><input type="text" class="form-control-plaintext input" id="cct" placeholder="Clave de Centro de Trabajo" /></div>
                </div>
                <div class="form-group row m-0 p-2">
                    <label class="col-sm-2 col-form-label">Dirección:</label>
                    <div class="col-sm-10 col-md-8 col-lg-9"><input type="text" class="form-control-plaintext input" id="direccion_institucion" placeholder="Dirección" /></div>
                    <input type="button" class="col-sm-12 col-md-2 col-lg-1 btn btn-danger" value="Buscar" id="buscar_direccion">
                </div>
                <div class="form-group row m-0 p-2"></div>
                <div class="col-sm-12 col-md-12 col-lg-6" style="height: 250px;">
                    <div class="col-12 w-100 h-100 rounded" id="map2"></div>
                </div>
                <input type="hidden" id="lat" />
                <input type="hidden" id="lng" />
            </form>
        </div>
    </div>

    <div class="row col-12 m-0 p-0 h-100" id="base_modulo_InstituciónAcadémica">
        <div class="col-sm-12 col-md-12 col-lg-6 d-flex align-items-center">
            <div class="datos_institucion">
                <h2 id="InstituciónAcadémica_codigo">##-########</h2>
                <h1 id="InstituciónAcadémica_nombre">Institución Demo 10</h1>
                <h5 id="InstituciónAcadémica_direccion">Zurich 221 ampliacion granada</h5>
                <hr />
                <h4><strong>Director: </strong><span id="InstituciónAcadémica_nombre_director"></span></span></h4>
                <h4><strong>Télefono: </strong><span id="InstituciónAcadémica_telefono"></span></h4>
                <h4><strong>Correo electrónico: </strong><span id="InstituciónAcadémica_correo"></span></h4>
                <h4><strong>Turno: </strong><span id="InstituciónAcadémica_turno"></span></h4>
                <h4><strong>Zona: </strong><span id="InstituciónAcadémica_zona"></span></h4>
                <h4><strong>Clave: </strong><span id="InstituciónAcadémica_clave"></span></h4>
                <h4><strong>CCT: </strong><span id="InstituciónAcadémica_cct"></span></h4>
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-6"><div class="col-12 w-100 h-100 rounded" id="map"></div></div>
    </div>




</section>

<%@include file="../plantilla/footer.jsp" %>
<%@include file="../plantilla/callhead.jsp" %>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAe5gzNGneaWmWLzmZs6bFKNlwdCTr0Odk&callback=initMap&callback=initMap">
</script>
<style>

    .menu_sidebar{
        width: 100%;
        height: 50px;
        border: solid 1px #495057;
        border-radius: 3px;
        margin: 5px 0;
        cursor: pointer;
        text-align: center;
        background: #343a40;
        align-items: center;
        padding: 0 20px;
        font: bold 1.12rem Arial;
    }
    .menu_sidebar:hover, .menu_selected{
        background: #0097a9;
    }
    .modulo_section{
        width: 100%;
        height: 100%;
        color: #343a40;
    }
    section{
        background: #f5f5f5;
    }
    #toggle{
        background: #f5f5f5;
    }
    footer{
        background-color: #40474f;
    }
    .datos_institucion{
        padding-bottom: 25%;
        width: 100%;
    }
    h1 {
        font: bold 2.1rem Arial;
        margin: 5px 0;
    }
    h2{
        font-size: 2rem;
        color: #0097a9;
        font: bold 2rem Ariel;
    }
    h5{
        font: normal 01rem Arial;
        margin: 0;
    }
    h6 {
        color: #0097a9;
        font: bold 1rem Ariel;
    }
    h4 {
        font: normal 1.4rem Arial;
        padding: 2px 10px;
    }
    h4 > strong{

        /* width: 25%; */

        font: bold 1.1rem Roboto;

        display: inline-block;
    }
    h4 > span{

        font: normal 1.3rem Roboto;

        padding-left: 10px;
    }
    .img_perfil{
        width: 150px; 
        height: 150px; 
        background: #6c757d; 
        border-radius: 50%;
    }
    .col-form-label {
        padding-top: calc(0.375rem + 1px);
        padding-bottom: calc(0.375rem + 1px);
        margin-bottom: 0;
        font-size: inherit;
        line-height: 1.5;
        font: bold 1.1rem Arial;
    }

    .input {
        overflow: visible;
        font: 1.1rem Arial;
        border: solid 1px #dee2e6;
        margin: 5px 0;
        padding: 5px 15px;
        border-width: 1px;
    }
    .multiselect {
        border-radius: 0px;
        border-bottom: 2px solid #d3d3d3;
        width: 96%;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 10px;
    }
    .multiselect__single {
        background: none;
        color: #303030;
        font: bold 12px arial;
    }
    .card-header {
        background: #0097a9;
        font: bold 1.1rem Arial;
        color: white;
        text-align: center;
    }
    .multiselect__input {
        background: none;
        color: #343a40;
        font: bold 12px arial;
    }
</style>
<script>
    console.log("Bingoooooo");

//    agregar_menu("Registrar mi Institución");

    agregar_menu("Perfil");
//    perfil_docente("Perfil");
//    listado_docente("Perfil");
    agregar_menu("Institución Académica");
    datos_institucion("Institución Académica");
    agregar_menu("Plantilla Docente");
    registro_plantilla_laboral("Plantilla Docente");
//    agregar_menu("Plantilla Académica");
    agregar_menu("Asignación de Grupos");
//    agregar_menu("Asignación de Horarios");

    var perfiles_personal = null;
    consulta_listado_profesores();
    function consulta_listado_profesores() {
        RequestGET("/API/GET/listado_personal/" + JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_usuario + "/" + JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio).then(function (response) {
            perfiles_personal = response;
            listado_docente("Perfil");
            colocar_grupos();
            listado_profesores();
        });
    }


    var profesores_grupo = new Array();
    function listado_profesores() {
        let vue = new Vue({
            components: {
                Multiselect: window.VueMultiselect.default
            },
            data: {
                value: [],
                options: perfiles_personal
            },
            methods: {
                customLabel(option) {
                    return  option.id360.toString().padStart(4, "0") + " " + option.nombre + " " + option.apellido_paterno + " " + option.apellido_materno;
                },
                onChange(value) {
                    console.log("change");
                    console.log(value);
                    profesores_grupo = new Array();
                    for (var i = 0; i < value.length; i++) {
                        let json = {
                            "id360": value[i].id360,
                            "nombre": value[i].nombre + " " + value[i].apellido_paterno + " " + value[i].apellido_materno
                        };
                        profesores_grupo.push(json);
                    }
//                    profesores_grupo = value;
                },
                onSelect(op) {

                    console.log(op);

                },
                onTouch() {
                    console.log("Open");

                }
            }
        }).$mount('#listado_profesores');
    }

    $("#form_crear_grupo").submit(function (e) {
        e.preventDefault();
        if ($("#nombre_grupo").val() !== "") {
            //crear el grupo
            let json = {
                id_institucion_academica: JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio,
                nombre: $("#nombre_grupo").val(),
                profesores_asignados: profesores_grupo
            };
            console.log(json);
            RequestPOST("/API/escuela360/registro_grupo", json).then(function (response) {
                Swal.fire({
                    title: 'Aviso',
                    text: response.mensaje
                }).then(function () {
                    if (response.success) {
                        tarjeta_grupo(response);
                    }
                });
            });
        } else {
            Swal.fire({
                title: 'Alto',
                text: "Debe de asignar un nombre para poder crear un grupo."
            });
        }

    });

    function tarjeta_grupo(info) {
        console.log(info);
        let div_card = document.createElement("div");
        div_card.className = 'col-sm-12 col-md-6 col-xl-4 p-0 mb-3 card';
        let h5_header = document.createElement("h5");
        h5_header.className = 'card-header';
        h5_header.innerHTML = info.nombre;
        let div_body = document.createElement("div");
        div_body.className = 'card-body';
        let h6 = document.createElement("h6");
        h6.innerHTML = 'Código: ' + info.id_grupo + '-' + info.token;
        let h5_title = document.createElement("h5");
        h5_title.className = 'card-title';
        h5_title.innerHTML = 'Profesores Asignados:';
        let ul = document.createElement("ul");
        let profesores = info.profesores;
        for (var i = 0; i < profesores.length; i++) {
            let li = document.createElement("li");
            li.innerHTML = 'Profesor(a): ' + profesores[i].nombre;
            ul.appendChild(li);
        }
        div_body.appendChild(h6);
        div_body.appendChild(h5_title);
        div_body.appendChild(ul);
        div_card.appendChild(h5_header);
        div_card.appendChild(div_body);
        //Hacer el append a grupos_escuela

        $("#grupos_escuela").append(div_card);
    }


    let info_institucion = {};
    var info_grupo = false;
    function agregar_menu(nombre) {
        let div = document.createElement("div");
        div.className = "menu_sidebar d-flex";
        div.innerHTML = nombre;
        div.id = "menu_section_" + nombre.replace(/\s/g, "");
        $("#sidebar").append(div);

        let div2 = document.createElement("div");
        div2.className = "modulo_section d-none";
        div2.id = "modulo_section_" + nombre.replace(/\s/g, "");//quitale los espacios si llegara a tener 
//            div2.innerHTML = nombre;

        $("#contenidoSection").append(div2);

        div.addEventListener("click", function () {
            let modulos = $(".modulo_section");
            modulos.addClass("d-none");
            let menus = $(".menu_sidebar");
            menus.removeClass("menu_selected");
            $("#modulo_section_" + nombre.replace(/\s/g, "")).removeClass("d-none");
            $("#menu_section_" + nombre.replace(/\s/g, "")).addClass("menu_selected");
        });

        if ($("#base_modulo_" + nombre.replace(/\s/g, "")).length) {
            $("#base_modulo_" + nombre.replace(/\s/g, "")).removeClass("d-none");
//                div2.appendChild($("#base_modulo_"+ nombre.replace(/\s/g, "")));
            div2.appendChild(document.getElementById("base_modulo_" + nombre.replace(/\s/g, "")));
        }
    }

    function colocar_grupos() {
        RequestGET("/API/GET/listado_grupos/" + JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio).then(function (response) {
            console.log(response);
            let grupos = response;
            $.each(grupos, function (i) {
                let profesores = grupos[i].profesores;
                $.each(profesores, function (j) {
                    let id360 = profesores[j].id360;
                    $.each(perfiles_personal, function (k) {
                        if (perfiles_personal[k].id360 === id360) {
                            let json = {
                                id360: id360,
                                id_grupo: grupos[i].id,
                                id_usuario: id360,
                                nombre: perfiles_personal[k].nombre + " " + perfiles_personal[k].apellido_paterno + " " + perfiles_personal[k].apellido_materno
                            };
                            profesores[j] = json;
                            return true;
                        }
                    });
                });
                grupos[i].profesores = profesores;
                grupos[i].id_grupo = grupos[i].id;
                tarjeta_grupo(grupos[i]);
            });
        });
    }

    var marker = null;
    var datosInstitucion = {};
    function datos_institucion(nombre) {
        let json = {
            id: JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio
        };
        RequestPOST("/API/escuela/servicio/info_escuela", json).then(function (response) {
            console.log(response);
            if (response.success) {
                datosInstitucion = response;

                perfil_docente("Perfil");

                $("#" + nombre.replace(/\s/g, "") + "_codigo").text(JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio + "-" + response.token);
                $("#" + nombre.replace(/\s/g, "") + "_nombre").text(response.nombre);
                $("#" + nombre.replace(/\s/g, "") + "_direccion").text(response.direccion);
                $("#" + nombre.replace(/\s/g, "") + "_nombre_director").text(response.nombre_director + " " + response.apellido_paterno_director + " " + response.apellido_materno_director);
                $("#" + nombre.replace(/\s/g, "") + "_telefono").text(response.telefono);
                $("#" + nombre.replace(/\s/g, "") + "_correo").text(response.correo);
                $("#" + nombre.replace(/\s/g, "") + "_zona").text(response.zona);
                $("#" + nombre.replace(/\s/g, "") + "_clave").text(response.clave);
                $("#" + nombre.replace(/\s/g, "") + "_cct").text(response.cct);
                $("#" + nombre.replace(/\s/g, "") + "_turno").text(response.turno);

                if (marker !== null) {
                    marker.setMap(null);
                }
                map.setZoom(10);
                //validar coordenadas map
                let lat = parseFloat(response.lat);
                let lng = parseFloat(response.lng);
                if (!isNaN(lat) && !isNaN(lng)) {
                    if (marker !== null) {
                        marker.setMap(null);

                    }
                    var latlng = {lat: lat, lng: lng};
                    marker = new google.maps.Marker({
                        position: latlng,
                        map: map,
                        draggable: false,
                        animation: google.maps.Animation.DROP
                    });
                    console.log(latlng);
                    map.setCenter(latlng);
                    map.setZoom(18);
                }
            }
        });
    }

    function perfil_docente(nombre) {

        let session = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA));

        let div_contenedor = document.createElement("div");
        div_contenedor.className = "row col-12 m-0 p-0";
        let div1 = document.createElement("div");
        div1.className = 'col-12 p-0';
        let div2 = document.createElement('div');
        div2.className = 'row col-12 m-0 p-0';
        let div_img_perfil = document.createElement("div");
        div_img_perfil.className = 'col-sm-12 col-md-12 col-lg-3 p-0 d-flex align-items-center justify-content-center';
        let div_img_perfil2 = document.createElement('div');
        div_img_perfil2.className = 'img_perfil';
        let div_info_perfil = document.createElement('div');
        div_info_perfil.className = 'col-sm-12 col-md-10 col-lg-7 p-0 pt-3';
        let h2_Bienvenida = document.createElement("h2");
        h2_Bienvenida.innerHTML = 'Bienvenida';
        let h2_nombre = document.createElement('h2');
        h2_nombre.innerHTML = 'Profesor(a) ' + session.nombre + " " + session.apellido_p + " " + session.apellido_m;
        let br = document.createElement("br");
        let h4_escuela = document.createElement("h4");
        h4_escuela.className = 'p-0';
        let strong_escuela = document.createElement("strong");
        strong_escuela.innerHTML = "Escuela: ";
        h4_escuela.appendChild(strong_escuela);
        h4_escuela.innerHTML += datosInstitucion.success ? datosInstitucion.nombre : "";
        let h4_tel = document.createElement("h4");
        h4_tel.className = 'p-0';
        let strong_tel = document.createElement("strong");
        strong_tel.innerHTML = "Télefono: ";
        h4_tel.appendChild(strong_tel);
        h4_tel.innerHTML += datosInstitucion.success ? datosInstitucion.telefono : "";
        let h4_mail = document.createElement('h4');
        h4_mail.className = 'p-0';
        let strong_mail = document.createElement("strong");
        strong_mail.innerHTML = 'Correo electrónico: ';
        h4_mail.appendChild(strong_mail);
        h4_mail.innerHTML += datosInstitucion.success ? datosInstitucion.correo : "";
        let div_editar = document.createElement("div");
        div_editar.className = 'col-sm-12 col-md-2 p-0';
        let boton_edit = document.createElement("button");
        boton_edit.type = 'button';
        boton_edit.className = 'btn btn-secondary';
        boton_edit.id = "editar_perfil";
        let i_edit = document.createElement("i");
        i_edit.className = 'fas fa-pencil-alt';
        i_edit.style = 'margin: 0 10px';
        boton_edit.appendChild(i_edit);
        boton_edit.innerHTML = "Editar";

        div_img_perfil.appendChild(div_img_perfil2);
        div_info_perfil.appendChild(h2_Bienvenida);
        div_info_perfil.appendChild(h2_nombre);
        div_info_perfil.appendChild(br);
        div_info_perfil.appendChild(br);
        div_info_perfil.appendChild(h4_escuela);
        div_info_perfil.appendChild(h4_tel);
        div_info_perfil.appendChild(h4_mail);
        div_editar.appendChild(boton_edit);

        div2.appendChild(div_img_perfil);
        div2.appendChild(div_info_perfil);
        div2.appendChild(div_editar);

        div1.appendChild(div2);

        div_contenedor.appendChild(div1);

        $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contenedor);
    }

    function listado_docente(nombre) {
        //obtener servicio de listado 
        let div_contenedor = document.createElement("div");
        div_contenedor.className = 'col-12 p-0';
        let h3_titulo = document.createElement("h3");
        h3_titulo.className = 'm-3';
        h3_titulo.innerHTML = 'Plantilla Docente';
        let div1 = document.createElement("div");
        div1.className = 'row col-12 m-0 p-0';
        for (var i = 0; i < perfiles_personal.length; i++) {
            div1.appendChild(agregar_personal_perfil(perfiles_personal[i]));
        }
        div_contenedor.appendChild(h3_titulo);
        div_contenedor.appendChild(div1);
        $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contenedor);
    }

    function agregar_personal_perfil(json) {
        let div2 = document.createElement("div");
        div2.className = "row col-12 m-0 p-0 mt-3 mb-3";
        let div_img = document.createElement("div");
        div_img.className = 'col-sm-12 col-md-4 col-lg-3 p-0 d-flex align-items-center justify-content-center';
        let div_img2 = document.createElement("div");
        div_img2.className = "img_perfil";
        if (json.hasOwnProperty("img")) {
            //Poner imagen del perfil
            div_img2.style = "width: 80px; height: 80px; background-image: url('" + json.img + "');";
        } else {
            //Poner imagen por default
            //<i class="fas fa-user-circle" style="font-size: 7rem;"></i>
            let icon = document.createElement("i");
            icon.className = 'fas fa-user-circle w-100 h-100';
//            icon.style = 'font-size: 7rem;';
            div_img2.className = 'd-flex justify-content-center align-items-center';
            div_img2.style = "width: 80px; height: 80px;";
            div_img2.appendChild(icon);
        }

        div_img.appendChild(div_img2);
        div2.appendChild(div_img);
        /*Iteracion*/
        let div_info_perfil = document.createElement("div");
        div_info_perfil.className = 'col-sm-12 col-md-8 col-lg-9 p-0 pt-3 mb-2';

        let h5_nombre = document.createElement("h5");
        h5_nombre.className = 'p-0';
        let strong_nombre = document.createElement("strong");
        strong_nombre.innerHTML = 'Profesor: ';
        h5_nombre.appendChild(strong_nombre);
        h5_nombre.innerHTML += json.nombre + " " + json.apellido_paterno + " " + json.apellido_materno;

        let h5_telefono = document.createElement("h5");
        h5_telefono.className = 'p-0';
        let strong_telefono = document.createElement("strong");
        strong_telefono.innerHTML = 'Télefono: ';
        h5_telefono.appendChild(strong_telefono);
        h5_telefono.innerHTML += json.telefono !== null ? json.telefono : "";

        let h5_mail = document.createElement("h5");
        h5_mail.className = 'p-0';
        let strong_mail = document.createElement("strong");
        strong_mail.innerHTML = 'Correo electrónico: ';
        h5_mail.appendChild(strong_mail);
        h5_mail.innerHTML += json.correo !== null ? json.correo : "";

        let h5_grupo = document.createElement("h5");
        h5_grupo.className = 'p-0';
        let strong_grupo = document.createElement("strong");
        strong_grupo.innerHTML = 'Grupos: ';
        h5_grupo.appendChild(strong_grupo);
        $.each(json.grupos, function (i) {
            h5_grupo.innerHTML += " " + json.grupos[i];
        });


        div_info_perfil.appendChild(h5_nombre);
        div_info_perfil.appendChild(h5_telefono);
        div_info_perfil.appendChild(h5_mail);
        div_info_perfil.appendChild(h5_grupo);
        div2.appendChild(div_info_perfil);
        /**/
        return div2;
    }

    function registro_plantilla_laboral(nombre) {
        let div_contendor = document.createElement("div");
        div_contendor.className = 'row col-12 m-0 p-2 pt-3';
        let h3_title = document.createElement("h3");
        h3_title.innerHTML = 'Registrar nuevo personal';
        div_contendor.appendChild(h3_title);

        let div_form = document.createElement("div");
        div_form.className = 'col-12 p-0';
        let form_registro = document.createElement("form");
        form_registro.id = 'form_registro_personal';

        form_registro.appendChild(form_info("Nombre", "docente_nombre", "text"));

        let div = document.createElement("div");
        div.className = 'form-group row m-0 p-2';
        let label = document.createElement("label");
        label.for = "docente_apellido_paterno";
        label.className = 'col-sm-2 col-form-label';
        label.innerHTML = "Apellidos:";
        div.appendChild(label);
        let div2 = document.createElement("div");
        div2.className = 'col-sm-5';
        let input = document.createElement("input");
        input.type = "text";
        input.className = 'form-control-plaintext input';
        input.id = "docente_apellido_paterno";
        input.placeholder = "Apellido Paterno";
        div2.appendChild(input);

        let div3 = document.createElement("div");
        div3.className = 'col-sm-5';
        let input1 = document.createElement("input");
        input1.type = "text";
        input1.className = 'form-control-plaintext  input';
        input1.id = "docente_apellido_materno";
        input1.placeholder = "Apellido Materno";
        div3.appendChild(input1);

        div.appendChild(div2);
        div.appendChild(div3);

        form_registro.appendChild(div);

        form_registro.appendChild(form_info("Fecha Nacimiento", "docente_fecha_nacimiento", "date"));
        form_registro.appendChild(form_info("Teléfono", "docente_telefono", "number"));
        form_registro.appendChild(form_info("Correo Electrónico", "docente_correo", "text"));
        form_registro.appendChild(form_info("Cedula Profesional", "docente_cedula", "text"));
        form_registro.appendChild(form_info("CURP", "docente_curp", "text"));
        form_registro.appendChild(form_info("RFC", "docente_rfc", "text"));

        let div_btn = document.createElement("div");
        div_btn.className = 'form-group row m-0 p-2';
        let div_btn2 = document.createElement("div");
        div_btn2.className = 'col-sm-12';
        let btn = document.createElement('button');
        btn.type = 'submit';
        btn.className = 'btn btn-danger mb-2';
        btn.innerHTML = 'Registrar';
        div_btn2.appendChild(btn);
        div_btn.appendChild(div_btn2);

        form_registro.appendChild(div_btn);
        div_form.appendChild(form_registro);
        let hr = document.createElement("hr");
        div_form.appendChild(hr);

        div_contendor.appendChild(div_form);

        let div_doc = document.createElement("div");
        div_doc.className = 'col-12 p-0';
        let h3_doc = document.createElement("h3");
        h3_doc.innerHTML = 'Subir documento (Excel) de plantilla laboral';
        div_doc.appendChild(h3_doc);
        let form_doc = document.createElement("form");
        form_doc.id = 'form_registro_personal_file';
        form_doc.appendChild(form_info("Seleccionar Archivo", "file_plantilla_laboral", "file"));

        let div_btn_doc = document.createElement("div");
        div_btn_doc.className = 'form-group row m-0 p-2';
        let div_btn2_doc = document.createElement("div");
        div_btn2_doc.className = 'col-sm-12';
        let btn_doc = document.createElement('button');
        btn_doc.type = 'submit';
        btn_doc.className = 'btn btn-danger mb-2';
        btn_doc.innerHTML = 'Subir Archivo';
        div_btn2_doc.appendChild(btn_doc);
        div_btn_doc.appendChild(div_btn2_doc);

        form_doc.appendChild(div_btn_doc);

        div_doc.appendChild(form_doc);

        div_contendor.appendChild(div_doc);

        $("#modulo_section_" + nombre.replace(/\s/g, "")).append(div_contendor);

        $("#form_registro_personal").submit(function (e) {
            e.preventDefault();
//        let inputs = $("[id^=docente_]");
            let json = buildJSON_Section("form_registro_personal");
            console.log(json);
            let keys = Object.keys(json);
            for (var i = 0; i < keys.length; i++) {
                let key = keys[i].split("docente_");
                key = key[1];
                json[key] = json[keys[i]];
                delete json[keys[i]];
            }
            json.tipo_usuario = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_usuario;
            json.tipo_servicio = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio;
            RequestPOST("/API/escuela360/registro_personal", json).then(function (response) {
                console.log(response);
                Swal.fire({
                    title: 'Aviso',
                    text: response.mensaje
                });
            });
        });



    }
    $("#file_plantilla_laboral").change(function (e) {
        fileReader(e);
    });
    function fileReader(oEvent) {
        console.log("En la funcion fileReader");
        var oFile = oEvent.target.files[0];
        var sFilename = oFile.name;

        var reader = new FileReader();
        var result = {};
        if (sFilename.toString().includes(".csv") || sFilename.toString().includes(".xlsx")) {
            reader.onload = function (e) {
                var data = e.target.result;
                console.log(data);
                data = new Uint8Array(data);
                var workbook = XLSX.read(data, {type: 'array', cellDates: true});
//            console.log(workbook);
                var result = {};
                workbook.SheetNames.forEach(function (sheetName) {
                    var roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName], {raw: true});
                    if (roa.length)
                        result[sheetName] = roa;
                });
                // see the result, caution: it works after reader event is done.
                console.log(result);
                if (validar_info(result)) {
                    let keys_archivo = Object.keys(result);
                    let info_completa = new Array();
                    $.each(keys_archivo, function (i) {
                        let info_hoja = result[keys_archivo[i]];
                        let info_completa_hoja = new Array();
                        $.each(info_hoja, function (j) {
                            let alias = Object.keys(info_hoja[j]);
                            let keys_hoja = transforma_arreglo(Object.keys(info_hoja[j]));
                            let json = {};
                            $.each(alias, function (k) {
                                if (info_hoja[j][alias[k]].toString().includes("(hora ")) {
                                    let fecha = formato_fecha(info_hoja[j][alias[k]].toString());
                                    json[keys_hoja[k]] = fecha;
                                } else {
                                    json[keys_hoja[k]] = info_hoja[j][alias[k]];
                                }
                            });
                            json.alias = alias;
                            json.tipo_usuario = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_usuario;
                            json.tipo_servicio = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).tipo_servicio;
                            json.id360 = JSON.parse(getCookie("username_v3.2_" + DEPENDENCIA)).id_usuario;
                            info_completa_hoja.push(json);
                        });
                        info_completa.push(info_completa_hoja);
                    });
                    RequestPOST("/API/escuela360/registro_personal_array",info_completa).then(function(response){
                        console.log(response);
                    });

                    

                } else {
                    Swal.fire({
                        title: 'Archivo incompleto',
                        text: "EL archivo debe contener la informacion mínima: Nombre, Apellido paterno, Apellido materno y Correo."
                    });
                }
            };
            reader.readAsArrayBuffer(oFile);
        } else {
            Swal.fire({
                title: 'Extención Inválida',
                text: "El archivo debe de ser un csv ó xlsx."
            });
        }
    }

    function formato_fecha(fecha) {
        let hoy = new Date(fecha);
        let dd = hoy.getDate();
        let mm = hoy.getMonth() + 1; //January is 0!
        let yyyy = hoy.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        let fecha_final = yyyy + '-' + mm + '-' + dd;
        return fecha_final;
    }

    function validar_info(info_archivo) {
        let procede = true;
        let keys_archivo = Object.keys(info_archivo);
        $.each(keys_archivo, function (i) {
            let info_hoja = info_archivo[keys_archivo[i]];
            console.log(info_hoja);
            $.each(info_hoja, function (j) {
//                let alias = Object.keys(info_hoja[j]);
                let keys_hoja = transforma_arreglo(Object.keys(info_hoja[j]));
                if (!keys_hoja.includes("nombre")
                        || !keys_hoja.includes("apellidopaterno")
                        || !keys_hoja.includes("apellidomaterno")
                        || !keys_hoja.includes("correo")) {
                    procede = false;
                    return false;
                }
            });
            if (!procede) {
                return false;
            }
        });

        return procede;
    }
    function transforma_arreglo(arreglo) {
        let arreglo_mod = new Array();
        $.each(arreglo, function (i) {
            let val = arreglo[i];

            //Comvertimos el valor a minuscula
            val = val.toString().toLowerCase();
            if (val.toString().includes("correo") || val.toString().includes("mail")) {
                val = "correo";
            }
            //cambiamos las letras con acento por letras sin acento
            val = val.normalize('NFD')
                    .replace(/([aeio])\u0301|(u)[\u0301\u0308]/gi, "$1$2")
                    .normalize();
            //Quitamos los caracteres epeciales
            val = val.replace(/[^\w\s]/gi, '');
            //Quitamos los espcacios
            val = val.replace(/ /gi, '');
            arreglo_mod.push(val);
        });
        return arreglo_mod;
    }

    function form_info(valor, id, tipo) {
        let div = document.createElement("div");
        div.className = 'form-group row m-0 p-2';
        let label = document.createElement("label");
        label.for = id;
        label.className = 'col-sm-2 col-form-label';
        label.innerHTML = valor + ":";
        div.appendChild(label);
        let div2 = document.createElement("div");
        div2.className = 'col-sm-10';
        let input = document.createElement("input");
        input.type = tipo;
        input.className = 'form-control-plaintext input';
        input.id = id;
        input.placeholder = valor;
        div2.appendChild(input);
        div.appendChild(div2);
        return div;
    }

    if ($("#direccion_institucion").length) {
        //initMap2();

        $("#buscar_direccion").on("click", function () {
            var geocoder = new google.maps.Geocoder();
            console.log($("#direccion_institucion").val());
            var address = $("#direccion_institucion").val();
            if (address !== "") {
                geocoder.geocode({'address': address}, geocodeResult);
            }
        });
    }
//    var mapa;
//    function initMap2() {
//        mapa = new google.maps.Map(document.getElementById('map2'), {zoom: 5, center: {lat: 19.503329, lng: -99.185714}/*,mapTypeId:'satellite'*/, styles: [{featureType: 'administrative', elementType: 'geometry', stylers: [{visibility: "off"}, {"weight": 1}]}, {featureType: 'administrative', elementType: 'geometry.fill', stylers: [{visibility: "on"}]}, {featureType: 'administrative', elementType: 'geometry.stroke', stylers: [{visibility: "off"}]}, {featureType: 'administrative', elementType: 'labels', stylers: [{color: '#000000'}, {visibility: "off"}]}, {featureType: 'administrative.country', elementType: 'geometry', stylers: [{color: '#a6a6a6'}, {visibility: "on"}, {"weight": 1.5}]}, {featureType: 'administrative.country', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.country', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.land_parcel', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.land_parcel', elementType: 'labels', stylers: [{visibility: "on"}]}, {featureType: 'administrative.land_parcel', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.locality', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.locality', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.locality', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.neighborhood', elementType: 'geometry', stylers: [{visibility: "on"}]}, {featureType: 'administrative.neighborhood', elementType: 'labels', stylers: [{color: '#696969'}, {visibility: "simplified"}]}, {featureType: 'administrative.neighborhood', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'administrative.province', elementType: 'geometry', stylers: [{visibility: "on"}, {"weight": 1.5}]}, {featureType: 'administrative.province', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'administrative.province', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: "landscape", stylers: [{color: '#D5D8DC'}]}, {featureType: 'landscape', elementType: 'geometry', stylers: [{color: '#D5D8DC'}]}, {featureType: 'landscape', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'landscape', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'landscape.man_made', elementType: 'geometry', stylers: [{color: '#526081'}, {visibility: "off"}]}, {featureType: 'landscape.natural.landcover', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.landcover', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'landscape.natural.terrain', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'poi', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'road', elementType: 'geometry', stylers: [{visibility: "simplified"}]}, {featureType: 'road', elementType: 'labels', stylers: [{visibility: "simplified"}]}, {featureType: 'road', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'geometry', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'labels', stylers: [{visibility: "off"}]}, {featureType: 'transit', elementType: 'labels.icon', stylers: [{visibility: "off"}]}, {featureType: 'water', elementType: 'geometry', stylers: [{color: '#F2F4F4'}, {visibility: "on"}]}, {featureType: 'water', elementType: 'labels', stylers: [{visibility: "off"}]}]});
//        mapa.setTilt(45);
//    }

    function geocodeResult(results, status) {
        if (status == 'OK') {
            $("#lat").val(results[0].geometry.location.lat());
            $("#lng").val(results[0].geometry.location.lng());
            marker = new google.maps.Marker({
                position: {lat: parseFloat($("#lat").val()), lng: parseFloat($("#lng").val())},
                map: mapa,
                draggable: true,
                animation: google.maps.Animation.DROP
            });
            var latlng = {lat: parseFloat($("#lat").val()), lng: parseFloat($("#lng").val())};
            console.log(latlng);
            mapa.setCenter(latlng);
            mapa.setZoom(18);
            marker.addListener('dragend', function () {
                var lat = marker.getPosition().lat();
                var lng = marker.getPosition().lng();
                $("#lat").val(lat);
                $("#lng").val(lng);
            });
        } else {
            alert("Geocoding no tuvo éxito debido a: " + status);
        }
    }
//    function initMaps() {
//        initMap();
//        initMap2();
//    }


</script>

</body>